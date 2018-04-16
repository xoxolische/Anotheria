package magic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MagicSquareMethod1 {

	private static int c = 0;
	// save permutations for our SET
	private static Map<Integer, List<int[]>> cachePermutations = new HashMap<>();
	// ???? K = int[] + length maybe?
	private static Map<List<Integer>, List<int[]>> cacheSubsets = new HashMap<>();
	private static final int SIZE = 5;

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		long endTime = 0;
		long duration = 0;
		long sum = 0;
		int cp = 0;
		int cg = 0;
		// MagicSquare m = new MagicSquare();
		// m.setCenter(12);
		// m.setMainDiagonal(new int[] { 1, 2, 3, 4, 5 });
		// //m.setMainDiagonal(new int[] { 2, 2, 2, 2 });
		// m.setMinorDiagonal(new int[] { 7, 25, 20, 23 });
		// m.print();
		// m.getRow(1);
		// System.out.println(m.getCollection());

		// System.out.println(25 / 2);
		Set<Integer> set = new HashSet<>();
		for (int i = 1; i < 26; i++) {
			set.add(i);
		}

		List<MagicSquare> basisList = new LinkedList<MagicSquare>();

		// fill possible values of center A (1<=A<=13)
		List<Integer> A = new LinkedList<>();
		for (Integer i : set) {
			if (i >= 1 && i <= 13) {
				A.add(i);
			}
		}
		// create square basis for each possible center
		// for (Integer c : A) {
		// MagicSquare ms = new MagicSquare();
		// ms.setCenter(c);
		// basisList.add(ms);
		// }

		// generate permutations of main diagonal \
		// include clause from A generating
		// E > B AND D > C > B

		for (Integer center : A) {
			Set<Integer> subSet = new HashSet<>(set);
			subSet.remove(center);
			Subset p = new Subset();
			int[] temp = subSet.stream().mapToInt(Integer::intValue).toArray();
			p.generateSubsets(temp, temp.length, 4);

			for (int[] vector : p.subsets) {
				// generate permutations of 4size main diagonal vectors
				List<int[]> list;
				if (cachePermutations.containsKey(vector.hashCode())) {
					list = cachePermutations.get(vector.hashCode());
					cg++;
				} else {
					Permutations s = new Permutations();
					s.permutates(vector);
					list = s.permutationsList;
					addToCache(vector.hashCode(), new LinkedList<>(s.permutationsList));
					cp++;
				}
				for (int[] v : list) {
					// System.out.println(Arrays.toString(v));
					if (v[3] > v[0] && v[1] > v[0] && v[2] > v[1] && (v[0] + v[1] + v[2] + v[3] + center) == 65) {
						MagicSquare ms = new MagicSquare(SIZE);
						ms.setCenter(center);
						ms.setMainDiagonal(v);
						basisList.add(ms);
					}
				}
			}
		}
		System.out.println("Got : " + cg + "; Put : " + cp);
		cp = 0;
		cg = 0;
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("Main diagonal... \r\n Seconds : " + (double) duration / 1000000000.0);
		System.out.println("Basis : " + basisList.size());
		sum += duration;
		// now we have magic squares with main diagonal
		List<MagicSquare> toAdd = new LinkedList<>();
		//List<Thread> threads = new LinkedList<>();

		//-----------------------------------
		for (MagicSquare magicSquare : basisList) {
			// 0. retain sets with already filled values
			// 1. set of int take 4subsets
			// 2. permutate
			// 3. check if good -> save to minor diagonal or create new instance and save in
			// basis

			// increment();
			Set<Integer> minorSet = new HashSet<>(set);
			minorSet.removeAll(magicSquare.getCollection());

			Subset p = new Subset();
			int[] temp = minorSet.stream().mapToInt(Integer::intValue).toArray();
			p.generateSubsets(temp, temp.length, 4);

			for (int[] vector : p.subsets) {
				//System.out.println(vector.hashCode());
				// generate permutations of 4size minor diagonal vectors
				List<int[]> list;

				if (cachePermutations.containsKey(vector.hashCode())) {
					list = cachePermutations.get(vector.hashCode());
					cg++;
				} else {
					Permutations s = new Permutations();
					s.permutates(vector);
					list = s.permutationsList;
					addToCache(vector.hashCode(), new LinkedList<>(s.permutationsList));
					cp++;
				}
				for (int[] v : list) {
					int[] mainDiagonal = magicSquare.getMainDiagonal();
					if (v[2] > mainDiagonal[0] && v[1] > mainDiagonal[0] && v[3] > v[0] && v[0] > mainDiagonal[0]
							&& (v[0] + v[1] + v[2] + v[3] + magicSquare.getCenter()) == 65) {
						if (magicSquare.minorDiagonalIsSet()) {
							MagicSquare newMagicSquare = magicSquare.newInstance(magicSquare.getCenter(),
									magicSquare.getSquare());
							magicSquare.setMinorDiagonal(v);
							add(toAdd, newMagicSquare);
							// System.out.println("new added!");
						} else {
							magicSquare.setMinorDiagonal(v);
						}
					}
				}
			}
		}
		//------------------------------
		System.out.println("Total Got : " + cg + "; Put : " + cp);
		cp = 0;
		cg = 0;

		// add new squares
		basisList.addAll(toAdd);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("1st row combinations... \r\n Seconds : " + (double) duration / 1000000000.0);
		System.out.println("Basis : " + basisList.size());
		sum += duration;
		// now we need to finish our squares

		List<MagicSquare> toAdd2 = new LinkedList<>();
		int c = 0;
		for (MagicSquare ms : basisList) {
			c++;
			if (c == 100000) {
				System.out.println("!!!!!!!");
				c = 0;
			}
			Set<Integer> row1 = new HashSet<>(set);
			row1.removeAll(ms.getCollection());

			Subset p = new Subset();
			int[] temp = row1.stream().mapToInt(Integer::intValue).toArray();
			p.generateSubsets(temp, temp.length, 3);// 4????

			for (int[] vector : p.subsets) {
				// generate permutations of 3size 1st row vectors
				Permutations s = new Permutations();
				s.permutates(vector);
				for (int[] v : s.permutationsList) {
					int[] r = ms.getRow(0);
					if ((v[0] + v[1] + v[2] + r[0] + r[4]) == 65) {
						if (ms.rowIsSet(0)) {
							MagicSquare newMagicSquare = ms.newInstance(ms.getCenter(), ms.getSquare());
							ms.setRow(0, v);
							add(toAdd2, newMagicSquare);
						} else {
							ms.setRow(0, v);
						}
					}
				}
			}
		}
		basisList.addAll(toAdd2);
		System.out.println("Basis : " + basisList.size());
		//
		toAdd2 = new LinkedList<>();
		for (MagicSquare ms : basisList) {
			Set<Integer> mn = new HashSet<>(set);
			mn.removeAll(ms.getCollection());

			Subset p = new Subset();
			int[] temp = mn.stream().mapToInt(Integer::intValue).toArray();
			p.generateSubsets(temp, temp.length, 2);

			for (int[] vector : p.subsets) {
				// generate permutations of 2size for MN
				Permutations s = new Permutations();
				s.permutates(vector);
				for (int[] v : s.permutationsList) {
					int[] MN = ms.getCol(1);
					if ((MN[0] + MN[1] + MN[3] + v[0] + v[1]) == 65) {
						if (ms.cellFilled(11) && ms.cellFilled(21)) {
							MagicSquare newMagicSquare = ms.newInstance(ms.getCenter(), ms.getSquare());
							ms.setCell(11, v[0]);
							ms.setCell(21, v[1]);
							add(toAdd2, newMagicSquare);
						} else {
							ms.setCell(11, v[0]);
							ms.setCell(21, v[1]);
						}
					}
				}
			}
		}
		basisList.addAll(toAdd2);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("MN... \r\n Seconds : " + (double) duration / 1000000000.0);
		System.out.println("Basis : " + basisList.size());
		sum += duration;
		// PO
		toAdd2 = new LinkedList<>();
		for (MagicSquare ms : basisList) {
			Set<Integer> mn = new HashSet<>(set);
			mn.removeAll(ms.getCollection());

			Subset p = new Subset();
			int[] temp = mn.stream().mapToInt(Integer::intValue).toArray();
			p.generateSubsets(temp, temp.length, 2);

			for (int[] vector : p.subsets) {
				// generate permutations of 2size for PO
				Permutations s = new Permutations();
				s.permutates(vector);
				for (int[] v : s.permutationsList) {
					int[] MN = ms.getCol(3);
					if ((MN[0] + MN[1] + MN[3] + v[0] + v[1]) == 65) {
						if (ms.cellFilled(11) && ms.cellFilled(21)) {
							MagicSquare newMagicSquare = ms.newInstance(ms.getCenter(), ms.getSquare());
							ms.setCell(13, v[0]);
							ms.setCell(23, v[1]);
							add(toAdd2, newMagicSquare);
						} else {
							ms.setCell(13, v[0]);
							ms.setCell(23, v[1]);
						}
					}
				}
			}
		}
		basisList.addAll(toAdd2);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("PO... \r\n Seconds : " + (double) duration / 1000000000.0);
		System.out.println("Basis : " + basisList.size());
		sum += duration;
		// Q
		toAdd2 = new LinkedList<>();
		for (MagicSquare ms : basisList) {
			Set<Integer> mn = new HashSet<>(set);
			mn.removeAll(ms.getCollection());

			// Permutation p = new Permutation();
			int[] temp = mn.stream().mapToInt(Integer::intValue).toArray();
			// p.printCombination(temp, temp.length, 2);

			for (int v : temp) {
				int[] Q = ms.getCol(2);
				if ((Q[0] + Q[1] + Q[2] + Q[3] + v) == 65) {
					if (ms.cellFilled(22)) {
						MagicSquare newMagicSquare = ms.newInstance(ms.getCenter(), ms.getSquare());
						ms.setCell(22, v);
						add(toAdd2, newMagicSquare);
					} else {
						ms.setCell(22, v);
					}
				}
			}
		}
		basisList.addAll(toAdd2);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("Q... \r\n Seconds : " + (double) duration / 1000000000.0);
		System.out.println("Basis : " + basisList.size());
		sum += duration;
		// RS row
		toAdd2 = new LinkedList<>();
		for (MagicSquare ms : basisList) {
			Set<Integer> row1 = new HashSet<>(set);
			row1.removeAll(ms.getCollection());

			Subset p = new Subset();
			int[] temp = row1.stream().mapToInt(Integer::intValue).toArray();
			p.generateSubsets(temp, temp.length, 2);

			for (int[] vector : p.subsets) {

				Permutations s = new Permutations();
				s.permutates(vector);
				for (int[] v : s.permutationsList) {
					int[] r = ms.getRow(2);
					if ((v[0] + v[1] + r[1] + r[2] + r[3] + r[4]) == 65) {
						if (ms.cellFilled(10) && ms.cellFilled(14)) {
							MagicSquare newMagicSquare = ms.newInstance(ms.getCenter(), ms.getSquare());
							ms.setCell(10, v[0]);
							ms.setCell(14, v[1]);
							add(toAdd2, newMagicSquare);
						} else {
							ms.setCell(10, v[0]);
							ms.setCell(14, v[1]);
						}
					}
				}
			}
		}
		basisList.addAll(toAdd2);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("RS... \r\n Seconds : " + (double) duration / 1000000000.0);
		System.out.println("Basis : " + basisList.size());
		sum += duration;
		// TU
		toAdd2 = new LinkedList<>();
		for (MagicSquare ms : basisList) {
			Set<Integer> mn = new HashSet<>(set);
			mn.removeAll(ms.getCollection());

			Subset p = new Subset();
			int[] temp = mn.stream().mapToInt(Integer::intValue).toArray();
			p.generateSubsets(temp, temp.length, 2);

			for (int[] vector : p.subsets) {
				// generate permutations of 2size for TU
				Permutations s = new Permutations();
				s.permutates(vector);
				for (int[] v : s.permutationsList) {
					int[] MN = ms.getCol(2);
					if ((MN[0] + MN[2] + MN[4] + v[0] + v[1]) == 65) {
						if (ms.cellFilled(7) && ms.cellFilled(17)) {
							MagicSquare newMagicSquare = ms.newInstance(ms.getCenter(), ms.getSquare());
							ms.setCell(7, v[0]);
							ms.setCell(17, v[1]);
							add(toAdd2, newMagicSquare);
						} else {
							ms.setCell(7, v[0]);
							ms.setCell(17, v[1]);
						}
					}
				}
			}
		}
		basisList.addAll(toAdd2);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("TU... \r\n Seconds : " + (double) duration / 1000000000.0);
		System.out.println("Basis : " + basisList.size());
		sum += duration;
		// vw
		toAdd2 = new LinkedList<>();
		for (MagicSquare ms : basisList) {
			Set<Integer> mn = new HashSet<>(set);
			mn.removeAll(ms.getCollection());

			Subset p = new Subset();
			int[] temp = mn.stream().mapToInt(Integer::intValue).toArray();
			p.generateSubsets(temp, temp.length, 2);

			for (int[] vector : p.subsets) {
				// generate permutations of 2size for vw
				Permutations s = new Permutations();
				s.permutates(vector);
				for (int[] v : s.permutationsList) {
					int[] MN = ms.getCol(2);
					if ((MN[0] + MN[2] + MN[4] + v[0] + v[1]) == 65) {
						if (ms.cellFilled(7) && ms.cellFilled(17)) {
							MagicSquare newMagicSquare = ms.newInstance(ms.getCenter(), ms.getSquare());
							ms.setCell(5, v[0]);
							ms.setCell(15, v[1]);
							add(toAdd2, newMagicSquare);
						} else {
							ms.setCell(5, v[0]);
							ms.setCell(15, v[1]);
						}
					}
				}
			}
		}
		basisList.addAll(toAdd2);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("VW... \r\n Seconds : " + (double) duration / 1000000000.0);
		System.out.println("Basis : " + basisList.size());
		sum += duration;
		// xy
		toAdd2 = new LinkedList<>();
		for (MagicSquare ms : basisList) {
			Set<Integer> mn = new HashSet<>(set);
			mn.removeAll(ms.getCollection());

			Subset p = new Subset();
			int[] temp = mn.stream().mapToInt(Integer::intValue).toArray();
			p.generateSubsets(temp, temp.length, 2);

			for (int[] vector : p.subsets) {
				// generate permutations of 2size for xy
				Permutations s = new Permutations();
				s.permutates(vector);
				for (int[] v : s.permutationsList) {
					int[] MN = ms.getCol(2);
					if ((MN[0] + MN[2] + MN[4] + v[0] + v[1]) == 65) {
						if (ms.cellFilled(7) && ms.cellFilled(17)) {
							MagicSquare newMagicSquare = ms.newInstance(ms.getCenter(), ms.getSquare());
							ms.setCell(9, v[0]);
							ms.setCell(19, v[1]);
							add(toAdd2, newMagicSquare);
						} else {
							ms.setCell(9, v[0]);
							ms.setCell(19, v[1]);
						}
					}
				}
			}
		}
		basisList.addAll(toAdd2);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("XY... \r\n Seconds : " + (double) duration / 1000000000.0);
		System.out.println("Basis : " + basisList.size());
		sum += duration;
		System.out.println("Total Seconds : " + (double) sum / 1000000000.0);
	}

	private static synchronized <T> void add(List<T> l, T m) {
		l.add(m);
	}

	private static synchronized void addToCache(int K, List<int[]> V) {
		cachePermutations.put(K, V);
	}

	private static synchronized void increment() {
		c++;
	}

	private static synchronized int get() {
		return c;
	}

}
