package advanced.remake.commands;

import java.io.InputStream;

public interface ICommand {
	
	boolean isCommand(String cmd);

	Object execute(InputStream fileInput);
}
