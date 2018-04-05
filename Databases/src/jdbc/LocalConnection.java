package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class LocalConnection {

	private static final String HOST = "localhost";
	private static final String DBNAME = "test";
	private static final String USER = "postgres";
	private static final String PASSWORD = "admin";
	private static final int PORT = 5432;

	private LocalConnection() {

	}

	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://" + HOST + ":" + PORT + "/" + DBNAME,
					USER, PASSWORD);
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getHost() {
		return HOST;
	}

	public static String getDbname() {
		return DBNAME;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	public static int getPort() {
		return PORT;
	}

}
