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
		b.setVisited(this.p, true);
		this.positions.add(to);
		this.p = to;// new Position(to.getX(), to.getY());
	}

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
		newPos.from = this.p;
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}
		/*
		 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 X 0 0 0 0 0 & 0 0 0 0 0 0 0 0 0 1
		 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 */
		newPos = new Position(this.p.getX() + 2, this.p.getY() - 1);
		newPos.from = this.p;
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}
		/*
		 * 0 0 0 0 0 0 0 0 0 0 0 0 0 X 0 0 0 0 0 0 0 0 1 0 0 0 0 0 & 0 0 0 0 0 0 0 0 0 1
		 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 */
		newPos = new Position(this.p.getX() + 1, this.p.getY() - 2);
		newPos.from = this.p;
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}
		/*
		 * 0 0 0 0 0 0 0 0 0 0 0 X 0 1 0 0 0 0 0 0 0 0 1 0 0 0 0 0 & 0 0 0 0 0 0 0 0 0 1
		 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 */
		newPos = new Position(this.p.getX() - 1, this.p.getY() - 2);
		newPos.from = this.p;
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}

		/*
		 * 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 X 0 0 0 1 0 0 0 0 0 & 0 0 0 0 0 0 0 0 0 1
		 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 */
		newPos = new Position(this.p.getX() - 2, this.p.getY() - 1);
		newPos.from = this.p;
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}

		/*
		 * 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 1 0 0 0 1 0 0 0 0 0 & 0 0 0 0 0 X 0 0 0 1
		 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 */
		newPos = new Position(this.p.getX() - 2, this.p.getY() + 1);
		newPos.from = this.p;
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}

		/*
		 * 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 1 0 0 0 1 0 0 0 0 0 & 0 0 0 0 0 1 0 0 0 1
		 * 0 0 0 0 X 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 */
		newPos = new Position(this.p.getX() - 1, this.p.getY() + 2);
		newPos.from = this.p;
		// System.out.println("???" + newPos.toString());
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}

		/*
		 * 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 1 0 0 0 1 0 0 0 0 0 & 0 0 0 0 0 1 0 0 0 1
		 * 0 0 0 0 1 0 X 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		 */
		newPos = new Position(this.p.getX() + 1, this.p.getY() + 2);
		newPos.from = this.p;
		if (onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}

		return possibleMoves;
	}

	private boolean onBoard(Board b, Position p) {
		int index = p.getAbsolute(b.getSize());
		if (p.getX() <= (b.getSize() - 1) && p.getX() >= 0 && p.getY() <= b.getSize() - 1 && p.getY() >= 0
				&& index <= b.getBoard().length && index >= 0) {
			return true;
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

	public boolean isClosedTour(Board b) {
		b.setVisited(this.getPosition(), true);
		int[] a = b.getBoard();
		for (int i : a) {
			if (i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return knight to the position he can go to the specified position.
	 * 
	 * @param next
	 *            position to go
	 * @param b
	 *            board
	 */
	public void goBackUntillReacheble(Position next, Board b) {
		while (true) {
			Position last = positions.get(positions.size() - 1);
			if (positions.size() < 2) {
				break;
			}
			Position prelast = positions.get(positions.size() - 2);
			this.p = prelast;
			b.setVisited(last, false);
			positions.remove(positions.size() - 1);
			if (this.p == next.from && this.possibleMoves(b).contains(next)) {
				break;
			}
		}
	}

	// private void removeFromHistory(List<Position> toRemove) {
	// this.positions.removeAll(toRemove);
	// }

	public String getMovesHistoryString() {
		String s = "";
		for (Position p : this.getMovesHistory()) {
			s += p.toString() + " => ";
		}
		return s;
	}

}
