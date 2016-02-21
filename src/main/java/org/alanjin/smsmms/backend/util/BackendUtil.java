package org.alanjin.smsmms.backend.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.alanjin.smsmms.backend.bean.Response;
import org.alanjin.smsmms.frontend.util.Lunar;
import org.json.JSONObject;

public class BackendUtil {
    public static final String dayFormatStr = "yyyy-MM-dd";
    public static final String timeFormatStr = "yyyy-MM-dd hh:mm:ss";
    public static final String birthFormatStr = "MMdd";
    public static final SimpleDateFormat dayFormat = new SimpleDateFormat(
            dayFormatStr);
    public static final SimpleDateFormat timeFormat = new SimpleDateFormat(
            timeFormatStr);
    public static final SimpleDateFormat birthFormat = new SimpleDateFormat(
            birthFormatStr);

    public static java.sql.Date toSQLDate(String dateString)
            throws ParseException {
        return new java.sql.Date(dayFormat.parse(dateString).getTime());
    }

    public static java.util.Date toNormalDate(String dateString)
            throws ParseException {
        return new java.util.Date(dayFormat.parse(dateString).getTime());
    }

    public static String fromSQLDate(java.sql.Date date) {
        return fromNormalDate(new java.util.Date(date.getTime()));
    }

    public static String toBirthDayStr(java.util.Date birthday) {
        return birthFormat.format(birthday);
    }

    public static String toBirthDayStrOfLunar(java.util.Date birthday)
            throws ParseException {
        return toBirthDayStr(toNormalDate(Lunar
                .solarTolunar(fromNormalDate(birthday))));
    }

    public static String fromNormalDate(java.util.Date date) {
        return dayFormat.format(date);
    }
    
    public static String fromNormalDateForTime(java.util.Date date) {
        return timeFormat.format(date);
    }

    public static java.util.Date dateConvert(java.sql.Date date) {
        return new java.util.Date(date.getTime());
    }

    public static java.sql.Date dateConvert(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date getNextYearFromNow() {
        return getDateFromDate(new java.util.Date(), Calendar.YEAR, 1);
    }

    public static java.util.Date getNextMonthFromDate(java.util.Date date) {
        return getDateFromDate(date, Calendar.MONTH, 1);
    }

    public static java.util.Date getNextNMonthFromDate(java.util.Date date,
            int n) {
        return getDateFromDate(date, Calendar.MONTH, n);
    }

    public static java.util.Date getDateFromDate(java.util.Date date,
            int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static java.util.Date getNextYearFromDate(java.util.Date date) {
        return getDateFromDate(date, Calendar.YEAR, 1);
    }

    public static java.util.Date getNextNYearFromDate(java.util.Date date, int n) {
        return getDateFromDate(date, Calendar.YEAR, n);
    }
    
    public static Response parseResponse(String jsonStr) throws IOException {
        try {
            JSONObject rootObject = new JSONObject(jsonStr);
            Response response = new Response();
            int code = rootObject.getInt("code");
            response.setCode(code);
            String msg = rootObject.getString("msg");
            response.setMsg(msg);
            if(code != 0) {
            	String detail = rootObject.getString("detail");
            	response.setDetail(detail);
            } else {
	            JSONObject resultObject = rootObject.getJSONObject("result");
	            int count = resultObject.getInt("count");
	            double fee = resultObject.getDouble("fee");
	            response.setCount(count);
	            response.setFee(fee);
            }
            return response;
        } catch (Exception e) {
            throw new IOException("json parse fail" ,e);
        }
    }
}
