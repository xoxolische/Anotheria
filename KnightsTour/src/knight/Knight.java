package knight;

import java.util.LinkedList;
import java.util.List;

public class Knight {

	private Position p;
	private List<Position> positions;
	private Position previous;

	public Knight(int x, int y) {
		this.p = new Position(x, y);
		this.positions = new LinkedList<>();
		//this.positions.add(this.p);
	}
	
	public List<Position> getMovesHistory(){
		List<Position> copy = new LinkedList<>();
		for(Position p : positions) {
			copy.add(new Position(p.getX(), p.getY()));
		}
		return copy;
	}
	
	public Position getPosition() {
		return p;
	}
	
	public void move(Position to, Board b) {
		//if(!p.equals(to)) {
			b.setVisited(this.p, true);
			addToHistory(p);
			p = to;
		//}
	}
	public void addToHistory(Position p) {
		positions.add(p);
	}
	public boolean ableToMove(Board b) {
		if(this.possibleMoves(b).size() != 0) {
			return true;
		}else {
			return false;
		}
	}

	public List<Position> possibleMoves(Board b) {
		List<Position> possibleMoves = new LinkedList<>();
		Position newPos;
		/* & - knight
		 * X - position to go
		 * 1 - visited
		 * 
		 *   0 1 2 3 4 5 6 7 x
		 * 0 0 0 0 0 0 0 0 0
		 * 1 0 0 0 0 0 0 0 0 
		 * 2 0 0 0 0 0 0 0 0
		 * 3 0 0 0 0 & 0 0 0
		 * 4 0 0 0 0 0 0 X 0
		 * 5 0 0 0 0 0 0 0 0
		 * 6 0 0 0 0 0 0 0 0
		 * 7 0 0 0 0 0 0 0 0 
		 * y
		 */ 
		newPos = new Position(this.p.getX() + 2, this.p.getY() + 1);
		if(onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}
		/*
		 * 0 0 0 0 0 0 0 0 
		 * 0 0 0 0 0 0 0 0 
		 * 0 0 0 0 0 0 X 0
		 * 0 0 0 0 & 0 0 0
		 * 0 0 0 0 0 0 1 0
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0 
		 */
		newPos = new Position(this.p.getX() + 2, this.p.getY() - 1);
		if(onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}
		/*
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 X 0 0 
		 * 0 0 0 0 0 0 1 0
		 * 0 0 0 0 & 0 0 0
		 * 0 0 0 0 0 0 1 0
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0 
		 */
		newPos = new Position(this.p.getX() + 1, this.p.getY() - 2);
		if(onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}
		/*
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 X 0 1 0 0 
		 * 0 0 0 0 0 0 1 0
		 * 0 0 0 0 & 0 0 0
		 * 0 0 0 0 0 0 1 0
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0 
		 */
		newPos = new Position(this.p.getX() - 1, this.p.getY() - 2);
		if(onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}
		
		/*
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 1 0 1 0 0 
		 * 0 0 X 0 0 0 1 0
		 * 0 0 0 0 & 0 0 0
		 * 0 0 0 0 0 0 1 0
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0 
		 */
		newPos = new Position(this.p.getX() - 2, this.p.getY() - 1);
		if(onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}
		
		/*
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 1 0 1 0 0 
		 * 0 0 1 0 0 0 1 0
		 * 0 0 0 0 & 0 0 0
		 * 0 0 X 0 0 0 1 0
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0 
		 */
		newPos = new Position(this.p.getX() - 2, this.p.getY() + 1);
		if(onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}
		
		/*
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 1 0 1 0 0 
		 * 0 0 1 0 0 0 1 0
		 * 0 0 0 0 & 0 0 0
		 * 0 0 1 0 0 0 1 0
		 * 0 0 0 X 0 0 0 0
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0 
		 */
		newPos = new Position(this.p.getX() - 1, this.p.getY() + 2);
		//System.out.println("???" + newPos.toString());
		if(onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}
		
		/*
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 1 0 1 0 0 
		 * 0 0 1 0 0 0 1 0
		 * 0 0 0 0 & 0 0 0
		 * 0 0 1 0 0 0 1 0
		 * 0 0 0 1 0 X 0 0
		 * 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0 
		 */
		newPos = new Position(this.p.getX() + 1, this.p.getY() + 2);
		if(onBoard(b, newPos) && !b.isVisited(newPos)) {
			possibleMoves.add(newPos);
		}
		
		return possibleMoves;
	}
	
	private boolean onBoard(Board b, Position p) {
		int index = p.getAbsolute(b.getSize());
//		System.out.println(this.p.toString());
//		System.out.println(index);
		if(p.getX() <= (b.getSize()-1) && p.getX() >= 0 && p.getY()<= b.getSize()-1 
				&& p.getY()>=0 && index <= b.getBoard().length && index >= 0) {
			//System.out.println("good : " + p.toString());
			return true;
		}else {
			//System.out.println("bad" + p.toString());
		}
		return false;
	}

	public boolean canMove(Position current, Board b) {
		if(onBoard(b, current) && !b.isVisited(current) && possibleMoves(b).contains(current)) {
			return true;
		}
		return false;
	}

	public Position getLast() {
		return previous;
	}
	public void stepBack(Board b) {
		Position last = positions.get(positions.size()-1);
		previous = last;
		b.setVisited(last, false);
		positions.remove(positions.size()-1);
		p = positions.get(positions.size()-1);
	}
	
	
	//add restriction if knight can reach from last position x0 y0 position. (closed tour)
	public boolean isCorrect(Board b) {
		int[] array = b.getBoard();
		int size = b.getSize();
		if(array[0] == 1 && array[size-1] == 1 && array[((size * size) - 1)] == 1 && array[((size * size) - 1) - (size - 1)] == 1) {			
			return true;
		}
		return false;
	}

}
