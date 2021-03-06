package knight.single.tour;

import knight.Board;
import knight.Knight;

/**
 * Class - executor for SingleSolutionFinder
 * 
 * @author Nikita Pavlov
 *
 */
public class MainSingleSolution {

	public static void main(String[] args) {
		Knight k = new Knight(0, 0);
		Board b = new Board(8);
		SingleSolutionFinder s = new SingleSolutionFinder(k, b);
		s.search();
	}

}
