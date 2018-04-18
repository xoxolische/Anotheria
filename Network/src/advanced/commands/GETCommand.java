package advanced.commands;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import advanced.Server;

/**
 * Class for GET command
 * 
 * @author Nikita Pavlov
 *
 */
public class GETCommand extends Command {

	private Pattern p;
	private Server server;
	private String memo;

	/**
	 * 
	 * @param server entity
	 */
	public GETCommand(Server server) {
		p = Pattern.compile("[G][E][T][\\s].{1,255}");
		this.server = server;
	}

	@Override
	public boolean isCommand(String cmd) {
		Matcher m = p.matcher(cmd);
		if (m.matches()) {
			fileName(cmd);
			return true;
		}
		return false;
	}

	public Object execute(InputStream fileInput) {
		return server.get(memo);
	}

	private void fileName(String cmd) {
		memo = cmd.split(" ")[1];
	}
}
