package com.preps.sample;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class PlayGround {
	public static void main(String[] args) {
		
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
	}

}
