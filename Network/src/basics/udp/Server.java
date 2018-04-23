package basics.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
	private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
	private DatagramSocket ds;

	public Server(int port) {
		try {
			ds = new DatagramSocket(port);
		} catch (SocketException e) {
			LOGGER.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server s = new Server(2456);
		s.startListen();
	}

	public void startListen() {
		while (true) {
			try {
				System.out.println("UDP Waiting for a packets...");
				byte[] buf = new byte[256];
				DatagramPacket request = new DatagramPacket(buf, buf.length);
				ds.receive(request);
				String m = new String(request.getData(), StandardCharsets.UTF_8);
				System.out.println(m);
			} catch (IOException e) {
				LOGGER.warn(e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
