package com.preps.practice.java;

import java.util.Arrays;

public class PassByValueVsRef {
	
	String name;
	
	public static void main(String[] args) {
		demonstratePassByValue();
	}
	
	/**
	 * Java is always Pass By Value
	 * In below example, 
	 * 
	 * 1. the value of integer and String won't change after changeValue method call - obvious reason primitives - pass by value.
	 * 2. any change to the values of integer array or name attribute in PassByValueVsRef updates the original ref. IT DOESN'T MEAN JAVA IS PASS BY REFERENCE
	 * 3. refer the definition method swapRef
	 * 
	 * @param args
	 */
	static void demonstratePassByValue() {
		Integer oldVal = 6;
		String oldValueStr = "test";
		int [] newintvalues = {1,2,3};
		PassByValueVsRef ref = new PassByValueVsRef();
		ref.name = "testagain";
		
		PassByValueVsRef ref1 = new PassByValueVsRef();
		ref1.name="1";
		
		PassByValueVsRef ref2 = new PassByValueVsRef();
		ref2.name="2";
		
		
		System.out.println(oldVal);
		System.out.println(oldValueStr);
		System.out.println(Arrays.toString(newintvalues));
		System.out.println(ref.name);
		System.out.println("Before Swap");
		System.out.println("Ref1 : " + ref1.name + " Ref2 : " + ref2.name);
		
		changeValue(oldVal);
		changeValue(oldValueStr);
		changeValue(newintvalues); 
		changeValue(ref); 
		swapRef(ref1,ref2);
		
		System.out.println(oldVal);
		System.out.println(oldValueStr);
		System.out.println(Arrays.toString(newintvalues));
		System.out.println(ref.name);
		System.out.println("After Swap");
		System.out.println("Ref1 : " + ref1.name + " Ref2 : " + ref2.name);
	}
	
	
	/**
	 * newRef1, newRef2 are copies of ref1, ref2. They are created by Pass by Value.
	 * So any swap of reference ( newRef1, newRef2) won't impact the original reference ref1, ref2 - "Pass By Value"
	 * 
	 * As both reference newRef1, ref1 (or newRef2, ref2) point to same object, changing the name attribute via one will impact other but it doesn't mean Java is pass by reference
	 * 
	 * @param newRef1
	 * @param newRef2
	 */
	private static void swapRef(PassByValueVsRef newRef1, PassByValueVsRef newRef2) {
		PassByValueVsRef temp = newRef1;
		newRef1=newRef2;
		newRef2=temp;
	}

	static void changeValue(int val){	
		val = 10;
	}
	
	static void changeValue(String val){
		val = "newTest";
	}
	
	static void changeValue(int[] arr){
		arr[0]=9;
	}
	
	static void changeValue(PassByValueVsRef newRef){
		//newRef = new PassByValueVsRef();
		newRef.name = "newTestAgain";
	}
}
