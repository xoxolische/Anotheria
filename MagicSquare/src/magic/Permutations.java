package magic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class for array permutations
 * 
 * @author Nikita Pavlov
 *
 */
public class Permutations {

	static int counter = 0;
	public List<int[]> permutationsList;

	public Permutations() {
		permutationsList = new ArrayList<>();
	}

	public static void main(String[] args) {

		long start = System.currentTimeMillis();

		// 5! each = 120
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }; // 1
		// int[] arr1 = { 6, 7, 8, 9, 10 }; // 2
		// int[] arr2 = { 11, 12, 13, 14, 15 }; // 3
		// int[] arr3 = { 16, 17, 18, 19, 20 }; // 4
		// int[] arr4 = { 21, 22, 23, 24, 25 }; // 5
		//
		Permutations s = new Permutations();

		// s.list.add(arr1);
		// s.list.add(arr2);
		// s.list.add(arr3);
		// s.list.add(arr4);
		// printArr(null, arr);
		s.shifter(new int[] {}, arr);
		// s.shifter(new int[] {}, arr1);
		// s.shifter(new int[] {}, arr2);
		// s.shifter(new int[] {}, arr3);
		// s.shifter(new int[] {}, arr4);
		long end = System.currentTimeMillis();
		for (int[] i : s.permutationsList) {
			System.out.println(Arrays.toString(i));
		}
		System.out.println(
				"Minutes : " + TimeUnit.MILLISECONDS.toMinutes(end - start) + "; L - " + s.permutationsList.size());
	}

	/**
	 * Fills the list with int[] arrays of permutations
	 * 
	 * @param arrTail
	 */
	public void permutates(int[] arrTail) {
		this.permutationsList.add(arrTail);
		this.shifter(new int[] {}, arrTail);
	}

	private void shifter(int[] arrHead, int[] arrTail) {

		if (arrTail.length <= 1) {
			return;
		}

		for (int i = 0; i < arrTail.length; i++) {
			int[] arrBuf = arrTail.clone();
			shiftOn(arrBuf, i);
			int[] arrNewHead = incrementArr(arrHead, arrBuf[0]);
			int[] arrNewTail = decrementArr(arrBuf);
			if (i != 0) {
				int[] a = concat(arrNewHead, arrNewTail);
				this.permutationsList.add(a);
			}

			shifter(arrNewHead, arrNewTail);
		}
	}

	private void shiftOn(int[] arr, int shift) {
		if (shift % arr.length != 0) {
			reverse(arr, 0, shift - 1);
			reverse(arr, shift, arr.length - 1);
			reverse(arr, 0, arr.length - 1);
		}
	}

	private int[] decrementArr(int[] arr) {

		int[] arrNext = new int[arr.length - 1];
		for (int item = 0; item < arrNext.length; item++) {
			arrNext[item] = arr[item + 1];
		}
		return arrNext;
	}

	private int[] incrementArr(int[] arr, int num) {
		int[] arrNext = new int[arr.length + 1];
		for (int item = 0; item < arr.length; item++) {
			arrNext[item] = arr[item];
		}
		arrNext[arr.length] = num;
		return arrNext;
	}

	private void reverse(int[] arrBuf, int first, int second) {
		int buf;

		int firstCycleElement = first % arrBuf.length;
		int secondCycleElement = second % arrBuf.length;

		buf = arrBuf[firstCycleElement];
		arrBuf[firstCycleElement] = arrBuf[secondCycleElement];
		arrBuf[secondCycleElement] = buf;
	}

	private int[] concat(int[] arrHead, int[] arrTail) {
		int[] res = null;
		if (arrHead == null) {
			if (arrTail == null) {

			} else {
				res = Arrays.copyOf(arrTail, arrTail.length);
			}
		} else {
			if (arrTail == null) {
				res = Arrays.copyOf(arrHead, arrHead.length);
			} else {
				res = new int[arrHead.length + arrTail.length];
				for (int i = 0; i < arrHead.length; i++) {
					res[i] = arrHead[i];
				}
				for (int i = 0; i < arrTail.length; i++) {
					res[arrHead.length + i] = arrTail[i];
				}
			}
		}

		return res;
	}
}