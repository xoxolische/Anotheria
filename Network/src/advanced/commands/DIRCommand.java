package advanced.commands;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import advanced.Server;

/**
 * Class for DIR command
 * 
 * @author Nikita Pavlov
 *
 */
public class DIRCommand extends Command {

	private Pattern p;
	private Server server;

	/**
	 * 
	 * @param server
	 *            entity
	 */
	public DIRCommand(Server server) {
		p = Pattern.compile("[D][I][R]");
		this.server = server;
	}

	@Override
	public boolean isCommand(String cmd) {
		Matcher m = p.matcher(cmd);
		if (m.matches())
			return true;
		return false;
	}

	/**
	 * Call DIR method on server side
	 */
	public Object execute(InputStream fileInput) {
		return server.dir();
	}

}
