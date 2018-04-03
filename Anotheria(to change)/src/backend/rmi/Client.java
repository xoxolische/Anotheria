package backend.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
	private static RmiInterface rmi;

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		rmi = (RmiInterface) Naming.lookup("//localhost/EchoService");
		String response = rmi.echo("sasas");
		System.out.println(response);

	}
}
