package network.basics.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Server {
	public static void main(String[] args) {
		int port = 2453;
		try {
			DatagramSocket ds = new DatagramSocket(port);

			System.out.println("UDP Waiting for a packets...");		
			
			
			byte[] buf = new byte[256];
			DatagramPacket request = new DatagramPacket(buf, buf.length);
			ds.receive(request);
			System.out.println(new String(request.getData(), StandardCharsets.UTF_8));
			
			ds.close();
//
//			InetAddress clientAddress = request.getAddress();
//			int clientPort = request.getPort();
//			 
//			String data = "Message from server";
//			buf = data.getBytes();
//			 
//			DatagramPacket response = new DatagramPacket(buf, buf.length, clientAddress, clientPort);
//			ds.send(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
