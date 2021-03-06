package basics.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Server class for TCP socket
 * 
 * @author Nikita Pavlov
 *
 */
public class Server {
	private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
	private ServerSocket socket;

	public static void main(String[] args) {
		Server s = new Server(2453);
		try {
			s.startListen();
		} catch (IOException e) {
			LOGGER.warn(e.getMessage());
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
			LOGGER.warn(e.getMessage());
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
			Scanner in = new Scanner(sin);
			String line = in.nextLine();
			System.out.println("Client : " + line);
			in.close();
			con.close();
		}

	}
}
