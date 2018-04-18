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

	/**
	 * Check possibility of the knight to the given position.
	 * 
	 * @param to
	 *            position to go
	 * @param b
	 *            board
	 * @return true if position in range and not visited.
	 */
	public boolean canMove(Position to, Board b) {
		if (onBoard(b, to) && !b.isVisited(to) && possibleMoves(b).contains(to)) {
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

}
