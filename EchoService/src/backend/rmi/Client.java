package backend.rmi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	private Client() {
	}

	public static void main(String[] args) {

		String host = (args.length < 1) ? null : args[0];
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			Echo stub = (Echo) registry.lookup("Echo service");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				String response = stub.echo(br.readLine());
				System.out.println("Server: " + response);
			}
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
