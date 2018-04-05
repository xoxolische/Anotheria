package advanced.commands;

import java.io.InputStream;

abstract class Command implements ICommand {
	
	public abstract boolean isCommand(String cmd);

	public abstract Object execute(InputStream fileInput);
	

}
