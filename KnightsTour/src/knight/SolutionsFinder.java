package knight;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * This class represents solution finder which will find all knight`s tours for
 * given board and knight.
 * 
 * @author Nikita Pavlov
 *
 */
public class SolutionsFinder {

	private Stack<Position> possibleMoves;
	private Board b;
	private Knight k;

	/**
	 * 
	 * @param k
	 *            Knight entity
	 * @param b
	 *            Board entity
	 */
	public SolutionsFinder(Knight k, Board b) {
		this.b = b;
		this.k = k;
		possibleMoves = new Stack<>();
	}

	private void fillStack(List<Position> list) {
		for (Position p : list) {
			possibleMoves.push(p);
		}
	}

	/**
	 * Search for closed tours and print them out to console
	 */
	public void search() {
		Position pop = null;
		Set<List<Position>> closed = new HashSet<>();
		fillStack(k.possibleMoves(b));
		k.move(k.getPosition(), b);
		while (!possibleMoves.empty()) {
			long start = System.nanoTime();
			Position to = possibleMoves.pop();
			k.move(to, b);
			List<Position> movesFromCurrentPosition = k.possibleMoves(b);
			if (movesFromCurrentPosition.size() != 0) {
				fillStack(movesFromCurrentPosition);
			} else {
				if (k.isClosedTour(b)) {
					closed.add(k.getMovesHistory());
					// Printer.l(k.getMovesHistoryString());
					// System.out.println("!");
				} else {

				}
				if (possibleMoves.empty()) {
					System.out.println("The end.");
				} else {
					pop = possibleMoves.pop();
					k.goBackUntillReacheble(pop, b);
					possibleMoves.push(pop);
					if (!k.possibleMoves(b).contains(pop)) {
						break;
					}
				}
			}
			long end = System.nanoTime();
			long duration = end - start;
			System.out.printf("Time sec : %f\n", (double) duration / 1000000000.0);
		}
		System.out.println(closed.size());
	}
}
