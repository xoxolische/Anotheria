package jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class QueenDB {

	private static final String INSERT = "INSERT INTO queens (state) VALUES (?)";
	private PreparedStatement prep;

	public QueenDB() throws SQLException {
		prep = LocalConnection.getConnection().prepareStatement(INSERT);
	}

	public void insert(String solution) throws SQLException {
		if (prep != null) {
			prep.setString(1, solution);
			prep.executeUpdate();
			prep.getConnection().close();
		}
	}
	
	public void insert(String[] solutions) throws SQLException {
		if (prep != null) {
			for(String s : solutions) {				
				prep.setString(1, s);
				prep.executeUpdate();
			}
			prep.getConnection().close();
		}
	}
	
	public void insert(List<String[]> solutions) throws SQLException {
		if (prep != null) {
			for(String[] sa : solutions) {				
				for(String s : sa) {				
					prep.setString(1, s);
					prep.executeUpdate();
				}
			}
			prep.getConnection().close();
		}
	}

}
