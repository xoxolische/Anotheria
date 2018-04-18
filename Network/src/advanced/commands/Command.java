package advanced.commands;

import java.io.InputStream;

/**
 * Abstract class to extend with concrete command.
 * 
 * @author Nikita Pavlov
 *
 */
abstract class Command implements ICommand {

	public abstract boolean isCommand(String cmd);

	public abstract Object execute(InputStream fileInput);

}
