package network.advanced;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileTransfer implements Runnable {

	public static final int PORT = 9696;
	ServerSocket ss;
	Socket socket;
	DataInputStream in;
	DataOutputStream out;

	public FileTransfer(Socket socket, DataInputStream in, DataOutputStream out) {
//		this.socket = socket;
//		this.in = in;
//		this.out = out;
	}
	
	public FileTransfer() {
		
	}

	public void recieve() throws IOException {

		System.out.println("W8ing for file socket...");
		socket = this.ss.accept();
		System.out.println("got file socket...");
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		byte[] bytes = new byte[1024];

		in.read(bytes);
		System.out.println(bytes);

		FileOutputStream fos = new FileOutputStream("t.txt");
		fos.write(bytes);
		System.out.println("finished recieve");
	}

	public void send() throws UnknownHostException, IOException {

	}

	@Override
	public void run() {
		Socket socket = null;
		DataOutputStream out = null;
		DataInputStream in = null;
		String host = "127.0.0.1";

		try {
			socket = new Socket(host, PORT);
			out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			File file = new File("t.txt");
			// InputStream is = new FileInputStream(file);
			// Get the size of the file
			long length = file.length();
			if (length > Integer.MAX_VALUE) {
				System.out.println("File is too large.");
			}
			byte[] bytes = new byte[(int) length];

			// out.write(bytes);
			System.out.println(bytes);

			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
