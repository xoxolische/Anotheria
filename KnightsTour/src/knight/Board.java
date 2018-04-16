package knight;

/**
 * Board representation class.
 * 
 * @author Nikita Pavlov
 *
 */
public class Board {

	private int[] board;
	private int size;

	/**
	 * Constructor
	 * 
	 * @param size
	 *            initializes board with given value
	 */
	public Board(int size) {
		this.size = size;
		this.board = new int[size * size];
	}

	/**
	 * 
	 * @return array of int values that is our board
	 */
	public int[] getBoard() {
		return board;
	}

	/**
	 * 
	 * @param board
	 */
	public void setBoard(int[] board) {
		this.board = board;
	}

	/**
	 * 
	 * @return size of our board
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Checks if position visited by knight.
	 * 
	 * @param p
	 *            position
	 * @return true if visited otherwise false
	 */
	public boolean isVisited(Position p) {
		if (board[p.getX() + p.getY() * this.size] == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Sets the cell visited or not visited.
	 * 
	 * @param p
	 *            position to set visited or unvisited
	 * @param b
	 *            true for visited, false for unvisited
	 */
	public void setVisited(Position p, boolean b) {
		board[p.getY() * this.size + p.getX()] = b ? 1 : 0;
	}

	/**
	 * Prints in console the board with the knight
	 * 
	 * @param k
	 *            knight entity
	 */
	public void print(Knight k) {
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				if (i == k.getPosition().getY() && j == k.getPosition().getX()) {
					System.out.print("# ");
				} else {
					System.out.print(this.board[i * this.size + j] + " ");
				}
			}
			System.out.println("");
		}

		System.out.println("");
		System.out.println("");
	}

}
