package com.preps.practice.java;

public class BinaryInteger {

	public static void main(String[] args) {
		System.out.println("Maximum positive Integer Value is 				:" + ( (1<<31) - 1));
		System.out.println("Maximum positive Integer Value (in Binary ) is 	:" + Integer.toBinaryString(( (1<<31) - 1)));
		System.out.println("Minimum negative Integer Value is 				:" + ((1<<31)));
		System.out.println("Minimum negative Integer Value (in Binary ) is 	:" + Integer.toBinaryString(1<<31));
		System.out.println("Guess the value is 				:" + (1<<32));
		System.out.println("Guess the value is 				:" + Integer.toBinaryString(1<<32));
		System.out.println("Guess the value is 				:" +~0);
		System.out.println("Guess the value is 				:" +Integer.toBinaryString(~0));
	}
}
