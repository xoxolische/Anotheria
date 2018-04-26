package knight;

public class Main {

	public static void main(String[] args) {
		Knight k = new Knight(0, 0);
		Board b = new Board(6);
		SolutionFinder s = new SolutionFinder(k, b);
		s.search();
	}
}
