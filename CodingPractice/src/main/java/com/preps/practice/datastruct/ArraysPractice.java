package com.preps.practice.datastruct;

import java.util.Arrays;
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
		
		System.out.println(rob(new int[]{100, 5, 20, 125, 130}));
		
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
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	static int[] twoSum(int[] nums, int target) {
		int [] targetindex = new int[2];
		if(nums==null || nums.length==0){
			return targetindex;
		}
        for(int i=0;i<=nums.length-2;i++){
        	for(int j=i+1;j<=nums.length-1;j++){
        		if(nums[i]+nums[j]==target){
        			targetindex[0]=i+1;
        			targetindex[1]=j+1;
        			return targetindex;
        		}
        	}
        }
        return targetindex;
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
				tailTable[ceilIndex(tailTable, 0, len - 1, seq[i])] = seq[i];
		}
		return len;
	}
	
	static int ceilIndex(int A[], int l, int r, int key) {
		int m=0;
		boolean isMinus = false;
		while (r - l > 1) {
			m = (l + r) / 2;
			if(key>=A[m]){
				l=m;
			}else{
				r=m;
				isMinus=true;
			}
		}
		return isMinus?m-1:m+1;
	}
	
	static void increasingSubsequenceOptimal2(int[] array){
		int sz = 1;
		int c[] = new int[array.length];
		int dp[] = new int[array.length];
		
		c[1] = array[0]; /*at this point, the minimum value of the last element of the size 1 increasing sequence must be array[0]*/
		dp[0] = 1;
		for( int i = 1; i < array.length-1; i++ ) {
		   if( array[i] < c[1] ) {
		      c[1] = array[i]; /*you have to update the minimum value right now*/
		      dp[i] = 1;
		   }
		   else if( array[i] > c[sz] ) {
		      c[sz+1] = array[i];
		      dp[i] = sz+1;
		      sz++;
		   }
		   else {
		      int k = ceilIndex(c, 0, sz, array[i] ); /*you want to find k so that c[k-1]<array[i]<c[k]*/
		      c[k] = array[i];
		      dp[i] = k;
		   }
		}
		System.out.println(Arrays.toString(dp));
	}
}

