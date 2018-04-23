package basics.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UDP client socket class
 * 
 * @author Nikita Pavlov
 *
 */
public class Client {
	private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
	private DatagramSocket socket;
	private InetAddress address;
	private int port;

	public Client(String address, int port) {
		try {
			this.address = InetAddress.getByName(address);
			socket = new DatagramSocket();
			this.port = port;
		} catch (UnknownHostException | SocketException e) {
			LOGGER.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Client c = new Client("127.0.0.1", 2456);
		c.sendMessage(args[0]);
	}

	public void sendMessage(String text) {
		try {
			byte[] b = text.getBytes();
			DatagramPacket request = new DatagramPacket(b, b.length, address, port);
			socket.send(request);
		} catch (IOException e) {
			LOGGER.warn(e.getMessage());
			e.printStackTrace();
		}
	}
}
