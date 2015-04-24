package com.preps.ds.practice;

import java.util.Stack;

public class StackPractice {
	
	public static void main(String[] args) {
/*		Tower[] towers = new Tower[3];
		for(int i=0;i<3;i++){
			towers[i] = new Tower(i);
		}
		int n=5;
		for(int i=n; i>=1;i--){
			towers[0].add(i);
		}
		System.err.println(towers[0].getStacks());
		System.err.println(towers[2].getStacks());
		towers[0].moveDisks(n, towers[2], towers[1]);
		System.err.println(towers[2].getStacks());
		System.err.println(towers[0].getStacks());*/
		
		MinStack stack = new MinStack();
		stack.push(5);
		stack.push(10);
		System.out.println(stack.getMin());
		System.out.println(stack.top());
		stack.push(-3);
		System.out.println(stack.getMin());
		System.out.println(stack.top());
		System.out.println(stack.pop());
		stack.push(4);
		stack.push(1);
		System.out.println(stack.getMin());
		System.out.println(stack.top());
		System.out.println(stack.pop());
		System.out.println(stack.getMin());
	}

	/**
	 * Towers of Hanoi Solution 
	 *
	 */
	static class Tower{
		private int index;
		private Stack<Integer> disks;
		public Tower(int i){
			index=i;
			disks = new Stack<Integer>();
		}
		
		private int index() {
			return index;
		}
		
		public Stack<Integer> getStacks(){
			return disks;
		}

		public void add(int i){
			if(!disks.isEmpty()&&disks.peek()<i){
				System.err.println("Invalid Insert");
			}else{
				disks.push(i);
			}
		}
		
		public void moveTopTo(Tower t){
			int top = disks.pop();
			t.add(top);
			System.out.println("Move disk " + top +" from " + index() + " to " + t.index());
		}
		
		public void moveDisks(int n, Tower dest, Tower buffer){
			if(n>0){
				moveDisks(n-1, buffer, dest);
				moveTopTo(dest);
				buffer.moveDisks(n-1, dest, this);
			}
		}

	}
	
	static class MinStack {
	    
	    int top=0;
	    int min = Integer.MAX_VALUE;
	    int [] array = new int[100000];
	    
	    public void push(int x) {
	        array[top++]=x;
	        if(x<min){
	            min = x;
	        }
	    }

	    public int pop() {
	        int pop=0;
	        if(array.length>0){
	            pop = array[--top];
	        } 
	        for(int i=0;i<array.length;i++){
	            if(array[i]<min){
	                min = array[i];
	            }
	        }
	        return pop;
	    }

	    public int top() {
	        return array[top-1];
	    }

	    public int getMin() {
	        return min;
	    }
	}
}

