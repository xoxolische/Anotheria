package advanced.commands;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import advanced.Server;

/**
 * Class for PUT command
 * 
 * @author Nikita Pavlov
 *
 */
public class PUTCommand extends Command {

	private Pattern p = Pattern.compile("[P][U][T][\\s].{1,255}");
	private Server server;
	private String file;

	/**
	 * 
	 * @param server
	 *            entity
	 */
	public PUTCommand(Server server) {
		this.server = server;
	}

	@Override
	public boolean isCommand(String cmd) {
		Matcher m = p.matcher(cmd);
		if (m.matches()) {
			file = cmd.split(" ")[1];
			return true;
		}
		return false;
	}

	public Object execute(InputStream fileInput) {
		return server.put(file, fileInput);
	}

}
