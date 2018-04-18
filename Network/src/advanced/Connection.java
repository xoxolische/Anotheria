package advanced;

import java.io.*;
import java.net.Socket;

import advanced.commands.DIRCommand;
import advanced.commands.GETCommand;
import advanced.commands.ICommand;
import advanced.commands.PUTCommand;
import advanced.transfer.DataTransfer;

/**
 * This class separates clients from each other.
 * 
 * @author Nikita Pavlov
 *
 */
public class Connection implements Runnable {

	private Socket clientCommandSocket;
	private Socket clientFileSocket;
	private Server server;
	private ObjectInputStream commandInput;
	private ObjectOutputStream commandOutput;
	private InputStream fileInput;
	private OutputStream fileOutput;

	/**
	 * 
	 * @param clientCommandSocket
	 * @param clientFileSocket
	 * @param server
	 */
	public Connection(Socket clientCommandSocket, Socket clientFileSocket, Server server) {
		this.clientCommandSocket = clientCommandSocket;
		this.clientFileSocket = clientFileSocket;
		this.server = server;
		try {
			commandOutput = new ObjectOutputStream(clientCommandSocket.getOutputStream());
			commandInput = new ObjectInputStream(clientCommandSocket.getInputStream());
			fileInput = clientFileSocket.getInputStream();
			fileOutput = clientFileSocket.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		ICommand[] commands = { new DIRCommand(server), new GETCommand(server), new PUTCommand(server) };
		while (true) {
			try {
				String cmd = (String) commandInput.readObject();
				if (cmd != null) {
					for (ICommand c : commands) {
						if (c.isCommand(cmd)) {
							DataTransfer dt = new DataTransfer(c.execute(fileInput), commandOutput, fileOutput);
							dt.send();
						}
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}

}
