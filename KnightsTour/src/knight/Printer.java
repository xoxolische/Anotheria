package knight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Printer {

	private Printer() {
	}

	public static void printClosedTour(Knight k) {
		for (Position p : k.getMovesHistory()) {
			System.out.print(p.toString() + " -> ");
		}
		System.out.println("");
	}

	public static void printHistoryMoves(Knight k) {
		for (Position p : k.getMovesHistory()) {
			System.out.println(p.toString());
		}
	}

	public static void l(String s) {
		try (FileWriter fw = new FileWriter("z://log.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(s);
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
	}

	public static void printHistoryMovesOneLine(Knight k) {
		//System.out.println("");
		for (Position p : k.getMovesHistory()) {
			System.out.print(p.toString() + " -> ");
		}
	}

}
