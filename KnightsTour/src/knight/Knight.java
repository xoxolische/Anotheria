package knight;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class represents the Knight.
 * 
 * @author Nikita Pavlov
 *
 */
public class Knight {

	/**
	 * Current Knight`s X and Y position on board.
	 */
	private Position p;
	/**
	 * Moves history of Knight
	 */
	private List<Position> positions;
	/**
	 * Possible moves cache to improve performance and avoid recalculations.
	 */
	private static Map<Position, List<Position>> possibleMovesCache = new HashMap<>();
	/**
	 * List with possible moves that we calculate or retrieve from the cache
	 */
	private static List<Position> pos = new LinkedList<>();

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
	 * Retrieves from memory possible moves from current position, if in memory not
	 * present List of Positions we generate and add it.
	 * 
	 * @param b
	 *            board
	 * @return List of possible positions to move.
	 */
	public List<Position> possibleMoves(Board b) {
		if (possibleMovesCache.get(this.p) == null) {
			possibleMovesCache.put(this.p, movesFromCurrentLocation(b));
		}
		pos.clear();
		for (Position possible : possibleMovesCache.get(this.p)) {
			if (!b.isVisited(possible)) {
				possible.from = this.p;
				pos.add(possible);
			}
		}

		return pos;
	}

	private List<Position> movesFromCurrentLocation(Board b) {
		List<Position> possibleMoves = new LinkedList<>();
		Position newPos;

		newPos = new Position(this.p.getX() + 2, this.p.getY() + 1);
		if (onBoard(b, newPos)) {
			possibleMoves.add(newPos);
		}
		newPos = new Position(this.p.getX() + 2, this.p.getY() - 1);
		if (onBoard(b, newPos)) {
			possibleMoves.add(newPos);
		}
		newPos = new Position(this.p.getX() + 1, this.p.getY() - 2);
		if (onBoard(b, newPos)) {
			possibleMoves.add(newPos);
		}
		newPos = new Position(this.p.getX() - 1, this.p.getY() - 2);
		if (onBoard(b, newPos)) {
			possibleMoves.add(newPos);
		}
		newPos = new Position(this.p.getX() - 2, this.p.getY() - 1);
		if (onBoard(b, newPos)) {
			possibleMoves.add(newPos);
		}
		newPos = new Position(this.p.getX() - 2, this.p.getY() + 1);
		if (onBoard(b, newPos)) {
			possibleMoves.add(newPos);
		}
		newPos = new Position(this.p.getX() - 1, this.p.getY() + 2);
		if (onBoard(b, newPos)) {
			possibleMoves.add(newPos);
		}
		newPos = new Position(this.p.getX() + 1, this.p.getY() + 2);
		if (onBoard(b, newPos)) {
			possibleMoves.add(newPos);
		}
		return possibleMoves;
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
	 * Moves our Knight backward 1 step.
	 * 
	 * @param b
	 *            Board
	 */
	public void moveBack(Board b) {
		Position previous = this.positions.get(positions.size() - 2);
		Position last = this.positions.get(positions.size() - 1);
		b.setVisited(last, false);
		this.p = previous;
		this.positions.remove(this.p);
	}

	private boolean onBoard(Board b, Position p) {
		int index = p.getAbsolute(b.getSize());
		if (p.getX() <= (b.getSize() - 1) && p.getX() >= 0 && p.getY() <= b.getSize() - 1 && p.getY() >= 0
				&& index <= b.getBoard().length && index >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * Check if tour is closed (visited all squares, no check for return in start
	 * position)
	 * 
	 * @param b
	 *            board
	 * @return true if closed, otherwise false
	 */
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

	/**
	 * 
	 * @return Knight`s moves history inline
	 */
	public String getMovesHistoryString() {
		String s = "";
		for (Position p : this.getMovesHistory()) {
			s += p.toString() + " => ";
		}
		return s;
	}

	public Position getP() {
		return p;
	}

	public void setP(Position p) {
		this.p = p;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

}
