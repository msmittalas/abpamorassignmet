package com.abn.assignment.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateConversion.java Utility class to converts Date 
 * @author MITTAL
 *
 */
public class DateConversion {

	final static String FORMAT="yyyy-MM-dd hh:mm";
	
	/**method to convert Timestamp to Date
	 * @param timestamp
	 * @return String format of TimeStamp
	 */
	public static String toString(Timestamp timestamp)
	{
		 SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
		 return dateFormat.format(timestamp);
		 
	}
	/**method to convert String to timeStamp
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp toTimestamp(String date) throws ParseException
	{
		 SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
		    Date parsedDate = dateFormat.parse(date);
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    return timestamp;
	}
	
}
