package knight;

/**
 * Note: recommended board size for current version is <6. In this algorithm was
 * not used multithreading and time for board with size 6 or more highly increases.
 * 
 * @author Nikita Pavlov
 *
 */
public class MainAllSolutions {

	public static void main(String[] args) {
		Knight k = new Knight(0, 0);
		Board b = new Board(6);
		SolutionsFinder s = new SolutionsFinder(k, b);
		s.search();
	}
}
