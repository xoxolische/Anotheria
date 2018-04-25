package knight;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents the Knight.
 * 
 * @author Nikita Pavlov
 *
 */
public class Knight {

	private Position p;
	private List<Position> positions;
	private Position previous;
	public Position memo;

	/**
	 * Constructor with coordinates. X and Y as parameters start with 0.
	 * <p>
	 * x0, y0 are located in the upper left corner.
	 * 
	 * @param x
	 *            number of cell in row, start with 0
	 * @param y
	 *            number of row, start with 0
	 */
	public Knight(int x, int y) {
		this.p = new Position(x, y);
		this.positions = new LinkedList<>();
		// this.positions.add(this.p);
	}

	/**
	 * 
	 * @return List of Positions of Knight
	 */
	public List<Position> getMovesHistory() {
		List<Position> copy = new LinkedList<>();
		for (Position p : positions) {
			copy.add(new Position(p.getX(), p.getY()));
		}
		return copy;
	}

	public Position getPosition() {
		return p;
	}

	/**
	 * Moves our Knight to specific position
	 * 
	 * @param to
	 *            position go to
	 * @param b
	 *            board
	 */
	public void move(Position to, Board b) {
		// if(!p.equals(to)) {
		b.setVisited(this.p, true);
		addToHistory(p);
		p = to;
		// }
	}

	private void addToHistory(Position p) {
		positions.add(p);
	}

	// private boolean ableToMove(Board b) {
	// if (this.possibleMoves(b).size() != 0) {
	// return true;
	// } else {
	// return false;
	// }
	// }

	/**
	 * 
	 * @param b
	 * @return List of possible positions to move.
	 */
	public List<Position> possibleMoves(Board b) {
		List<Position> possibleMoves = new LinkedList<>();
		Position newPos;
		/*
		 * & - knight X - position to go 1 - visited
		 * 
		 * 0 1 2 3 4 5 6 7 x 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 2 0 0 0 0 0 0 0 0 3 0 0
		 * 0 0 & 0 0 0 4 0 0 0 0 0 0 X 0 5 0 0 0 0 0 0 0 0 6 0 0 0 0 0 0 0 0 7 0 0 0 0 0
		 * 0 0 0 y
		 */
		newPos = new Position(this.p.getX() + 2, this.p.getY() + 1);
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}
		/*
		 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 X 0 0 0 0 0 & 0 0 0 0 0 0 0 0 0 1
		 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 */
		newPos = new Position(this.p.getX() + 2, this.p.getY() - 1);
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}
		/*
		 * 0 0 0 0 0 0 0 0 0 0 0 0 0 X 0 0 0 0 0 0 0 0 1 0 0 0 0 0 & 0 0 0 0 0 0 0 0 0 1
		 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 */
		newPos = new Position(this.p.getX() + 1, this.p.getY() - 2);
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}
		/*
		 * 0 0 0 0 0 0 0 0 0 0 0 X 0 1 0 0 0 0 0 0 0 0 1 0 0 0 0 0 & 0 0 0 0 0 0 0 0 0 1
		 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 */
		newPos = new Position(this.p.getX() - 1, this.p.getY() - 2);
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}

		/*
		 * 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 X 0 0 0 1 0 0 0 0 0 & 0 0 0 0 0 0 0 0 0 1
		 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 */
		newPos = new Position(this.p.getX() - 2, this.p.getY() - 1);
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}

		/*
		 * 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 1 0 0 0 1 0 0 0 0 0 & 0 0 0 0 0 X 0 0 0 1
		 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 */
		newPos = new Position(this.p.getX() - 2, this.p.getY() + 1);
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}

		/*
		 * 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 1 0 0 0 1 0 0 0 0 0 & 0 0 0 0 0 1 0 0 0 1
		 * 0 0 0 0 X 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 */
		newPos = new Position(this.p.getX() - 1, this.p.getY() + 2);
		// System.out.println("???" + newPos.toString());
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}

		/*
		 * 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 1 0 0 0 1 0 0 0 0 0 & 0 0 0 0 0 1 0 0 0 1
		 * 0 0 0 0 1 0 X 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 */
		newPos = new Position(this.p.getX() + 1, this.p.getY() + 2);
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}

		return possibleMoves;
	}

	private boolean onBoard(Board b, Position p) {
		int index = p.getAbsolute(b.getSize());
		// System.out.println(this.p.toString());
		// System.out.println(index);
		if (p.getX() <= (b.getSize() - 1) && p.getX() >= 0 && p.getY() <= b.getSize() - 1 && p.getY() >= 0
				&& index <= b.getBoard().length && index >= 0) {
			// System.out.println("good : " + p.toString());
			return true;
		} else {
			// System.out.println("bad" + p.toString());
		}
		return false;
	}

	public boolean canMove(Position to, Board b) {
		if (onBoard(b, to) && !b.isVisited(to)) {
			return true;
		}
		return false;
	}

	public Position getLast() {
		return previous;
	}

	/**
	 * Make our Knight to go to previous position.
	 * 
	 * @param b
	 */
	public void stepBack(Board b) {
		Position last = positions.get(positions.size() - 1);
		previous = last;
		b.setVisited(last, false);
		positions.remove(positions.size() - 1);
		p = positions.get(positions.size() - 1);
	}

	/**
	 * Check if our board is correct. That is Knight has no moves and corners are
	 * visited and our next step will return us to 0,0 point.
	 * 
	 * @param b
	 * @return
	 */
	public boolean isCorrect(Board b) {
		int[] array = b.getBoard();
		int size = b.getSize();
		if (array[0] == 1 && array[size - 1] == 1 && array[((size * size) - 1)] == 1
				&& array[((size * size) - 1) - (size - 1)] == 1) {
			return true;
		}
		return false;
	}

	public boolean isClosedTour(Board b) {
		int[] a = b.getBoard();
//		int a4 = b.getSize() * b.getSize() - 1;
//		if (a[0] == 1 && a[b.getSize() - 1] == 1 && a[a4] == 1 && a[a4 - b.getSize() + 1] == 1 && nextMoveIsStart(b)) {
//			// b.print(this);
//			return true;
//		}
		b.setVisited(this.getPosition(), true);
		for(int i : a) {
			if(i == 0) {
				b.setVisited(this.getPosition(), false);
				return false;
			}
		}
		b.setVisited(this.getPosition(), true);
		return true;
	}

	private boolean nextMoveIsStart(Board b) {
		b.getBoard()[0] = 0;
		// b.print(this);
		if (this.p != new Position(0, 0) && possibleMoves(b).contains(new Position(0, 0))) {
			// System.out.println(this.p != new Position(0, 0));
			// b.print(this);
			// for(Position p : getMovesHistory()) {
			// //System.out.println("H - " + p.toString());
			// }
			b.getBoard()[0] = 1;
			// positions.add(this.p);
			this.memo = this.p;
			return true;
		}
		b.getBoard()[0] = 1;
		return false;
	}

	public void goBackUntillReacheble(Position next, Board b) {
		List<Position> toRemove = new LinkedList<>();
		Position preLast = this.getMovesHistory().get(this.getMovesHistory().size() - 2);
		this.move(preLast, b);
		b.setVisited(this.getMovesHistory().get(this.getMovesHistory().size() - 1), false);
		toRemove.add(this.getMovesHistory().get(this.getMovesHistory().size() - 1));
		// b.print(this);
		// remove last and go previous position
		for (int i = this.getMovesHistory().size() - 2; i >= 0; i--) {

			if (this.canMove(next, b) && possibleMoves(b).contains(next)) {
				removeFromHistory(toRemove);
				b.setVisited(this.p, true);
				// b.print(this);
				break;
			} else {
				// b.print(this);
				if (i - 1 >= 0) {
					this.move(getMovesHistory().get(i - 1), b);
					b.setVisited(getMovesHistory().get(i), false);
					toRemove.add(getMovesHistory().get(i));
				}else {
					break;
				}
			}
		}
	}

	private void removeFromHistory(List<Position> toRemove) {
		this.positions.removeAll(toRemove);
	}

}
