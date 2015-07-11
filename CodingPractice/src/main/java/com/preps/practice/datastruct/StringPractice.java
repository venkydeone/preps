package com.preps.practice.datastruct;

import java.util.HashMap;



public class StringPractice {

	
	public static void main(String[] args) throws InterruptedException {
		System.out.println(isPalindromeRec("abadba"));
				
	}

	private static void checkHashCode() {
		String string = "venkat";
		int chash1 = hashCode(string);
		int hash1 = string.hashCode();
		int hash2 = "taknev".hashCode();
		System.out.println(hash1==hash2);
	}
	
	static int hashCode(String vals) {
		int h=0;
		char [] value = vals.toCharArray();
        if (value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
        }
        return h;
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
		//base - watercooler, waterwatch
		//rotated - erwatercool (or) ercoolerwat, watchwater
		if(base==null || rotated==null||base.length()!=rotated.length()){
			System.err.println("No Match");
			return;
		}
		int start=0,end=0;
		char[] rotChar = rotated.toCharArray();
		for(int i=0, j=0; i<rotated.length()&&j<base.length(); i++){
			if(rotChar[i]==base.charAt(j)){
				j++;
				if(start==0)
					start=i;
			}else{
				end=i;
				if(start!=0){
					start=0;
					j=0;
				}
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
        return titleToNumberRec(s.substring(0,s.length()-1))*26 + ((int)(s.substring(length-1).toCharArray()[0])+1-65);
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
			int maxLength = Math.max(values1.length, values2.length);
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
    
    static boolean isPalindromeRec(String s) {
    	if(s==null || s.length()==0 || s.length()==1 ){
    		return true;
    	}
    	if(s.charAt(0)==s.charAt(s.length()-1)){
    		return isPalindromeRec(s.substring(1, s.length()-1));
    	}
    	return false;
    }
    
   /**
    * http://codingbat.com/prob/p104029
    * @param str
    * @return
    */
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
	
	
	/**
	 * http://codingbat.com/prob/p143900
	 * @param str
	 * @return
	 */
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
	
	/**
	 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
	 * @param s
	 * @return
	 */
	static int longestSubstringWithUniqueChars(String s) {
        int currentLength = 1;
        int maxLength =1;
        int prev_index; // index of previous occurrence of that particular character
        
        int visited[] = new int[256];
        for(int i=0;i<256;i++){
        	visited[i]=-1;
        }
        char[] input = s.toCharArray();
        
        visited[(int)input[0]] = 0;
        
        for(int i=1;i<input.length;i++){
        	prev_index = visited[(int)input[i]];
        	
        	if(prev_index==-1 || i - currentLength > prev_index){// when current Length is reset after a character's previous occurence, make sure any second occurrence from that isn't having greater prev_index ... e.g. for input abba : expected output is 2. but if second part of condition is removed then you'll get wrong answer because the second occurence of 'a' should be ignored after current length reset.
        		currentLength++;
        	}else{
        		if(currentLength>maxLength){
        			maxLength=currentLength;
        		}
        		currentLength = i- prev_index; // if same character is occurring for second time, reset new currentlength since the character's previous occurrence. For input : "dvdf" after 'd' occurs 2nd time, reset currentlength from v again .
        	}
        	visited[(int)input[i]]=i;
        }
        if(currentLength>maxLength)
        	maxLength = currentLength;
        
        return maxLength;
    }
	
	
	/**
	 * https://leetcode.com/problems/longest-palindromic-substring/
	 * @param s
	 * @return
	 */
	static int longestPalindromicSubString(String s){
		if(s==null || s.isEmpty()){
			return 0;
		}
		int maxLength=1,start=0;
		int length = s.length();
		char[] charArray = s.toCharArray();
		boolean pal[][] = new boolean[length][length];
		for(int i=0;i<length;i++){
			pal[i][i] = true;
		}
		
		for(int i=0;i<length-1;i++){
			if(charArray[i]==charArray[i+1]){
				start = i;
				maxLength=2;
				pal[i][i+1]= true;
			}
		}
		
		for(int k=3; k<=length ; k++){
			for(int i=0;i<length-k+1;i++){
				int j=i+k-1;
				
				if(pal[i+1][j-1] && charArray[i]==charArray[j]){
					pal[i][j]=true;
					if(k>maxLength){
						start = i;
						maxLength=k;
					}		
				}
			}
		}
		System.out.println("Palidrome is :" + s.substring(start,start+maxLength));
		return maxLength;
	}
	
	static String longestCommonSubsequenceRec(String a, String b){
	    int aLen = a.length();
	    int bLen = b.length();
	    if(aLen == 0 || bLen == 0){
	        return "";
	    }else if(a.charAt(aLen-1) == b.charAt(bLen-1)){
	        return longestCommonSubsequenceRec(a.substring(0,aLen-1),b.substring(0,bLen-1))
	            + a.charAt(aLen-1);
	    }else{
	        String x = longestCommonSubsequenceRec(a, b.substring(0,bLen-1));
	        String y = longestCommonSubsequenceRec(a.substring(0,aLen-1), b);
	        return (x.length() > y.length()) ? x : y;
	    }
	}
	
	static String longestCommonSubsequence(String a, String b) {
	    int[][] lengths = new int[a.length()+1][b.length()+1];
	 
	    // row 0 and column 0 are initialized to 0 already
	 
	    for (int i = 0; i < a.length(); i++)
	        for (int j = 0; j < b.length(); j++)
	            if (a.charAt(i) == b.charAt(j))
	                lengths[i+1][j+1] = lengths[i][j] + 1;
	            else
	                lengths[i+1][j+1] =
	                    Math.max(lengths[i+1][j], lengths[i][j+1]);
	 
	    // read the substring out from the matrix
	    StringBuffer sb = new StringBuffer();
	    for (int x = a.length(), y = b.length();
	         x != 0 && y != 0; ) {
	        if (lengths[x][y] == lengths[x-1][y])
	            x--;
	        else if (lengths[x][y] == lengths[x][y-1])
	            y--;
	        else {
	            assert a.charAt(x-1) == b.charAt(y-1);
	            sb.append(a.charAt(x-1));
	            x--;
	            y--;
	        }
	    }
	 
	    return sb.reverse().toString();
	}
	
	
	static Integer toInteger(String number){
		if(number==null)
			return 0;
		
		char [] nums = number.toCharArray();
		Integer output = 0;
		boolean setNegative = false;
		for(char n : nums){
			if(n=='-'){
				setNegative = true;
			}else{
				output = output * 10 + new Integer(n+"");
			}
		}
		if(setNegative)
			return -1* output;
		
		return output;
	}
	
	static void findRepWord(String word) {
		if (word == null)
			return;
		String[] words = word.split("\\s");
		if (words.length == 1)
			return;

		HashMap<String, Integer> wordToIndex = new HashMap<String, Integer>();
		int minDiff = Integer.MAX_VALUE;
		String minWord = null;

		for (int i = 0; i < words.length; i++) {

			if (wordToIndex.containsKey(words[i])) {
				Integer prevIndex = wordToIndex.get(words[i].toLowerCase());
				if (i - prevIndex < minDiff) {
					minDiff = i - prevIndex;
					minWord = words[i].toLowerCase();
				}
			} else {
				wordToIndex.put(words[i].toLowerCase(), i);
			}
		}
		System.out.println(minWord);
	}
	
	static void pangrams(String input) {
        input = input.toLowerCase();
        
        boolean[] check = new boolean[26];
        int count = 0;
        for(char ch : input.toCharArray()){
            int asciiVal = (int)ch;
            asciiVal -=97;
            if(asciiVal>=0&&asciiVal<=25 && !check[asciiVal]){
                check[asciiVal]=true;
                count++;
            }
        }
        
        if(count!=26){
            System.out.println("not pangram");
        }else{
            System.out.println("pangram");   
        }
    }
	
	static void permutation(String str) { 
	    permutation("", str); 
	}

	private static void permutation(String prefix, String str) {
	    int n = str.length();
	    if (n == 0) System.out.println(prefix);
	    else {
	        for (int i = 0; i < n; i++)
	            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
	    }
	}
}
