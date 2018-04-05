package advanced.remake.commands;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import advanced.remake.Server;

public class DIRCommand extends Command {

	private Pattern p;
	private Server server;

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

	public Object execute(InputStream fileInput) {
		return server.dir();
	}

}
