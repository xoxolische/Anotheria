package advanced.transfer;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class helps to exchange commands and files between server and client
 * 
 * @author Nikita Pavlov
 *
 */
public class DataTransfer implements Serializable {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataTransfer.class);
	private static final long serialVersionUID = 5487422122369484574L;
	private Object data;
	private ObjectOutputStream commandOut;
	private OutputStream fileOut;

	/**
	 * 
	 * @param data
	 *            to transfer
	 * @param commandOut
	 *            stream for commands
	 * @param fileOut
	 *            stream for files
	 */
	public DataTransfer(Object data, ObjectOutputStream commandOut, OutputStream fileOut) {
		this.data = data;
		this.commandOut = commandOut;
		this.fileOut = fileOut;
	}

	/**
	 * This method sends response to the client
	 */
	public void send() {
		if (this.data instanceof List) {
			try {
				commandOut.writeObject(data);
				System.out.println("DIR sent!");
			} catch (IOException e) {
				LOGGER.warn(e.getMessage());
				e.printStackTrace();
			}
		}
		if (this.data instanceof File) {
			// System.out.println("This is file!");
			File f = (File) this.data;
			if (f != null) {
				try {
					byte[] fData = Files.readAllBytes(f.toPath());
					fileOut.write(fData, 0, fData.length);
					fileOut.flush();
				} catch (IOException e) {
					e.printStackTrace();
					LOGGER.warn(e.getMessage());
				}
			}
		}
	}
}
