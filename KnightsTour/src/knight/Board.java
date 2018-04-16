package knight;

public class Board {

	private int[] board;
	private int size;

	public Board(int size) {
		this.size = size;
		this.board = new int[size * size];
	}

	public int[] getBoard() {
		return board;
	}

	public void setBoard(int[] board) {
		this.board = board;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isVisited(Position p) {
		if(board[p.getX() + p.getY() * this.size] == 1) {
			return true;
		}
		return false;
	}
	
	public void setVisited(Position p, boolean b) {
		board[p.getY() * this.size + p.getX()] = b? 1 : 0;
	}
	
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
