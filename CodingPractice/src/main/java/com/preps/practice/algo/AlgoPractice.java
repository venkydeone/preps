/**
 * 
 */
package com.preps.practice.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Venkat P
 * 
 */
public class AlgoPractice {

	public static void main(String[] args) {
		System.out.println(palindromeNumberWithConstraints(12321));
	}

	/**
	 * 
	 * @see <a href="http://www.careercup.com/question?id=16759664">Find the
	 *      shortest range in the list of numbers</a>
	 * 
	 * @Description
	 * 
	 *              You have k lists of sorted integers. Find the smallest range
	 *              that includes at least one number from each of the k lists.
	 *              For example, List 1: [4, 10, 15, 24, 26] List 2: [0, 9, 12,
	 *              20] List 3: [5, 18, 22, 30] The smallest range here would be
	 *              [20, 24] as it contains 24 from list 1, 20 from list 2, and
	 *              22 from list 3.
	 * 
	 */
	static void findRangeAcrossNumbersList() {
		boolean firstRun = true;
		int lengthExceeded = 0;
		int bestMax = Integer.MIN_VALUE;
		int bestMin = Integer.MAX_VALUE;
		int bestRange = Integer.MAX_VALUE;
		int currentMax = 0;
		List<Integer> finalList = new ArrayList<Integer>();
		List<Integer> currentList = new ArrayList<Integer>();

		Integer[][] twoDArray = { new Integer[] { 4, 10, 15, 19, 24, 26 },
				new Integer[] { 0, 9, 12, 17, 21, 27 },
				new Integer[] { 5, 14, 18, 22, 30 },
				new Integer[] { 31, 34, 50, 55 } };
		for (int i = 0; lengthExceeded < twoDArray.length; i++) {
			for (int j = 0; j < twoDArray.length; j++) {
				if (i >= twoDArray[j].length) {
					lengthExceeded++;
					continue;
				}
				Integer currentVal = twoDArray[j][i];
				if (firstRun) {
					finalList.add(j, currentVal);
				} else {
					Integer oldValue = finalList.get(j);
					if ((currentMax > 0 && oldValue == currentMax)
							|| (currentMax == 0 && oldValue == bestMax)) {
						continue;
					} else {
						currentList.remove(j);
						currentList.add(j, currentVal);
						BestElement bestElement = findBestElement(currentList);
						currentMax = bestElement.getMax();
						if (bestElement.getRange() <= bestRange) {
							bestRange = bestElement.getRange();
							bestMin = bestElement.getMin();
							bestMax = bestElement.getMax();
							copyList(finalList, currentList);
						}
					}
				}
			}
			if (firstRun) {
				BestElement findBestElement = findBestElement(finalList);
				bestMax = findBestElement.getMax();
				bestMin = findBestElement.getMin();
				bestRange = findBestElement.getRange();
				copyList(currentList, finalList);
			}
			firstRun = false;
		}

		System.err.println("Best Range is.... ");
		System.err.println("Best Max : " + bestMax + " ; " + " Best Min : "
				+ bestMin + " ; " + " Best Range  : " + bestRange + " ; ");
	}

	private static void copyList(List<Integer> currentList,
			List<Integer> finalList) {
		currentList.clear();
		for (int i = 0; i < finalList.size(); i++) {
			currentList.add(i, finalList.get(i));
		}
	}

	private static BestElement findBestElement(List<Integer> finalList) {
		int newRange = Integer.MAX_VALUE;
		int prevMin = Integer.MAX_VALUE;
		int prevMax = Integer.MIN_VALUE;
		for (int i = 0; i < finalList.size(); i++) {
			Integer currentValue = finalList.get(i);
			if (currentValue > prevMax) {
				prevMax = currentValue;
			}
			if (currentValue < prevMin) {
				prevMin = currentValue;
			}
		}
		if (prevMin != prevMax && (prevMax - prevMin) < newRange) {
			newRange = prevMax - prevMin;
		}
		System.out.println(" Best Max : " + prevMax + " ; " + " Best Min : "
				+ prevMin + " ; " + " Best Range  : " + newRange + " ; ");
		return new BestElement(prevMin, prevMax, newRange);
	}

	public static class BestElement {

		public BestElement(int min, int max, int range) {
			super();
			this.min = min;
			this.max = max;
			this.range = range;
		}

		private int min;

		public int getMin() {
			return min;
		}

		public void setMin(int min) {
			this.min = min;
		}

		public int getMax() {
			return max;
		}

		public void setMax(int max) {
			this.max = max;
		}

		public int getRange() {
			return range;
		}

		public void setRange(int range) {
			this.range = range;
		}

		private int max;
		private int range;
	}

	static int splitArrayToTwoDisJointArray() {
		int val[] = { 2, -1, -2, 1, -4, 2, 8 };

		int maxsum = 0;
		int maxEndingHere = 0;

		boolean wholeArray = true;
		for (int i = 0; i < val.length; i++) {
			maxEndingHere = Math.max(maxEndingHere + val[i], 0);

			if (maxEndingHere == 0)
				wholeArray = false;

			maxsum = Math.max(maxsum, maxEndingHere);
		}
		if (wholeArray && maxsum > 0)
			return maxsum;

		int minsum = 0;
		int minEndingHere = 0;
		for (int i = 0; i < val.length; i++) {
			minEndingHere = Math.min(minEndingHere + val[i], 0);

			minsum = Math.min(minsum, minEndingHere);
		}

		return maxsum - minsum;
	}

	/**
	 * Fibonacci Series
	 * 
	 * @param limit
	 */
	static void printFibo(int limit) {
		int a = 1, b = 1;
		int sum;
		System.out.println(a);
		System.out.println(b);
		for (int i = 2; i <= limit; i++) {
			sum = a + b;
			a = b;
			b = sum;
			System.out.println(sum);
		}
	}

	/**
	 * * Fibonacci Series Recursion
	 * 
	 * @param index
	 * @return
	 */
	static int printFiboRec(int index) {
		if (index <= 1) {
			return 1;
		}
		return printFiboRec(index - 1) + printFiboRec(index - 2);
	}

	/**
	 * Factorial
	 * 
	 * @param index
	 * @return
	 */
	static int fact(int index) {
		if (index <= 1) {
			return 1;
		}
		int fact = 1;
		for (int i = 1; i <= index; i++) {
			fact *= i;
		}
		return fact;
	}

	/**
	 * Factorial Recursion
	 * 
	 * @param index
	 * @return
	 */
	static int factRec(int index) {
		if (index <= 1) {
			return 1;
		}
		return index * fact(index - 1);
	}

	/**
	 * https://leetcode.com/problems/excel-sheet-column-title/
	 * 
	 * @param index
	 * @return
	 */
	static String printExcelColumnRec(int index) {// 2
		index--;
		int mod = index % 26;
		if (index / 26 == 0) {
			return (char) (97 + mod) + "";
		}
		return printExcelColumnRec(index / 26) + (char) (97 + mod) + "";
	}

	/**
	 * https://leetcode.com/problems/excel-sheet-column-title/
	 * 
	 * @param index
	 * @return
	 */
	static String printExcelColumn(int index) {
		StringBuilder sb = new StringBuilder();
		while (index > 0) {
			index--;
			int mod = index % 26;
			sb.append((char) (97 + mod));
			index = index / 26;
		}
		return sb.reverse().toString();
	}

	/**
	 * https://leetcode.com/problems/number-of-1-bits/
	 * 
	 * @param n
	 * @return
	 */
	static int hammingWeight(int n) {
		int count = 0;
		for (int i = 1; i < 33; i++) {
			if ((n & (1 << i)) != 0) {
				count++;
			}
		}
		return count;
	}

	/**
	 * https://leetcode.com/problems/reverse-bits/
	 * 
	 * @param n
	 * @return
	 */
	static int reverseBits(int n) {
		int result = 0;
		for (int i = 0; i < 32; i++) {
			if (((1 << i) & n) != 0)
				result |= 1 << (31 - i);
		}
		return result;
	}

	/**
	 * https://leetcode.com/problems/factorial-trailing-zeroes/
	 * 
	 * @param n
	 * @return
	 */
	static int findTrailingZeros(int n) {
		int count = 0;

		while (n > 0) {
			n = n / 5;
			count += n;
		}
		return count;
	}

	static int pal(int x) {

		int n = x;
		long rev = 0;
		while (n != 0) {
			rev = (long) rev * 10 + n % 10;
			n = n / 10;
		}

		return (int) rev;
	}

	static List<List<Integer>> pascal(int num) {
		List<List<Integer>> pascals = new ArrayList<List<Integer>>();
		if (num == 0) {
			return pascals;
		}
		int start = 1, end = 1;
		List<Integer> list = new ArrayList<Integer>();
		list.add(start);
		pascals.add(list);
		if (num == 1) {
			return pascals;
		}
		list = new ArrayList<Integer>();
		list.add(start);
		list.add(end);
		pascals.add(list);
		if (num == 2) {
			return pascals;
		}
		for (int i = 3; i <= num; i++) {
			list = new ArrayList<Integer>();
			list.add(start);
			for (int j = 1; j <= i - 2; j++) {
				list.add(pascals.get(i - 2).get(j - 1)
						+ pascals.get(i - 2).get(j));
			}
			list.add(end);
			pascals.add(list);
		}
		return pascals;
	}

	static class MergeSort {
		private int[] array;
		private int[] tempMergArr;
		private int length;

		public static void main(String a[]) {
			int[] inputArr = { 25, 8, 90, 1, 54, 9, 23, 4, 9, 5 };
			MergeSort mms = new MergeSort();
			mms.sort(inputArr);
			System.out.println(Arrays.toString(inputArr));
		}

		public void sort(int inputArr[]) {
			this.array = inputArr;
			this.length = inputArr.length;
			this.tempMergArr = new int[length];
			doMergeSort(0, length - 1);
		}

		private void doMergeSort(int lowerIndex, int higherIndex) {

			if (lowerIndex < higherIndex) {
				int middle = (lowerIndex + higherIndex) / 2;
				doMergeSort(lowerIndex, middle);
				doMergeSort(middle + 1, higherIndex);
				mergeParts(lowerIndex, middle, higherIndex);
			}
		}

		private void mergeParts(int lowerIndex, int middle, int higherIndex) {

			for (int i = lowerIndex; i <= higherIndex; i++) {
				tempMergArr[i] = array[i];
			}
			int low = lowerIndex;
			int mid = middle + 1;
			int k = lowerIndex;
			while (low <= middle && mid <= higherIndex) {
				if (tempMergArr[low] <= tempMergArr[mid]) {
					array[k] = tempMergArr[low];
					low++;
				} else {
					array[k] = tempMergArr[mid];
					mid++;
				}
				k++;
			}
			while (low <= middle) {
				array[k] = tempMergArr[low];
				k++;
				low++;
			}

		}
	}

	static class QuickSort {
		int[] array;

		public static void main(String[] args) {
			QuickSort qs = new QuickSort();
			int[] input = { 3, 7, 8, 5, 2, 1, 9, 5, 4 };
			System.out.println(Arrays.toString(input));
			qs.array = input;
			qs.sort(0, qs.array.length - 1);
			System.out.println(Arrays.toString(qs.array));
		}

		void sort(int low, int high) {
			int mid = (low + high) / 2;
			int pivot = array[mid];

			int i = low, j = high;

			while (i <= j) {
				while (array[i] < pivot) {
					i++;
				}
				while (array[j] > pivot) {
					j--;
				}
				if (i <= j) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
					i++;
					j--;
					System.out.println(Arrays.toString(array));
				}
			}
			if (low < j) {
				sort(low, j);
			}
			if (i < high) {
				sort(i, high);
			}

		}
	}

	/**
	 * 
	 * @param num
	 * @return
	 */
	static boolean isPalindromeNumber(int num) {
		if (num < 10) {
			return true;
		}
		int prevNum = num;
		int newNum = 0;
		while (num > 0) {
			newNum = newNum * 10 + num % 10;
			num = num / 10;
		}
		return newNum == prevNum;
	}

	/**
	 * 
	 * @param x
	 * @return
	 */
	static int reverse(int x) {
		if (x > 0) {
			long rev = 0;
			while (x > 0) {
				rev = rev * 10 + x % 10;
				x = x / 10;
			}
			return rev > Integer.MAX_VALUE ? 0 : (int) rev;
		} else {
			long rev = 0;
			while (x != 0) {
				rev = rev * 10 + x % 10;
				x = x / 10;
			}
			return rev < Integer.MIN_VALUE ? 0 : (int) rev;
		}
	}

	static int countWays(int n) {
		if (n < 0)
			return 0;
		else if (n == 0)
			return 1;
		else
			return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
	}

	static int increasingSubsequenceDP(int[] seq) {
		int length = seq.length;
		int[] L = new int[length];
		for (int i = 0; i < length; i++)
			L[i] = 1;

		// 0, -2, -1, 8, 4, 12, 0, 1, 2, 10, 6, 14, 1, 9, 5, 10, 3, 11, 7, 15
		for (int i = 1; i < length; i++) {
			for (int j = 0; j < i; j++) {
				if (seq[j] < seq[i] && L[j] + 1 > L[i]) {
					L[i] = L[j] + 1;
				}
			}
		}

		int maxi = 0;
		for (int i = 0; i < length; i++) {
			if (L[i] > maxi) {
				maxi = L[i];
			}
		}
		return maxi;
	}

	/**
	 * http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence
	 * -size-n-log-n/
	 * 
	 * @param seq
	 * @return
	 */
	static int increasingSubsequenceOptimal(int[] seq) {
		int[] tailTable = new int[seq.length];
		int size = seq.length;
		tailTable[0] = seq[0];
		int len = 1;
		for (int i = 1; i < size; i++) {
			if (seq[i] < tailTable[0])
				// new smallest value
				tailTable[0] = seq[i];
			else if (seq[i] > tailTable[len - 1])
				// A[i] wants to extend largest subsequence
				tailTable[len++] = seq[i];
			else
				// A[i] wants to be current end candidate of an existing
				// subsequence
				// It will replace ceil value in tailTable
				tailTable[ceilIndex(tailTable, -1, len - 1, seq[i])] = seq[i];
		}
		return len;
	}

	static int ceilIndex(int A[], int l, int r, int key) {
		int m;
		while (r - l > 1) {
			m = (l + r) / 2;
			m = (A[m] >= key ? r : l);
		}
		return r;
	}

	/**
	 * https://leetcode.com/problems/happy-number/
	 * 
	 * @param n
	 * @return
	 */
	static boolean isHappy(int n) {
		if (n < 1)
			return false;
		if (n == 1)
			return true;
		Set<Integer> result = new HashSet<Integer>();
		int sqNo = squareNumber(n);

		while (!result.contains(sqNo)) {
			if (sqNo == 1) {
				return true;
			}
			result.add(sqNo);
			sqNo = squareNumber(sqNo);
		}
		return false;
	}

	static int squareNumber(int n) {
		if (n == 0) {
			return 0;
		}
		int sqNo = 0;
		while (n > 0) {
			int num = n % 10;
			n = n / 10;
			sqNo = sqNo + num * num;
		}
		System.out.println(sqNo);
		return sqNo;
	}

	/**
	 * Check if number is Palindrome. Constraints:
	 * 
	 * 1. Don't convert into String 2. Don't create reverse number and compare
	 * results.
	 * 
	 * @param number
	 * @return
	 */
	static int palindromeNumberWithConstraints(int x) {
		if (x < 0)
			return 0;
		int div = 1;
		while (x / div >= 10) {
			div *= 10;
		}

		while (x != 0) {
			int l = x / div;
			int r = x % 10;
			if (l != r)
				return 0;
			x = (x % div) / 10;
			div /= 100;
		}
		return 1;
	}

	static class HeapSort {
		private static int total;

		private static void swap(Integer[] arr, int a, int b) {
			Integer tmp = arr[a];
			arr[a] = arr[b];
			arr[b] = tmp;
			System.out.println(Arrays.toString(arr));
		}

		private static void heapify(Integer[] arr, int i) {
			int lft = i * 2;
			int rgt = lft + 1;
			int grt = i;

			if (lft <= total && arr[lft] > arr[grt])
				grt = lft;
			if (rgt <= total && arr[rgt] > arr[grt])
				grt = rgt;
			if (grt != i) {
				swap(arr, i, grt);
				heapify(arr, grt);
			}
		}

		public static void sort(Integer[] arr) {
			total = arr.length - 1;

			for (int i = total / 2; i >= 0; i--)
				heapify(arr, i);

			for (int i = total; i > 0; i--) {
				swap(arr, 0, i);
				total--;
				heapify(arr, 0);
			}
		}

		public static void main(final String[] args) {
			Integer[] arr = new Integer[] { 3, 2, 1, 5, 4, 9, 11, 8, 15, 22,
					17, 10 };
			System.out.println(Arrays.toString(arr));
			sort(arr);
			System.out.println(Arrays.toString(arr));
		}
	}
}
