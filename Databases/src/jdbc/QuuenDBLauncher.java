package jdbc;

import java.sql.SQLException;

import queen.Solution;

public class QuuenDBLauncher {

	public static void main(String[] args) {
		Solution s = new Solution();
		s.solveNQueens(8);
		
		try {
			QueenDB db = new QueenDB();
			db.insert(s.getPreparedResult());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}