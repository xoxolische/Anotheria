package backend.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiInterface extends Remote{

	String echo(String s) throws RemoteException;
}
