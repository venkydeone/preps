package com.preps.ds.practice;

public class DataStructPractice {
	
	static LinkedList head ;
	static int headIndex, length, noOfComp;
	

	public static void main(String[] args) {
		testFindMaxOccurNoInArray();
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
	 * http://www.careercup.com/question?id=14916697
	 * There is a given linked list where each node can consist of any number of characters 
	 * Now please write a function where the linked list will return true if it is a palindrome . 
	 */
	static void testPalindromeLinkedList(){
		LinkedList head = new LinkedList("ab".toCharArray(), new LinkedList("cde".toCharArray(), new LinkedList("fgedc".toCharArray(), new LinkedList("ba".toCharArray(), null))));
		DataStructPractice.head = head;
		boolean isPal = isPalindrome(head);
		if(isPal){
			System.out.println("Palindrome");
		}else{
			System.out.println("Not Palindrome");
		}
	}
	
	 private static boolean isPalindrome(LinkedList node){
		boolean isPal = true;
		length += node.getData().length;
		if(node.getNode()!=null){
			isPal = isPal && isPalindrome(node.getNode());
		}
		if(isPal || node.getNode()==null){
			char[] headData = head.getData();
			char[] iterData = node.getData();
			int headLength = headData.length;
			for(int j=0;;headIndex++, j++){
				if(noOfComp >= DataStructPractice.length/2){
					return isPal;
				}
				if(headLength-1 < headIndex){
					headIndex = 0;
					head = head.getNode();
					headData = head.getData();
					headLength = headData.length;
				}
				if(iterData.length-1 < j){
					return true;
				}
				noOfComp++;
				if(headData[headIndex] != iterData[iterData.length-1-j]){
					return false;
				}
			}
		}
		return isPal;
		
	}
	static class LinkedList{
		public LinkedList(char[] data, LinkedList node){
			this.data = data;
			this.node = node;
		}
		private char[] data;
		public char[] getData() {
			return data;
		}
		public LinkedList getNode() {
			return node;
		}
		private LinkedList node;
	}
}

