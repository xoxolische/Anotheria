package magic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Subset {

	public Set<int[]> subsets;

	public Subset() {
		subsets = new HashSet<>();
	}

	/*
	 * arr[] ---> Input Array data[] ---> Temporary array to store current
	 * combination start & end ---> Staring and Ending indexes in arr[] index --->
	 * Current index in data[] r ---> Size of a combination to be printed
	 */
	void combinationUtil(int arr[], int n, int r, int index, int data[], int i) {
		// Current combination is ready to be printed,
		// print it
		if (index == r) {
			subsets.add(Arrays.copyOf(data, data.length));
			// for (int j = 0; j < r; j++)
			// System.out.print(data[j] + " ");
			// System.out.println("");
			return;
		}

		// When no more elements are there to put in data[]
		if (i >= n)
			return;

		// current is included, put next at next
		// location
		data[index] = arr[i];
		combinationUtil(arr, n, r, index + 1, data, i + 1);

		// current is excluded, replace it with
		// next (Note that i+1 is passed, but
		// index is not changed)
		combinationUtil(arr, n, r, index, data, i + 1);
	}

	// The main function that prints all combinations
	// of size r in arr[] of size n. This function
	// mainly uses combinationUtil()
	/**
	 * Generates subsets given int array
	 * @param arr - set of values
	 * @param n - size of array
	 * @param r - size of subset array
	 */
	public void generateSubsets(int arr[], int n, int r) {
		// A temporary array to store all combination
		// one by one
		int data[] = new int[r];

		// Print all combination using temprary
		// array 'data[]'
		combinationUtil(arr, n, r, 0, data, 0);
	}

	/*
	 * 1. fill 1st row. Take all possible subsets, permutate sets, check if correct.
	 *
	/* Driver function to check for above function */
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