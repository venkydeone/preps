package com.preps.practice.datastruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;



public class StringPractice {

	
	private static int lo;
	private static int maxLen;

	public static void main(String[] args) throws InterruptedException {
//		System.out.println(minWindow("ABODECODEBANC", "ABC"));
//		String[] emails = new String[]{"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
//		System.out.println(numUniqueEmails(emails ));
		checkEqualsWithStringInterning();
		System.out.println(fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
		System.out.println(wordLadders("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
		System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
	}
	
	static void checkEqualsWithStringInterning(){
		String s1 = "Test";
        String s2 = "Test";
        String s3 = new String("Test");
        String s31 = new String(s1);
        final String s4 = s3.intern();
        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
        System.out.println(s3 == s4);
        System.out.println(s31 == s3);
        System.out.println(s2 == s4);
        System.out.println(s1.equals(s2));
        System.out.println(s2.equals(s3));
        System.out.println(s3.equals(s4));
        System.out.println(s1.equals(s4));
        System.out.println(s1.equals(s3));
	}
	
	static int numUniqueEmails(String[] emails) {
        Set<String> emailSet = new HashSet<>();
        if(emails==null || emails.length==0)
        	return emailSet.size();
        
        for(String email : emails){
        	int index = email.indexOf('@');
			String domain = email.substring(index);
			String name = email.substring(0,index);
        	if(name!=null){
        		if(name.contains("."))
        			name = name.replaceAll("\\.", "");
        		if(name.contains("+")){
        			name = name.replaceFirst("\\+", "~");
        			name = name.substring(0,name.indexOf('~'));
        		}
        	}
        	emailSet.add(name+domain);
        }
        return emailSet.size();
    }
	
	static String convertExcelRepresentation(int input) {
		if (input == 0) {
			return "";
		}

		int val = (input - 1) / 26; // 2 B
		int remainder = input - val * 26; // 1 A

		return convertExcelRepresentation(val) + map(remainder);
	}
	
	static String map(int in){
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "C");
		map.put(25, "Y");
		map.put(26, "Z");
		
		return map.get(in);
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
	
	static String reverseVowels(String s) {
        if(s==null || s.isEmpty()){
            return "";
        }
        
        char[] chArray = s.toCharArray();
        int l = 0; int r = chArray.length-1;
        while(l<r){
            while( l<r && (chArray[l]!='a' && chArray[l]!='A' && chArray[l]!='e' && chArray[l]!='E' && chArray[l]!='i' && chArray[l]!='I'&& chArray[l]!='o' && chArray[l]!='O' && chArray[l]!='u' && chArray[l]!='U') ){
                l++;
            }
            while( l<r && (chArray[r]!='a' && chArray[r]!='A' && chArray[r]!='e' && chArray[r]!='E' && chArray[r]!='i' && chArray[r]!='I'&& chArray[r]!='o' && chArray[r]!='O' && chArray[r]!='u' && chArray[r]!='U') ){
                r--;
            }
            if(l<r){
                char temp = chArray[l];
                chArray[l] = chArray[r];
                chArray[r] = temp;
                l++;
                r--;
            }
            else
                break;
        }
        return new String(chArray);
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
		String[] v1 = version1.split("\\.");
		String[] v2 = version2.split("\\.");

		for (int i = 0; i < Math.max(v1.length, v2.length); i++) {
			int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
			int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
			if (num1 < num2) {
				return -1;
			} else if (num1 > num2) {
				return +1;
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
	 * Solution from : https://discuss.leetcode.com/topic/23498/very-simple-clean-java-solution
	 * @param s
	 * @return
	 */
	static String longestPalindromicSubstring(String s){
		int len = s.length();
		if (len < 2)
			return s;
		
	    for (int i = 0; i < len-1; i++) {
	     	extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
	     	extendPalindrome(s, i, i+1); //assume even length.
	    }
	    return s.substring(lo, lo + maxLen);
	}

	static void extendPalindrome(String s, int j, int k) {
		while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
			j--;
			k++;
		}
		if (maxLen < k - j - 1) {
			lo = j + 1;
			maxLen = k - j - 1;
		}
	}
	/**
	 * https://leetcode.com/problems/longest-palindromic-substring/
	 * @param s
	 * @return
	 */
	static int longestPalindromicSubStringDP(String s){
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
	/**
	 * https://www.youtube.com/watch?v=NnD96abizww
	 * @param a
	 * @param b
	 * @return
	 */
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
	
	/**
	 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/ 
	 * @param digits
	 * @return
	 */
	static LinkedList<String> letterCombinations(String digits) {
        String[] letters = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        LinkedList<String> list = new LinkedList<String>();
        list.add("");
        for (int i = 0; i < digits.length(); i++) {
            int num = digits.charAt(i) - '2';
            int size = list.size();
            for (int k = 0; k < size; k++) {
                String tmp = list.pop();
                for (int j = 0; j < letters[num].length(); j++)
                    list.add(tmp + letters[num].charAt(j));
            }
        }
        return list;
    }
	
	static List<String> letterCombinationsRec(String digits) {
        String[] letters = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> rec = new LinkedList<String>();
        StringBuilder string = new StringBuilder();
        letterCombinationsRec(digits, 0, letters, string, rec);
        return rec;
    }
    
    private static void letterCombinationsRec(String digits, int number, String[] letters, StringBuilder string, List<String> rec) {
        if (digits.length() == number) {
            rec.add(string.toString());
            return;
        }
        String letter = letters[digits.charAt(number) - '2'];
        for (int i = 0; i < letter.length(); i++) {
            string.append(letter.charAt(i));
            letterCombinationsRec(digits, number + 1, letters, string, rec);
            string.deleteCharAt(string.length() - 1);
        }
    }
    
    
    /**
     * https://leetcode.com/problems/longest-common-prefix/
     * @param strs
     * @return
     */
    static String longestCommonPrefix(String[] strs) {
        if(strs ==null || strs.length==0){
            return "";
        }
        
        int minLength = Integer.MAX_VALUE;
        for(int i=0; i<strs.length; i++){
            minLength = minLength>=strs[i].trim().length()?strs[i].trim().length():minLength;
        }
        
        if(minLength==0){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        boolean allGood = true;
        for(int i=0; i<minLength && allGood; i++){
            char com = strs[0].charAt(i);
            for(int j=1; j<strs.length; j++){
                if(com != strs[j].charAt(i)){
                    allGood = false;
                    break;
                }
            }
            if(allGood){
                sb.append(com);
            }
        }
        
        return sb.toString();
    }
    
    /**
     * https://leetcode.com/problems/zigzag-conversion/#/description
     * @return
     */
    static String zigZagString(String s, int n){
    	
    	if(s==null||s.isEmpty()||n<=1){
    		return s;
    	}
    	
    	List<StringBuilder> sbList = new ArrayList<StringBuilder>();
    	for(int i=0;i<n;i++){
    		sbList.add(new StringBuilder());
    	}
    	
    	int si=0;
    	while(si<s.length()){
    		for(int sbi=0; sbi<n && si<s.length(); sbi++){
    			sbList.get(sbi).append(s.charAt(si++));
    		}
    		for(int sbi=n-2; sbi>=1 && si<s.length(); sbi--){
    			sbList.get(sbi).append(s.charAt(si++));
    		}
    	}
    	
    	for(int sbi=1; sbi<n;sbi++){
    		sbList.get(0).append(sbList.get(sbi));
    	}
    	
    	return sbList.get(0).toString();
    }
    
    static String convertToRoman(int number){
		TreeMap<Integer, String> numberToRomanMap = new TreeMap<Integer, String>(Collections.reverseOrder());
		numberToRomanMap.put(1, "I");
		numberToRomanMap.put(4, "IV");
		numberToRomanMap.put(5, "V");
		numberToRomanMap.put(9, "IX");
		numberToRomanMap.put(10, "X");
		numberToRomanMap.put(40, "XL");
		numberToRomanMap.put(50, "L");
		numberToRomanMap.put(90, "XC");
		numberToRomanMap.put(100, "C");
		numberToRomanMap.put(400, "CD");
		numberToRomanMap.put(500, "D");
		numberToRomanMap.put(900, "CM");
		numberToRomanMap.put(1000, "M");
		
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<Integer, String> key : numberToRomanMap.entrySet()){
			while(number>=key.getKey()){
				sb.append(key.getValue());
				number-=key.getKey();
			}
		}
		return sb.toString();
	}
    
    /**
     * https://leetcode.com/problems/add-strings
     * @param num1
     * @param num2
     * @return
     */
    static String addStrings(String num1, String num2) {
        if(num1==null || num1.isEmpty()){
            return num2;
        }
        if(num2==null || num2.isEmpty()){
            return num1;
        }
        
        StringBuilder sb = new StringBuilder();
        int carry=0;
        int i=num1.length()-1, j= num2.length()-1;
        
        while(i>=0 || j>=0 || carry>0){
            int sum = (i>=0 ? (num1.charAt(i--) - '0'): 0) + (j>=0 ? (num2.charAt(j--) - '0'): 0);
            if(carry>0)
                sum+=carry;
                
            sb.insert(0,sum%10);
            carry=sum/10;
        }
        
        return sb.toString();
    }
    
    /**
     * https://leetcode.com/problems/multiply-strings/#/description
     * @param num1
     * @param num2
     * @return
     */
    static String multiply(String num1, String num2) {
        if(num1==null || num2==null ){
            return null;
        }
        
        int[] multiply = new int[num1.length()+num2.length()];
        for(int i=num1.length()-1; i>=0; i--){
        	for(int j=num2.length()-1; j>=0; j--){
        		multiply[i+j+1]+=((num1.charAt(i)-'0')*(num2.charAt(j)-'0'));
        		multiply[i+j]+=(multiply[i+j+1]/10);
        		multiply[i+j+1]=(multiply[i+j+1]%10);
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        int allZeros=0;
        for( int n: multiply){
        	if(sb.length()==0 && n==0){
        	    allZeros++;
        		continue;
        	}
        	sb.append(n);
        }
        if(allZeros==multiply.length)
            return "0";
            
        return sb.toString();
    }
    
	static int rabinKarp(String t, String s) {
		if (s.length() > t.length()) {
			return -1; // s is not a substring of t.
		}

		final int BASE = 26;
		int tHash = 0, sHash = 0; // Hash codes for the substring of t and s.
		int powerS = 1; // BASE^|s|.
		for (int i = 0; i < s.length(); i++) {
			powerS = i > 0 ? powerS * BASE : 1;
			tHash = tHash * BASE + t.charAt(i);
			sHash = sHash * BASE + s.charAt(i);
		}

		for (int i = s.length(); i < t.length(); i++) {
			// Checks the two substrings are actually equal or not, to protect
			// against hash collision.
			if (tHash == sHash && t.substring(i - s.length(), i).equals(s)) {
				return i - s.length(); // Found a match.
			}

			// Uses rolling hash to compute the new hash code.
			tHash -= t.charAt(i - s.length()) * powerS;
			tHash = tHash * BASE + t.charAt(i);
		}
		// Tries to match s and t.substring(t.length() - s.length()).
		if (tHash == sHash && t.substring(t.length() - s.length()).equals(s)) {
			return t.length() - s.length();
		}
		return -1; // s is not a substring of t.
	}
	
	static String strStrKMP(String haystack, String needle) {
		//KMP algorithms
		if(needle.equals("")) return haystack;
		if(haystack.equals("")) return null;
		char[] arr = needle.toCharArray();
		int[] next = makeNext(arr);

		for(int i = 0, j = 0, end = haystack.length(); i < end;){
			if(j == -1 || haystack.charAt(i) == arr[j]){
				j++;
				i++;
				if(j == arr.length) return haystack.substring(i - arr.length);
			}
			if(i < end && haystack.charAt(i) != arr[j]) j = next[j];
		}
	    return null;
	}

	static int[] makeNext(char[] arr){
		int len = arr.length;
		int[] next = new int[len];

		next[0] = -1;
		for(int i = 0, j = -1; i + 1 < len;){
			if(j == -1 || arr[i] == arr[j]){
				next[i+1] = j+1;
				if(arr[i+1] == arr[j+1]) next[i+1] = next[j+1];
				i++;
				j++;
			}
			if(arr[i] != arr[j]) j = next[j];
		}

		return next;
	}
	/**
	 * Naive solution... Best case is O(n)
	 * but worst case is O(m*(n-m+1)) ; e.g. haystack --> AAAAAAAAAAAAAAAAB (length n); needle --> AAAB (length m)
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;
        int len1 = haystack.length(), len2 = needle.length(), i = 0, j = 0;
        for (; i < len1; i++) {
            if (j < len2 && haystack.charAt(i) == needle.charAt(j)) j++;
            else if (j == len2) break;
            else {
                i -= j;// without this; if needle word didn't match by last char, then we have to reset i. try input: mississippi/issip
                j = 0;
            }
        }

        return j != len2 ? -1 : i - j;
    
    }
	
	/**
	 * https://leetcode.com/problems/minimum-window-substring/
	 * @param S
	 * @param T
	 * @return
	 */
	static String minWindow(String S, String T) {
	    if(S==null||S.isEmpty()||T==null||T.isEmpty()) return "";
	    int left=0, right=0;
	    int[] Tmap=new int[256];
	    int[] Smap=new int[256];
	    for(int k=0; k< T.length(); k++){
	        Tmap[T.charAt(k)]++;
	    }
	    int found=0;
	    int length=Integer.MAX_VALUE;
	    String res="";
	    while(right<S.length()){
	        if(found<T.length()){
	            if(Tmap[S.charAt(right)]>0){
	                Smap[S.charAt(right)]++;
	                if(Smap[S.charAt(right)]<=Tmap[S.charAt(right)]){
	                    found++;
	                }
	            }
	            right++;
	        }
	        while(found==T.length()){
	            if(right-left<length){
	                length=right-left; res=S.substring(left,right);
	            }
	            if(Tmap[S.charAt(left)]>0){
	                Smap[S.charAt(left)]--;
	                if(Smap[S.charAt(left)]<Tmap[S.charAt(left)]){
	                    found--;
	                }
	            }
	            left++;
	        }
	    }
	    return res;
	}
	
	/**
	 * https://leetcode.com/problems/regular-expression-matching/
	 * 
	 * https://www.youtube.com/watch?v=l3hda49XcDE&t=204s
	 * 
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	static boolean isMatch(String s, String p) {
        if(s==null || p==null)
            return false;
        
        char[] text = s.toCharArray();
        char[] pattern = p.toCharArray();
        boolean T[][] = new boolean[text.length + 1][pattern.length + 1];

        T[0][0] = true;
        //Deals with patterns like a* or a*b* or a*b*c*
        for (int i = 1; i < T[0].length; i++) {
            if (pattern[i-1] == '*') {
                T[0][i] = T[0][i - 2];
            }
        }

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                if (pattern[j - 1] == '.' || pattern[j - 1] == text[i - 1]) {
                    T[i][j] = T[i-1][j-1];
                } else if (pattern[j - 1] == '*')  {
                    T[i][j] = T[i][j - 2];
                    if (pattern[j-2] == '.' || pattern[j - 2] == text[i - 1]) {
                        T[i][j] = T[i - 1][j];
                    }
                } else {
                    T[i][j] = false;
                }
            }
        }
        return T[text.length][pattern.length];
    }
	
	/**
	 * https://leetcode.com/problems/text-justification/discuss/181479/Very-easy-Java-Solution-with-explanation-beats-100
	 * @param words
	 * @param maxWidth
	 * @return
	 */
	static List<String> fullJustify(String[] words, int maxWidth) {
        
        List<String> result = new ArrayList<>();
        int len = -1 , count = -1, start = 0;
        
        for(int i=0;i<words.length;i++){            
            if(len+words[i].length()+1<=maxWidth){
                len += words[i].length()+1;
                count++;                
            }
            else{                 
                addLine(words,start,i-1,len,count,maxWidth,result,false);
                start = i;
                i--;
                len = -1;
                count= -1;
            }
        }
        addLine(words,start,words.length-1,len,count,maxWidth,result,true);
        return result;
    }
    
    static void addLine(String[] words,int start, int end, int len, int count, int maxWidth, List<String> result,boolean isLast){
        
            int spaces = maxWidth-len;
            spaces += count;         
            int same = isLast || (count==0)? 0 : spaces/count;
            int extra = isLast || (count==0)? count : spaces%count;        
            int trail = isLast || (count==0) ? maxWidth-len : 0;
						
            StringBuilder line = new StringBuilder();
                
            while(start<=end){
                line.append(words[start]);                    
                for(int k=0;k<same && start!=end;k++)
                    line.append(' ');
                    
                if(extra>0){
                    line.append(' ');
                    extra--;
                }
                start++;
            }        
            while(trail>0){
                line.append(' ');
                trail--;
            }                
            result.add(line.toString());
    }
    
    /**
     * https://leetcode.com/problems/basic-calculator-ii/discuss/63088/Explanation-for-Java-O(n)-time-and-O(1)-space-solution
     * @param s
     * @return
     */
	static int calculate2(String s) {
		int sum = 0;
		int tempSum = 0;
		int num = 0;
		char lastSign = '+';
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c))
				num = num * 10 + c - '0';
			if (i == s.length() - 1 || !Character.isDigit(c) && c != ' ') {
				switch (lastSign) {
				case '+':
					sum += tempSum;
					tempSum = num;
					break;
				case '-':
					sum += tempSum;
					tempSum = -num;
					break;
				case '*':
					tempSum *= num;
					break;
				case '/':
					tempSum /= num;
					break;
				}
				lastSign = c;
				num = 0;
			}
		}
		sum += tempSum;
		return sum;
	}
    
    /**
     * https://leetcode.com/problems/basic-calculator/discuss/62361/Iterative-Java-solution-with-stack
     * @param s
     * @return
     */
    static int calculate1(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                number = 10 * number + (int)(c - '0');
            }else if(c == '+'){
                result += sign * number;
                number = 0;
                sign = 1;
            }else if(c == '-'){
                result += sign * number;
                number = 0;
                sign = -1;
            }else if(c == '('){
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;   
                result = 0;
            }else if(c == ')'){
                result += sign * number;  
                number = 0;
                result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis
                
            }
        }
        if(number != 0) result += sign * number;
        return result;
    }
    
    /**
     * https://leetcode.com/problems/basic-calculator-iii/discuss/113600/Java-and-Python-O(n)-Solution-Using-Two-Stacks
     * @param s
     * @return
     */
    static int calculate3(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> nums = new Stack<>(); // the stack that stores numbers
        Stack<Character> ops = new Stack<>(); // the stack that stores operators (including parentheses)
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                num = c - '0';
                // iteratively calculate each number
                while (i < s.length() - 1 && Character.isDigit(s.charAt(i+1))) {
                    num = num * 10 + (s.charAt(i+1) - '0');
                    i++;
                }
                nums.push(num);
                num = 0; // reset the number to 0 before next calculation
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                // do the math when we encounter a ')' until '('
                while (ops.peek() != '(') nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
                ops.pop(); // get rid of '(' in the ops stack
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && precedence(c, ops.peek())) nums.push(operation(ops.pop(), nums.pop(),nums.pop()));
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) {
            nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
        }
        return nums.pop();
    }

    private static int operation(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b; // assume b is not 0
        }
        return 0;
    }
    // helper function to check precedence of current operator and the uppermost operator in the ops stack 
    private static boolean precedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }
    
    
    
    static List<List<String>> wordLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<List<String>>();
 
        HashSet<String> unvisited = new HashSet<>();
        unvisited.addAll(wordList);
 
        LinkedList<Node> queue = new LinkedList<>();
        Node node = new Node(beginWord,0,null);
        queue.offer(node);
 
        int minLen = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            Node top = queue.poll();
 
            //top if have shorter result already
            if(result.size()>0 && top.depth>minLen){
                return result;
            }
 
            for(int i=0; i<top.word.length(); i++){
                char c = top.word.charAt(i);
                char[] arr = top.word.toCharArray();
                for(char j='a'; j<='z'; j++){
                    if(j==c){
                        continue;
                    }
                    arr[i]=j;
                    String t = new String(arr);
 
                    if(t.equals(endWord)){
                        //add to result
                        List<String> aResult = new ArrayList<>();
                        aResult.add(endWord);
                        Node p = top;
                        while(p!=null){
                            aResult.add(p.word);
                            p = p.prev;
                        }
 
                        Collections.reverse(aResult);
                        result.add(aResult);
 
                        //stop if get shorter result
                        if(top.depth<=minLen){
                            minLen=top.depth;
                        }else{
                            return result;
                        }
                    }
 
                    if(unvisited.contains(t)){
                        Node n=new Node(t,top.depth+1,top);
                        queue.offer(n);
                        unvisited.remove(t);
                    }
                }
            }
        }
 
        return result;
    }

 
    static class Node{
        public String word;
        public int depth;
        public Node prev;

        public Node(String word, int depth, Node prev){
            this.word=word;
            this.depth=depth;
            this.prev=prev;
        }
    }
    
    static List<String> findRepeatedDnaSequences(String s) {
    	if(s.length() < 11) return new ArrayList<>();
        Set<Integer> words1 = new HashSet<>();
        Set<Integer> words2 = new HashSet<>();
        List<String> res = new ArrayList<>();
        char[] map = new char[26];
        map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;
        int val = 0;
        
        for(int i = 0; i < 10; i++){  // first value
    		val = val << 2;
    		val = val | map[s.charAt(i) - 'A'];
		}
		words1.add(val);

        for(int i = 1; i < s.length() - 9; i++){ 
    		val &= ~(3 << 18);
    		val = val << 2;
    		val = val | map[s.charAt(i+9) - 'A'];
        	if(!words1.add(val) && words2.add(val)){
        		res.add(s.substring(i, i + 10));
        	}
        }
        return res;
    }
    
    /**
     * https://leetcode.com/problems/remove-duplicate-letters/discuss/76766/Easy-to-understand-iterative-Java-solution
     * @param s
     * @return
     */
    static String removeDuplicateLetters(String s) {
        if (s == null || s.length() <= 1) return s;

        Map<Character, Integer> lastPosMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastPosMap.put(s.charAt(i), i);
        }

        char[] result = new char[lastPosMap.size()];
        int begin = 0, end = findMinLastPos(lastPosMap);

        for (int i = 0; i < result.length; i++) {
            char minChar = 'z' + 1;
            for (int k = begin; k <= end; k++) {
                if (lastPosMap.containsKey(s.charAt(k)) && s.charAt(k) < minChar) {
                    minChar = s.charAt(k);
                    begin = k+1;
                }
            }

            result[i] = minChar;
            if (i == result.length-1) break;

            lastPosMap.remove(minChar);
            if (s.charAt(end) == minChar) end = findMinLastPos(lastPosMap);
        }

        return new String(result);
    }

    static int findMinLastPos(Map<Character, Integer> lastPosMap) {
        if (lastPosMap == null || lastPosMap.isEmpty()) return -1;
        int minLastPos = Integer.MAX_VALUE;
        for (int lastPos : lastPosMap.values()) {
             minLastPos = Math.min(minLastPos, lastPos);
        }
        return minLastPos;
    }
}
