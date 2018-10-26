package com.preps.practice.sample;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class PlayGround {
	
	static void dutchFlagAlgo(int[] arr){
		int low=0, mid=0, hi=arr.length-1;
		while(mid<=hi){
			switch(arr[mid]){
				case 0:
					swap(arr,low++,mid++);
					break;
				case 1:
					mid++;
					break;
				case 2:
					swap(arr,mid,hi--);
					break;
			}
		}
		System.out.println(Arrays.toString(arr));
	}
	
	static void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i]=arr[j]; 
		arr[j]=temp;
	}
	static int increasingSubSequenceBruteForce(int[] input){
		return increasingSSBFHelper(input,1,1);
	}
	
	static int increasingSSBFHelper(int[] input, int index, int length){
		if(input==null||input.length==0 ||index==input.length-1){
			return length;
		}
		else{
			int lengthConsideringInc = 0;
			if(input[index-1]<input[index]){
				 lengthConsideringInc = increasingSSBFHelper(input, index+1, length)+1;
			}
			int lengthNotConsideringInc = increasingSSBFHelper(input, index+1, length);
			return Math.max(lengthConsideringInc, lengthNotConsideringInc);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
	}
	
	static int removeElement(int[] A, int elem) {
	   int m = 0;    
	   for(int i = 0; i < A.length; i++){
	       
	       if(A[i] != elem){
	           A[m] = A[i];
	           m++;
	       }
	   }
	   
	   return m;
	}
	
	static int findComplement(int num) {
        int n=0, i=0;
        while(num>0){
            n = n | ((num & 1<<i)^1<<i)<<i;
            num>>=1;
            i++;
        }
        return n;
    }
	
	public static int[] findRound(double[] arr) {
        int[] result = new int[arr.length];

        double sum = 0;
        int floorSum = 0;
        Map<Integer, Double> decimalMap = new TreeMap<Integer, Double>();


        for(int i=0;i<arr.length;i++) {
            result[i] = (int)Math.floor(arr[i]);
            String decimal = String.format("%.1f", arr[i] - Math.floor(arr[i]));
            decimalMap.put(i, Double.parseDouble(decimal));
            floorSum+=Math.floor(arr[i]);
            sum+=arr[i];
        }

        double remaining =  Math.round(sum) - floorSum;
        if(remaining == 0) {
            return result;
        }

        Map<Integer, Double> sortedMap = sortByValue(decimalMap);
        Integer[] keySet = sortedMap.keySet().toArray(new Integer[sortedMap.size()]);
        for(int i=0;i<remaining;i++) {
            result[keySet[i]]++;
        }
        return result;
    }


    static  Map<Integer, Double> sortByValue(Map<Integer, Double> map) {
        List<Map.Entry<Integer, Double>> mapList = new LinkedList<Map.Entry<Integer, Double>>(map.entrySet());

        Collections.sort(mapList, new Comparator<Map.Entry<Integer, Double>>() {
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<Integer, Double> m = new LinkedHashMap<Integer, Double>();
        for(Map.Entry<Integer, Double> cur: mapList) {
            m.put(cur.getKey(), cur.getValue());
        }
        return m;
    }
    
    static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

        int i=0, j=0;
        while(i<nums1.length || j<nums2.length){
        	if(i<nums1.length){
        		minHeap.add(nums1[i++]);
        	}
        	
        	if(j<nums2.length){
        		minHeap.add(nums2[j++]);
        	}
        	
        	if(minHeap.size()!=maxHeap.size()){
        		maxHeap.add(minHeap.poll());
        	}
        }
        
        return minHeap.size()!=maxHeap.size()?minHeap.poll():(double)(maxHeap.poll()+minHeap.poll())/2;
    }
    
    
    static boolean canReachEnd(int nums[]){
    	if(nums.length<=1)
            return true;
        int max = nums[0], i=1;
        for(;max>0&&i<nums.length;i++){
        	max--;
            max=Math.max(nums[i],max);
        }
        return i==nums.length;
    }
   
    static int removeDuplicates(int[] nums) {
    	int index=1;
        for(int i=0;i<nums.length-1;i++){
        	if(nums[i]!=nums[i+1]){
        		nums[index]=nums[i+1];
        		index++;
        	}
        }
        return index;
    }

}
