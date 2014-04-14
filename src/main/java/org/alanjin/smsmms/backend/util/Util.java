package org.alanjin.smsmms.backend.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Util {
	public static final String dayFormatStr = "yyyy-MM-dd";
	public static final String timeFormatStr = "yyyy-MM-dd hh:mm:ss";
	public static final String birthFormatStr = "MMdd";
	public static final SimpleDateFormat dayFormat = new SimpleDateFormat(dayFormatStr);
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat(timeFormatStr);
	public static final SimpleDateFormat birthFormat = new SimpleDateFormat(birthFormatStr);
	
	public static java.sql.Date toSQLDate(String dateString) throws ParseException {
		return new java.sql.Date(dayFormat.parse(dateString).getTime());
	}
	
	public static String fromSQLDate(java.sql.Date date) {
		return fromNormalDate(new java.util.Date(date.getTime()));
	}

	public static String toBirthDayStr(Date birthday) {
		return birthFormat.format(birthday);
	}

	public static String fromNormalDate(java.util.Date date) {
		return dayFormat.format(date);
	}
}
