package magic.x3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import magic.MagicSquare;
import magic.Permutations;
import magic.Subset;

public class MagicFinder3x3 {

	public static final int SIZE = 3;

	public static void main(String[] args) {
		Set<int[]> magicVectors = new HashSet<>();
		Map<Integer, List<int[]>> cache = new HashMap<>();
		List<MagicSquare> magicSquareList = new LinkedList<>();
		int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		MagicSquare m3 = new MagicSquare(3);
		m3.setCol(0, new int[] { 1, 2, 3 });
		m3.setRow(2, new int[] { 9, 9, 9 });
		m3.print();

		Subset subset = new Subset();
		subset.generateSubsets(values, values.length, SIZE);

		// int magic = 0;
		for (int[] sub : subset.subsets) {
			if (m3.sum(sub) == m3.getMagicSum()) {
				Permutations p = new Permutations();
				p.permutates(sub);
				for (int[] pe : p.permutationsList) {
					magicVectors.add(pe);
					// System.out.println(Arrays.toString(pe));
				}
			}
		}
		// System.out.println("Magic : " + magic);
		System.out.println("Total : " + magicVectors.size());

		// initialize 1st row
		for (int[] vector : magicVectors) {
			if (cache.get(vector[0]) == null) {
				cache.put(vector[0], new LinkedList<>());
			}
			cache.get(vector[0]).add(vector);
			MagicSquare m = new MagicSquare(SIZE);
			m.setRow(0, vector);
			magicSquareList.add(m);
		}
//		int k = cache.keySet().size();
//		int total = 0;
//		for (int i : cache.keySet()) {
//			total += cache.get(i).size();
//			for (int[] r : cache.get(i)) {
//				MagicSquare m = new MagicSquare(3);
//				m.setRow(0, r);
//				magicSquareList.add(m);
//			}
//		}
		List<MagicSquare> onlyMagic = new LinkedList<>();
		Set<MagicSquare> toAdd = new HashSet<>();
		List<MagicSquare> toRemove = new LinkedList<>();
		//System.out.println("K: " + k + "; V.total : " + total);
		for (int i = 0; i < SIZE; i++) {
			for (MagicSquare m : magicSquareList) {
				int start = m.getRow(0)[i];
				//System.out.println(start);
				
				for (int[] vector : cache.get(start)) {
					m.setCol(i, vector);
					//if (m.noConflictsInMatrix()) {
					if(m.noConflictsInMatrix()) {
						if (i != SIZE - 1) {
							//for (int j = 1; j < SIZE; j++) {
								//if (m.sum(m.getRow(j)) < m.getMagicSum()) {
									MagicSquare t = new MagicSquare(SIZE);
									for (int c = 0; c < SIZE; c++) {
										int[] col = Arrays.copyOf(m.getCol(c), m.getCol(c).length);
										t.setCol(c, col);
									}
									toAdd.add(t);
								//}
							//}
						} else {
								if (true) {
									MagicSquare t = new MagicSquare(SIZE);
									for (int c = 0; c < SIZE; c++) {
										int[] col = Arrays.copyOf(m.getCol(c), m.getCol(c).length);
										t.setCol(c, col);
									}
									toAdd.add(t);
								}						
						}
					} 
				}
			}
			magicSquareList = new LinkedList<>();
			magicSquareList.addAll(toAdd);
			toAdd = new HashSet<>();
		}
		int c = 0;
		for (MagicSquare m : magicSquareList) {
			if(m.isMagic()) {				
				m.print();
				System.out.println(Arrays.toString(m.getDiagonal2()));
				c++;
			}
		}
		System.out.println("Magic squres : " + c);

	}

}
