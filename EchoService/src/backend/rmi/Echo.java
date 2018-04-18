package backend.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for remote invocation.
 * 
 * @author Nikita Pavlov
 *
 */
public interface Echo extends Remote {
	/**
	 * 
	 * @param s String message to operate with
	 * @return text message response 
	 * @throws RemoteException
	 */
	String echo(String s) throws RemoteException;
}
