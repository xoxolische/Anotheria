package basics.tcp;

import java.net.*;
import java.io.*;

public class Client {
	public static void main(String[] args) {
		int serverPort = 2453;
		String address = "127.0.0.1";

		try {
			InetAddress ipAddress = InetAddress.getByName(address);
			Socket socket = new Socket(ipAddress, serverPort);

			InputStream sin = socket.getInputStream();
			OutputStream sout = socket.getOutputStream();
			DataInputStream in = new DataInputStream(sin);
			DataOutputStream out = new DataOutputStream(sout);

			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			System.out.println("Input message : ");
			System.out.println();

			while (true) {
				line = bf.readLine();
				System.out.println("Sending...");
				out.writeUTF(line);
				out.flush();
				line = in.readUTF();
				System.out.println("Server : " + line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
