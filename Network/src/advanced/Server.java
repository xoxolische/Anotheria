package advanced;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {

	private ServerSocket commandTransfer;
	private ServerSocket fileTransfer;

	public Server(int commandPort, int filePort) {
		try {
			commandTransfer = new ServerSocket(commandPort);
			fileTransfer = new ServerSocket(filePort);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> dir() {
		File folder = new File(".");
		File[] listOfFiles = folder.listFiles();
		List<String> listToUser = new LinkedList<>();
		for (File f : listOfFiles) {
			if (f.isFile()) {
				listToUser.add(f.getName());
				// System.out.println(f.getName());
			}
		}
		//System.out.println("DIR called!");
		return listToUser;
	}

	public Object put(String fileName, InputStream fileInput) {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			int count;
			byte[] buffer = new byte[8192];
			while ((count = fileInput.read(buffer)) > 0) {
				fos.write(buffer, 0, count);
			}
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public File get(String fileName) {
		File f = new File(fileName);
		//System.out.println(f.getName() + " " + f.getAbsolutePath());
		return f;
	}

	public void start() {
		while (true) {
			try {
				Socket clientCommandSocket = commandTransfer.accept();
				// System.out.println("Got commandTransfer!");
				Socket clientFileSocket = fileTransfer.accept();
				// System.out.println("Got fileTransfer!");
				new Thread(new Connection(clientCommandSocket, clientFileSocket, this)).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Server s = new Server(1234, 1235);
		s.start();
	}

}
