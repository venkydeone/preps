/**
 * 
 */
package com.preps.practice.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Venkat P
 *
 */
public class TimeStuff {

	private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";
	
	public static void main(String[] args) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        String dateInString = "01-09-2016 10:45:00 PM";
        Date date = formatter.parse(dateInString);
        TimeZone tz = TimeZone.getDefault();

        // From TimeZone PST
        System.out.println("TimeZone : " + tz.getID() + " - " + tz.getDisplayName());
        System.out.println("TimeZone : " + tz);
        System.out.println("Date (PST) : " + formatter.format(date));
        System.out.println("Time (in ms) : " + date.getTime());

        // To TimeZone America/New_York
        SimpleDateFormat sdfIndia = new SimpleDateFormat(DATE_FORMAT);
        TimeZone tzIndia = TimeZone.getTimeZone("Asia/Kolkata");
        sdfIndia.setTimeZone(tzIndia);
        String dateIndiaStr = "01-09-2016 10:45:00 PM";
        Date dateIndia = formatter.parse(dateIndiaStr);

        System.out.println("\nTimeZone : " + tzIndia.getID() + " - " + tzIndia.getDisplayName());
        System.out.println("TimeZone : " + tzIndia);
        System.out.println("Date (India) (String) : " + tzIndia);
        System.out.println("Date (in ms) : " + dateIndia.getTime());

    }

}
