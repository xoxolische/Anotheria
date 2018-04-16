package magic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
	static int SIZE = 41820;
	private static final Set<Integer> valueSet = new HashSet<>(Arrays.asList(a));

	private static Map<Integer, List<int[]>> row1 = new HashMap<>();
	private static Set<int[]> setOfRow1 = new HashSet<>();
	private static Set<MagicSquare> squares = new HashSet<>();
	// private static Map<String, int[]> vectorsCache = new HashMap<>();
	private static Map<Integer, Map<int[], int[]>> vectorsCache = new HashMap<>();
	private static List<MagicSquare> mss;
	private int part;
	private int index;

	public MagicSquareMethod2(int p, int index) {
		this.part = p;
		this.index = index;
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		 MagicSquare m = new MagicSquare(5);
		 m.setRow(1, new int[] { 1, 2, 3, 4, 5 });
//		 m.setRow(1, new int[] { 6, 7, 8, 9, 10 });
//		 m.setRow(2, new int[] { 11, 12, 13, 14, 15 });
//		 m.setRow(3, new int[] { 1, 2, 3, 4, 5 });
		// m.setRow(4, new int[] { 1, 2, 3, 4, 5 });
		// m.setCol(0, new int[] { 8, 8, 8, 8, 8 });
		 m.print();
		 Set<MagicSquare> test = new HashSet<>();
		 test.add(m);
		 m.writeInFile("Z:\\test.txt", test);
		long startTime = System.nanoTime();
		long endTime = 0;
		long duration = 0;
		Set<int[]> possibleVectors = generatePossibleVectors(valueSet, 5);
		for(int i : a) {
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
		
		int av = 0;
		for(int k : vectorsCache.keySet()) {
			for(int[] v : vectorsCache.get(k).keySet()) {
				//System.out.println(Arrays.toString(v));
				av++;
			}
		}
		System.out.println("Actual vectors " + av);
		mss = new ArrayList<>(squares);

		for (int index = 0; index < 5; index++) {
			Thread[] ts = new Thread[4];
			for (int i = 0; i < 4; i++) {
				Thread t = new Thread(new MagicSquareMethod2(i, index));
				ts[i] = t;
				t.start();
			}

			for (Thread t : ts) {
				t.join();
			}

			long e = System.nanoTime();
			long d = e - startTime;

			System.out.println("Column i = " + index + " finished in Seconds: " + (double) d / 1000000000.0);

		}

		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("Program finished in Seconds : " + (double) duration / 1000000000.0);
		System.out.println("Squares : " + squares.size());

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
	synchronized private void addSquareSet(Set<MagicSquare> ms) {
		squares.addAll(ms);
	}

	private void fillColumn(int index) {
		// 41820
		long startTime = 0;
		int c = 0;
		int b = SIZE * part;
		int e = SIZE + b;
		Set<MagicSquare> sub = new LinkedHashSet<>(mss.subList(b, e));
		Set<MagicSquare> setOfFilledMs = new HashSet<>();
		//do we need this??
		//Map<Integer, Map<int[], int[]>> copy = new HashMap<>(vectorsCache);
		for (MagicSquare ms : sub) {
			c++;
			startTime = System.nanoTime();
			// for each ms get vectors that begins with ms.getRow.firstChar
			// copy ms and set new vector as a column
			
			for(int k : vectorsCache.keySet()) {
				int[] a = ms.getRow(0);
				if (k == a[0]) {
					for(int[] vectorKey : vectorsCache.get(k).keySet()) {						
						if (!a.equals(vectorKey)) {
							MagicSquare ms2 = ms.newInstance(ms.getSquare());
							ms2.setCol(0, vectorKey);
							addSquare(ms2);
							//setOfFilledMs.add(ms2);
						}
					}
				}
			}
//			for (String k : vectorsCache.keySet()) {
//				int[] a = ms.getRow(0);
//				if (k.split(" ")[0].equals(String.valueOf(a[0]))) {
//					if (!a.equals(vectorsCache.get(k))) {
//						MagicSquare ms2 = ms.newInstance(ms.getSquare());
//						ms2.setCol(0, vectorsCache.get(k));
//						addSquare(ms2);
//					}
//				}
//			}
			long endTime = System.nanoTime();
			long duration = endTime - startTime;
			if (c % 10000 == 0) {
				System.err.println(Thread.currentThread().getName() + " -> ms " + c + " of 41820; Seconds : "
						+ (double) duration / 1000000000.0);
			}
		}
		
		//addSquareSet(setOfFilledMs);
	}
}
