package basics.tcp;

import java.net.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;

/**
 * Client class for TCP socket
 * 
 * @author Nikita Pavlov
 *
 */
public class Client {
	private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
	private Socket socket;
	private DataOutputStream out;

	public static void main(String[] args) {
		Client c = new Client("127.0.0.1", 2453);
		c.sendMessage(args[0]);
		c.stop();
	}

	/**
	 * 
	 * @param address
	 *            of our server
	 * @param port
	 *            of our server
	 */
	public Client(String address, int port) {
		try {
			InetAddress ipAddress = InetAddress.getByName(address);
			socket = new Socket(ipAddress, port);
			OutputStream sout = socket.getOutputStream();
			out = new DataOutputStream(sout);
		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
		}

	}

	/**
	 * Close our client socket.
	 */
	public void stop() {
		try {
			out.close();
			socket.close();
		} catch (IOException e) {
			LOGGER.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Send message to the server
	 * 
	 * @param text
	 */
	public void sendMessage(String text) {
		try {
			out.writeUTF(text);
			out.flush();
		} catch (IOException e) {
			LOGGER.warn(e.getMessage());
			e.printStackTrace();
		}
	}

}
