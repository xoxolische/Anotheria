package knight;

/**
 * This class represents Position for Knight.
 * 
 * @author Nikita Pavlov
 *
 */
public class Position {
	/**
	 * Array of letters for chess view
	 */
	private static final char[] X = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	/**
	 * Array of numbers for chess view
	 */
	private static final int[] Y = new int[X.length];
	static {
		for (int i = 0; i < X.length; i++) {
			Y[i] = i + 1;
		}
	}

	public Position from;
	private int x;
	private int y;

	/**
	 * Constructor with X and Y parameters.
	 * 
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getAbsolute(int boardSize) {
		return y * boardSize + x;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Position [x=" + (x) + ", y=" + (y) + "]";
	}

	/**
	 * Prints the board where # - is our Knight, 0 - unvisited and 1 - is visited
	 * square
	 * 
	 * @param b
	 */
	public void print(Board b) {
		for (int i = 0; i < b.getSize(); i++) {
			for (int j = 0; j < b.getSize(); j++) {
				if (i == y && j == x) {
					System.out.print("# ");
				} else {
					System.out.print(b.getBoard()[i * b.getSize() + j] + " ");
				}
			}
			System.out.println("");
		}

		System.out.println("");
		System.out.println("");
	}

	/**
	 * 
	 * @return chess notation String of position
	 */
	public String toChessNotation() {
		return "" + X[this.x] + Y[this.y];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
