package network.advanced;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

//TODO maybe each recieve and send should be in different threads ???
public class FileHelper {
	static ServerSocketChannel ssChannel = null;
	static SocketChannel sChannel = null;

	public static void send() {
		try {
			ssChannel = ServerSocketChannel.open();
			ssChannel.configureBlocking(true);
			ssChannel.socket().bind(new InetSocketAddress(7000));
			SocketChannel sChannel = ssChannel.accept();
			ObjectOutputStream oos = new ObjectOutputStream(sChannel.socket().getOutputStream());
			oos.writeObject("t.txt");
			oos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}		
		System.out.println("Transfer ended");

	}

	public static void recieve() throws IOException, ClassNotFoundException {
		

		sChannel = SocketChannel.open();
		sChannel.configureBlocking(true);

		if (!sChannel.connect(new InetSocketAddress("127.0.0.1", 7000))) {

			ObjectInputStream ois = new ObjectInputStream(sChannel.socket().getInputStream());

			String s = (String) ois.readObject();
			System.out.println("String is: '" + s + "'");
		}

		sChannel.close();
		System.out.println("End Receiver");
	}

}
