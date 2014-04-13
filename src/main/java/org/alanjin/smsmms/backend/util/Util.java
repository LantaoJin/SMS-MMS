package org.alanjin.smsmms.backend.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Util {
	public static final String dayFormatStr = "yyyy-MM-dd";
	public static final String timeFormatStr = "yyyy-MM-dd hh:mm:ss";
	public static final SimpleDateFormat dayFormat = new SimpleDateFormat(dayFormatStr);
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat(timeFormatStr);
	
	public static java.sql.Date toSQLDate(String dateString) throws ParseException {
		return new java.sql.Date(dayFormat.parse(dateString).getTime());
	}
	
	public static String fromSQLDate(java.sql.Date date) {
		return dayFormat.format(new java.util.Date(date.getTime()));
	}
}
