package com.preps.algo.practice;

import java.util.ArrayList;
import java.util.List;

public class AlgoPractice {
	
	
	public static void main(String[] args) {
		//allReverseWordUsecases();
		findRangeAcrossNumbersList();
	}
	
	
	static void allReverseWordUsecases(){
		
		reverseWordinSentence("I have a Java textbook");
		reverseWordinSentence("I have		a		 Java textbook");
		
		reversePositionWordsinSentence("I have a Java textbook");
		reversePositionWordsinSentence("I have a Java 		textbook");
	}

	/**
	 * @see <a href="http://www.careercup.com/question?id=5697358784364544">Reversing word usecase</a>
	 * <br>
	 * I/P : I am an human being
	 * <br>
	 * O/P : I ma na namuh gnieb
	 */
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
	
	/**
	 * @see <a href="http://www.careercup.com/question?id=5697358784364544">Reversing word usecase</a>
	 * <br>
	 * I/P : I have a Java textbook
	 * <br>
	 * O/P : textbook Java a have I
	 */
	static void reversePositionWordsinSentence(String sentence){
		StringBuilder sb = new StringBuilder();
		String[] split = sentence.split("\\s+");
		for(int i=0; i< split.length; i++){
			sb.append(split[split.length-1-i]).append(" ");
		}
		System.out.println(sb.toString());
	}

	/**
	 * 
	 * @see <a href="http://www.careercup.com/question?id=16759664">Find the shortest range in the list of numbers</a>
	 * 
	 * @Description
	 * 
	 * You have k lists of sorted integers. Find the smallest range that includes at least one number from each of the k lists.
	 * For example,
	 * List 1: [4, 10, 15, 24, 26]
	 * List 2: [0, 9, 12, 20]
	 * List 3: [5, 18, 22, 30]
	 * The smallest range here would be [20, 24] as it contains 24 from list 1, 20 from list 2, and 22 from list 3.
	 * 
	 */
	static void findRangeAcrossNumbersList(){
		boolean firstRun = true;
		int lengthExceeded=0;
		int bestMax = Integer.MIN_VALUE;
		int bestMin = Integer.MAX_VALUE;
		int bestRange = Integer.MAX_VALUE;
		int currentMax = 0;
		List<Integer> finalList = new ArrayList<Integer>();
		List<Integer> currentList = new ArrayList<Integer>();
		
		Integer[][] twoDArray= {
								new Integer[]{4, 10, 15, 19, 24, 26},
								new Integer[]{0, 9, 12, 17, 21, 27},
								new Integer[]{5, 14, 18, 22, 30},
								new Integer[]{31, 34, 50, 55}
								};
		for(int i=0;lengthExceeded<twoDArray.length;i++){
			for(int j=0;j<twoDArray.length;j++){
				if(i>=twoDArray[j].length){
					lengthExceeded++;
					continue;
				}
				Integer currentVal = twoDArray[j][i];
				if(firstRun){
					finalList.add(j, currentVal);
				}else{
					Integer oldValue = finalList.get(j);
					if((currentMax > 0 && oldValue == currentMax) || (currentMax ==0 && oldValue==bestMax)){
						continue;
					}else{
						currentList.remove(j);
						currentList.add(j, currentVal);
						BestElement bestElement = findBestElement(currentList);
						currentMax = bestElement.getMax();
						if(bestElement.getRange()<=bestRange){
							bestRange=bestElement.getRange();
							bestMin = bestElement.getMin();
							bestMax = bestElement.getMax();
							copyList(finalList, currentList);
						}
					}
				}
			}
			if(firstRun){
				BestElement findBestElement = findBestElement(finalList);
				bestMax  = findBestElement.getMax();
				bestMin = findBestElement.getMin();
				bestRange = findBestElement.getRange();
				copyList(currentList, finalList);
			}
			firstRun = false;
		}
		
		System.err.println("Best Range is.... ");
		System.err.println( "Best Max : " + bestMax + " ; " +  " Best Min : " + bestMin + " ; " + " Best Range  : " + bestRange + " ; ");
	}
	
	private static void copyList(List<Integer> currentList,
			List<Integer> finalList) {
		currentList.clear();
		for(int i=0;i<finalList.size();i++){
			currentList.add(i, finalList.get(i));
		}
	}


	private static BestElement findBestElement(List<Integer> finalList) {
		int newRange = Integer.MAX_VALUE;
		int prevMin = Integer.MAX_VALUE;
		int prevMax = Integer.MIN_VALUE;
		for(int i=0;i<finalList.size();i++){
			Integer currentValue = finalList.get(i);
			if(currentValue>prevMax){
				prevMax=currentValue;
			}
			if(currentValue<prevMin){
				prevMin = currentValue;
			}
		}
		if(prevMin!=prevMax && (prevMax-prevMin)<newRange){
			newRange= prevMax-prevMin;
		}
		System.out.println( " Best Max : " + prevMax + " ; " +  " Best Min : " + prevMin + " ; " + " Best Range  : " + newRange + " ; ");
		return new BestElement(prevMin, prevMax, newRange);
	}
	
	public static class BestElement{
		
		public BestElement(int min, int max, int range) {
			super();
			this.min = min;
			this.max = max;
			this.range = range;
		}
		private int min;
		public int getMin() {
			return min;
		}
		public void setMin(int min) {
			this.min = min;
		}
		public int getMax() {
			return max;
		}
		public void setMax(int max) {
			this.max = max;
		}
		public int getRange() {
			return range;
		}
		public void setRange(int range) {
			this.range = range;
		}
		private int max;
		private int range;
	}
	
}

