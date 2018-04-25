package magic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for array subsets
 * 
 * @author Nikita Pavlov
 *
 */
public class Subset {

	public Set<int[]> subsets;

	public Subset() {
		subsets = new HashSet<>();
	}

	private void combinationUtil(int arr[], int n, int r, int index, int data[], int i) {
		if (index == r) {
			subsets.add(Arrays.copyOf(data, data.length));
			return;
		}
		if (i >= n)
			return;
		data[index] = arr[i];
		combinationUtil(arr, n, r, index + 1, data, i + 1);
		combinationUtil(arr, n, r, index, data, i + 1);
	}

	/**
	 * Generates subsets given int array
	 * 
	 * @param arr
	 *            - set of values
	 * @param n
	 *            - size of array
	 * @param r
	 *            - size of subset array
	 */
	public void generateSubsets(int arr[], int n, int r) {
		int data[] = new int[r];
		combinationUtil(arr, n, r, 0, data, 0);
	}

	public static void main(String[] args) {
		int[] a = new int[23];
		for (int i = 0; i < a.length; i++) {
			a[i] = i;
		}
		int r = 4;
		int n = a.length;
		Subset p = new Subset();
		p.generateSubsets(a, n, r);

	}

}