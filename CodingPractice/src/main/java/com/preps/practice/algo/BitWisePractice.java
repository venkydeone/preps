package com.preps.practice.algo;

public class BitWisePractice {
	
	
	public static void main(String[] args) {
		System.out.println(divideTwoNumbers(1, -1));
	}
	
	/**
	 * Courtesy : http://www.geeksforgeeks.org/closest-next-smaller-greater-numbers-number-set-bits/
	 * @param n
	 * @return
	 */
	
	static int findMaxWithSameWeight(int n) {
		/* Compute c0 and c1 */
		int c = n;
		int c0 = 0;
		int c1 = 0;

		while (((c & 1) == 0) && (c != 0)) {
			c0++;
			c >>= 1;
		}
		while ((c & 1) == 1) {
			c1++;
			c >>= 1;
		}

		// If there is no bigger number with the
		// same no. of 1's
		if (c0 + c1 == 31 || c0 + c1 == 0)
			return -1;

		// position of rightmost non-trailing zero
		int p = c0 + c1;

		// Flip rightmost non-trailing zero
		n |= (1 << p);

		// Clear all bits to the right of p
		n &= ~((1 << p) - 1);

		// Insert (c1-1) ones on the right.
		n |= (1 << (c1 - 1)) - 1;

		return n;
	}
	
	/**
	 * Multiplying two numbers a, b --> by Shift and Add
	 * @param a
	 * @param b
	 * @return
	 */
	static long multiplyTwoNumbers(long a, long b){
		long res = 0;  // initialize result
	    // While second number doesn't become 1
	    while (b > 0)
	    {
	         // If second number becomes odd, add the first number to result
	         if ((b&1) ==1)
	             res = simpleAdd(res, a);
	         // Double the first number and halve the second number
	         a = a << 1;
	         b = b >> 1;
	     }
	     return res;
	}
	
	/**
	 * Addition of two numbers
	 * @param a
	 * @param b
	 * @return
	 */
	static long simpleAdd(long a, long b){
		return b==0?a:simpleAdd(a^b, (a&b)<<1);
	}
	
	/**
	 * Subtraction of two numbers
	 * @param a
	 * @param b
	 * @return
	 */
	static long simpleSub(long a, long b){
		return b==0?a:simpleSub(a^b, (~a&b)<<1);
	}
	
	/**
	 * Add numbers - Naive way..
	 */
	
	static long addTwoBinary(long a , long b){
		long idx=1, tempA = a, tempB = b;
		long carryIn =0, sum =0;
		while(tempA !=0 || tempB != 0){
			long aidx = (a & idx), bidx = (b & idx);
			long carryout = (aidx & bidx) | (aidx & carryIn) | (bidx & carryIn);
			sum |= (aidx ^ bidx ^ carryIn); 
			carryIn = carryout << 1;
			idx <<= 1;
			tempA >>>= 1;
			tempB >>>= 1;
		}
		return sum | carryIn;
	}
	
	
	/**
	 * Subtract two numbers.. logic almost same as Addition of two numbers
	 * @param x
	 * @param y
	 * @return
	 */
	static int subtract(int x, int y){
	    // Iterate till there is no carry
	    while (y != 0)
	    {
	        // borrow contains common set bits of y and unset
	        // bits of x
	        int borrow = (~x) & y;

	        // Subtraction of bits of x and y where at least
	        // one of the bits is not set
	        x = x ^ y;

	        // Borrow is shifted by one so that subtracting it from
	        // x gives the required sum
	        y = borrow << 1;
	    }
	    return x;
	}
	
	/**
	 * Divide two numbers
	 * @param x
	 * @param y
	 * @return
	 */
	static long divideTwoNumbers(long x, long y){
		long result=0;
		int power =32;
		long yPower = y << power;
		while(x >= y){
			while(yPower > x){
				yPower >>>= 1;
				--power;
			}
			
			result += 1L << power;
			x -= yPower;
		}
		return result;
	}
	
	/**
	 * Power of ..
	 * @param x
	 * @param y
	 * @return
	 */
	static double powerOf(double x, long y){
		double result = 1.0;
		long power = y;
		if( y < 0 ){
			power = -power;
			x = 1.0/x;
		}
		
		while(power!=0){
			if((power & 1) !=0){
				result *= x;
			}
			x *= x;
			power >>>= 1;
		}
		return result;
	}
	
	/**
	 * https://leetcode.com/problems/number-of-1-bits/
	 * 
	 * @param n
	 * @return
	 */
	static int hammingWeight(int n) {
		int count = 0;
		for (int i = 0; i < 32; i++) {
			if ((n & (1 << i)) != 0) {
				count++;
			}
		}
		return count;
	}
	
	static int hammingWeightOptimized(int n) {
		int count = 0;
		while(n>0) {
			n = n&(n-1);//Does the magic- How??
			count++;
		}
		return count;
	}
	

	/**
	 * https://leetcode.com/problems/reverse-bits/
	 * 
	 * @param n
	 * @return
	 */
	static int reverseBits(int n) {
		int result = 0;
		for (int i = 0; i < 32; i++) {
			if (((1 << i) & n) != 0)
				result |= 1 << (31 - i);
		}
		return result;
	}

}
