package knight;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * This class represents solution finder
 * 
 * @author Nikita Pavlov
 *
 */
public class SolutionFinder {

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
	public SolutionFinder(Knight k, Board b) {
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
		Set<List<Position>> closed = new HashSet<>();
		fillStack(k.possibleMoves(b));
		k.move(k.getPosition(), b);
		while (!possibleMoves.empty()) {
			Position to = possibleMoves.pop();
			k.move(to, b);
			List<Position> movesFromCurrentPosition = k.possibleMoves(b);
			if (movesFromCurrentPosition.size() != 0) {
				fillStack(movesFromCurrentPosition);
			} else {
				if (k.isClosedTour(b)) {
					closed.add(k.getMovesHistory());
					Printer.l(k.getMovesHistoryString());
				} else {
					
				}
				if (possibleMoves.empty()) {
					System.out.println("The end.");
				} else {
					Position p = possibleMoves.pop();
					k.goBackUntillReacheble(p, b);
					possibleMoves.push(p);
					if(!k.possibleMoves(b).contains(p)) {
						break;
					}
				}
			}
		}
		System.out.println(closed.size());
	}
}
