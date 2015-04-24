package com.preps.algo.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgoPractice {
	
	public static void main(String[] args) {
		System.out.println("Main");
	}
	
	/**
	 * 
	 * @see <a href="http://www.careercup.com/question?id=16759664">Find the shortest range in the list of numbers</a>
	 * 
	 * @Description
	 * 
	 * You have k lists of sorted integers. Find the smallest range that includes at least one number from each of the k lists.
	 * For example,
	 * List 1: [4, 10, 15, 24, 26]
	 * List 2: [0, 9, 12, 20]
	 * List 3: [5, 18, 22, 30]
	 * The smallest range here would be [20, 24] as it contains 24 from list 1, 20 from list 2, and 22 from list 3.
	 * 
	 */
	static void findRangeAcrossNumbersList(){
		boolean firstRun = true;
		int lengthExceeded=0;
		int bestMax = Integer.MIN_VALUE;
		int bestMin = Integer.MAX_VALUE;
		int bestRange = Integer.MAX_VALUE;
		int currentMax = 0;
		List<Integer> finalList = new ArrayList<Integer>();
		List<Integer> currentList = new ArrayList<Integer>();
		
		Integer[][] twoDArray= {
								new Integer[]{4, 10, 15, 19, 24, 26},
								new Integer[]{0, 9, 12, 17, 21, 27},
								new Integer[]{5, 14, 18, 22, 30},
								new Integer[]{31, 34, 50, 55}
								};
		for(int i=0;lengthExceeded<twoDArray.length;i++){
			for(int j=0;j<twoDArray.length;j++){
				if(i>=twoDArray[j].length){
					lengthExceeded++;
					continue;
				}
				Integer currentVal = twoDArray[j][i];
				if(firstRun){
					finalList.add(j, currentVal);
				}else{
					Integer oldValue = finalList.get(j);
					if((currentMax > 0 && oldValue == currentMax) || (currentMax ==0 && oldValue==bestMax)){
						continue;
					}else{
						currentList.remove(j);
						currentList.add(j, currentVal);
						BestElement bestElement = findBestElement(currentList);
						currentMax = bestElement.getMax();
						if(bestElement.getRange()<=bestRange){
							bestRange=bestElement.getRange();
							bestMin = bestElement.getMin();
							bestMax = bestElement.getMax();
							copyList(finalList, currentList);
						}
					}
				}
			}
			if(firstRun){
				BestElement findBestElement = findBestElement(finalList);
				bestMax  = findBestElement.getMax();
				bestMin = findBestElement.getMin();
				bestRange = findBestElement.getRange();
				copyList(currentList, finalList);
			}
			firstRun = false;
		}
		
		System.err.println("Best Range is.... ");
		System.err.println( "Best Max : " + bestMax + " ; " +  " Best Min : " + bestMin + " ; " + " Best Range  : " + bestRange + " ; ");
	}
	
	private static void copyList(List<Integer> currentList,
			List<Integer> finalList) {
		currentList.clear();
		for(int i=0;i<finalList.size();i++){
			currentList.add(i, finalList.get(i));
		}
	}


	private static BestElement findBestElement(List<Integer> finalList) {
		int newRange = Integer.MAX_VALUE;
		int prevMin = Integer.MAX_VALUE;
		int prevMax = Integer.MIN_VALUE;
		for(int i=0;i<finalList.size();i++){
			Integer currentValue = finalList.get(i);
			if(currentValue>prevMax){
				prevMax=currentValue;
			}
			if(currentValue<prevMin){
				prevMin = currentValue;
			}
		}
		if(prevMin!=prevMax && (prevMax-prevMin)<newRange){
			newRange= prevMax-prevMin;
		}
		System.out.println( " Best Max : " + prevMax + " ; " +  " Best Min : " + prevMin + " ; " + " Best Range  : " + newRange + " ; ");
		return new BestElement(prevMin, prevMax, newRange);
	}
	
	public static class BestElement{
		
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
	
	static int splitArrayToTwoDisJointArray(){
		int val[] = {2, -1, -2, 1, -4, 2, 8};
		
		int maxsum = 0;
		int maxEndingHere = 0;

	        boolean wholeArray = true;
		for (int i = 0; i < val.length; i++) {
			maxEndingHere = Math.max(maxEndingHere + val[i], 0);
			
	                if (maxEndingHere == 0) wholeArray = false;

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
	 * @param limit
	 */
	static void printFibo(int limit){
		int a=1, b=1;
		int sum;
		System.out.println(a);
		System.out.println(b);
		for(int i=2; i<=limit; i++){
			sum = a+b;
			a=b;
			b=sum;
			System.out.println(sum);
		}
	}
	
	/**
	 * 	 * Fibonacci Series Recursion
	 * @param index
	 * @return
	 */
	static int printFiboRec(int index){
		if(index<=1){
			return 1;
		}
		return printFiboRec(index-1) + printFiboRec(index-2);
	}
	
	/**
	 * Factorial
	 * @param index
	 * @return
	 */
	static int fact(int index){
		if(index<=1){
			return 1;
		}
		int fact =1;
		for(int i=1;i<=index;i++){
			fact*=i;
		}
		return fact;
	}
	
	/**
	 * Factorial Recursion
	 * @param index
	 * @return
	 */
	static int factRec(int index){
		if(index<=1){
			return 1;
		}
		return index*fact(index-1);
	}
	
	/**
	 * https://leetcode.com/problems/excel-sheet-column-title/
	 * @param index
	 * @return
	 */
	static String printExcelColumnRec(int index){//2
		index--;
		int mod = index % 26 ;
		if(index/26 == 0){
			return (char)(97+mod)+"";
		}
		return printExcelColumnRec(index/26)+(char)(97+mod)+"";
	}
	
	/**
	 * https://leetcode.com/problems/excel-sheet-column-title/
	 * @param index
	 * @return
	 */
	static String printExcelColumn(int index){
		StringBuilder sb = new StringBuilder();
		while(index>0){
			index--;
			int mod = index % 26 ;
			sb.append((char)(97+mod));
			index=index/26;
		}
		return sb.toString();
	}
	
	/**
	 * https://leetcode.com/problems/number-of-1-bits/
	 * @param n
	 * @return
	 */
	static int hammingWeight(int n) {
	    int count = 0;
	    for(int i=1; i<33; i++){
	        if((n & (1 << i)) != 0 == true){
	            count++;
	        }
	    }
	    return count;
	}
	
	/**
	 * https://leetcode.com/problems/reverse-bits/
	 * @param n
	 * @return
	 */
	static int reverseBits(int n) {
		int result = 0;
        for(int i =0;i<32;i++){
            if( ((1<<i) & n) != 0 )
                result |= 1<< (31-i);
        }
        return result;
    }
	
	/**
	 * https://leetcode.com/problems/factorial-trailing-zeroes/
	 * 
	 * @param n
	 * @return
	 */
	static int findTrailingZeros(int  n)
	{
	    int count = 0;
	 
	    while(n>0){
	    	n=n/5;
	    	count+=n;
	    }
	    return count;
	}
	
	
	static int pal(int x){
		
		int n=x;
		long rev =0;
		while(n!=0){
			rev = (long)rev*10+n%10;
			n=n/10;
		}
		
		return (int) rev;
	}
	
	static List<List<Integer>> pascal(int num){
		List<List<Integer>> pascals = new ArrayList<List<Integer>>();
		if(num==0){
			return pascals;
		}
		int start =1, end=1;
		List<Integer> list = new ArrayList<Integer>();
		list.add(start);
		pascals.add(list);
		if(num==1){
			return pascals;
		}
		list = new ArrayList<Integer>();
		list.add(start);
		list.add(end);
		pascals.add(list);
		if(num==2){
			return pascals;
		}
		for(int i=3; i<=num; i++){
			list = new ArrayList<Integer>();
			list.add(start);
			for(int j=1;j<=i-2;j++){
				list.add(pascals.get(i-2).get(j-1)+pascals.get(i-2).get(j));
			}
			list.add(end);
			pascals.add(list);
		}
		return pascals;
	}
	
	static class MergeSort{
		private int[] array;
	    private int[] tempMergArr;
	    private int length;
	 
	    public static void main(String a[]){
	        int[] inputArr = {25,8,90,1,54,9,23,4,9,5};
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
	            int middle = (lowerIndex + higherIndex)/ 2;
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
	
}

