package queen;

public class Main {
	public static void main(String[] args) {
		Solution q = new Solution();
		q.solveNQueens(8);
		Printer.printResult(q.getResult());
		//System.out.println(q.getResult().size());
	}
}
