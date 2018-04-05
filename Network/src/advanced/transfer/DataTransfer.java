package advanced.transfer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;

public class DataTransfer implements Serializable {

	private static final long serialVersionUID = 5487422122369484574L;
	private Object data;
	private ObjectOutputStream commandOut;
	private OutputStream fileOut;

	public DataTransfer(Object data, ObjectOutputStream commandOut, OutputStream fileOut) {
		this.data = data;
		this.commandOut = commandOut;
		this.fileOut = fileOut;
	}

	public void send() {
		if (this.data instanceof List) {
			try {
				commandOut.writeObject(data);
				System.out.println("DIR sent!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (this.data instanceof File) {
			//System.out.println("This is file!");
			File f = (File) this.data;
			if (f != null) {
				try {
					byte[] fData = Files.readAllBytes(f.toPath());
					fileOut.write(fData, 0, fData.length);
					fileOut.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
