package queen;

public class Main {
	public static void main(String[] args) {
		Solution q = new Solution();
		q.solveNQueens(8);
		System.out.println(q.resultToString());
		System.out.println(q.getResult().size());
	}
}
