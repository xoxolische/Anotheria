package magic.finder;

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

/**
 * This class implements search of magic squares for given size in multiple
 * threads.
 * <p>
 * Main idea of method to separate big amount of data into smaller pieces and
 * make permutations of recieved data.
 * <p>
 * We begin with setting the size N of our MagicSquare entity. Then, we fill the
 * set of possible values for this case. I.e we have a set filled with integers
 * {1..n^2}
 * <p>
 * Then, we search for subsets of our set. Subset size equals N, also subset sum
 * must be equals to our magic sum. Then, we do permutations of those vectors.
 * After these manipulations, foreach vector we have create new MagicSquare, set
 * 0 row with given vector and store it in the List. Now we have our basic
 * object list. Also, we store our "Magic Vectors" (MV) in HashMap in format K -
 * 1st number of MV, V - List<MV>. So we can get all MV that begin with
 * specified number.
 * <p>
 * Now we take a portion of MagicSquares and foreach column take 1st column
 * number (already set with previous step and belongs to 0 row of our MS) we
 * take List of MV from map. Place it and check if there no duplicate values in
 * matrix. Then we check each row if it`s less than Magic Sum.
 * <p>
 * Repeat until we face last column. Foreach last column we set it to MS and
 * check if it is magical and save in case true.
 * 
 * @author Nikita Pavlov
 *
 */
public class MainMagicFinder implements Runnable {
	private static int sizeOfSquareToFind;
	private static int threadsNumber;

	private static int step = 100;

	private static Map<Integer, List<int[]>> cache = new HashMap<>();
	private static List<MagicSquare> magicSquareList = new LinkedList<>();
	private static List<MagicSquare> finalList = new LinkedList<>();

	private static int threadNum = 0;
	private List<MagicSquare> initialList;

	private static int current = 0;

	public MainMagicFinder(List<MagicSquare> ms) {
		this.initialList = ms;
	}

	/**
	 * 
	 * @param squareSize
	 *            that is size of magic squares to search
	 * @param threads
	 *            thats is number of threads
	 */
	public static void generateMagicVectors(int squareSize, int threads) {
		sizeOfSquareToFind = squareSize;
		threadsNumber = threads;
		Set<int[]> magicVectors = new HashSet<>();
		MagicSquare m3 = new MagicSquare(sizeOfSquareToFind);
		int[] values = valueListForN();
		Subset subset = new Subset();
		subset.generateSubsets(values, values.length, sizeOfSquareToFind);
		for (int[] sub : subset.subsets) {
			if (m3.sum(sub) == m3.getMagicSum()) {
				Permutations p = new Permutations();
				p.permutates(sub);
				for (int[] pe : p.permutationsList) {
					magicVectors.add(pe);
				}
			}
		}
		cacheMagicVectors(magicVectors);
		search();
	}

	private static void search() {
		long startTime = System.nanoTime();
		boolean foo = true;
		while (foo) {
			Thread[] ts = new Thread[threadsNumber];
			for (int i = 0; i < threadsNumber; i++) {
				List<MagicSquare> m = getPartition(magicSquareList);
				if (m != null) {
					Thread t = new Thread(new MainMagicFinder(m));
					t.start();
					ts[i] = t;
					current += step;
				} else {
					foo = false;
				}
			}

			for (Thread t : ts) {
				if (t != null) {
					try {
						t.join();
					} catch (InterruptedException e) {
						// TODO LOGGER
						e.printStackTrace();
					}
				}
			}
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println("Time sec : " + (double) duration / 1000000000.0);
		try {
			MagicSquare.writeInFile("z://result//final-" + sizeOfSquareToFind + ".txt", finalList);
		} catch (IOException e) {
			// TODO LOGGER
			e.printStackTrace();
		}
		System.out.println("Magic squres : " + finalList.size());
	}

	private static int[] valueListForN() {
		int[] values = new int[sizeOfSquareToFind * sizeOfSquareToFind];
		for (int i = 0; i < sizeOfSquareToFind * sizeOfSquareToFind; i++) {
			values[i] = i + 1;
		}
		return values;
	}

	private static void cacheMagicVectors(Set<int[]> magicVectors) {
		for (int[] vector : magicVectors) {
			if (cache.get(vector[0]) == null) {
				cache.put(vector[0], new LinkedList<>());
			}
			cache.get(vector[0]).add(vector);
			MagicSquare m = new MagicSquare(sizeOfSquareToFind);
			m.setRow(0, vector);
			magicSquareList.add(m);
		}
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

	private static void searchForSquares(List<MagicSquare> ms) throws IOException {
		// int counter = 0;
		long startTime = System.nanoTime();
		List<MagicSquare> magicSquareList = new LinkedList<>(ms);
		Set<MagicSquare> toAdd = new HashSet<>();
		Set<MagicSquare> fin = new HashSet<>();
		for (int i = 0; i < sizeOfSquareToFind; i++) {
			for (MagicSquare m : magicSquareList) {
				int start = m.getRow(0)[i];
				for (int[] vector : cache.get(start)) {
					m.setCol(i, vector);
					if (m.noConflictsInMatrix()) {
						// counter++;
						// if (counter == 500000) {
						// System.gc();
						// counter = 0;
						// //System.out.println("GC called!");
						// }
						if (i != sizeOfSquareToFind - 1) {
							boolean good = true;
							for (int j = 1; j < sizeOfSquareToFind; j++) {
								if (m.sum(m.getRow(j)) > m.getMagicSum()) {
									good = false;
									break;
								}
							}
							if (good) {
								MagicSquare t = new MagicSquare(sizeOfSquareToFind);
								for (int c = 0; c < sizeOfSquareToFind; c++) {
									int[] col = Arrays.copyOf(m.getCol(c), m.getCol(c).length);
									t.setCol(c, col);
								}
								toAdd.add(t);
							}
						} else {
							if (m.isMagic()) {
								MagicSquare t = new MagicSquare(sizeOfSquareToFind);
								for (int c = 0; c < sizeOfSquareToFind; c++) {
									int[] col = Arrays.copyOf(m.getCol(c), m.getCol(c).length);
									t.setCol(c, col);
								}
								fin.add(t);
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
		MagicSquare.writeInFile("z://result//size-" + sizeOfSquareToFind + "-part" + getThread() + ".txt", fin);
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
	
	public static List<MagicSquare> getResult(){
		return finalList;
	}
}
