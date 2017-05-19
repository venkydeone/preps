package com.preps.practice.leetcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
	
	public static void main(String[] args) {
		Arrays.findMedianSortedArrays(new int[]{1,4,8,15,60,88}, new int[]{5,7,12,25,35,66,80});
	}
	public static class Arrays{
		/**
		 * https://leetcode.com/problems/maximum-subarray/#/description
		 * @param nums
		 * @return
		 */
		static int maxSubArray(int[] nums) {
	        if(nums==null||nums.length==0){
	            return 0;
	        }
	        
	        int[] dp = new int[nums.length];
	        dp[0] = nums[0];
	        int max = dp[0];
	        
	        for(int i=1; i<nums.length; i++){
	            if(nums[i]>0 && dp[i-1]<0){
	                dp[i]=nums[i];
	            }else{
	                dp[i] = dp[i-1]+nums[i]>nums[i]?dp[i-1]+nums[i]:nums[i];
	            }
	            max = Math.max(max,dp[i]);
	        }
	        return max;
	    }
		
		/**
		 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/#/description
		 * @param nums
		 * @param target
		 * @return
		 */
		static int[] twoSum(int[] nums, int target) {
	        if(nums==null || nums.length ==0)
	    		return null;
	        Map<Integer,Integer> numToIndex = new HashMap<Integer, Integer>();
	        for(int i=0; i<nums.length; i++){
	            if(!numToIndex.containsKey(nums[i]))
	                numToIndex.put(nums[i], i+1);
	        }
	        
	        for(Integer n : nums){
	            if(target == 2*n){
	                return new int[]{numToIndex.get(n),numToIndex.get(n)+1}; 
	            }
	            if(numToIndex.containsKey(n)&&numToIndex.containsKey(target-n)){
	                return new int[]{numToIndex.get(n),numToIndex.get(target-n)};
	            }
	        }
	        return null;
	    }
		
		static int[] twoSumBEST(int[] num, int target) {
		    int[] indice = new int[2];
		    if (num == null || num.length < 2) return indice;
		    int left = 0, right = num.length - 1;
		    while (left < right) {
		        int v = num[left] + num[right];
		        if (v == target) {
		            indice[0] = left + 1;
		            indice[1] = right + 1;
		            break;
		        } else if (v > target) {
		            right --;
		        } else {
		            left ++;
		        }
		    }
		    return indice;
		}
		
		/**
		 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/#/description
		 * @param nums
		 * @return
		 */
		static TreeNode sortedArrayToBST(int[] nums) {
	        if(nums==null||nums.length==0){
	            return null;
	        }
	        return helper(nums, 0,nums.length-1);
	    }
	    
	    static TreeNode helper(int[] nums, int low, int high){
	        if(low>high)
	            return null;
	        int mid = low + (high-low)/2;
	        TreeNode root = new TreeNode(nums[mid]);
	        root.left = helper(nums, low, mid-1);
	        root.right = helper(nums, mid+1, high);
	        return root;
	    }
	    
	    /**
	     * Answer : https://leetcode.com/problems/median-of-two-sorted-arrays/#/description
	     * Solution Expln : http://www.programcreek.com/2012/12/leetcode-median-of-two-sorted-arrays-java/
	     * @param k
	     * @param nums1
	     * @param nums2
	     * @param s1
	     * @param s2
	     * @return
	     */
	    static double findMedianSortedArrays(int[] nums1, int[] nums2) {
	        int total = nums1.length+nums2.length;
	        if(total%2==0){
	            return (findKth(total/2+1, nums1, nums2, 0, 0)+findKth(total/2, nums1, nums2, 0, 0))/2.0;
	        }else{
	            return findKth(total/2+1, nums1, nums2, 0, 0);
	        }
	    }
	    
	    static int findKth(int k, int[] nums1, int[] nums2, int s1, int s2){
	        if(s1>=nums1.length)
	            return nums2[s2+k-1];
	     
	        if(s2>=nums2.length)
	            return nums1[s1+k-1];
	     
	        if(k==1)
	            return Math.min(nums1[s1], nums2[s2]);
	     
	        int m1 = s1+k/2-1;
	        int m2 = s2+k/2-1;
	     
	        int mid1 = m1<nums1.length?nums1[m1]:Integer.MAX_VALUE;    
	        int mid2 = m2<nums2.length?nums2[m2]:Integer.MAX_VALUE;
	     
	        if(mid1<mid2){
	            return findKth(k-k/2, nums1, nums2, m1+1, s2);
	        }else{
	            return findKth(k-k/2, nums1, nums2, s1, m2+1);
	        }
	    }
	    
	    static PriorityQueue<Integer> min = new PriorityQueue<Integer>();
	    static PriorityQueue<Integer> max = new PriorityQueue<Integer>(1000, Collections.reverseOrder());
	    /**
	     * https://leetcode.com/problems/find-median-from-data-stream/#/description
	     * @param num
	     */
	    static void addNum(int num) {
	        max.offer(num);
	        min.offer(max.poll());
	        if (max.size() < min.size()){
	            max.offer(min.poll());
	        }
	    }

	    // Returns the median of current data stream
	    static double findMedian() {
	        if (max.size() == min.size()) return (max.peek() + min.peek()) /  2.0;
	        else return max.peek();
	    }
	}
	
	

	
	public static class TreeNode {
		  int val;
		  TreeNode left;
		  TreeNode right;
		  TreeNode(int x) { val = x; }
	 }
}
