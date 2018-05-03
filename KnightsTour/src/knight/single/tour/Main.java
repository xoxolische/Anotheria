package knight.single.tour;

import knight.Board;
import knight.Knight;

public class Main {

	public static void main(String[] args) {
		Knight k = new Knight(5, 3);
		Board b = new Board(8);
		SingleSolutionFinder s = new SingleSolutionFinder(k, b);
		s.search();
	}

}
