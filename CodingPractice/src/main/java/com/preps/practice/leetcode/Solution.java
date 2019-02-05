package com.preps.practice.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
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
	//[[2,3],[5,5],[2,2],[3,4],[3,4]]
	public static void main(String[] args) {
		System.out.println(largestPalindrome(3));
	}
	
	static int largestPalindrome(int n) {
		if (n==1) return 9;
        int max=(int)Math.pow(10, n)-1;
        for (int v=max;v>max/10;v--) {
            long u=Long.valueOf(v+new StringBuilder().append(v).reverse().toString());
            for (long x=max;x*x>=u;x--)
                if (u%x==0)
                    return (int)(u%1337);
        }
        return 0;
    }
    
    static boolean isPal(String pal){
        int len = pal.length();
        for(int i=0; i<len/2;i++){
            if(pal.charAt(i)!=pal.charAt(len-1-i))
                return false;
        }
        return true;
    }
    
	public static class Arrays{
		
		interface Robot{
			void clean();
			boolean move();
			void turnRight();
			void turnLeft();
		}
		
		static class RobotCleaner {
		    int[] dx = {-1, 0, 1, 0};
		    int[] dy = {0, 1, 0, -1};
		    public void cleanRoom(Robot robot) {
		        // use 'x@y' mark visited nodes, where x,y are integers tracking the coordinates
		        dfs(robot, new HashSet<>(), 0, 0, 0); // 0: up, 90: right, 180: down, 270: left
		    }
		 
		    public void dfs(Robot robot, Set<String> visited, int x, int y, int curDir) {
		        String key = x + "@" + y;
		        if (visited.contains(key)) return;
		        visited.add(key);
		        robot.clean();
		 
		        for (int i = 0; i < 4; i++) { // 4 directions
		            if(robot.move()) { // can go directly. Find the (x, y) for the next cell based on current direction
		                dfs(robot, visited, x + dx[curDir], y + dy[curDir], curDir);
		                backtrack(robot);
		            }
		 
		            // turn to next direction
		            robot.turnRight();
		            curDir += 1;
		            curDir %= 4;
		        }
		    }
		 
		    // go back to the starting position
		    private void backtrack(Robot robot) {
		        robot.turnLeft();
		        robot.turnLeft();
		        robot.move();
		        robot.turnRight();
		        robot.turnRight();
		    }
		}
	
		
		static List<List<String>> nQueens(int N){
			char[][] chess = new char[N][N];
			for(int i=0; i<N; i++)
				for(int j=0; j<N; j++)
					chess[i][j] = '.';
			
			List<List<String>> results = new ArrayList<>();
			solveQueen(chess, 0, results);
			
			return results;
		}
		
		private static List<String> printQueen(char[][] chess) {
			List<String> rowAns = new ArrayList<>();
			for(int i=0;i<chess.length;i++){
				rowAns.add(new String(chess[i]));
			}
			return rowAns;
		}

		static void solveQueen(char[][] chess, int row, List<List<String>> results){
			if(row==chess.length){
				results.add(printQueen(chess));
				return;
			}
			
			int N = chess.length;
			for(int col=0; col<N; col++){
				if(validateQueen(chess, row, col, N)){
					chess[row][col]='Q';
					solveQueen(chess,row+1,results);
					chess[row][col]='.';
				}
			}
		}
		
		static boolean validateQueen(char[][] pos, int row, int col, int N) {
			for(int i=0; i<row;i++){
				if(pos[i][col]=='Q')
					return false;
			}
			
			for(int i=row-1, j=col-1; i>=0 && j>=0 ;i--, j--){
				if(pos[i][j]=='Q')
					return false;
			}
			
			for(int i=row-1, j=col+1; i>=0&&j<N;i--, j++){
				if(pos[i][j]=='Q')
					return false;
			}
				
			return true;
		}
		
		static List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
	        List<Integer> res = new ArrayList<>();
	        if(pid==null || ppid==null || pid.isEmpty() || ppid.isEmpty()){
	           return res; 
	        } 
	        Map<Integer,List<Integer>> ppidMap = new HashMap<>();

	        for(int i = 0; i<ppid.size(); i++){
	            Integer pp = ppid.get(i);
	            List<Integer> val = new ArrayList<>();
	            if(ppidMap.containsKey(pp)){
	                val = ppidMap.get(pp);
	            }
	            val.add(pid.get(i));
	            ppidMap.put(pp , val);
	        }
	        
	        if(ppidMap.get(kill)==null){
	            res.add(kill);
	            return res;
	        }else{
	            int i=0;
	            res.add(kill);
	            while(i<res.size()){
	                List<Integer> child = ppidMap.get(res.get(i++));
	                if(child!=null)
	                    res.addAll(child);
	            }
	        }
	        return res;  
	    }

		static int totalFruit(int[] tree) {
			if(tree==null || tree.length==0)
	            return 0;
	        
	        int [] n = new int[2];
	        java.util.Arrays.fill(n,-1);
	        Map<Integer,Integer> map = new HashMap<>();
	        int len=0, max=0;
	        
	        for(int i=0; i<tree.length; i++){
	            if(n[0]==-1 || n[1]==-1){
	                if(n[0]!=tree[i]){
	                    util(tree, n, i);    
	                }
	                len = i+1;
	            }else{
	                if(n[0] == tree[i] || n[1] == tree[i]){
	                	if(n[0]!=tree[i]){
		                    util(tree, n, i);    
		                }
	                    ++len;
	                }else{
	                    Integer p = n[1];
	                    util(tree, n, i);
	                    Integer ind = map.remove(p);
	                    len = i - ind;
	                }
	            }
	            max = Math.max(len,max);
	            map.put(tree[i], i);
	        }
	        
	        return max;
	    }

		private static void util(int[] tree, int[] n, int i) {
			swap(n,0,1);
			n[0]=tree[i];
		}
		
		
		static int longestTwoDistinct(String word){
			if(word==null || word.isEmpty())
	            return 0;

	        int p1=0,p2=-1,len=1,max=1;
	        for(int i=1;i<word.length();i++){
	            if(word.charAt(p1)==word.charAt(i)){
	                p1=i;
	                ++len;
	            }else if(p2==-1 || word.charAt(p2) == word.charAt(i)){
	                ++len;
	                p2=i;
	            }else{
	                len = i - (p1<p2?p1:p2) ;
	                p1=i-1;
	                p2=i;
	            }
	            max= Math.max(len, max);
	        }
	        return max;
		}
	    
		/**
         * below one is my solution : refer below links for best two solutions :
         * https://www.programcreek.com/2014/05/leetcode-sliding-window-maximum-java/
         * https://leetcode.com/problems/sliding-window-maximum/discuss/65881/O(n)-solution-in-Java-with-two-simple-pass-in-the-array
         * @param nums
         * @param k
         * @return
         */
        static int[] maxSlidingWindow(int[] nums, int k) {
           
            if(nums==null||nums.length==0)
                return new int[] {};
           
            PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k,Collections.reverseOrder());
            for(int i=0;i<k;i++) {
                maxHeap.add(nums[i]);
            }
           
            if(k==nums.length)
                return new int[] {maxHeap.poll()};
           
            int[] output = new int[nums.length-k+1];
            int j=0;
            for(int i=k;i<nums.length;i++) {
                if(maxHeap.size()==k) {
                    output[j] = maxHeap.peek();
                    maxHeap.remove(new Integer(nums[j]));
                    maxHeap.add(nums[i]);
                    j++;
                }
            }
            if(maxHeap.size()==k) {
                output[j]=maxHeap.peek();
            }
            return output;
        }
       
        static int[] productExceptSelf(int[] nums) {
            if(nums==null||nums.length==0)
                return new int[0];
           
            int len = nums.length;
            int[] left = new int [len];
            int[] right = new int [len];
            int l=0, r=len-1, def=1;
            for(int i=0; i<len; i++){
                if(l==0 && r==len-1){
                    left[l++]=def;
                    right[r--]=def;
                }else{
                    left[l++]=nums[i-1]*left[i-1];
                    right[r--]=nums[len-i]*right[len-i];
                }
            }
            for(int i=0;i<left.length;i++) {
                left[i]=left[i]*right[i];
            }
            return left;
           
        }
       
        static int maxEnvelopes(int[][] envelopes) {
            List<Envelope> envs = new ArrayList<Envelope>();
            for(int[] en : envelopes) {
                envs.add(new Envelope(en[0],en[1]));
            }
            Collections.sort(envs);
            int max[] = new int[envs.size()];
            max[0]=1;
            for(int i=1;i<max.length;i++)
                max[i]=1;
           
            int maxL = 0;
            for(int i=1;i<envs.size();i++) {
                for(int j=0;j<i;j++) {
                    Envelope s = envs.get(i);
                    Envelope f = envs.get(j);
                    if(s.end>f.end && s.start>f.start && max[j]+1>max[i]) {
                        max[i]=max[j]+1;
                    }
                }
            }
            for(int m : max) {
                if(Math.max(maxL, m)>maxL) {
                    maxL = m;
                }
               
            }
            return maxL;
        }
       
        static class Envelope implements Comparable<Envelope>{
            Integer start;
            public Envelope(Integer start, Integer end) {
                super();
                this.start = start;
                this.end = end;
            }
 
            Integer end;
           
            public int compareTo(Envelope o) {
                int startCompare = this.start.compareTo(o.start);
                if (startCompare == 0)
                    return this.end.compareTo(o.end);
                return startCompare;
            }
        }
		
		static int[] wiggleSort(int [] nums){
			for(int n : nums){
				addNum(n);
			}
			
			int median = (int) findMedian();
			int minIndex = 0, maxIndex = 1;
			
			while(minIndex<nums.length || maxIndex<nums.length){
				if(minIndex<nums.length && nums[minIndex]>median){
					//find the min value in maxIndex
					while(maxIndex<nums.length && nums[maxIndex]>median){
						maxIndex=maxIndex+2;
					}
					swap(nums,minIndex,maxIndex);
				}
				minIndex+=2;
				if(maxIndex<nums.length && nums[maxIndex]<=median){
					//find the min value in maxIndex
					while(minIndex<nums.length && nums[minIndex]<=median ){
						minIndex=minIndex+2;
					}
					swap(nums,minIndex,maxIndex);
				}
				maxIndex+=2;
			}
			return nums;
		}
		
		static void swap(int[] nums, int minIndex, int maxIndex) {
			if(minIndex<nums.length && maxIndex<nums.length){
				int temp = nums[minIndex];
				nums[minIndex]=nums[maxIndex];
				nums[maxIndex]=temp;
			}
		}

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
		static int maxSubArray(int[] A) {
			if (A == null || A.length == 0)
				return 0;

			int curr = A[0], max = A[0];
			for (int i = 1; i < A.length; i++) {
				curr = Math.max(curr + A[i], A[i]);
				max = Math.max(curr, max);
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
	    
		static int[] prisonAfterNDays(int[] old, int N) {
			int[] initState = null;
			int days = 0;
			String key = null;

			while (N > 0) {
				int[] temp = old.clone();
				StringBuilder sb = new StringBuilder();

				// Loop to find next state of array.
				for (int i = 0; i < temp.length; i++) {
					if (i == 0 || i == temp.length - 1) {
						if (temp[i] == 1)
							old[i] = 0;
					} else {
						if ((temp[i - 1] == 0 && temp[i + 1] == 0) || (temp[i - 1] == 1 && temp[i + 1] == 1)) {
							old[i] = 1;
						} else {
							old[i] = 0;
						}

						sb.append(String.valueOf(old[i]));
					}
				}

				// Reduce iteration if see number again.
				if (key != null && key.equals(sb.toString())) {
					N--;
					N = N % days;
					continue;
				}

				// Set up initial state to set the state and key
				if (initState == null) {
					initState = old.clone();
					key = sb.toString();
				}

				days++;
				N--;
			}

			return old;
		}
	    
	}
	
	public static class Trees{
		
		public static void main(String[] args) {
		
		}		
		
		class TrieNode {
		    public char val;
		    public boolean isWord; 
		    public TrieNode[] children = new TrieNode[26];
		    public TrieNode() {}
		    TrieNode(char c){
		        TrieNode node = new TrieNode();
		        node.val = c;
		    }
		}

		public class Trie {
		    private TrieNode root;
		    public Trie() {
		        root = new TrieNode();
		        root.val = ' ';
		    }

		    public void insert(String word) {
		        TrieNode ws = root;
		        for(int i = 0; i < word.length(); i++){
		            char c = word.charAt(i);
		            if(ws.children[c - 'a'] == null){
		                ws.children[c - 'a'] = new TrieNode(c);
		            }
		            ws = ws.children[c - 'a'];
		        }
		        ws.isWord = true;
		    }

		    public boolean search(String word) {
		        TrieNode ws = root; 
		        for(int i = 0; i < word.length(); i++){
		            char c = word.charAt(i);
		            if(ws.children[c - 'a'] == null) return false;
		            ws = ws.children[c - 'a'];
		        }
		        return ws.isWord;
		    }

		    public boolean startsWith(String prefix) {
		        TrieNode ws = root; 
		        for(int i = 0; i < prefix.length(); i++){
		            char c = prefix.charAt(i);
		            if(ws.children[c - 'a'] == null) return false;
		            ws = ws.children[c - 'a'];
		        }
		        return true;
		    }
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
	
	/**
	 * https://leetcode.com/problems/number-of-digit-one/discuss/64382/JavaPython-one-pass-solution-easy-to-understand/65988
	 * @param n
	 * @return
	 */
	static int countDigitOne(int n) {
	    if (n <= 0) return 0;
	    int k = n, factor = 1, count = 0;
	    do {
	        int digit = k % 10;
	        k /= 10;
	        count += k * factor;
	        if (digit == 1) count += n % factor + 1;
	        if (digit >  1) count += factor;
	        factor *= 10;
	    } while (k > 0);
	    return count;
	}

	static boolean isPalindrome(String s, int low, int high){
	   while(low < high)
	      if(s.charAt(low++) != s.charAt(high--)) return false;
	   return true;
	} 
	
	static int eraseOverlapIntervals(Interval[] intervals) {
		if(intervals==null || intervals.length==0)
            return 0;
		
		List<Interval> ins = java.util.Arrays.asList(intervals);
        
        Collections.sort(ins, new Comparator<Interval>(){
			@Override
			public int compare(Interval o1, Interval o2) {
				if(Integer.compare(o1.start, o2.start)==0)
					return Integer.compare(o1.end, o2.end);
				return Integer.compare(o1.start, o2.start);
			}
		});
        
        int c=0, i=0,j=0;
        while( j<ins.size() && i<ins.size()-1){
        	j=i+1;
        	while(j<ins.size() && ( ins.get(i).start == ins.get(j).start || (ins.get(j).start< ins.get(i).end)) ){
        		c++;
        		j++;
        	}
        	i=j;
        }
        return c;
    }
	
	static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		
		if(intervals==null&&newInterval==null)
			return null;
		
		if(intervals==null)
			return java.util.Arrays.asList(newInterval);
		
		if(newInterval==null)
			return intervals;
		
		int i=0;
		List<Interval> finalList = new LinkedList<>();
		while(i<intervals.size() && intervals.get(i).end < newInterval.start){
			finalList.add(intervals.get(i));
			i++;
		}
		
		Interval merge = null;
		while(i<intervals.size() && intervals.get(i).start <= newInterval.end){
			merge= new Interval(Math.min(intervals.get(i).start, newInterval.start),
					Math.max(intervals.get(i).end, newInterval.end));
			i++;
		}
		
		finalList.add(merge);
		
		while(i<intervals.size()){
			finalList.add(intervals.get(i++));
		}
		
		return finalList;
		
    }
	
	static List<Interval> mergeIntervals(List<Interval> intervals){
		if(intervals==null || intervals.size()==0)
            return intervals;
        
        Collections.sort(intervals, new Comparator<Interval>(){
			@Override
			public int compare(Interval o1, Interval o2) {
				return Integer.compare(o1.start, o2.start);
			}
		});
		
		LinkedList<Interval> finalList = new LinkedList<>();
		for(Interval interval : intervals){
			if(finalList.isEmpty() || finalList.getLast().end<interval.start){
				finalList.add(interval);
			}else{
				finalList.getLast().end = Math.max(finalList.getLast().end, interval.end);
			}
		}
		
		return finalList;
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
	
	static int sum(int a, int b){
    	while(b!=0){
    		int sum =a^b;
    		b=a&b;
    		if(b>0)
    			b<<=1;
    		a=sum;
    	}
    	return a;
    }
	
	static int sumRec(int a, int b){
		return b==0?a:sumRec(a^b,(a&b)<<1);
    }
	
	static int countPrimeSetBits(int L, int R) {
        HashMap<Integer,Boolean> primeMap = new HashMap<>();
        for(int i=0; i<33;i++){
            primeMap.put(i,false);    
        }
        int i=2;
        while(i<17){
            int j=i+i;
            while(j<33){
                primeMap.put(j,true);
                j+=i;
            }
            i++;
        }
        int temp=L;
        int sum=0;
        while(temp<=R){
        	i=temp;
            int bit=0;
            while(i>0){
                bit++;
                i=i&(i-1);
            }
            if(!primeMap.get(bit)){
                sum++;
            }
            temp++;
        }
        return sum;
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
    
    /**
     * https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/
     * @param arr
     * @param l
     * @param h
     * @param key
     * @return
     */
    static int search(int arr[], int l, int h, int key){ 
        if (l > h)  
            return -1; 
        
        int mid = (l+h)/2; 
        if (arr[mid] == key) 
            return mid; 
        
        /* If arr[l...mid] is sorted */
        if (arr[l] <= arr[mid]) 
        { 
            /* As this subarray is sorted, we  
               can quickly check if key lies in  
               half or other half */
            if (key >= arr[l] && key <= arr[mid]) 
               return search(arr, l, mid-1, key); 
        
            return search(arr, mid+1, h, key); 
        } 
        
        /* If arr[l..mid] is not sorted,  
           then arr[mid... r] must be sorted*/
        if (key >= arr[mid] && key <= arr[h]) 
            return search(arr, mid+1, h, key); 
        
        return search(arr, l, mid-1, key); 
    } 
    
}
