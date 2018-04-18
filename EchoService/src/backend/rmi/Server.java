package backend.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Class - server.
 * <p>
 * Implements Echo interface.
 * 
 * @author Nikita Pavlov
 *
 */
public class Server implements Echo {

	@Override
	public String echo(String s) throws RemoteException {
		System.out.println("Request: " + s);
		return new StringBuffer(s).reverse().toString();
	}

	public static void main(String args[]) {
		try {
			Server obj = new Server();
			Echo stub = (Echo) UnicastRemoteObject.exportObject(obj, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("Echo service", stub);
			System.out.println("Server ready");
		} catch (Exception e) {
			System.out.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
