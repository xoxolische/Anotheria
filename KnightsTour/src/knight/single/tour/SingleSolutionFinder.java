package knight.single.tour;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import knight.Board;
import knight.Knight;
import knight.Position;
import knight.Printer;

/**
 * This class created for searching exactly one knight`s tour.
 * <p>
 * Using Warnsdorf's rule.
 * 
 * @author Nikita Pavlov
 *
 */
public class SingleSolutionFinder {

	private Knight k;
	private Board b;

	public SingleSolutionFinder(Knight k, Board b) {
		this.k = k;
		this.b = b;
	}

	public void search() {
		b.print(k);
		k.move(k.getPosition(), b);
		while (true) {
			List<Position> possibleMoves = new LinkedList<>(k.possibleMoves(b));
			if (possibleMoves.size() == 0) {
				Printer.printHistoryMovesOneLine(k);
				break;
			}

			List<PositionExtended> temp = new LinkedList<>();
			for (Position p : possibleMoves) {
				temp.add(new PositionExtended(p, k, b));
			}
			Collections.sort(temp);
			Position toGo = new Position(temp.get(0).getX(), temp.get(0).getY());
			k.move(toGo, b);
			b.print(k);
		}

	}
}
