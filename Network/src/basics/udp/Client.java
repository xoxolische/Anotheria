package basics.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Client {

	public static void main(String[] args) {
		try {
			int port = 2456;

			InetAddress address = InetAddress.getByName("127.0.0.1");
			DatagramSocket socket = new DatagramSocket();

			byte[] m = "Client message".getBytes();

			DatagramPacket request = new DatagramPacket(m, m.length, address, port);
			socket.send(request);

			byte[] buf = new byte[256];
			DatagramPacket responce = new DatagramPacket(buf, buf.length);
			socket.receive(responce);

			String mess = new String(responce.getData(), StandardCharsets.UTF_8);
			System.out.println(mess);

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
