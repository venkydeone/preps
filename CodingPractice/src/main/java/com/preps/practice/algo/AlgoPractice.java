/**
 * 
 */
package com.preps.practice.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.udojava.evalex.Expression;

/**
 * @author Venkat P
 * 
 */
public class AlgoPractice {
	
	public static void main(String[] args) {
		splitArrayToTwoDisJointArray();
	}

	static int splitArrayToTwoDisJointArray() {
		int a[] = { 2, -1, -2, 1, -4, 2, 8 };

		int localMax = a[0], partitionIdx = 0, max = localMax;
        for (int i = 1; i < a.length; i++)
            if (localMax > a[i]) {
                localMax = max;
                partitionIdx = i;
            } else max = Math.max(max, a[i]);
        return partitionIdx + 1;
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
	 * https://leetcode.com/problems/first-bad-version/solution/
	 * @param n
	 * @return
	 */
	static int firstBadVersion(int n) {
	    int left = 1;
	    int right = n;
	    while (left < right) {
	        int mid = left + (right - left) / 2;
	        if (isBadVersion(mid)) {
	            right = mid;
	        } else {
	            left = mid + 1;
	        }
	    }
	    return left;
	}
	
	

	private static boolean isBadVersion(int mid) {
		return mid >= 2;
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
		if(index<0)
			return 0;
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
		if(index<0)
			return 0;
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
			return (char) (65 + mod) + "";
		}
		return printExcelColumnRec(index / 26) + (char) (65 + mod) + "";
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
			sb.insert(0,(char) (65 + mod));
			index = index / 26;
		}
		return sb.toString();
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
	
	static long countWays(int S[], int m, int n)
    {
        long[] table = new long[n+1];
        Arrays.fill(table, 0);  
        
        table[0] = 1;
 
        for (int i=0; i<m; i++)
            for (int j=S[i]; j<=n; j++)
                table[j] += table[j-S[i]];
 
        return table[n];
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
	 * 1. Don't convert into String 
	 * 2. Don't create reverse number and compare results.
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

	static void gcd(int x, int y) {
		int r = 0, a, b;
		a = (x > y) ? x : y; // a is greater number
		b = (x < y) ? x : y; // b is smaller number

		r = b;
		while (a % b != 0) {
			r = a % b;
			a = b;
			b = r;
		}
		System.out.println("GCD : " + r );
	}

	static void lcm(int x, int y) {
		int a;
		a = (x > y) ? x : y; // a is greater number
		int inc = a;
		while (true) {
			if (a % x == 0 && a % y == 0){
				System.out.println("LCM : " + a);
				return;
			}
			a+=inc;
		}
	}
	
	static List<Integer> lexicalDFS(int n){
        List<Integer> res = new ArrayList<Integer>();
        for(int i=1;i<10;++i){
          dfs(i, n, res); 
        }
        return res;
	}
	
    static void dfs(int cur, int n, List<Integer> res){
        if(cur>n)
            return;
        else{
            res.add(cur);
            for(int i=0;i<10;++i){
                int next = cur*10+i;
				if(next>n)
                    return;
                dfs(next, n, res);
            }
        }
    }
    
    static List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<Integer>(n);
        int curr = 1;
        for (int i = 1; i <= n; i++) {
            list.add(curr);
            if (curr * 10 <= n) {
                curr *= 10;
            } else if (curr % 10 != 9 && curr + 1 <= n) {
                curr++;
            } else {
                while ((curr / 10) % 10 == 9) {
                    curr /= 10;
                }
                curr = curr / 10 + 1;
            }
        }
        return list;
    }
    
    static List<Integer> lexicalOrderComparator(int n) {
        List<Integer> list = new ArrayList<Integer>(n);
        for(int i=1;i<=n;i++){
        	list.add(i);
        }
        
        Collections.sort(list, new Comparator<Integer>() {
        	public int compare(Integer o1, Integer o2) {
        		return String.valueOf(o1).compareTo(String.valueOf(o2));
        	}
		});
        return list;
    }
    
    /**
     * Quick Select 
     * @param nums
     * @param k
     * @return
     */
    static int findKthLargest(int[] nums, int k) {
    	System.err.println(Arrays.toString(nums));
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int pivot = partition(nums, start, end);
            if (k < (nums.length-pivot)) {
            	start = pivot+1; 
            }
            else if (k > (nums.length-pivot)) {
            	end = pivot-1;
            }
            else
            	return nums[pivot];
        }
        return nums[start];
    }
    //5, 8, 4, 1, 2, 9
    static int partition(int[] nums, int start, int end) {
        int pivot = start, temp;
        while (start <= end) {
            while (start <= end && nums[start] <= nums[pivot]) {
            	start++;
            }
            while (start <= end && nums[end] > nums[pivot]) {
            	end--;
            }
            if (start > end) break;
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
        temp = nums[end];
        nums[end] = nums[pivot];
        nums[pivot] = temp;
        System.err.println(Arrays.toString(nums));
        return end;
    }
    
    /**
     * https://leetcode.com/problems/single-number-ii/
     * 
     * @param A
     * @return
     */
    static int singleNumber(int[] A) {
    	int ones = 0, twos = 0;
        for(int i = 0; i < A.length; i++){
            ones = (ones ^ A[i]) & ~twos;
            twos = (twos ^ A[i]) & ~ones;
        }
        throw new RuntimeException();
    }
    /**
     * Easy to understand than above
     * https://leetcode.com/problems/single-number-ii/discuss/43297/Java-O(n)-easy-to-understand-solution-easily-extended-to-any-times-of-occurance
     * @param nums
     * @return
     */
    static int singleNumber2(int[] nums) {
        int ans = 0;
        for(int i = 0; i < 32; i++) {
            int sum = 0;
            for(int j = 0; j < nums.length; j++) {
                if(((nums[j] >> i) & 1) == 1) {
                    sum++;
                    sum %= 3;
                }
            }
            if(sum != 0) {
                ans |= sum << i;
            }
        }
        return ans;
    }
   
    static class SortingAlgorithms{
    	
    	static class CountingSort{
    		public static void main(String[] args) {
				sortPositiveNumber(new int[]{1,4,2,1,5,2,7});
				sortWithNegativeNumber(new int[]{1,-4,2,1,5,-2,7,-2});
			}
    		
    		static void sortPositiveNumber(int[] nums){ //[1,4,2,1,5,2,7]
    	    	if(nums==null || nums.length==0)
    	    		return;
    	    	int[] count  = new int[10]; // for char array sort, change this to 256 
    	    	System.out.println("Before Sort: " + Arrays.toString(nums));    	
    	    	
    	    	for(int n : nums){
    	    		count[n]++; //0,2,2,0,1,1,0,1
    	    	}
    	    	
    	    	for(int i=1;i<count.length;i++){
    	    		count[i]+=count[i-1]; //0,2,4,4,5,6,7,7
    	    	}
    	    	
    	    	int result[] = new int[nums.length];
    	    	for(int i=0;i<nums.length;i++){
    	    		result[count[nums[i]]-1]= nums[i];
    	    		count[nums[i]]--;
    	    	}
    	    	System.out.println("After Sort: " + Arrays.toString(result));
    		}
    		
    		static void sortWithNegativeNumber(int[] nums){ //[1,-4,2,1,5,-2,7,-2]
    	    	if(nums==null || nums.length==0)
    	    		return;
    	    	
    	    	int max = Integer.MIN_VALUE;
    	    	int min = Integer.MAX_VALUE;
    	    	
    	    	for(int n : nums){
    	    		if(n > max){
    	    			max = n;
    	    		}
    	    	}
    	    	
    	    	for(int n : nums){
    	    		if(n < min){
    	    			min = n;
    	    		}
    	    	}
    	    	
    	    	int[] count  = new int[max-min+1]; // for char array sort, change this to 256 
    	    	System.out.println("Before Sort: " + Arrays.toString(nums));    	
    	    	
    	    	for(int n : nums){
    	    		count[n-min]++; //0,2,2,0,1,1,0,1
    	    	}
    	    	
    	    	for(int i=1;i<count.length;i++){
    	    		count[i]+=count[i-1]; //0,2,4,4,5,6,7,7
    	    	}
    	    	
    	    	int result[] = new int[nums.length];
    	    	for(int i=0;i<nums.length;i++){
    	    		result[count[nums[i]-min]-1]= nums[i];
    	    		count[nums[i]-min]--;
    	    	}
    	    	System.out.println("After Sort: " + Arrays.toString(result));
    		}
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

    	static class HeapSort {
    		private static int total;

    		private static void swap(Integer[] arr, int a, int b) {
    			Integer tmp = arr[a];
    			arr[a] = arr[b];
    			arr[b] = tmp;
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
    			System.out.println(Arrays.toString(arr));
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
  
    
	
	
	static class MathCalc{
		private static final Integer EXPECTED_OUTPUT=42;
		public static void main(String[] args) throws IOException {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String input=null;
			Integer[] inputNumbers = new Integer[5];
			String[] operators = {"*", "/", "+", "-"};
			while( (input= bufferedReader.readLine())!=null){
				String[] split = input.split("\\s+");
				int i=0;
				for(String in : split){
					try{
						inputNumbers[i++]=(Integer.parseInt(in));
						//System.out.println(Arrays.toString(inputNumbers));
					}catch(NumberFormatException ex){
						System.err.println("Invalid Input");
					}
				}
				
				HashSet<String> operatorCombi = new HashSet<String>();
				combination(4, operators, "", operatorCombi);
				
				ArrayList<ArrayList<Integer>> shuffledNumbers = new ArrayList<ArrayList<Integer>>();
				permute(inputNumbers, 0, shuffledNumbers);
				
				evalExpression(shuffledNumbers,operatorCombi);
			}
			
		}		

		private static void evalExpression(
				ArrayList<ArrayList<Integer>> shuffledNumbers,
				HashSet<String> output) {
			for(ArrayList<Integer> numbers : shuffledNumbers){
				for(String expr : output){
					if(eval(numbers, expr)){
						return;
					}
				}
			}
		}

		private static boolean eval(ArrayList<Integer> numbers, String expr) {
			StringBuilder sb = new StringBuilder();
			int i=0;
			for(Integer num : numbers){
				sb.append(num);
				if(i<expr.length()){
					sb.append(expr.charAt(i++));
				}
			}
			Expression expression = new Expression(sb.toString());
			BigDecimal result = expression.eval();
			boolean evalResult = result.toBigInteger().intValue() == EXPECTED_OUTPUT;
			if(evalResult){
				System.out.println(sb.toString());
			}
			return evalResult;
		}

		static void permute(Integer[] a, int k, ArrayList<ArrayList<Integer>> shuffledNumbers)	{
			if (k == a.length)
			{
				ArrayList<Integer> in = new ArrayList<Integer>();
				for (int i = 0; i < a.length; i++)
				{
					in.add(a[i]);
				}
				shuffledNumbers.add(in);
			}
			else
			{
				for (int i = k; i < a.length; i++)
				{
					int temp = a[k];
					a[k] = a[i];
					a[i] = temp;
					permute(a, k + 1, shuffledNumbers);
					temp = a[k];
					a[k] = a[i];
					a[i] = temp;
				}
			}
		}
		
		private static void combination(int maxLength, String[] alphabet, String curr, HashSet<String> output) {
	        // If the current string has reached it's maximum length
	        if(curr.length() == maxLength) {
	            output.add(curr);
	        // Else add each letter from the alphabet to new strings and process these new strings again
	        } else {
	            for(int i = 0; i < alphabet.length; i++) {
	                String oldCurr = curr;
	                curr += alphabet[i];
	                combination(maxLength,alphabet,curr,output);
	                curr = oldCurr;
	            }
	        }
	    }
	}
}

