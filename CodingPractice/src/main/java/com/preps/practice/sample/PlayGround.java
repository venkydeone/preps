package com.preps.practice.sample;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;

public class PlayGround {
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	
	int a;
	static int sa;
	
	public static void main(String[] args) throws UnknownHostException {
		System.err.println((int)Math.ceil((double)(1+1/2)));
		List<Integer> intList1 = new ArrayList<Integer>();
		intList1.add(1);
		intList1.add(2);
		intList1.add(3);
		System.out.println(intList1);
		
		for(int i=0; i<intList1.size();i++){
			if(intList1.get(i)==2){
				intList1.remove(i);
				intList1.add(5);
			}
		}
		System.out.println(intList1);
		
		intList1.add(2);
		for(Integer in : intList1){
			if(in==2){
				intList1.remove(in);
				intList1.add(5);
			}
		}
		System.out.println(intList1);
		
		intList1.add(2);
		Iterator<Integer> iter = intList1.iterator();
		while(iter.hasNext()){
			if(iter.next()==2){
				iter.remove();
				intList1.add(5);
			}
		}
		System.out.println(intList1);
		
		Float f = new Float(3.1);
		Integer i1 = new Integer (1);
		long m = 2;
		System.out.println(m+f+i1);
		System.out.println("Venkat".getClass().getName());
		System.out.println(-1>>>32);
		System.out.println(-1>>>5);
		System.out.println((byte)((byte)-1)>>>5);
		System.out.println(-1<<16);
		System.out.println(-1>>5);
		Random rand = new Random();
		for(int i=0; i<5;i++){
			System.out.println(rand.nextInt(15));
		}
		System.out.println();
		TreeSet<Integer> intList = new TreeSet<Integer>();
		intList.add(10);
		intList.add(3);
		intList.add(5);
		
		TreeMap<Car,String> carToNameMap = new TreeMap<Car,String>();
		Car car = new Car("Ford", "Mustang");
		Car car2 = new Car("BMW", "328i");
		Car car3 = new Car("Acura", "TSX");
		carToNameMap.put(car,"Venkat");
		carToNameMap.put(car2, "Ramesh");
		carToNameMap.put(car3, "Ganesh");
		
		byte ba = (byte)255;
		System.out.println(ba);
		System.out.println((int)ba);
		
		StringBuilder sb = new StringBuilder();
		sb.append((char)('A'+5-1));
		System.out.println(sb.toString());
		
		System.out.println(5<<1);
		System.out.println(10>>1);
		System.out.println((1<<31));
		System.out.println(Integer.toBinaryString((1<<31)));
		
		PlayGround p = new PlayGround();
		p.a=5;
		p.sa=10;
		
		PlayGround p1 = new PlayGround();
		p1.a=15;
		p1.sa=20;

		System.out.println(p.a);
		System.out.println(p.sa);
		System.out.println(p1.a);
		System.out.println(p1.sa);
		
		
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
	
	static class Car implements Comparable{
		private String make;
		public String getMake() {
			return make;
		}
		public void setMake(String make) {
			this.make = make;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public Car(String make, String model) {
			super();
			this.make = make;
			this.model = model;
		}
		private String model;
		
		public int compareTo(Object o) {
			if(this!=null&&o!=null){
				if(this.make.compareTo(((Car)o).getMake())==0){
					return this.model.compareTo(((Car)o).getModel());
				}
				return this.make.compareTo(((Car)o).getMake());
			}
			return 0;
		}
		
	}

}
