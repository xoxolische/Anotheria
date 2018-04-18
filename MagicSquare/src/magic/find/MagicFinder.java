package magic.find;

import java.io.IOException;
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

public class MagicFinder implements Runnable {
	private static final int SIZE = 4;

	private static Map<Integer, List<int[]>> cache;
	private static final int THREADS = 5;
	private static List<MagicSquare> magicSquareList;
	private static List<MagicSquare> finalList = new LinkedList<>();

	private static int threadNum = 0;
	private List<MagicSquare> initialList;

	private static int current = 0;
	private static int step = 100;

	public MagicFinder(List<MagicSquare> ms) {
		this.initialList = ms;
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		Set<int[]> magicVectors = new HashSet<>();
		cache = new HashMap<>();
		magicSquareList = new LinkedList<>();
		int[] values = new int[SIZE * SIZE];
		for (int i = 0; i < SIZE * SIZE; i++) {
			values[i] = i + 1;
		}
		MagicSquare m3 = new MagicSquare(SIZE);

		Subset subset = new Subset();
		subset.generateSubsets(values, values.length, SIZE);

		for (int[] sub : subset.subsets) {
			if (m3.sum(sub) == m3.getMagicSum()) {
				Permutations p = new Permutations();
				p.permutates(sub);
				for (int[] pe : p.permutationsList) {
					magicVectors.add(pe);
				}
			}
		}

		for (int[] vector : magicVectors) {
			if (cache.get(vector[0]) == null) {
				cache.put(vector[0], new LinkedList<>());
			}
			cache.get(vector[0]).add(vector);
			MagicSquare m = new MagicSquare(SIZE);
			m.setRow(0, vector);
			magicSquareList.add(m);
		}
		System.out.println("Before search : " + magicSquareList.size());
		long startTime = System.nanoTime();
		int c = 0;
		boolean foo = true;
		while (foo) {
			Thread[] ts = new Thread[THREADS];
			for (int i = 0; i < THREADS; i++) {
				List<MagicSquare> m = getPartition(magicSquareList);
				if (m != null) {
					Thread t = new Thread(new MagicFinder(m));
					t.start();
					ts[i] = t;
					current += step;
				} else {
					foo = false;
				}
				// searchForSquares(magicSquareList, index);
			}

			for (Thread t : ts) {
				if (t != null)
					t.join();
			}
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println("Time sec : " + (double) duration / 1000000000.0);
		MagicSquare.writeInFile("z://result//final-" + SIZE + ".txt", finalList);
		// System.out.println("Total Magic vectors : " + magicVectors.size());
		System.out.println("Magic squres : " + finalList.size());

	}

	private static List<MagicSquare> getPartition(List<MagicSquare> ms) {
		int start = current;
		int end = current + step;
		List<MagicSquare> sub;
		if (ms.size() >= end) {
			sub = new LinkedList<>(ms.subList(current, end));
			return sub;
		} else {
			if (ms.size() < start) {
				return null;
			}
			end = end - (end - ms.size());
			sub = new LinkedList<>(ms.subList(start, end));
			return sub;
		}
	}

	// private static void searchForSquares(List<MagicSquare> ms, int numberOfPart)
	// {
	// int partSize = ms.size() / THREADS;
	// int begin = numberOfPart * partSize;
	// int end = begin + partSize;
	// List<MagicSquare> magicSquareList = new LinkedList<>(ms.subList(begin, end));
	private static void searchForSquares(List<MagicSquare> ms) throws IOException {
		long startTime = System.nanoTime();
		List<MagicSquare> magicSquareList = new LinkedList<>(ms);
		Set<MagicSquare> toAdd = new HashSet<>();
		Set<MagicSquare> fin = new HashSet<>();
		for (int i = 0; i < SIZE; i++) {
			for (MagicSquare m : magicSquareList) {
				// System.out.println(Thread.currentThread().getName() + " -> list : " +
				// magicSquareList.size());
				int start = m.getRow(0)[i];
				for (int[] vector : cache.get(start)) {
					m.setCol(i, vector);
					if (m.noConflictsInMatrix()) {
						if (i != SIZE - 1) {
							boolean good = true;
							for (int j = 1; j < SIZE; j++) {
								if (m.sum(m.getRow(j)) > m.getMagicSum()) {
									good = false;
									break;
								}
							}
							if (good) {
								MagicSquare t = new MagicSquare(SIZE);
								for (int c = 0; c < SIZE; c++) {
									int[] col = Arrays.copyOf(m.getCol(c), m.getCol(c).length);
									t.setCol(c, col);
								}
								toAdd.add(t);
								// System.out.println(Thread.currentThread().getName() + " -> No conflicts, add
								// : " + t.squareToString());
							}
						} else {
							if (m.isMagic()) {
								MagicSquare t = new MagicSquare(SIZE);
								for (int c = 0; c < SIZE; c++) {
									int[] col = Arrays.copyOf(m.getCol(c), m.getCol(c).length);
									t.setCol(c, col);
								}
								fin.add(t);
								// System.out.println(
								// Thread.currentThread().getName() + " ->Magic!, add : " + t.squareToString());
							}
						}
					}
				}
			}
			magicSquareList = new LinkedList<>();
			magicSquareList.addAll(toAdd);
			toAdd = new HashSet<>();
		}
		addAll(fin);
		MagicSquare.writeInFile("z://result//size-" + SIZE + "-part" + getThread() + ".txt", fin);
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println("Found " + fin.size() + " magic squares in " + (double) duration / 1000000000.0 + " sec.");
	}

	synchronized private static void addAll(Set<MagicSquare> m) {
		finalList.addAll(m);
	}

	@Override
	public void run() {

		try {
			searchForSquares(this.initialList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	synchronized private static int getThread() {
		threadNum++;
		return threadNum;
	}
}
