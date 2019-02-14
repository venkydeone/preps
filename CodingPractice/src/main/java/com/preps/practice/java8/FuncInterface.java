/**
 * 
 */
package com.preps.practice.java8;

/**
 * @author VP
 *
 */
public class FuncInterface {

	public static class CustomImpl implements Functional1, Functional2 {

		@Override
		public void print() {
			Functional1.super.print();
		}
		
		public static void main(String[] args) {
			CustomImpl customImpl = new CustomImpl();
			customImpl.print();
			Functional1.log();
			Functional2.log();
		}
	}
	
	public static class Impl1 implements Functional1{
		public static void main(String[] args) {
			Impl1 impl = new Impl1();
			impl.print();
			Functional1.log();
			Functional2.log();
		}
	}
	
/*	Uncomment to see the compilation error
 * 	public static class DiamondImpl implements Functional1, Functional2 {

		//Either this class should implement print similar to CustomImpl or implement either one of the interfaces
		
		//Also you can't override static method of the interfaces
		private void log() {
			// TODO Auto-generated method stub

		}
		
	}
	*/
}


interface Functional1{
	default void print(){
		System.out.println("Interface - Functional1");
	}
	
	static void log(){
		System.out.println("Logger - Functional1");
	}
}

interface Functional2{
	default void print(){
		System.out.println("Interface - Functional2");
	}
	
	static void log(){
		System.out.println("Logger - Functional2");
	}
}