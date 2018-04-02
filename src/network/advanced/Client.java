package network.advanced;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFileChooser;

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
			System.out.println("Input command [DIR, PUT <filename>, GET <filename>]");
			System.out.println();

			while (true) {
				line = bf.readLine();
				Command c = Command.isCommand(line);
				if (c != null) {
					switch (c) {
					case DIR:
						out.writeUTF(line);
						out.flush();
						line = in.readUTF();
						System.out.println("Server : " + line);
						break;
					case PUT:
						out.writeUTF(line);
						out.flush();
						line = in.readUTF();
						System.out.println("Server : " + line);
						break;
					case GET:
						out.writeUTF(line);
						out.flush();
						line = in.readUTF();
						System.out.println("Server : " + line);
						break;
					}

				} else {
					System.out.println("Unknown command!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
