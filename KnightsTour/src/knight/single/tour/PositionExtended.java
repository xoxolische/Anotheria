package knight.single.tour;

import knight.Board;
import knight.Knight;
import knight.Position;

/**
 * Class represents extended version of Position to provide possibility to get
 * number of moves from given X and Y. Also, this class implements Comparable,
 * so we can use it to sort our Positions later.
 * 
 * @author Nikita Pavlov
 *
 */
public final class PositionExtended extends Position implements Comparable<PositionExtended> {
	private int possibleMoves;

	/**
	 * 
	 * @param p
	 *            Position with X and Y
	 * @param k
	 *            Knight
	 * @param b
	 *            Board
	 */
	public PositionExtended(Position p, Knight k, Board b) {
		super(p.getX(), p.getY());
		this.possibleMoves = calculateMoves(k, b);
	}

	private int calculateMoves(Knight k, Board b) {
		Position next = new Position(this.getX(), this.getY());
		k.move(next, b);
		int moves = k.possibleMoves(b).size();
		Position previous = k.getMovesHistory().get(k.getMovesHistory().size() - 2);
		Position last = k.getMovesHistory().get(k.getMovesHistory().size() - 1);
		b.setVisited(last, false);
		k.getPositions().remove(last);
		k.setP(previous);
		return moves;
	}

	@Override
	public int compareTo(PositionExtended o) {
		return (this.possibleMoves > o.getPossibleMoves()) ? 1 : (this.possibleMoves < o.getPossibleMoves()) ? -1 : 0;
	}

	/**
	 * 
	 * @return number of moves from current Position
	 */
	public int getPossibleMoves() {
		return possibleMoves;
	}

	public void setPossibleMoves(int possibleMoves) {
		this.possibleMoves = possibleMoves;
	}

	@Override
	public String toString() {
		return "PositionExtended [possibleMoves=" + possibleMoves + ", getX()=" + getX() + ", getY()=" + getY() + "]";
	}

}
