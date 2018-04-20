package knight;

import java.util.List;
import java.util.Stack;

public class Main {

	/**
	 * Executes DFS algorithm
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Knight k = new Knight(0, 0);
		Board b = new Board(3);
		// List<List<Position>> variants = new LinkedList<>();
		Stack<Position> possibleMoves = new Stack<>();
		for (Position p : k.possibleMoves(b)) {
			possibleMoves.push(p);
		}
		while (!possibleMoves.empty()) {
			Position to = possibleMoves.pop();
			if (k.canMove(to, b)) {
				k.move(to, b);
				// System.out.println("move -> " + to.toString());
				List<Position> movesFromCurrentPosition = k.possibleMoves(b);
				if (movesFromCurrentPosition.size() != 0) {
					for (Position p : movesFromCurrentPosition) {
						possibleMoves.push(p);
						// System.out.println("add -> " + p.toString());
					}
				} else {
					if (k.isClosedTour(b)) {
						for (Position p : k.getMovesHistory()) {
							System.out.println("C " + p.toString());
						}
						System.out.println("C " + k.memo.toString());
					} else {
						for (Position p : k.getMovesHistory()) {
							// System.out.println("O " + p.toString());
						}
						// System.out.println("C " + k.memo.toString());
					}

					if (possibleMoves.empty()) {
						System.out.println("The end.");

						break;
					} else {
						Position p = possibleMoves.pop();
						// System.out.println("Go back. Until able to go " + p.toString());
						k.goBackUntillReacheble(p, b);
						possibleMoves.push(p);
						// System.out.println(possibleMoves.size());
					}
				}
			} else {
				System.out.println("Cant move to " + to.toString());
				// b.print(k);
			}
			if (possibleMoves.isEmpty()) {
				System.out.println("No moves!");
			}
		}
	}

}
