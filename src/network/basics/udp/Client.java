package network.basics.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

	public static void main(String[] args) {
		try {
			int port = 2453;

			InetAddress address = InetAddress.getByName("127.0.0.1");
			DatagramSocket socket = new DatagramSocket();

			byte[] m = "hello from a client".getBytes();

			DatagramPacket request = new DatagramPacket(m, m.length, address, port);
			socket.send(request);
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
