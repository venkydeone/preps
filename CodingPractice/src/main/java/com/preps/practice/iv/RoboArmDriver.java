package com.preps.practice.iv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class RoboArmDriver {

	List<Stack<Integer>> blocks = new ArrayList<Stack<Integer>>();
	Map<Integer, Integer> currentPositionMap = new HashMap<Integer, Integer>();
	
	public RoboArmDriver(int size){
		init(size);
	}

	private void init(int initVal) {
		if(!blocks.isEmpty())
			return;
		for (int i = 0; i < initVal; i++) {
			Stack<Integer> e = new Stack<Integer>();
			e.push(i);
			blocks.add(i, e);
		}
	}
	
	public void moveInstruction(int source , int target){
		validateAndMove(source,target);
		
		Stack<Integer> srcStack = this.blocks.get(source);
		Stack<Integer> targetStack = this.blocks.get(target);
		
		if(srcStack.peek()==source && targetStack.peek()==target){
			targetStack.push(srcStack.pop());
			currentPositionMap.put(source, target);
		}else{
			int currentPositionOfSource = source;
			int currentPositionOfTarget = target;
			if(srcStack.peek()!=source){
				currentPositionOfSource = currentPositionMap.get(source);
			}
			if(targetStack.peek()!=target){
				currentPositionOfTarget = currentPositionMap.get(target);
			}
			
			Stack<Integer> currentPositionOfSourceStack = this.blocks.get(currentPositionOfSource);
			Stack<Integer> currentPositionOfTargetStack = this.blocks.get(currentPositionOfTarget);
			currentPositionOfTargetStack.push(currentPositionOfSourceStack.pop());
			currentPositionMap.put(currentPositionOfSource, currentPositionOfTarget);
		}
	}

	private void validateAndMove(int source, int target) {
		
		
		Integer newSourcePosition = currentPositionMap.get(source);
		Stack<Integer> newSrcPosStack = this.blocks.get(newSourcePosition);
		if(newSrcPosStack.peek()==source){
			
		}
		
		Integer newTargetPosition = currentPositionMap.get(target);
		Stack<Integer> newTargetPosStack = this.blocks.get(newTargetPosition);
		if(newTargetPosStack.peek()==target){
			
		}
		
		Stack<Integer> targetStack = this.blocks.get(target);
		if(targetStack.size()>1){
			while(targetStack.size()>1){
				Integer prevValue = targetStack.pop();
				Stack<Integer> stack = this.blocks.get(prevValue);
				stack.push(prevValue);
				currentPositionMap.put(prevValue, prevValue);
			}
		}
		
		Stack<Integer> sourceStack = this.blocks.get(source);
		if(sourceStack.size()>1){
			while(sourceStack.size()>1){
				Integer prevValue = sourceStack.pop();
				Stack<Integer> stack = this.blocks.get(prevValue);
				stack.push(prevValue);
				currentPositionMap.put(prevValue, prevValue);
			}
		}
		
	}
	
	public static void main(String[] args) {
		RoboArmDriver driver = new RoboArmDriver(8);
		driver.moveInstruction(7, 1);
		driver.moveInstruction(5, 1);
		driver.moveInstruction(1, 6);
		driver.moveInstruction(4, 3);
		driver.moveInstruction(1, 4);
		
		System.out.println(driver.blocks);
		System.out.println(driver.currentPositionMap);
	}
}
