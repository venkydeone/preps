package com.preps.algo.practice;

public class AlgoPractice {
	
	
	public static void main(String[] args) {
		//testUnsortedArray();
		allReverseWordUsecases();
	}
	
	static void allReverseWordUsecases(){
		/*
		 * http://www.careercup.com/question?id=5697358784364544
		 * I/P : I am an human being
		 * O/P : I ma na namuh gnieb
		 */
		reverseWordinSentence("I have a Java textbook");
		reverseWordinSentence("I have		a		 Java textbook");
		/*
		 * I/P : I have a Java textbook
		 * O/P : textbook Java a have I
		 */
		reversePositionWordsinSentence("I have a Java textbook");
		reversePositionWordsinSentence("I have a Java 		textbook");
	}

	static void reverseWordinSentence(String sentence){
		StringBuilder sb = new StringBuilder();
		String[] split = sentence.split("\\s+");
		for(String str : split){
			char[] charArray = str.toCharArray();
			char[] reversedWord = new char[str.length()];
			for(int i=0; i< charArray.length; i++){
				reversedWord[charArray.length-1-i]=charArray[i];
			}
			sb.append(reversedWord).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	static void reversePositionWordsinSentence(String sentence){
		StringBuilder sb = new StringBuilder();
		String[] split = sentence.split("\\s+");
		for(int i=0; i< split.length; i++){
			sb.append(split[split.length-1-i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}
