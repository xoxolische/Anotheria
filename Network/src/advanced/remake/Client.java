package advanced.remake;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import advanced.remake.commands.DIRCommand;
import advanced.remake.commands.GETCommand;
import advanced.remake.commands.ICommand;
import advanced.remake.commands.PUTCommand;
import advanced.remake.transfer.DataTransfer;

public class Client {

	private Socket commandSocket;
	private Socket fileSocket;
	private ObjectInputStream commandInput;
	private ObjectOutputStream commandOutput;
	private InputStream fileInput;
	private OutputStream fileOutput;

	public Client(String address, int commandPort, int filePort) {
		try {
			commandSocket = new Socket(InetAddress.getByName(address), commandPort);
			fileSocket = new Socket(InetAddress.getByName(address), filePort);

			commandInput = new ObjectInputStream(commandSocket.getInputStream());
			commandOutput = new ObjectOutputStream(commandSocket.getOutputStream());

			fileInput = fileSocket.getInputStream();
			fileOutput = fileSocket.getOutputStream();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cmd(String cmd) {
		try {
			commandOutput.writeObject(cmd);
			commandOutput.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object recieve() {
		try {
			return commandInput.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void recieveFile(String fileName) {
		try {
			FileOutputStream fos = new FileOutputStream("Z:\\" + fileName);
			int count;
			byte[] buffer = new byte[8192];
			count = fileInput.read(buffer);
			fos.write(buffer, 0, count);
			// System.out.println(count);

			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendFile(String fileName) {
		File f = new File("Z:\\" + fileName);
		if (f != null) {
			try {
				byte[] fData = Files.readAllBytes(f.toPath());
				fileOutput.write(fData, 0, fData.length);
				fileOutput.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ICommand[] commands = { new DIRCommand(null), new GETCommand(null), new PUTCommand(null) };
		Client c = new Client("127.0.0.1", 1234, 1235);
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				String s = bf.readLine();
				if (s != null) {
					for (ICommand cc : commands) {
						if (cc.isCommand(s)) {
							c.cmd(s);
							if (cc instanceof DIRCommand) {
								List<String> l = (List<String>) c.recieve();
								for (String f : l) {
									System.out.println(f);
								}
							}
							if (cc instanceof GETCommand) {
								c.recieveFile(s.split(" ")[1]);
								System.out.println("File saved!");
							}
							if (cc instanceof PUTCommand) {
								c.sendFile("text.txt");
								System.out.println("File sent!");
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
