package basics.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server class for TCP socket
 * 
 * @author Nikita Pavlov
 *
 */
public class Server {
	private ServerSocket socket;

	public static void main(String[] args) {
		Server s = new Server(2453);
		try {
			s.startListen();
		} catch (IOException e) {
			// TODO LOGGER
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param port
	 *            of our server
	 */
	public Server(int port) {
		try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO LOGGER
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param port
	 *            of our TCP server
	 * @throws IOException
	 */
	private void startListen() throws IOException {
		while (true) {
			System.out.println("Waiting for a connection...");
			Socket con = socket.accept();
			System.out.println("Connection established.");
			InputStream sin = con.getInputStream();
			DataInputStream in = new DataInputStream(sin);
			String line = in.readUTF();
			System.out.println("Client : " + line);
			in.close();
			con.close();
		}

	}
}
