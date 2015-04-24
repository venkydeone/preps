/**
 * 
 */
package com.preps.ds.practice;


public class LinkedListPractice {
	
	static LinkedList head ;
	static int headIndex, length, noOfComp;
	
	/**
	 * http://www.careercup.com/question?id=14916697
	 * There is a given linked list where each node can consist of any number of characters 
	 * Now please write a function where the linked list will return true if it is a palindrome . 
	 */
	static void testPalindromeLinkedList(){
		LinkedList head = new LinkedList("ab".toCharArray(), new LinkedList("cde".toCharArray(), new LinkedList("fgedc".toCharArray(), new LinkedList("ba".toCharArray(), null))));
		LinkedListPractice.head = head;
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
				if(noOfComp >= LinkedListPractice.length/2){
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
	
	
	/**
	 * https://leetcode.com/problems/remove-linked-list-elements/
	 *
	 */
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(val).append("-->");
			ListNode node= next;
			while(node!=null){
				sb.append(node.val).append("-->");
				node=node.next;
			}
			return sb.toString();
		}

		static ListNode removeElementsWithNewList(ListNode head, int val) {
			if(head==null)
				return head;
			ListNode temp = head;
			ListNode newNode = null;
			ListNode newNodeHead = null;
			while (temp != null) {
				if(temp.val == val){
					temp = temp.next;
				}
				else{
					if(temp!=null){
						if(newNode==null){
							newNode= new ListNode(temp.val);
							newNodeHead = newNode;
						}else{
							newNode.next = new ListNode(temp.val);
							newNode = newNode.next;
						}
					}
					temp = temp.next;
				}
			}
			return newNodeHead;
		}
		
		static ListNode removeElementsWithExistingNode(ListNode head, int val) {
			if(head==null)
				return head;
			if(head.val==val){
				head = head.next;
				return head;
			}
			ListNode temp = head;
			ListNode prev = temp;
			while (temp != null) {
				if(temp.val == val){
					prev.next = temp.next;
				}
				else{
					prev = temp;
				}
				temp = temp.next;
			}
			return head;
		}
		
		static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			if (l1 == null && l2 == null) {
				return null;
			}
			if (l1 == null && l2 != null) {
				return l2;
			}
			if (l1 != null && l2 == null) {
				return l1;
			}

			ListNode newList = null;
			ListNode head = null;
			int carryOverVal = 0;
			while (l1 != null && l2 != null) {
				int sum = l1.val + l2.val;
				sum = sum + carryOverVal;
				if (sum > 9) {
					carryOverVal = sum / 10;
					sum = sum % 10;
				}

				if (newList == null) {
					newList = new ListNode(sum);
					head = newList;
				} else {
					newList.next = new ListNode(sum);
					newList = newList.next;
				}
				l1 = l1.next;
				l2 = l2.next;
			}

			if (l1 != null) {
				while (l1 != null) {
					int sum = carryOverVal+l1.val;
					if (sum > 9) {
						carryOverVal = sum / 10;
						sum = sum % 10;
					}
					if (newList == null) {
						newList = new ListNode(sum);
						head = newList;
					} else {
						newList.next = new ListNode(sum);
						newList = newList.next;
					}
				}
			} else if (l2 != null) {
				while (l2 != null) {
					int sum = carryOverVal+l2.val;
					if (sum > 9) {
						carryOverVal = sum / 10;
						sum = sum % 10;
					}
					if (newList == null) {
						newList = new ListNode(sum);
						head = newList;
					} else {
						newList.next = new ListNode(sum);
						newList = newList.next;
					}
				}
			}else{
				newList.next = new ListNode(carryOverVal);
				newList = newList.next;
			}

			return head;
		}
		
		public static void main(String[] args) {
			ListNode val = new ListNode(1);
			val.next= new ListNode(2);
			val.next.next = new ListNode(3);
			val.next.next.next = new ListNode(8);
			val.next.next.next.next = new ListNode(93);			
			val.next.next.next.next.next = new ListNode(11);			
			val.next.next.next.next.next.next = new ListNode(12);			
			System.out.println(val);
			System.out.println(removeElementsWithNewList(val, 1));
			System.out.println(removeElementsWithExistingNode(val, 8));
			
			ListNode l1 = new ListNode(9);
			l1.next= new ListNode(2);
			l1.next.next = new ListNode(9);
			
			ListNode l2 = new ListNode(1);
			l2.next= new ListNode(2);
			l2.next.next = new ListNode(3);
			
			System.out.println(addTwoNumbers(l1, l2));
		}
	}
}