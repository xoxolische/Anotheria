package advanced.remake.commands;

import java.io.File;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import advanced.remake.Server;

public class PUTCommand extends Command {

	private Pattern p;
	private Server server;
	private String file;

	public PUTCommand(Server server) {
		p = Pattern.compile("[P][U][T][\\s].{1,255}");
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
