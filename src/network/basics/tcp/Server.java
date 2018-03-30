package network.basics.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		int port = 2453;
		try {
			ServerSocket ss = new ServerSocket(port);
			while (true) {

				System.out.println("Waiting for a connection...");
				Socket socket = ss.accept();

				System.out.println("Connection established.");

				InputStream sin = socket.getInputStream();
				OutputStream sout = socket.getOutputStream();
				DataInputStream in = new DataInputStream(sin);
				DataOutputStream out = new DataOutputStream(sout);

				String line = null;
				while (true) {
					try {
						line = in.readUTF();
						System.out.println("Client : " + line);
						System.out.println("Returned to client in upper case...");
						out.writeUTF(line.toUpperCase());
						out.flush();
					} catch (Exception e) {
						System.out.println("Client disconnected!");
						socket.close();
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
