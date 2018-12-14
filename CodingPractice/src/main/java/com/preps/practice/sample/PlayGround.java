package com.preps.practice.sample;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PlayGround {
	
	static void dutchFlagAlgo(int[] arr){
		int low=0, mid=0, hi=arr.length-1;
		while(mid<=hi){
			switch(arr[mid]){
				case 0:
					swap(arr,low++,mid++);
					break;
				case 1:
					mid++;
					break;
				case 2:
					swap(arr,mid,hi--);
					break;
			}
		}
		System.out.println(Arrays.toString(arr));
	}
	
	static void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i]=arr[j]; 
		arr[j]=temp;
	}
	static int increasingSubSequenceBruteForce(int[] input){
		return increasingSSBFHelper(input,1,1);
	}
	
	static int increasingSSBFHelper(int[] input, int index, int length){
		if(input==null||input.length==0 ||index==input.length-1){
			return length;
		}
		else{
			int lengthConsideringInc = 0;
			if(input[index-1]<input[index]){
				 lengthConsideringInc = increasingSSBFHelper(input, index+1, length)+1;
			}
			int lengthNotConsideringInc = increasingSSBFHelper(input, index+1, length);
			return Math.max(lengthConsideringInc, lengthNotConsideringInc);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(toDecimalValue("123", 8));
		System.out.println(fromDecimalValue(30, 16));
	}
	
	static int removeElement(int[] A, int elem) {
	   int m = 0;    
	   for(int i = 0; i < A.length; i++){
	       
	       if(A[i] != elem){
	           A[m] = A[i];
	           m++;
	       }
	   }
	   
	   return m;
	}
	
	static int findComplement(int num) {
        int n=0, i=0;
        while(num>0){
            n = n | ((num & 1<<i)^1<<i)<<i;
            num>>=1;
            i++;
        }
        return n;
    }
	
	public static int[] findRound(double[] arr) {
        int[] result = new int[arr.length];

        double sum = 0;
        int floorSum = 0;
        Map<Integer, Double> decimalMap = new TreeMap<Integer, Double>();


        for(int i=0;i<arr.length;i++) {
            result[i] = (int)Math.floor(arr[i]);
            String decimal = String.format("%.1f", arr[i] - Math.floor(arr[i]));
            decimalMap.put(i, Double.parseDouble(decimal));
            floorSum+=Math.floor(arr[i]);
            sum+=arr[i];
        }

        double remaining =  Math.round(sum) - floorSum;
        if(remaining == 0) {
            return result;
        }

        Map<Integer, Double> sortedMap = sortByValue(decimalMap);
        Integer[] keySet = sortedMap.keySet().toArray(new Integer[sortedMap.size()]);
        for(int i=0;i<remaining;i++) {
            result[keySet[i]]++;
        }
        return result;
    }


    static  Map<Integer, Double> sortByValue(Map<Integer, Double> map) {
        List<Map.Entry<Integer, Double>> mapList = new LinkedList<Map.Entry<Integer, Double>>(map.entrySet());

        Collections.sort(mapList, new Comparator<Map.Entry<Integer, Double>>() {
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<Integer, Double> m = new LinkedHashMap<Integer, Double>();
        for(Map.Entry<Integer, Double> cur: mapList) {
            m.put(cur.getKey(), cur.getValue());
        }
        return m;
    }
    
    static int toDecimalValue(String input, int base){
    	char[] ch = input.toCharArray();
    	int dec = 0;
    	int i=0;
    	for(int j=ch.length-1; j>=0; j--){
    		char c = ch[j];
    		int n = 0;
    		if(c>='0' && c<='9' ){ // number
    			n = Integer.parseInt(""+c);
    		}else{
    			n = 10 + Integer.parseInt(""+ (c-'A'));
    		}
    		dec+= n*Math.pow(base, i++);
    	}
    	return dec;
    }
    
    static String fromDecimalValue(int num, int base){
    	StringBuilder output = new StringBuilder();
    	while(num>base){
    		int mod = num%base;
    		if(base>10 && mod >10){
    			output.append(((char)'A'+ (mod-10)));
    		}else{
    			output.append(mod);
    		}
    		num/=base;
    	}
    	output.append(num);
    	return output.reverse().toString();
    }
   
    

}
