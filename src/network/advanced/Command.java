package network.advanced;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public enum Command {
	DIR, PUT, GET;

	private static List<CommandMapper> commandList = new LinkedList<>();
	static {
		commandList.add(new CommandMapper(Pattern.compile("[D][I][R]"), Command.DIR));
		commandList.add(new CommandMapper(Pattern.compile("[P][U][T][\\s].{1,255}"), Command.PUT));
		commandList.add(new CommandMapper(Pattern.compile("[G][E][T][\\s].{1,255}"), Command.GET));
	}
	

	public static List<String> getCommands() {
		List<String> l = new ArrayList<>(Command.values().length);
		for (Command c : Command.values()) {
			l.add(c.name());
		}
		return l;
	}

	public static Command isCommand(String w) {
		if (w != null) {
			for (CommandMapper c : commandList) {
				if(c.matches(w)) {
					return c.getC();
				}
			}
		}
		return null;
	}
}
