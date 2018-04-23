package magic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Nikita Pavlov
 *
 */
public class MagicSquare {

	private int[] square;
	private int center;
	private int size;
	private int magicSum;

	public MagicSquare() {

	}

	/**
	 * 
	 * @param size
	 *            of square
	 */
	public MagicSquare(int size) {
		this.size = size;
		this.square = new int[size * size];
		setMagicSum();
	}

	private void setMagicSum() {
		int m = size * (size * size + 1) / 2;
		this.magicSum = m;
	}

	public void setSquare(int[] square) {
		this.square = square;
	}

	public int getMagicSum() {
		return magicSum;
	}

	/**
	 * 
	 * @param i
	 *            number of row begin with 0
	 * @return int array
	 */
	public int[] getRow(int i) {
		int[] row = new int[size];
		int r = size * i;
		for (int j = 0; j < size; j++) {
			row[j] = square[j + r];
		}
		return row;
	}

	/**
	 * 
	 * @param i
	 *            index of row to be set. Begins with 0
	 * @param v
	 */
	public void setRow(int i, int[] v) {
		int r = size * i;
		for (int j = 0; j < size; j++) {
			square[j + r] = v[j];
		}
	}

	/**
	 * 
	 * @param i
	 *            index of column begins with 0.
	 * @return
	 */
	public int[] getCol(int i) {
		int[] column = new int[size];
		int r = 0;
		for (int j = 0; j < size; j++) {
			column[j] = square[r + i];
			r += size;
		}
		return column;
	}

	/**
	 * 
	 * @param i
	 *            index of column to be set. Begins with 0
	 * @param v
	 */
	public void setCol(int i, int[] v) {
		int c = i;
		int r = 0;
		for (int j = 0; j < size; j++) {
			square[r + c] = v[j];
			r += size;
		}
	}

	/**
	 * Prints our magic square to console.
	 */
	public void print() {
		int c = 0;
		for (int i = 0; i < square.length; i++) {
			if (c == size) {
				c = 0;
				System.out.println();
			}
			System.out.print(square[i] + " ");
			c++;
		}
		System.out.println();
		System.out.println();
	}

	/**
	 * 
	 * @return List of magic square values
	 */
	public Collection<?> getCollection() {
		List<Integer> set = new LinkedList<>();
		int[] copy = Arrays.copyOf(square, square.length);
		Arrays.sort(copy);
		for (int i : copy) {
			if (i != 0) {
				set.add(i);
			}
		}
		return set;
	}

	public int[] getSquare() {
		return square;
	}

	public void setCells(Map<Integer, Integer> m) {
		for (Integer i : m.keySet()) {
			square[i] = m.get(i);
		}
	}

	public void setCell(int i, int v) {
		square[i] = v;
	}

	public boolean cellFilled(int i) {
		if (square[i] != 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return String representation of square
	 */
	public String squareToString() {
		String s = "";
		for (int i = 0; i < size; i++) {
			s += String.valueOf(Arrays.toString(this.getRow(i)) + "\r\n");
		}
		s += "\r\n";
		return s;
	}

	public String squareToDb() {
		String s = "";
		for (int i = 0; i < square.length; i++) {
			s += String.valueOf(square[i] + " ");
		}
		return s;
	}

	public String squareToPageView() {
		String s = "";
		for (int i = 0; i < size; i++) {
			s += String.valueOf(Arrays.toString(this.getRow(i)) + "<br>");
		}
		s += "<br>";
		return s;
	}

	/**
	 * Saves squares to the file.
	 * 
	 * @param path
	 *            - absolute path to file
	 * @param ms
	 *            Set of magic squares
	 * @throws IOException
	 */
	public static void writeInFile(String path, Collection<MagicSquare> ms) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(path));
		for (MagicSquare m : ms) {
			writer.write(m.squareToString());
		}

		writer.close();
	}

	public void setCenter(int center) {
		this.center = center;
	}

	public int getCenter() {
		return center;
	}

	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @return true if square has only uniq values
	 */
	public boolean noConflictsInMatrix() {
		int vCounter = 0;

		Set<Integer> s = new HashSet<>();
		for (int v : square) {
			if (v != 0) {
				s.add(v);
				vCounter++;
			}
		}
		if (vCounter != s.size()) {
			s = null;
			return false;
		}
		// WeakReference<Set<Integer>> ss = new WeakReference<>(s);
		s = null;

		return true;
	}

	public boolean isMagic() {
		for (int i = 0; i < size; i++) {
			if (sum(this.getCol(i)) != magicSum) {
				return false;
			}
			if (sum(this.getRow(i)) != magicSum) {
				return false;
			}
			if (sum(this.getDiagonal1()) != magicSum) {
				return false;
			}
			if (sum(this.getDiagonal2()) != magicSum) {
				return false;
			}

		}
		return true;
	}

	/**
	 * Get diagonal
	 * <p>
	 * o o X
	 * <p>
	 * o X o
	 * <p>
	 * X o o
	 * 
	 * @param diagonal
	 */
	public int[] getDiagonal1() {
		int[] d = new int[size];
		for (int i = 0; i < size; i++) {
			d[i] = square[i * (size + 1)];
		}
		return d;
	}

	/**
	 * Get diagonal
	 * <p>
	 * X o o
	 * <p>
	 * o X o
	 * <p>
	 * o o X
	 * 
	 * @param diagonal
	 */
	public int[] getDiagonal2() {
		int[] d = new int[size];
		for (int i = 0; i < size; i++) {
			d[i] = square[(size - 1) + i * (size - 1)];
		}
		return d;
	}

	/**
	 * 
	 * @param a
	 *            - array to sum up
	 * @return sum of array values
	 */
	public int sum(int[] a) {
		int sum = 0;
		for (int i : a) {
			sum += i;
		}
		// System.out.println(Arrays.toString(a) + " => " + sum);
		return sum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(square);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MagicSquare other = (MagicSquare) obj;
		if (!Arrays.equals(square, other.square))
			return false;
		return true;
	}
}
