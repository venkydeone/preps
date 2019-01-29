/**
 * 
 */
package com.preps.practice.datastruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
			if(l1==null) 
	            return l2;
	        if(l2 == null)
	            return l1;
	        int carry=0;
	        ListNode head = null;
	        ListNode temp = null;
	        while(l1!=null || l2!=null){
	            int sum = carry + (l1==null?0:l1.val) + (l2==null?0:l2.val);
	            if(head==null){
	                head = new ListNode(sum%10);
	                temp = head;
	            }else{
	                temp.next = new ListNode(sum%10);
	                temp=temp.next;
	            }
	            carry = sum/10;
	            if(l1!=null)
	                l1=l1.next;
	            if(l2!=null)
	                l2=l2.next;
	        }
	        
	        if(carry==1){
	        	temp.next=new ListNode(carry);
	        }
	        
	        return head; 
		}
		
		static ListNode reverse(ListNode inputNode){
			ListNode newNode= null;
			ListNode temp = inputNode;
			while(temp!=null){
				ListNode prev = newNode;
				newNode = temp;
				temp= temp.next; // if we swap this and next line , logic won't work, WHY????
				newNode.next = prev;
			}
			return newNode;
		}
		
		/**
		 * Recursive Reverse LinkedList
		 * @param inputNode
		 * @param prevNode
		 * @return
		 */
		static ListNode reverseRec(ListNode inputNode, ListNode prevNode){
			if(inputNode==null){
				return prevNode;
			}else{
				ListNode next = inputNode.next;
				inputNode.next = prevNode;
				return reverseRec(next, inputNode);
			} 
		}
		
		/**
		 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
		 * @param head
		 * @param n
		 * @return
		 */
		static ListNode removeNthFromEnd(ListNode head, int n) {
			if (head == null || n < 1) {
				return null;
			}
			ListNode temp = head;
			int firstPointer = 0;
			ListNode prev = null;
			while (temp != null) {
				if (prev != null)
					prev = prev.next;
				if (firstPointer == n) {
					prev = head;
				}
				firstPointer++;
				temp = temp.next;
				System.out.println(head);
			}
			if (prev != null) {
				prev.next = prev.next.next;
			} else {
				if (firstPointer == n) {
					head = head.next;
				}
			}
			return head;
		}
		
		/**
		 * https://leetcode.com/problems/reverse-linked-list-ii/
		 * 
		 * @param head
		 * @param m
		 * @param n
		 * @return
		 */
		static ListNode reverseBetween(ListNode head, int m, int n) {
	        ListNode outerNewNode = null;
	        ListNode innerNewNode = null;
	        ListNode innerEndNode = null;
	        int index=1;
	        while(head!=null){
	            if(index<=m&&index>=n){
	                ListNode prev = innerNewNode;
	                innerNewNode = head;
	                if(innerEndNode==null){
	                	innerEndNode = innerNewNode;
	                }
	                head = head.next;
	                innerNewNode.next = prev;
	                if(index==n){
	                	outerNewNode.next = innerNewNode;
	                }
	            }
	            else{
	            	if(innerNewNode!=null){
	            		outerNewNode = innerEndNode;
	            		outerNewNode.next = head;
	            	}else{
	            		outerNewNode = head;
	            	}
	            	head = head.next;
	            }
	            index++;
	        }
	        return outerNewNode;
	    }
		
		/**
		 * Partition the linked list by the boundary number - so the value less than boundary will be in left of list and remaining in right of it.
		 * @param node
		 * @param boundary
		 * @return
		 */
		static ListNode partition(ListNode node, int boundary){
			ListNode head = node;
			ListNode tail = node;
			
			while(node!=null){
				ListNode next = node.next;
				if(node.val<boundary){
					node.next = head;
					head = node;
				}else{
					tail.next = node;
					tail = node;
				}
				node = next;
			}
			tail.next=null;
			return head;
		}
		
		//1->2->3->4->5->N
		static ListNode swapPairNodes(ListNode head){
		    ListNode dummy = new ListNode(0);
		    dummy.next = head;
		    ListNode current = dummy;
		    while (current.next != null && current.next.next != null) {
		        ListNode first = current.next;
		        ListNode second = current.next.next;
		        first.next = second.next;
		        current.next = second;
		        current.next.next = first;
		        current = current.next.next;
		    }
		    return dummy.next;
		}
		
		
		
		public static void main(String[] args) {
			ListNode val = new ListNode(1);
			val.next= new ListNode(2);
			val.next.next = new ListNode(3);
			
			ListNode val1 = new ListNode(1);
			val1.next= new ListNode(2);
			val1.next.next = new ListNode(7);
			
			System.out.println(val);
			System.out.println(val1);
			System.out.println(addTwoNumbers(val, val1));
			
			
			ListNode val2 = new ListNode(1);
			val2.next= new ListNode(2);
			val2.next.next = new ListNode(3);
			val2.next.next.next = new ListNode(4);
			val2.next.next.next.next = new ListNode(5);
			
			System.out.println(swapPairNodes(val2));
			
		}
	}
	
	static class SortLinkedList {
		 
		// merge sort
		public static ListNode mergeSortList(ListNode head) {
	 
			if (head == null || head.next == null)
				return head;
	 
			// count total number of elements
			int count = 0;
			ListNode p = head;
			while (p != null) {
				count++;
				p = p.next;
			}
	 
			// break up to two list
			int middle = count / 2;
	 
			ListNode l = head, r = null;
			ListNode p2 = head;
			int countHalf = 0;
			while (p2 != null) {
				countHalf++;
				ListNode next = p2.next;
	 
				if (countHalf == middle) {
					p2.next = null;
					r = next;
				}
				p2 = next;
			}
	 
			// now we have two parts l and r, recursively sort them
			ListNode h1 = mergeSortList(l);
			ListNode h2 = mergeSortList(r);
	 
			// merge together
			ListNode merged = merge(h1, h2);
	 
			return merged;
		}
	 
		public static ListNode merge(ListNode l, ListNode r) {
			ListNode p1 = l;
			ListNode p2 = r;
	 
			ListNode fakeHead = new ListNode(100);
			ListNode pNew = fakeHead;
	 
			while (p1 != null || p2 != null) {
	 
				if (p1 == null) {
					pNew.next = new ListNode(p2.val);
					p2 = p2.next;
					pNew = pNew.next;
				} else if (p2 == null) {
					pNew.next = new ListNode(p1.val);
					p1 = p1.next;
					pNew = pNew.next;
				} else {
					if (p1.val < p2.val) {
						// if(fakeHead)
						pNew.next = new ListNode(p1.val);
						p1 = p1.next;
						pNew = pNew.next;
					} else if (p1.val == p2.val) {
						pNew.next = new ListNode(p1.val);
						pNew.next.next = new ListNode(p1.val);
						pNew = pNew.next.next;
						p1 = p1.next;
						p2 = p2.next;
	 
					} else {
						pNew.next = new ListNode(p2.val);
						p2 = p2.next;
						pNew = pNew.next;
					}
				}
			}
	 
			// printList(fakeHead.next);
			return fakeHead.next;
		}
	 
		public static void main(String[] args) {
			ListNode n1 = new ListNode(2);
			ListNode n2 = new ListNode(3);
			ListNode n3 = new ListNode(4);
	 
			ListNode n4 = new ListNode(3);
			ListNode n5 = new ListNode(4);
			ListNode n6 = new ListNode(5);
	 
			n1.next = n2;
			n2.next = n3;
			n3.next = n4;
			n4.next = n5;
			n5.next = n6;
	 
			n1 = mergeSortList(n1);
	 
			printList(n1);
		}
	 
		public static void printList(ListNode x) {
			if(x != null){
				System.out.print(x.val + " ");
				while (x.next != null) {
					System.out.print(x.next.val + " ");
					x = x.next;
				}
				System.out.println();
			}
	 
		}
	}
	
	static class LRUCache {
		Map<Integer, Node> map = new HashMap<>();
		Node head = new Node(-1, -1);
		Node tail = new Node(-1, -1);
		int capacity;

		public LRUCache(int capacity) {
		    join(head, tail);
		    this.capacity = capacity;
		}

		public int get(int key) {
		    if (!map.containsKey(key)) {
		        return -1;
		    }
		    Node node = map.get(key);
		    remove(node);
		    moveToHead(node);
		    return node.val;
		}

		public void set(int key, int value) {
		    if (map.containsKey(key)) {
		        Node node = map.get(key);
		        node.val = value;
		        remove(node);
		        moveToHead(node);
		    } else {
		        if (map.size() == capacity) {
		            if (tail.prev != head) {
		                map.remove(tail.prev.key);
		                remove(tail.prev);
		            }
		        }
		        Node node = new Node(key, value);
		        moveToHead(node); 
		        map.put(key, node);
		    }       
		}   
		    
		public void join(Node n1, Node n2) {
		    n1.next = n2;
		    n2.prev = n1;
		}

		public void remove(Node node) {
		    node.prev.next = node.next;
		    node.next.prev = node.prev;
		}

		public void moveToHead(Node node) {
		    Node next = head.next; 
		    join(head, node);
		    join(node, next);
		}

		class Node {
		    Node prev;
		    Node next;
		    int key;
		    int val;
		    public Node(int key, int val) {
		        this.key = key;
		        this.val = val;
		    }
		}
	}
}