package com.preps.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution {
	
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
	}

	
	public static class TreeNode {
		  int val;
		  TreeNode left;
		  TreeNode right;
		  TreeNode(int x) { val = x; }
	 }
}
