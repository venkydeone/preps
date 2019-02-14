package com.preps.practice.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SortingAlgorithms{
	
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
			SortingAlgorithms.MergeSort mms = new MergeSort();
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
			SortingAlgorithms.QuickSort qs = new QuickSort();
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
	
	static class BubbleSort {
	    public static void sort(Integer[] array) {
	        for (int i = 0; i < array.length - 1; i++) {
	            for (int j = 1; j < array.length - i; j++) {
	                if (array[j - 1] > array[j]) {
	                    swap(array, j, j - 1);
	                }
	            }
	        }
	    }

	    private static void swap(Integer[] array, int a, int b) {
	        Integer temp = array[a];
	        array[a] = array[b];
	        array[b] = temp;
	    }
	}
	
	static class BubbleSortOptimised {
	    public static void sort(Integer[] array) {
	        int unsortedBelow = array.length;
	        while (unsortedBelow != 0) {
	            int lastSwap = 0;
	            for (int i = 1; i < unsortedBelow; i++) {
	                if (array[i - 1] > array[i]) {
	                    swap(array, i, i - 1);
	                    lastSwap = i;
	                }
	            }
	            unsortedBelow = lastSwap;
	        }
	    }

	    private static void swap(Integer[] array, int a, int b) {
	        Integer temp = array[a];
	        array[a] = array[b];
	        array[b] = temp;
	    }
	}
	
	static class InsertionSort {
	    public static <T extends Comparable<T>> void sort(T[] array) {
	        for (int i = 1; i < array.length; i++) {
	            T item = array[i];
	            int indexHole = i;
	            while (indexHole > 0 && array[indexHole - 1].compareTo(item) > 0) {
	                array[indexHole] = array[--indexHole];
	            }
	            array[indexHole] = item;
	        }
	    }
	}
	
	static class SelectionSort {
	    public static void sort(Integer[] array) {
	        for (int i = 0; i < array.length - 1; i++) {
	            int minIndex = i;
	            for (int j = i + 1; j < array.length; j++) {
	                if (array[j] < array[minIndex]) {
	                    minIndex = j;
	                }
	            }
	            if (minIndex != i) {
	                swap(array, i, minIndex);
	            }
	        }
	    }

	    private static void swap(Integer[] array, int a, int b) {
	        Integer temp = array[a];
	        array[a] = array[b];
	        array[b] = temp;
	    }
	}
	
	static class BucketSort {
	    private static final int DEFAULT_BUCKET_SIZE = 5;

	    public static void sort(Integer[] array) {
	        sort(array, DEFAULT_BUCKET_SIZE);
	    }

	    public static void sort(Integer[] array, int bucketSize) {
	        if (array.length == 0) {
	            return;
	        }

	        // Determine minimum and maximum values
	        Integer minValue = array[0];
	        Integer maxValue = array[0];
	        for (int i = 1; i < array.length; i++) {
	            if (array[i] < minValue) {
	                minValue = array[i];
	            } else if (array[i] > maxValue) {
	                maxValue = array[i];
	            }
	        }

	        // Initialise buckets
	        int bucketCount = (maxValue - minValue) / bucketSize + 1;
	        List<List<Integer>> buckets = new ArrayList<List<Integer>>(bucketCount);
	        for (int i = 0; i < bucketCount; i++) {
	            buckets.add(new ArrayList<Integer>());
	        }

	        // Distribute input array values into buckets
	        for (int i = 0; i < array.length; i++) {
	            buckets.get((array[i] - minValue) / bucketSize).add(array[i]);
	        }

	        // Sort buckets and place back into input array
	        int currentIndex = 0;
	        for (int i = 0; i < buckets.size(); i++) {
	            Integer[] bucketArray = new Integer[buckets.get(i).size()];
	            bucketArray = buckets.get(i).toArray(bucketArray);
	            InsertionSort.sort(bucketArray);
	            for (int j = 0; j < bucketArray.length; j++) {
	                array[currentIndex++] = bucketArray[j];
	            }
	        }
	    }
	}
}