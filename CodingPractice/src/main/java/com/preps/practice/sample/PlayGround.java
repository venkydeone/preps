package com.preps.practice.sample;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TimeZone;

public class PlayGround {
	public static void main(String[] args) {
		
        String a1 = "0XF";
        System.out.println(Integer.parseInt("F", 16));
        
        System.out.println(reverse("Venkat"));
		
		Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        HashSet<Character> charset = new HashSet<Character>();
        input = input.toLowerCase();
        for(char a : input.toCharArray()){
            if(a>97||a<122 && a!=' '){
                charset.add(a);
            }
        }
        if(charset.size()==26){
            System.out.println("pangram");
        }else{
            System.out.println("not pangram");
        }
		
		List<String> values = new ArrayList<String>();
		values.add("ab");
		values.add("cd");
		values.add("de");
		values.add("fg");
		Iterator<String> valuesIter = values.iterator();
		while(valuesIter.hasNext()){
			valuesIter.remove();
		}
		
		Date currentTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		System.out.println(dateFormat.format(currentTime));
		dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
		System.out.println(dateFormat.format(currentTime));
		dateFormat.setTimeZone(TimeZone.getTimeZone("MST"));
		System.out.println(dateFormat.format(currentTime));
		dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		System.out.println(dateFormat.format(currentTime));
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.println(dateFormat.format(currentTime));
		
		System.err.println("******************");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.format(currentTime);
		System.err.println(new Date(currentTime.getTime()));
		System.err.println(new Date(currentTime.getTime() + TimeZone.getTimeZone("GMT").getRawOffset()));
		System.err.println(new Date(currentTime.getTime() + TimeZone.getTimeZone("UTC").getRawOffset()));
		System.err.println(new Date(currentTime.getTime() + TimeZone.getTimeZone("IST").getRawOffset()));
		System.err.println(new Date(currentTime.getTime() + TimeZone.getTimeZone("MST").getRawOffset()));
		
		
		String oldVal = "10";
		String newVal = "10";
		System.out.println(oldVal == newVal);
		System.out.println(oldVal.equals(newVal));

		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(3, new MyComparator());
		priorityQueue.add(0);
		priorityQueue.add(4);
		priorityQueue.add(5);
		priorityQueue.add(9);
		System.out.println(priorityQueue.remove());
		System.out.println(priorityQueue.remove());
		System.out.println(priorityQueue.remove());
	}
	
	
	static class MyComparator implements Comparator<Integer>{
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1 - o2;
		}
	}
	
	public int countX(String str) {
		if (str == null || str.length() == 0)
			return 0;

		int comp = str.toCharArray()[0] == 'x' ? 1 : 0;
		return comp + countX(str.substring(1, str.length()));

	}
	
	public static String reverse(String input){
		if(input==null||input.length()==0){
			return "";
		}
		
		String lastChar = input.substring(input.length()-1,input.length());
		return lastChar + reverse(input.substring(0, input.length()-1));
	}

}
