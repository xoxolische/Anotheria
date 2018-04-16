package knight;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * This class executes search of Knight tours with BFS.
 * 
 * @author Nikita Pavlov
 *
 */
public class Main {

	public static void main(String[] args) {
		Knight k = new Knight(0, 0);
		Board b = new Board(8);
		List<List<Position>> variants = new LinkedList<>();
		Stack<Position> possibleMoves = new Stack<>();
		int c = 0;
		while (true) {
			// System.out.println("Stack " + possibleMoves);
			List<Position> possiblePositions = new LinkedList<>(k.possibleMoves(b));

			for (Position p : possiblePositions) {
				if (!k.getMovesHistory().contains(p) && !p.equals(k.getLast())) {
					possibleMoves.push(p);
					// System.out.println("added " + p.toString());
				}
			}
			// System.out.println("Stack after" + possibleMoves);
			if (!possibleMoves.empty()) {
				Position to = possibleMoves.pop();
				if (k.canMove(to, b)) {
					k.move(to, b);
					// System.out.println("Moved to " + to.toString());
					// b.print(k);
				} else {
					if (k.isCorrect(b)) {
						variants.add(k.getMovesHistory());
					}
					// for(Position p : k.getMovesHistory()) {
					// System.out.println(p.toString());
					// }
					if (!returnToPossiblePosition(k, b, to)) {
						// System.out.println("??");
						break;
					}
				}
			} else {
				if (k.isCorrect(b)) {
					variants.add(k.getMovesHistory());
				}
				break;
			}
			if ((c % 100000) == 0) {
				System.out.println(variants.size());
			}
		}
		printVariants(variants);

	}

	private static void printVariants(List<List<Position>> variants) {
		for (List<Position> pos : variants) {
			for (Position p : pos) {
				System.out.println(p.toString());
			}

			System.out.println("");
		}
	}

	private static boolean returnToPossiblePosition(Knight k, Board b, Position to) {
		while (true) {
			if (k.getMovesHistory().size() != 1) {
				k.stepBack(b);
				// System.out.println("-------"+
				// k.getMovesHistory().get(k.getMovesHistory().size()-1).toString());
				if (k.canMove(to, b) && !to.equals(k.getLast())) {
					return true;
				}
			} else {
				// System.out.println("got start position, do we have any moves?");
				return false;
			}
		}
	}
	// draft version functional method
	// private static void bfs() {
	// Knight k = new Knight(0, 0);
	// Board b = new Board(3);
	// Set<List<Position>> variants = new HashSet<>();
	// // Position p = new Position(7, 7);
	// // b.setVisited(new Position(0, 0), true);
	// // p.print(b);
	//
	// // for(Position p : k.possibleMoves(b)) {
	// // p.print(b);
	// // }
	//
	// Stack<Position> possibleMoves = new Stack<>();
	//
	// possibleMoves.push(k.getPosition());
	//
	// while (!possibleMoves.isEmpty()) {
	// // move knight to next position (last added in stack)
	// System.out.println("Stack at beggining " + possibleMoves);
	// Position current = possibleMoves.pop();
	// k.move(current, b);
	// // k.z(current);
	// // System.out.println("K POS : " + k.getPosition().toString());
	// if (k.ableToMove(b)) {
	// System.out.println("Able to move : ");
	// for (Position p : k.possibleMoves(b)) {
	// if (!k.getMovesHistory().contains(p)) {
	// possibleMoves.push(p);
	// // System.out.println("Possible move added to stack! : " + p.toString());
	// } else {
	// // System.out.println("VIsited : " + p.toString());
	// }
	// }
	// } else {
	// // ??????? whyyyyy
	// k.addToHistory(current);
	// System.out.println("No moves from this position!" + possibleMoves);
	// for (Position pos : k.getMovesHistory()) {
	// System.out.println(pos.toString());
	// }
	// b.print(k);
	// variants.add(k.getMovesHistory());
	// // current = possibleMoves.pop();
	// Position togo = null;
	// if (!possibleMoves.empty()) {
	// togo = possibleMoves.pop();
	// }
	// while (true) {
	// // make step back and check if move to last stack position is able
	// System.out.println("Step back! from -> " + k.getPosition().toString() + " for
	// " + togo);
	// k.stepBack(b);
	// b.print(k);
	//
	// if (k.canMove(togo, b) && !togo.equals(k.getLast())) {
	// k.move(togo, b);
	// break;
	// }
	//
	// if (k.getMovesHistory().size() == 0) {
	// System.out.println("Check the board! Something went wrong!");
	// break;
	// }
	// }
	// possibleMoves.push(togo);
	// }
	//
	// }
	//
	// // print our moves
	// for (List<Position> p : variants) {
	// for (Position pp : p) {
	// System.out.println(pp.toString());
	// }
	// System.out.println("");
	// System.out.println("");
	// }
	// }

}
