package basics.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Server {
	public static void main(String[] args) {
		int port = 2456;
		try {
			DatagramSocket ds = new DatagramSocket(port);

			System.out.println("UDP Waiting for a packets...");

			byte[] buf = new byte[256];
			DatagramPacket request = new DatagramPacket(buf, buf.length);
			ds.receive(request);
			String m = new String(request.getData(), StandardCharsets.UTF_8);
			System.out.println(m);

			InetAddress clientIp = request.getAddress();
			int clientPort = request.getPort();

			buf = "Server got your message.".getBytes();

			DatagramPacket response = new DatagramPacket(buf, buf.length, clientIp, clientPort);
			ds.send(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
