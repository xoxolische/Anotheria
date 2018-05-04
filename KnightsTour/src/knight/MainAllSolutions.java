package knight;

public class MainAllSolutions {

	public static void main(String[] args) {
		Knight k = new Knight(0, 0);
		Board b = new Board(6);
		SolutionsFinder s = new SolutionsFinder(k, b);
		s.search();
	}
}
