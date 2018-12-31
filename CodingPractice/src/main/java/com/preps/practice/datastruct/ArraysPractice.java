package com.preps.practice.datastruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ArraysPractice {
	
	public static void main(String[] args) {
		/*int[][] matrix = {
							{1,2,3,4,5},
							{6,7,8,9,10},
							{11,12,13,14,15},
							{16,17,18,19,20},
							{21,22,23,24,25}
						};
		printMatrix(matrix, 5);
		rotateMatrix(matrix,5);
		printMatrix(matrix, 5);*/
		
//		System.out.println(increasingSubsequenceBruteForceRec(new int[] {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}));
//		System.out.println(increasingSubsequenceDP(new int[] {0, -2, -1, 8, 4, 12, 0, 1, 2, 10, 6, 14, 1, 9, 5, 10, 3, 11, 7, 15}));
//		System.out.println(increasingSubsequenceOptimal(new int[] {0, -2, -1, 8, 4, 12, 0, 1, 2, 10, 6, 14, 1, 9, 5, 10, 3, 11, 7, 15}));
		
		System.out.println(findUnsortedSubarray(new int[]{2,6,4,8,10,9,15}));
		
	}
	
	/**
	 * http://www.careercup.com/question?id=12705676
	 * one unsorted array is given.Find out the index i and j ,j> i for which a[j] - a[i] is maximum.perform in linear time complexity
	 */
	static void testUnsortedArray(){
		findMaxAndMinInUnsortedArray(new int[]{4, 1, 9, 0, 54, 91, 3, 49});
		findMaxAndMinInUnsortedArray(new int[]{4, 1, -9, 0, 54, 91, -3, 49});
	}

	static void findMaxAndMinInUnsortedArray(int [] a){
		int min = a[0];
		int max = a[0];
		for(int i=0 ; i<a.length; i++){
			if(a[i]>max){
				max = a[i];
			}
			if(a[i]<min){
				min = a[i];
			}
		}
		System.out.println("Max : " + max + " Min: " + min +" Diff: "+ (max-min));
	}
	
	/**
	 * http://www.careercup.com/question?id=5104572540387328
	 * find no. with largest # of occurrences 
	 */
	static void testFindMaxOccurNoInArray(){
		findMaxOccurNoInArray(new int[]{1,1,2,2,2,3,3,4,4,4,4,5,5,7,7,7,7,7,7,7,7,9,9},true);
	}
	
	//
	static void findMaxOccurNoInArray(int [] a, boolean isSorted){
		if(isSorted){
			int maxCount = 1;
			int maxNum = a[0];
			int count = 1;
			int finalMaxNum = a[0];
			for(int i=1; i<a.length;i++){
				if(a[i] == maxNum){
					count++;
					if(count>maxCount){
						finalMaxNum = a[i];
						maxCount = count;
					}
				}else{
					count=1;
					maxNum = a[i];
				}
			}
			System.out.println("No with Max Occurence :"+  finalMaxNum + ", Max Count: " + maxCount);
		}else{
			
		}
	}
	
	/**
	 * 
	 * 	1	2	3	4		00	03	33	30
	 * 	5	6	7	8
	 * 	9	10	11	12
	 * 	13	14	15	16
	 *
	 *	13	9	5	1
	 *	14	10	6	2
	 * 	15	11	7	3
	 * 	16	12	8	4
	 * @param matrix
	 * @param size
	 */
	static void rotateMatrix(int [][] matrix, int size){
		for(int layer=0;layer<size/2;layer++){
			int first = layer;
			int last = size - 1 -layer;
			for(int i= first; i<last;i++){
				int offset=last-i+layer;
				int temp = matrix[layer][i]; // top to temp
				matrix[layer][i]=matrix[offset][layer]; //left to top
				matrix[offset][layer]=matrix[last][offset]; //bottom to left
				matrix[last][offset]=matrix[i][last]; // right to bottom
				matrix[i][last]=temp;
			}
		}
	}
	
	static void printMatrix(int [][] matrix, int size) {
		System.out.println("");
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				System.out.print(matrix[i][j]);
				System.out.print("\t");
			}
			System.out.println("");
		}
	}
	
	/**
	 * https://leetcode.com/problems/rotate-array/
	 * @param nums
	 * @param k
	 */
	public static void rotate(int[] nums, int k) {
        if(nums==null || nums.length==0 ||nums.length<k ||k==0){
            return ;
        }
        
        // 1 2 3 4 5 6
        // 5 6 1 2 3 4  2
        
        int[] temp = new int[nums.length];
        
        for(int i=0;i<k;i++){
            temp[i]=nums[nums.length-k+i];
        }
        for(int j=nums.length-1;j>=k;j--){
            nums[j]= nums[j-k];
        }
        
        for(int i=0; i<k;i++){
            nums[i]=temp[i];
        }
    }
	
	
	static int maxOccur(int[] num) {
		SortedMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		int count;
		int maxnum = num[0];
		int maxcount = 1;
		for (Integer n : num) {
			if (map.containsKey(n)) {
				count = map.remove(n);
				count++;
				map.put(n, count);
				if (count > maxcount) {
					maxnum = n;
					maxcount=count;
				}
				/*
				 * if(count>length/2){ return n; }
				 */
			} else {
				map.put(n, 1);
			}
		}
		return maxnum;
	}
	
	/**
	 * https://leetcode.com/problems/house-robber/ 
	 * @param num
	 * @return
	 */
	static int rob(int[] num) {
		int length = num.length;
		if (length == 0) {
			return 0;
		} else if (length == 1) {
			return num[0];
		} else if (length == 2) {
			return num[0] > num[1] ? num[0] : num[1];
		}
		int result = 0;
		int[] max = new int[length];
		max[0] = num[0];
		max[1] = num[1];
		max[2] = num[0] + num[2];
		result = max[1] > max[2] ? max[1] : max[2];
		for (int i = 3; i < length; ++i) {
			max[i] = num[i]
					+ (max[i - 2] > max[i - 3] ? max[i - 2] : max[i - 3]);
			if (max[i] > result) {
				result = max[i];
			}
		}
		return result;
	}
	
	/**
	 * https://leetcode.com/problems/3sum/#/description
	 * @param nums
	 * @return
	 */
	static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> numbers = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0)
			return numbers;

		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;

			int j = i + 1;
			int k = nums.length - 1;
			while (j < k) {
				int sum = nums[i] + nums[j] + nums[k];
				if (sum == 0) {
					List<Integer> n = new ArrayList<Integer>();
					n.add(nums[i]);
					n.add(nums[j]);
					n.add(nums[k]);
					numbers.add(n);
					j++;
					k--;
					while (nums[k] == nums[k + 1] && j < k) {
						k--;
					}
					while (nums[j] == nums[j - 1] && j < k) {
						j++;
					}
				}
				if (sum > 0) {
					k--;
				}
				if (sum < 0) {
					j++;
				}
			}
		}
		return numbers;
	}
	
	/**
	 * https://leetcode.com/problems/3sum-closest/#/description 
	 * @param nums
	 * @param target
	 * @return
	 */
	static int threeSumClosest(int[] nums, int target) {
        if(nums==null || nums.length<3)
			return 0;
		
		Arrays.sort(nums);
		int result = nums[0]+nums[1]+nums[nums.length-1];
		int minDiff = Math.abs(target - result);
		
		int i=0;
		while(i<nums.length-2){
			int j=i+1, k=nums.length-1;
			while (j<k) {
				int sum = nums[i] + nums[j] + nums[k];
				int diff = Math.abs(target - sum);
				if (diff < minDiff) {
					result = sum;
					minDiff = diff;
				}
				if (sum < target) {
					j++;
				} else {
					k--;
				}
			}
			i++;
		}
		
		return result;
    }
	
	static void merge(int A[], int m, int B[], int n) {
		 
        while(m > 0 && n > 0){
            if(A[m-1] > B[n-1]){
                A[m+n-1] = A[m-1];
                m--;
            }else{
                A[m+n-1] = B[n-1];
                n--;
            }
        }
 
        while(n > 0){
            A[m+n-1] = B[n-1];
            n--;
        }
        
        System.out.println(Arrays.toString(A));
    }
	
	/**
	 * http://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/
	 * @param array
	 */
	static void dutchFlagAlgorithm(int[] array){
		if(array==null|| array.length==0){
			return;
		}
		System.out.println(Arrays.toString(array));
		int low =0, mid =0, high = array.length-1;
		while(mid<=high){
			switch(array[mid]){
			case 0:
				swap(array, low++, mid++);
				break;
			case 1:
				mid++;
				break;
			case 2:
				swap(array, mid, high--);
				break;
			}
		}
		System.out.println(Arrays.toString(array));
	}
	
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	static int increasingSubsequenceBruteForceRec(int[] seq){
		int max = 1;
		max = findLISRec(seq, seq.length);
		return max;
	}
	
	static int findLISRec(int seq[], int n){
		if(n==1){
			return 1;
		}
		int result, maxResult=1;
		for(int i=1; i<n; i++){
			result = findLISRec(seq, i);
			if(seq[i-1] < seq[n-1] && result + 1 > maxResult){
				maxResult = result +1;
			}
		}
		return maxResult;
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
	 * Solution from : https://leetcode.com/articles/longest-increasing-subsequence/#approach-2-recursion-with-memorization-memory-limit-exceeded
	 * 
	 * @param seq
	 * @return
	 */
	static int increasingSubsequenceBinarySearch(int[] nums) {
		int[] dp = new int[nums.length];
	    int len = 0;
	    for (int num : nums) {
	        int i = Arrays.binarySearch(dp, 0, len, num);
	        if (i < 0) {
	            i = -(i+1);
	        }
	        dp[i] = num;
	        if (i == len) {
	            len++;
	        }
	    }
	    return len;
    }
	
	/**
	 * Solution From : https://leetcode.com/articles/longest-increasing-subsequence/
	 * @param nums
	 * @return
	 */
	static int increasingSubsequenceDP2(int[] nums){
		if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval+1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
	}
	
	/**
	 * https://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/
	 * @param arr
	 * @param size
	 */
	static void findRepeatingAndMissingNumber(int arr[], int size){ 
        int i; 
        System.out.print("The repeating element is "); 
  
        for (i = 0; i < size; i++) { 
            int abs_val = Math.abs(arr[i]); 
            if (arr[abs_val - 1] > 0) 
                arr[abs_val - 1] = -arr[abs_val - 1]; 
            else
                System.out.println(abs_val); 
        } 
  
        System.out.print("And the missing element is "); 
        for (i = 0; i < size; i++) { 
            if (arr[i] > 0) 
                System.out.println(i + 1); 
        } 
    } 
	
	/**
	 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
	 * @param nums
	 * @return
	 */
	
	//2,6,4,8,10,9,15
	static int findUnsortedSubarray(int[] A) {
		int n = A.length, beg = -1, end = -2, min = A[n-1], max = A[0];
		for (int i=1;i<n;i++) {
	      max = Math.max(max, A[i]);
	      min = Math.min(min, A[n-1-i]);
	      if (A[i] < max) end = i;
	      if (A[n-1-i] > min) beg = n-1-i; 
	    }
	    return end - beg + 1;
    }
}

