package knight;

import java.util.List;
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
		fillStack(k.possibleMoves(b));
		while (!possibleMoves.empty()) {
			Position to = possibleMoves.pop();
			if (k.canMove(to, b)) {
				k.move(to, b);
				List<Position> movesFromCurrentPosition = k.possibleMoves(b);
				if (movesFromCurrentPosition.size() != 0) {
					fillStack(movesFromCurrentPosition);
				} else {
					if (k.isClosedTour(b)) {
						printClosedTour();
					}
					if (possibleMoves.empty()) {
						System.out.println("The end.");
						break;
					} else {
						Position p = possibleMoves.pop();
						k.goBackUntillReacheble(p, b);
						possibleMoves.push(p);
					}
				}
			}
		}
	}

	private void printClosedTour() {
		for (Position p : k.getMovesHistory()) {
			System.out.print(p.toChessNotation() + " -> ");
		}
		System.out.println(k.memo.toChessNotation());
		System.out.println("");
	}
}
