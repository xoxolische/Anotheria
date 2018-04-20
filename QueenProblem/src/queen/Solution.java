package queen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents QueenProblem Solution
 * 
 * @author Nikita Pavlov
 *
 */
public class Solution {
	private static final char QUEEN = 'X';
	private static final char EMPTY = 'o';
	private List<String[]> result;

	public Solution() {
		result = new ArrayList<>();
	}

	/**
	 * 
	 * @param n
	 *            Size of board
	 * @return List with result Strings
	 */
	public List<String[]> solveNQueens(int n) {
		if (n < 1) {
			return result;
		}

		String[] rows = new String[n];

		int row = 0;

		solutions(row, n, rows, result);

		return result;
	}

	/*
	 * Recursively fills rows with different queen position in columns and check if
	 * it does not conflicts with other
	 */
	private void solutions(int start, int total, String[] rows, List<String[]> result) {
		if (start >= total) {
			result.add(rows.clone());
			return;
		}

		for (int col = 0; col < total; col++) {
			if (!isValid(col, start, rows)) {
				continue;
			}

			char[] chars = new char[total];
			Arrays.fill(chars, EMPTY);
			chars[col] = QUEEN;

			rows[start] = String.copyValueOf(chars);
			solutions(start + 1, total, rows, result);

		}

	}

	// check if current col conflicts with previous
	private boolean isValid(int col, int stRow, String[] rows) {
		for (int i = 0; i < stRow; i++) {
			int qCol = rows[i].indexOf(QUEEN);

			if (qCol == col) {
				return false;
			}

			int rowDistance = Math.abs(stRow - i);
			int colDistance = Math.abs(col - qCol);

			if (rowDistance == colDistance) {
				return false;
			}

		}

		return true;

	}

	public List<String[]> getResult() {
		return result;
	}

	public String[] getPreparedResult() {
		String[] preparedResult = new String[this.result.size()];
		String preparedString;
		int c = 0;
		for (String[] a : this.result) {
			preparedString = "";
			for (String s : a) {
				preparedString += s + "\r\n";
			}
			preparedResult[c] = preparedString;
			c++;
		}
		return preparedResult;
	}

	public String resultToString() {
		String resultString = "";
		for (String[] a : this.result) {
			for (String s : a) {
				resultString += s + "\r\n";
			}
			resultString += "\r\n";
		}

		return resultString;
	}

}