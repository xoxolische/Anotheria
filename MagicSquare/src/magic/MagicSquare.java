package magic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MagicSquare {

	private int[] square;
	private int center;
	private int size;

	public MagicSquare() {

	}

	public MagicSquare(int size) {
		this.size = size;
		square = new int[size * size];
	}

	public MagicSquare newInstance(int center, int[] square) {
		MagicSquare newMagic = new MagicSquare(size);
		newMagic.square = square;
		newMagic.setCenter(center);
		return newMagic;
	}

	public MagicSquare newInstance(int[] square) {
		MagicSquare newMagic = new MagicSquare(size);
		newMagic.square = square;
		return newMagic;
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
		int r = i * 5;
		int[] row = new int[5];
		row[0] = square[0 + r];
		row[1] = square[1 + r];
		row[2] = square[2 + r];
		row[3] = square[3 + r];
		row[4] = square[4 + r];
		// System.out.println(Arrays.toString(row));
		return row;
	}

	public int[] getCol(int i) {
		int r = i;
		int[] col = new int[5];
		col[0] = square[0 + r];
		col[1] = square[5 + r];
		col[2] = square[10 + r];
		col[3] = square[15 + r];
		col[4] = square[20 + r];
		// System.out.println(Arrays.toString(row));
		return col;
	}

	public void setCol(int i, int[] v) {
		int c = i;
		int r = 0;
		// square[0 + r] = v[0];
		// square[5 + r] = v[1];
		// square[10 + r] = v[2];
		// square[15 + r] = v[3];
		// square[20 + r] = v[4];
		for (int j = 0; j < size; j++) {
			square[r + c] = v[j];
			r += size;
		}
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

	public void print() {
		for (int i = 0; i < square.length; i++) {
			if (i != 0 && i % 5 == 0) {
				System.out.println();
				System.out.print(square[i] + "  ");
			} else {
				System.out.print(square[i] + "  ");
			}
		}
		System.out.println();
		System.out.println();
	}

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

	public int[] getSquare() {
		return square;
	}

	public boolean rowIsSet(int i) {
		int r = 5 * i;
		if (square[1 + r] != 0 && square[2 + r] != 0 && square[3 + r] != 0) {
			return true;
		}
		return false;
	}

	public void setRow(int i, int[] v) {
		int r = size * i;

		// if (v != null && v.length == 5) {
		// square[0 + r] = v[0];
		// square[1 + r] = v[1];
		// square[2 + r] = v[2];
		// square[3 + r] = v[3];
		// square[4 + r] = v[4];
		// }

		for (int j = 0; j < size; j++) {
			square[j + r] = v[j];
		}
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

	public String squareToString() {
		String s = "";
		for (int i = 0; i < size; i++) {
			s += String.valueOf(Arrays.toString(this.getRow(i)) + "\r\n");
		}
		s += "\r\n";
		return s;
	}

	public static void writeInFile(String path, Set<MagicSquare> ms) throws IOException {

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

}
