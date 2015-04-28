package com.preps.practice.datastruct;


public class StringPractice {

	
	public static void main(String[] args) {
		System.out.println(countHi2("ahixhi"));
	}
	
	static void findNonUniqueChar(String str){
		str = str.toLowerCase();
		boolean[] availSet = new boolean[256];
		for(int i=0; i<str.length();i++){
			int val = str.charAt(i);
			if(!availSet[val]){
				availSet[val]=true;
			}
			else{
				System.err.println("Not Unique");
				return;
			}
		}
		System.out.println("Unique");
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
	
	static void checkRotatedWords(String base, String rotated){
		//base - watercooler
		//rotated - erwatercool (or) ercoolerwat
		if(base==null || rotated==null||base.length()!=rotated.length()){
			System.err.println("No Match");
			return;
		}
		int start=0,end=0;
		char[] rotChar = rotated.toCharArray();
		int j=0;
		for(int i=0; i<rotChar.length&&j<base.length(); i++){
			if(rotChar[i]==base.charAt(j)){
				j++;
				if(start==0)
					start=i;
			}else{
				end=i;
			}
		}
		if(start==0||end==0){
			System.err.println("No Match");
			return;
		}
		if( (rotated.substring(start)+rotated.substring(0, end+1)).equals(base)){
			System.out.println("Match");
		}else{
			System.err.println("No Match");
		}
	}
	
	
	/**
	 * http://codingbat.com/prob/p105722
	 */
	
	public String endX(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}

		int length = str.length();
		String firstCh = str.substring(0, 1);
		if (firstCh.equals("x")) {
			return endX(str.substring(1, length)) + firstCh;
		} else {
			return firstCh + endX(str.substring(1, length));
		}
	}
	
	/**
	 * https://leetcode.com/problems/excel-sheet-column-number/
	 * @param s
	 * @return
	 */
	public static int titleToNumberRec(String s) {
        char[] words = s.toCharArray();
        if(words.length==1){
            return ((int)(words[0])+1-65);
        }
        int length = s.length();
        return titleToNumberRec(s.substring(0,s.length()-1))*26 + ((int)(s.substring(length-1,length).toCharArray()[0])+1-65);
	}
	
	/**
	 * https://leetcode.com/problems/excel-sheet-column-number/
	 * 
	 * @param s
	 * @return
	 */
	public static int titleToNumber(String s) {
		if(s==null||s.length()==0){
			return 0;
		}
		char chArray[] = s.toCharArray();
		int length = s.length();
        int sum = 0;
		for(int i=0;i<length;i++){
			sum += ((int)(chArray[length-1-i])+1-65)*Math.pow(26, i);
        }
        return sum;
	}
	
	/**
	 * https://leetcode.com/problems/compare-version-numbers/ 
	 * 
	 * @param version1
	 * @param version2
	 * @return
	 */
	static int compareVersion(String version1, String version2) {
		 if (version1 == null && version2 == null) {
				return 0;
			}
			if (version1 != null && version2 == null) {
				return 1;
			}
			if (version1 == null && version2 != null) {
				return -1;
			}
			String[] values1 = version1.contains(".") ? version1.split("\\.")
					: new String[] { version1 };
			String[] values2 = version2.contains(".") ? version2.split("\\.")
					: new String[] { version2 };
			int maxLength = values1.length > values2.length ? values1.length
					: values2.length;
			int i = 0;
			while (i < maxLength) {
			    int val1=0,val2=0;
			    if(i>values1.length-1 && i>values2.length-1){
					return 0;
				}
				if (i <= values1.length - 1) {
					val1 = Integer.parseInt(values1[i]);
				}
				if (i <= values2.length - 1) {
					val2 = Integer.parseInt(values2[i]);
				}
				if (val1 > val2) {
					return 1;
				} else if (val2 > val1) {
					return -1;
				} else {
					i++;
				}
			}
			return 0;
	}
	
	/**
	 * https://leetcode.com/problems/valid-palindrome/ 
	 * @param s
	 * @return
	 */
    static boolean isPalindrome(String s) {
        String newString = s.toLowerCase().replaceAll("[^a-zA-Z0-9]","");
        char [] newChar = newString.toCharArray();
        
        for(int i=0;i <newChar.length/2;i++){
            if(newChar[i]!=newChar[newChar.length-1-i]){
                return false;
            }
        }
        return true;
    }
    
	static String stringClean(String str) {

		if (str == null || str.length() == 0) {
			return "";
		}
		
		if(str.length()<=1){
			return str.substring(0);
		}

		String firstChar = str.substring(0, 1);
		String secondChar = str.substring(1, 2);

		if (firstChar.equals(secondChar)) {
			return stringClean(str.substring(1));
		} else {
			return firstChar  + stringClean(str.substring(1));
		}
	}
	
	static int countHi2(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}

		char firstChar = str.charAt(0);
		int result = 0;

		if (firstChar == 'x') {
			if("hi".equals(str.substring(1, 3))){
				return result += countHi2(str.substring(3));
			}{
				return result += countHi2(str.substring(1));
			}
		}

		String strhi = str.substring(0, 2);

		if ("hi".equals(strhi)) {
			result++;
			return result += countHi2(str.substring(2));
		} else {
			 return result += countHi2(str.substring(1));
		}
	}

}
