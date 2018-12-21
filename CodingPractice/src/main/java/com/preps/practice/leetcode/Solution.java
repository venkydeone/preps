package com.preps.practice.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;

import com.preps.practice.datastruct.TreePractice.TreeNode;

/**
 * 
 * @author Venkat P
 * 
 * Leetcode problems
 *
 */
public class Solution {
	
	public static void main(String[] args) {
		System.out.println(frequency("tree"));
	}
	public static class Arrays{
		
	
		
		/**
		 * 
		 * @param A
		 * @param val
		 * @return
		 */
		static int removeElement(int[] nums, int val) {
			if(nums==null|| nums.length==0){
	            return 0;
	        }
	        int prevIndex=0;
	        for(int i=0;i<nums.length;i++){
	            if(nums[i]==val){
	                prevIndex=i;
	                nums[i]=0;
	            }else{
	                nums[prevIndex++]=nums[i];
	                if(prevIndex-1!=i){
	                    nums[i]=0;
	                }
	            }
	        }
	        
	        return prevIndex;
		}
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
	        root.setLeft(helper(nums, low, mid-1));
	        root.setRight(helper(nums, mid+1, high));
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
	    static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
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
	    
	    /**
	     * Subset # 1 : https://discuss.leetcode.com/topic/46161/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning
	     * @param nums
	     * @return
	     */
	    static List<List<Integer>> subsets(int[] nums) {
	    	List<List<Integer>> list = new ArrayList<List<Integer>>();
	    	java.util.Arrays.sort(nums);
	    	backtrackSS1(list, new ArrayList<Integer>(), nums, 0);
	    	return list;
	    }
	    
	    static void backtrackSS1(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
	    	list.add(new ArrayList<Integer>(tempList));
	    	for(int i = start; i < nums.length; i++){
	    		tempList.add(nums[i]);
	    		backtrackSS1(list, tempList, nums, i + 1);
	    		tempList.remove(tempList.size() - 1);
	    	}
	    }
	    
	    /**
	     * Subset # 2 : https://discuss.leetcode.com/topic/46161/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning
	     * @param nums
	     * @return
	     */
	    static List<List<Integer>> subsets2(int[] nums) {
	    	List<List<Integer>> list = new ArrayList<List<Integer>>();
	    	java.util.Arrays.sort(nums);
	    	backtrackSS2(list, new ArrayList<Integer>(), nums, 0);
	    	return list;
	    }
	    
		static void backtrackSS2(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
			list.add(new ArrayList<Integer>(tempList));
			for (int i = start; i < nums.length; i++) {
				if (i > start && nums[i] == nums[i - 1])
					continue; // skip duplicates
				tempList.add(nums[i]);
				backtrackSS2(list, tempList, nums, i + 1);
				tempList.remove(tempList.size() - 1);
			}
		}

		/**
		 * https://discuss.leetcode.com/topic/46161/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning
		 * @param nums
		 * @return
		 */
		static List<List<Integer>> permute(int[] nums) {
		   List<List<Integer>> list = new ArrayList<List<Integer>>();
		   // Arrays.sort(nums); // not necessary
		   backtrackP1(list, new ArrayList<Integer>(), nums);
		   return list;
		}

		static void backtrackP1(List<List<Integer>> list, List<Integer> tempList, int [] nums){
		   if(tempList.size() == nums.length){
		      list.add(new ArrayList<Integer>(tempList));
		   } else{
		      for(int i = 0; i < nums.length; i++){ 
		         if(tempList.contains(nums[i])) continue; // element already exists, skip
		         tempList.add(nums[i]);
		         backtrackP1(list, tempList, nums);
		         tempList.remove(tempList.size() - 1);
		      }
		   }
		} 
		
		/**
		 * Permutaion Unique
		 * https://discuss.leetcode.com/topic/46161/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning
		 * @param nums
		 * @return
		 */
		static List<List<Integer>> permuteUnique(int[] nums) {
		    List<List<Integer>> list = new ArrayList<List<Integer>>();
		    java.util.Arrays.sort(nums);
		    backtrackPU(list, new ArrayList<Integer>(), nums, new boolean[nums.length]);
		    return list;
		}

		static void backtrackPU(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
		    if(tempList.size() == nums.length){
		        list.add(new ArrayList<Integer>(tempList));
		    } else{
		        for(int i = 0; i < nums.length; i++){
		            if(used[i] 
		            		|| i > 0 && nums[i] == nums[i-1] 
		            				&& !used[i - 1]) 
		            	continue;
		            used[i] = true; 
		            tempList.add(nums[i]);
		            backtrackPU(list, tempList, nums, used);
		            used[i] = false; 
		            tempList.remove(tempList.size() - 1);
		        }
		    }
		}
		
		/**
		 * Next Permutaion
		 * @param nums
		 */
		static void nextPermutation(int[] nums) {
	      if(nums.length<=1){
	          return;
	      }
	      
	      int i= nums.length-1;
	      
	      for(;i>=1;i--){
	         
	         if(nums[i]>nums[i-1]){ //find first number which is smaller than it's after number
	             break;
	         }
	      }
	      
	      if(i!=0){
	          swap(nums,i-1); //if the number exist,which means that the nums not like{5,4,3,2,1}
	      }
	      reverse(nums,i);    
	    }
	    
	    static void swap(int[] a,int i){
	        for(int j = a.length-1;j>i;j--){
	            if(a[j]>a[i]){
	                int t = a[j];
	                a[j] = a[i];
	                a[i] = t;
	                break;
	            }
	        }
	    }
	    
	    static void reverse(int[] a,int i){//reverse the number after the number we have found
	        int first = i;
	        int last = a.length-1;
	        while(first<last){
	            int t = a[first];
	            a[first] = a[last];
	            a[last] = t;
	            first ++;
	            last --;
	        }
	    }
	    
	    /**
	     * https://leetcode.com/problems/search-for-a-range/#/description
	     * @param nums
	     * @param target
	     * @return
	     */
	    static int[] searchRange(int[] nums, int target) {
	    	int beginning=-1,ending=-1;
	        if(nums==null||nums.length==0)
	            return null;
	        int start = 0, end = nums.length-1;
	        while(start<end){
	        	int mid = start + (end-start)/2;
	        	if(nums[mid]==target){
	        		beginning=findFirstOccurence(nums, target, start, mid, true);
	        		break;
	        	}else{
	        		start=mid+1;
	        	}
	        }
	        while(start<end){
	        	int mid = start + (end-start)/2;
	        	if(nums[mid]==target){
	        		ending=findFirstOccurence(nums, target, mid, end, false);
	        		break;
	        	}else{
	        		end=mid-1;
	        	}
	        }
	        if(beginning==-1&&ending!=-1){
	        	beginning=ending;
	        }
	        if(ending==-1&&beginning!=-1){
	        	ending=beginning;
	        }
	        return new int[]{beginning,ending};
	    }
	    
	    static int findFirstOccurence(int[] nums, int target, int start, int end, boolean isFirst){
	    	boolean found =false;
	    	int prevMid=start;
	        while(start<end){
	            int mid = start + (end-start)/2;
	            if(target == nums[mid]){
	            	if(isFirst)
	            		end=mid-1;
	            	else
	            		start=mid+1;
	                prevMid=mid;
	                found=true;
	            }else{
	            	if(found){
	            		return prevMid;
	            	}
	            	if(isFirst)
	            		start = mid+1;
	            	else
	            		end=mid-1;
	        	}
	        }
	        if(isFirst){
	        	if(nums[start]==target)
	        		return start;
	        }else{
	        	if(nums[end]==target)
	        		return end;
	        }
	        return prevMid;
	    }
	    
	    /**
	     * https://discuss.leetcode.com/topic/11408/single-loop-simple-java-solution
	     * @param A
	     * @return
	     */
	    static int jumpHard(int[] A) {
	        int sc = 0;
	        int e = 0;
	        int max = 0;
	        for(int i=0; i<A.length-1; i++) {
	            max = Math.max(max, i+A[i]);
	            if( i == e ) {
	                sc++;
	                e = max;
	            } 
	        }
	        return sc;
	    }
	    
	    /**
	     * Credits : https://discuss.leetcode.com/topic/246/round-numbers/9
	     * @param arr
	     * @return
	     */
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
	    
	    /**
		 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
		 * @param nums
		 * @return
		 */
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
		
		
		/**
		 * https://leetcode.com/problems/median-of-two-sorted-arrays/
		 * @param nums1
		 * @param nums2
		 * @return
		 */
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
	    

	   /**
	    * https://leetcode.com/problems/jump-game/ 
	    * @param nums
	    * @return
	    */
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
	    
	    /**
	     * 
	     * @param prices
	     * @return
	     */
	    static int maxProfit(int[] prices) {
	        int min=prices[0];
	        int max=0, total=0, pmax=min;
	        for(int i=1;i<prices.length;i++){
	            if(prices[i]<pmax){
	                min=prices[i];
	                pmax=min;
	                total+=max;
	            }
	            
	            if(prices[i]>min){
	                max=Math.max(prices[i]-min,max); 
	                pmax= prices[i];
	            }
	            
	        }
	        return total;
	    }
	}
	
	public static class Trees{
		
		public static void main(String[] args) {
			System.out.println(buildTree(new int[]{3,1,2,4},new int[]{1,2,3,4}));
		}
		
		
		static String tree2str(TreeNode t) {
	        if(t==null)
	            return "";
	            
	        StringBuilder sb =new StringBuilder();
	        sb.append(t.getVal());
	        if(t.getLeft()!=null)
	        	sb.append("(").append(tree2str(t.getLeft())).append(")");
	        if(t.getRight()!=null){
	        	if(t.getLeft()==null)
	        		sb.append("(").append(")");            
	        	sb.append("(").append(tree2str(t.getRight())).append(")");            
	        }
	        
	        return sb.toString();
	    }
		
		static List<List<Integer>> pathSum2(TreeNode root, int sum) {
	        List<List<Integer>> list = new ArrayList<List<Integer>>();
	        if(root==null)
	            return list;
	        
	        pathSum2(root, list, new ArrayList<Integer>(), sum);
	        return list;
	    }
	    
	    static void pathSum2(TreeNode root, List<List<Integer>> list, List<Integer> current, 
				int sum) {
			current.add(root.val);

			if (root.val == sum && root.left == null && root.right == null) {
				list.add(new ArrayList<Integer>(current));
			} else {
				if(root.left!=null){
					pathSum2(root.left, list, current, sum - root.val);
					current.remove(current.size()-1);
				}
				if(root.right!=null){
					pathSum2(root.right, list, current, sum - root.val);
					current.remove(current.size()-1);
				}
			}
		}
		
	    static TreeNode buildTree(int[] pre, int[] in) {
	        if(pre == null || in == null || pre.length==0 || in.length==0)
	            return null;
	        
	        return build(pre, in, 0, in.length-1, 0);
	        
	    }
	    
	    static TreeNode build(int [] pre, int [] in, int inStart, int inEnd, int preIn){
	        if(inStart>inEnd || preIn>=pre.length)
	            return null;
	        
	        int j=inStart;
	        TreeNode root = new TreeNode(pre[preIn]);
	        for(; j<=inEnd; j++){
	            if(in[j]==pre[preIn])
	                break;
	        }
	        
	        root.left = build(pre,in,inStart,j-1,preIn+1);
	        root.right = build(pre,in,j+1,inEnd, preIn + j - inStart + 1);
	        return root;
	    }
	    
	    static class Codec {

	        private static final String spliter = ",";
	        private static final String NN = "X";

	        // Encodes a tree to a single string.
	        public String serialize(TreeNode root) {
	            StringBuilder sb = new StringBuilder();
	            buildString(root, sb);
	            return sb.toString();
	        }

	        private void buildString(TreeNode node, StringBuilder sb) {
	            if (node == null) {
	                sb.append(NN).append(spliter);
	            } else {
	                sb.append(node.val).append(spliter);
	                buildString(node.left, sb);
	                buildString(node.right,sb);
	            }
	        }
	        // Decodes your encoded data to tree.
	        public TreeNode deserialize(String data) {
	            Deque<String> nodes = new LinkedList<String>();
	            nodes.addAll(java.util.Arrays.asList(data.split(spliter)));
	            return buildTree(nodes);
	        }
	        
	        private TreeNode buildTree(Deque<String> nodes) {
	            String val = nodes.remove();
	            if (val.equals(NN)) return null;
	            else {
	                TreeNode node = new TreeNode(Integer.valueOf(val));
	                node.left = buildTree(nodes);
	                node.right = buildTree(nodes);
	                return node;
	            }
	        }

	    }
		
		
	}
	static List<String> generateParenthesis(int n) {
	    List<String> list = new ArrayList<String>();
	    generateOneByOne("", list, n, n);
	    return list;
	}
	
	static void generateOneByOne(String sublist, List<String> list, int left, int right){
	    if(left > right){
	        return;
	    }
	    if(left > 0){
	        generateOneByOne( sublist + "(" , list, left-1, right);
	    }
	    if(right > 0){
	        generateOneByOne( sublist + ")" , list, left, right-1);
	    }
	    if(left == 0 && right == 0){
	        list.add(sublist);
	        return;
	    }
	}
	
	static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int max=0;
        int left = -1;
        for(int j=0;j<s.length();j++){
            if(s.charAt(j)=='(') stack.push(j);            
            else {
                if (stack.isEmpty()) left=j;
                else{
                    stack.pop();
                    if(stack.isEmpty()) max=Math.max(max,j-left);
                    else max=Math.max(max,j-stack.peek());
                }
            }
        }
        return max;
    }
	
	static List<List<String>> partition(String s) {
	   List<List<String>> list = new ArrayList<List<String>>();
	   backtrack(list, new ArrayList<String>(), s, 0);
	   return list;
	}

	static void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
	   if(start == s.length())
	      list.add(new ArrayList<String>(tempList));
	   else{
	      for(int i = start; i < s.length(); i++){
	         if(isPalindrome(s, start, i)){
	            tempList.add(s.substring(start, i + 1));
	            backtrack(list, tempList, s, i + 1);
	            tempList.remove(tempList.size() - 1);
	         }
	      }
	   }
	}

	static boolean isPalindrome(String s, int low, int high){
	   while(low < high)
	      if(s.charAt(low++) != s.charAt(high--)) return false;
	   return true;
	} 
	
	/**
	 * 
	 
Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 
	 * @param intervals
	 * @param newInterval
	 * @return
	 */
	static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval o1, Interval o2) {
				return o1.start-o2.start;
			};
		});
		
		List<Interval> newResults = new ArrayList<Interval>();
		if(intervals==null || intervals.isEmpty()){
		    newResults.add(newInterval);
		    return newResults;
		}
		
		if(intervals.get(intervals.size()-1).end < newInterval.start){
			intervals.add(newInterval);
			return intervals;
		}
		
		if(intervals.get(0).start > newInterval.end){
			newResults.add(newInterval);
			newResults.addAll(intervals);
			return newResults;
		}
		int start = -1;
        for(Interval in : intervals){
        	if(start!=-1){
        		if(in.start>newInterval.end){
        			newResults.add(new Interval(start, newInterval.end));
        			newResults.add(in);
        			start=-1;
        		}else if(in.end<newInterval.end){
        		}else if(in.end> newInterval.end){
        			newResults.add(new Interval(start, in.end));
        			start=-1;
        		}
        		continue;
        	}else if(newInterval.start > in.end || newInterval.end < in.start || (in.start < newInterval.start && in.end>newInterval.end)){
        		newResults.add(in);
        	}
        	else{
        		if(start==-1)
        			start = in.start;
        	}
        }
        if(start!=-1){
        	newResults.add(new Interval(start,newInterval.end));
        }
        return newResults;
    }
	
	static class Interval {
		 int start;
		 int end;
		 Interval() { start = 0; end = 0; }
		 Interval(int s, int e) { start = s; end = e; }
		 
		 @Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[ " + start + " : " + end + " ] ";
		}
	}
	
	static String frequency(String word){
		if(word==null || word.isEmpty())
			return null;
		PriorityQueue<Word> words = new PriorityQueue<Solution.Word>();
		HashMap<Character, Word> mapToWord = new HashMap<Character, Solution.Word>();
		char[] chArr = word.toCharArray();
		
		for(char ch : chArr){
			if(mapToWord.containsKey(ch)){
				Word w = mapToWord.remove(ch);
				words.remove(w);
				Word nw = new Word(w.ch, w.count+1);
				mapToWord.put(ch, nw);
				words.add(nw);
			}else{
				Word w = new Word(ch, 1);
				mapToWord.put(ch, w);
				words.add(w);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!words.isEmpty()){
			Word w = words.poll();
			if(w!=null){
				for(int i=0;i<w.count;i++){
					sb.append(w.ch);
				}
			}
		}
		return sb.toString();
	}
	
	static class Word implements Comparable<Word>{
		private char ch;
		public Word(char ch, int count) {
			super();
			this.ch = ch;
			this.count = count;
		}
		private Integer count;
		public int compareTo(Word o) {
			return Integer.compare(((Word)o).count, this.count);
		}
	}
	
	static int findFirst(int[] nums, int target){
        int start = 0, end = nums.length-1;
        while(start<=end){
            int mid = start + (end-start)/2;
            if(nums[mid]==target && (nums[mid-1]!=target || mid==0)){
                return mid;
            }else if(nums[mid]>=target){
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return -1;
    }
               
    static int findEnd(int[] nums, int target){
        int start = 0, end = nums.length-1;
        while(start<=end){
            int mid = start + (end-start)/2;
            if(nums[mid]==target && (nums[mid+1]!=target || mid==nums.length-1)){
                return mid;
            }else if(nums[mid]<target || nums[mid]==target){
                start=mid+1;
            }else{
                end=mid-1;
            }
        }
        return -1;
    }
	
}
