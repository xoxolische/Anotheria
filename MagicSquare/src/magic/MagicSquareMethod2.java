package magic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MagicSquareMethod2 implements Runnable {
	private static final int S_SIZE = 5;
	private static Integer[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
			24, 25 };
	static int SIZE = 2500; // 41820;
	static int THREADS = 4;
	static int partNumber = 1;
	private static final Set<Integer> valueSet = new HashSet<>(Arrays.asList(a));

	// private static Map<Integer, List<int[]>> row1 = new HashMap<>();
	// private static Set<int[]> setOfRow1 = new HashSet<>();
	private static Set<MagicSquare> squares = new HashSet<>();
	private static Set<MagicSquare> finalResult = new HashSet<>();
	// private static Map<String, int[]> vectorsCache = new HashMap<>();
	private static Map<Integer, Map<int[], int[]>> vectorsCache = new HashMap<>();
	private static List<MagicSquare> mss;
	private int partOfPartition;
	private int index;
	private static int totalSquaresCounter = 0;

	public MagicSquareMethod2(int p, int index) {
		this.partOfPartition = p;
		this.index = index;
	}

	public static void main(String[] args) throws InterruptedException, IOException {

		solve();
	}

	public static void solve() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		// MagicSquare m = new MagicSquare(5);
		// m.setRow(1, new int[] { 1, 2, 3, 4, 5 });
		// m.setCol(0, new int[] {2, 3, 0, 5, 6});
		// System.out.println(m.colIsSet(0));
		// // m.setRow(1, new int[] { 6, 7, 8, 9, 10 });
		// // m.setRow(2, new int[] { 11, 12, 13, 14, 15 });
		// // m.setRow(3, new int[] { 1, 2, 3, 4, 5 });
		// // m.setRow(4, new int[] { 1, 2, 3, 4, 5 });
		// // m.setCol(0, new int[] { 8, 8, 8, 8, 8 });
		// m.print();
		// Set<MagicSquare> test = new HashSet<>();
		// test.add(m);
		// m.writeInFile("Z:\\test.txt", test);
		long startTime = System.nanoTime();
		long endTime = 0;
		long duration = 0;
		Set<int[]> possibleVectors = generatePossibleVectors(valueSet, 5);
		for (int i : a) {
			vectorsCache.put(i, new HashMap<>());
		}

		for (int[] v : possibleVectors) {
			// string version
			// String s = "";
			// for (int i : v) {
			// s += i + " ";
			// }
			// s = s.trim();
			// vectorsCache.put(s, v);

			vectorsCache.get(v[0]).put(v, v);
			MagicSquare ms = new MagicSquare(S_SIZE);
			ms.setRow(0, v);
			squares.add(ms);
		}

		mss = new ArrayList<>(squares);

		/*
		 * 167 280
		 */
		int totalSquares = squares.size();
		// 10k
		int partition = SIZE * THREADS;
		int lastIndex = 0;
		long lastValOfTime = 0;
		while (true) {
			for (int columnNumber = 0; columnNumber < 5; columnNumber++) {
				Thread[] ts = new Thread[THREADS];
				for (int i = 0; i < THREADS; i++) {
					Thread t = new Thread(new MagicSquareMethod2(i, columnNumber));
					ts[i] = t;
					t.start();
				}

				for (Thread t : ts) {
					t.join();
				}
			}
			endTime = System.nanoTime();
			duration = endTime - startTime;
			System.out.println("Iteration " + partNumber + " : " + (double) (duration - lastValOfTime) / 1000000000.0);
			lastValOfTime = duration;
			Set<MagicSquare> partOfFinal = new HashSet<>();
			int c = 0;

			// for(MagicSquare m : finalResult) {
			// if(c > lastIndex) {
			// partOfFinal.add(m);
			// }
			// c++;
			// }
			lastIndex = c;
			if (partNumber == 16)
				MagicSquare.writeInFile("z://" + partNumber + ".txt", new HashSet<>(finalResult));// finalResult or
																									// partOFFinal
			// finalResult = new HashSet<>();
			if (totalSquares - partition * partNumber < partition) {
				break;
			}
			partNumber++;
			// totalSquaresCounter = finalResult.size();
		}
		System.out.println("Square basises done! Started permutation for rows.");
		permutateSquares();
		
		MagicSquare.writeInFile("z://final.txt", new HashSet<>(finalResult));
		endTime = System.nanoTime();
		duration = endTime - startTime;

		System.out.println("Program finished in Seconds : " + (double) duration / 1000000000.0);
		System.out.println("Squares : " + squares.size());
		System.out.println("Total finished squares : " + finalResult.size());
	}

	private static void permutateSquares() {
		Permutations p = new Permutations();
		p.permutates(new int[] { 0, 1, 2, 3, 4 });
		// for (int[] a : p.permutationsList) {
		// System.out.println(Arrays.toString(a));
		// }
		List<int[]> permutationIndexes = new ArrayList<>(p.permutationsList);
		Set<MagicSquare> permutationsOfRows = new HashSet<>();
		int c = 0;
		long start = System.nanoTime();
		for (MagicSquare m : finalResult) {
			if (c >= 100) {
				break;
			}
			for (int i = 1; i < permutationIndexes.size(); i++) {
				permutationsOfRows.add(swaper(m, permutationIndexes.get(i)));
			}
			c++;
		}

		long end = System.nanoTime();
		long duration = end - start;
		
		System.out.println("Permutation of 120/"+finalResult.size()+" squares finished in Seconds : " + (double) duration / 1000000000.0);
		for (MagicSquare m : permutationsOfRows) {
			finalResult.add(m);
		}
	}

	private static MagicSquare swaper(MagicSquare m, int[] indexMapper) {
		MagicSquare newMagic = new MagicSquare(m.getSize());
		for (int i = 0; i < indexMapper.length; i++) {
			newMagic.setRow(i, m.getRow(indexMapper[i]));
		}
		return newMagic;
	}

	private void fillColumn(int index) {
		// 41820
		long startTime = 0;
		// int c = 0;
		int start = 10000 * partNumber - SIZE * partOfPartition;
		int end = start - SIZE;
		// System.out.println("Start : " + start + " End : " + end + "p = "+
		// partNumber);
		// System.out.println("Thread : " + Thread.currentThread().getName() + " got
		// start index: " + start
		// + " and end index: " + end);
		Set<MagicSquare> partitionSquareSet = new LinkedHashSet<>(mss.subList(end, start));
		Set<MagicSquare> goodSquaresWithChanges = new HashSet<>();
		for (MagicSquare ms : partitionSquareSet) {
			// c++;
			startTime = System.nanoTime();

			for (int k : vectorsCache.keySet()) {
				int[] a = ms.getRow(0);

				int[] col0 = ms.getCol(0);
				int[] col1 = ms.getCol(1);
				int[] col2 = ms.getCol(2);
				int[] col3 = ms.getCol(3);
				int[] col4 = ms.getCol(4);
				if (k == a[index]) {
					for (int[] vectorKey : vectorsCache.get(k).values()) {

						if (!a.equals(vectorKey) && !col0.equals(vectorKey) && !col1.equals(vectorKey)
								&& !col2.equals(vectorKey) && !col3.equals(vectorKey) && !col4.equals(vectorKey)) {
							// if (index == 88) {
							// System.out.println("1 - " + Arrays.toString(col0));
							// System.out.println("2 - " + Arrays.toString(vectorKey));
							// }
							if (ms.notConflictWithVector(vectorKey)) {

								MagicSquare ms2 = ms.newInstance(ms.getSquare());
								ms2.setCol(index, vectorKey);
								// addSquare(ms2);
								//if(index == 4 ) {
								//if (ms2.isMagic()) {
									goodSquaresWithChanges.add(ms2);
								//}
//								}else {
//									goodSquaresWithChanges.add(ms2);									
//								}
								// if (index == 1) {
								// ms2.print();
								// }
								// }
							}
						}
					}
				}
			}

			long endTime = System.nanoTime();
			long duration = endTime - startTime;

			// System.err.println(Thread.currentThread().getName() + " -> ms " + c + " of
			// 41820; Seconds : "
			// + (double) duration / 1000000000.0);

		}
		// System.out.println("Removed : " + partitionSquareSet.size());
		// // remove old squares from this part
		// removeAll(partitionSquareSet);
		// System.out.println("Added : " + goodSquaresWithChanges.size());
		// add new good squares
		// addAll(goodSquaresWithChanges);
		//if (index == 4) {
			for (MagicSquare m : goodSquaresWithChanges) {
				// m.print();
				// finalResult.add(m);
				addFinal(m);
			}
		//}
	}

	private static Set<int[]> generatePossibleVectors(Set<Integer> values, int subSetSize) {
		int arr[] = new int[values.size()];
		int c = 0;
		for (int v : values) {
			arr[c] = v;
			c++;
		}
		Set<int[]> possibleVectors = new HashSet<>();
		int r = subSetSize;
		int n = arr.length;
		int total = 0;
		int filtered = 0;
		Subset sub = new Subset();
		sub.generateSubsets(arr, n, r);
		for (int[] i : sub.subsets) {
			// System.out.println(Arrays.toString(i));
			Permutations permForSubs = new Permutations();
			permForSubs.permutates(i);
			total += permForSubs.permutationsList.size();
			for (int[] p : permForSubs.permutationsList) {
				if (correct(p)) {
					possibleVectors.add(p);
					filtered++;
				}
			}
		}
		System.out.println("Size : " + sub.subsets.size());
		System.out.println("Total vectors for 1st row : " + total);
		System.out.println("Filtered : " + filtered);

		return possibleVectors;
	}

	private static boolean correct(int[] v) {
		int sum = 0;
		for (int i : v) {
			sum += i;
		}
		if (sum == 65) {
			return true;
		}
		return false;
	}

	@Override
	public void run() {
		fillColumn(this.index);
	}

	synchronized private void addSquare(MagicSquare ms) {
		squares.add(ms);
	}

	synchronized private void removeAll(Set<MagicSquare> ms) {
		// squares.removeAll(ms);
		for (MagicSquare m : ms) {
			squares.remove(m);
		}
	}

	synchronized private void addAll(Set<MagicSquare> ms) {
		for (MagicSquare m : ms) {
			squares.add(m);
		}
		// squares.addAll(new HashSet<>(ms));
	}

	synchronized void addFinal(MagicSquare m) {
		finalResult.add(m);
	}

}
