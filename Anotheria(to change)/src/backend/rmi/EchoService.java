package backend.rmi;

import java.rmi.RemoteException;

public class EchoService implements RmiInterface{

	protected EchoService() throws RemoteException {
		super();
	}

	@Override
	public String echo(String s) {
		return new StringBuilder(s).reverse().toString();
	}

}
