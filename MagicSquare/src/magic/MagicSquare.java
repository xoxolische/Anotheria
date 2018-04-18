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
import java.util.stream.IntStream;

public class MagicSquare {

	private int[] square;
	private int center;
	private int size;
	private int magicSum;
	// private Set<Integer> container = new HashSet<>();

	public MagicSquare() {

	}

	public MagicSquare(int size) {
		this.size = size;
		this.square = new int[size * size];
		setMagicSum();
	}

	public MagicSquare newInstance(int center, int[] square) {
		MagicSquare newMagic = new MagicSquare(size);
		newMagic.square = square;
		newMagic.setCenter(center);
		// newMagic.container = new HashSet<>(this.container);
		return newMagic;
	}

	public MagicSquare newInstance(int[] square) {
		MagicSquare newMagic = new MagicSquare(size);
		newMagic.setSquare(square);
		// newMagic.container = new HashSet<>(this.container);
		return newMagic;
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

	public void setMainDiagonal(int[] diagonal) {
		if (diagonal.length == 5) {
			setMainDiagonalVector5(diagonal);
		}
		if (diagonal.length == 4) {
			setMainDiagonalVector4(diagonal);
		}
	}

	private void setMainDiagonalVector5(int[] diagonal) {
		if (diagonal != null && diagonal.length == 5) {
			for (int i = 0; i < diagonal.length; i++) {
				square[i * 6] = diagonal[i];
			}

		}
	}

	private void setMainDiagonalVector4(int[] diagonal) {
		if (diagonal != null && diagonal.length == 4) {
			square[0] = diagonal[0];
			square[6] = diagonal[1];
			square[18] = diagonal[2];
			square[24] = diagonal[3];
		}
	}

	public int[] getRow(int i) {
		int[] row = new int[size];
		int r = size * i;
		for (int j = 0; j < size; j++) {
			row[j] = square[j + r];
		}
		return row;
	}

	public void setRow(int i, int[] v) {
		int r = size * i;
		for (int j = 0; j < size; j++) {
			square[j + r] = v[j];
		}
	}

	public int[] getCol(int i) {
		// int r = i;
		// int[] col = new int[5];
		// col[0] = square[0 + r];
		// col[1] = square[5 + r];
		// col[2] = square[10 + r];
		// col[3] = square[15 + r];
		// col[4] = square[20 + r];
		// // System.out.println(Arrays.toString(row));

		int[] column = new int[size];
		int r = 0;
		for (int j = 0; j < size; j++) {
			column[j] = square[r + i];
			r += size;
		}
		return column;
	}

	public void setCol(int i, int[] v) {
		int c = i;
		int r = 0;
		for (int j = 0; j < size; j++) {
			square[r + c] = v[j];
			r += size;
		}
	}

	public boolean colIsSet(int i) {
		int r = 0;
		for (int j = 0; j < size; j++) {
			if (square[r + i] != 0) {
				r += size;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public boolean rowIsSet(int i) {
		return true;
	}

	public void setMinorDiagonal(int[] diagonal) {
		if (diagonal != null && diagonal.length == 4) {
			// int c = 1;
			// for (int i = 0; i < diagonal.length; i++) {
			// square[4 * c] = diagonal[i];
			// c++;
			// }
			// if (center != 0) {
			// setCenter(center);
			// }
			square[4] = diagonal[0];
			square[8] = diagonal[1];
			square[16] = diagonal[2];
			square[20] = diagonal[3];

		}
	}

	public int[] getMainDiagonal() {
		int[] main = new int[5];
		main[0] = square[0];
		main[1] = square[6];
		main[2] = square[12];
		main[3] = square[18];
		main[4] = square[24];
		return main;
	}

	public boolean minorDiagonalIsSet() {
		if (square[4] != 0 && square[8] != 0 && square[16] != 0 && square[20] != 0) {
			return true;
		}
		return false;
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
		// for (int i = 0; i < square.length; i++) {
		// if (i != 0 && i % si == 0) {
		// System.out.println();
		// System.out.print(square[i] + " ");
		// } else {
		// System.out.print(square[i] + " ");
		// }
		// }
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
	 * THIS IS WRONG! Check if vector does not conflict with magic square
	 * 
	 * @param vectorKey
	 * @return
	 */
	public boolean notConflictWithVector(int[] vectorKey) {
		for (int i = 1; i < vectorKey.length; i++) {
			for (int sq : square) {
				if (sq == vectorKey[i]) {
					return false;
				}
			}
		}
		return true;
	}

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
			return false;
		}
		return true;
	}

	/**
	 * TODO add diagonals check & 65 to class field Check if current magic square is
	 * magical.
	 * 
	 * @return
	 */
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

	public int[] getDiagonal1() {
		int[] d = new int[size];
		for (int i = 0; i < size; i++) {
			d[i] = square[i * (size + 1)];
		}
		return d;
	}

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
