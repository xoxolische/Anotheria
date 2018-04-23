package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains common data for connection to local postgreSQL database.
 * 
 * @author Nikita Pavlov
 *
 */
public final class LocalConnection {
	private static final Logger LOGGER = LoggerFactory.getLogger(LocalConnection.class);
	/**
	 * Hostname of our database
	 */
	private static final String HOST = "localhost";
	/**
	 * Name of our database
	 */
	private static final String DBNAME = "test";
	/**
	 * Username for our database
	 */
	private static final String USER = "postgres";
	/**
	 * Password for our database
	 */
	private static final String PASSWORD = "admin";

	/**
	 * Port of our database
	 */
	private static final int PORT = 5432;

	private LocalConnection() {

	}

	/**
	 * 
	 * @return java.sql.Connection initialized with parameters for local connection
	 *         to postgreSQL database
	 */
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://" + HOST + ":" + PORT + "/" + DBNAME,
					USER, PASSWORD);
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			LOGGER.warn(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.warn(e.getMessage());
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
