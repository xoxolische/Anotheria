package knight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Printer {

	private Printer() {
	}

	/**
	 * Prints closed tour to console of Knight
	 * 
	 * @param k
	 */
	public static void printClosedTour(Knight k) {
		for (Position p : k.getMovesHistory()) {
			System.out.print(p.toString() + " -> ");
		}
		System.out.println("");
	}

	/**
	 * Saves moves history String to the file
	 * 
	 * @param s
	 *            moves history as String
	 */
	public static void l(String s) {
		try (FileWriter fw = new FileWriter("z://log.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(s);
		} catch (IOException e) {
		}
	}

	/**
	 * Prints Knight moves history to one line in console
	 * 
	 * @param k
	 */
	public static void printHistoryMovesOneLine(Knight k) {
		for (Position p : k.getMovesHistory()) {
			System.out.print(p.toString() + " -> ");
		}
	}

}
