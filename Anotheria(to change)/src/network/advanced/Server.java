package network.advanced;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	private static final int PORT = 2453;
	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	ServerSocket t;

	public Server(Socket socket, DataInputStream in, DataOutputStream out, ServerSocket t) {
		this.t = t;
		this.socket = socket;
		this.in = in;
		this.out = out;
	}

	public static void main(String[] args) {
		try {
			ServerSocket commandSocket = new ServerSocket(PORT);
			ServerSocket transfer = new ServerSocket(FileTransfer.PORT);
			while (true) {
				Socket socket = commandSocket.accept();
				InputStream sin = socket.getInputStream();
				OutputStream sout = socket.getOutputStream();
				DataInputStream in = new DataInputStream(sin);
				DataOutputStream out = new DataOutputStream(sout);
				new Thread(new Server(socket, in, out, transfer)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Client connected!");
		String line = null;
		while (true) {
			try {
				line = in.readUTF();
				System.out.println("Client : " + line);

				Command c = Command.isCommand(line);
				if (c != null) {
					switch (c) {
					case DIR:
						out.writeUTF("All files NA");
						out.flush();
						break;
					case PUT:
						out.writeUTF("FILE STORED");
						out.flush();
						break;
					case GET:
						
						out.writeUTF("FILE RETURNED NA");
						out.flush();
						break;
					}

				} else {
					System.out.println("Unknown command!");
					out.writeUTF("Unknown command!");
					out.flush();
				}

			} catch (Exception e) {
				System.out.println("Client disconnected!");
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			}
		}
	}
}
