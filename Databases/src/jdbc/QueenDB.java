package jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class helps to insert data in database.
 * 
 * @author Nikita Pavlov
 *
 */
public class QueenDB {

	/**
	 * INSERT SQL QUERY
	 */
	private static final String INSERT = "INSERT INTO queens (state) VALUES (?)";
	private PreparedStatement prep;

	/**
	 * Initializes PreparedStatement
	 * 
	 * @throws SQLException
	 */
	public QueenDB() throws SQLException {
		prep = LocalConnection.getConnection().prepareStatement(INSERT);
	}

	/**
	 * Inserts single solution to database.
	 * 
	 * @param solution
	 * @throws SQLException
	 */
	public void insert(String solution) throws SQLException {
		if (prep != null) {
			prep.setString(1, solution);
			prep.executeUpdate();
			prep.getConnection().close();
		}
	}

	/**
	 * Inserts array of solutions to database.
	 * 
	 * @param solutions
	 * @throws SQLException
	 */
	public void insert(String[] solutions) throws SQLException {
		if (prep != null) {
			for (String s : solutions) {
				prep.setString(1, s);
				prep.executeUpdate();
			}
			prep.getConnection().close();
		}
	}
}
