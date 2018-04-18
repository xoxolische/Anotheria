package advanced.commands;

import java.io.InputStream;

/**
 * Interface for common interraction with commands.
 * 
 * @author Nikita Pavlov
 *
 */
public interface ICommand {

	/**
	 * Check if it is the concrete command
	 * 
	 * @param cmd
	 *            String command
	 * @return true if command detected, otherwise false
	 */
	boolean isCommand(String cmd);

	/**
	 * Execute the command
	 * 
	 * @param fileInput
	 * @return Object to interact
	 */
	Object execute(InputStream fileInput);
}
