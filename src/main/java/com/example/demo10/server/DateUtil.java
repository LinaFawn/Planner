package com.example.demo10.server;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Utility class for working with dates
 */
@SuppressWarnings("deprecation")
public class DateUtil {
	private static int getDayMonthRepresentation(Date date) {
		return date.getMonth() * 100 + date.getDate();
	}
	
	public static boolean isDayMonthAfter(Date date, Date afterWhat) {
		return getDayMonthRepresentation(date) > getDayMonthRepresentation(afterWhat);
	}
	
	public static boolean isDayMonthBefore(Date date, Date beforeWhat) {
		return getDayMonthRepresentation(date) < getDayMonthRepresentation(beforeWhat);
	}
	
	/**
	 * Get Year number of specific date
	 * @param dt Date to be processed
	 * @return Year number
	 */
	public static int getYear(Date dt) {
		return dt.getYear() + 1900;
	}
	
	/**
	 * Get Month number of specific date
	 * @param dt Date to be processed
	 * @return Month number
	 */
	public static int getMonthOfYear(Date dt) {
		return dt.getMonth();
	}
	
	/**
	 * Get number of current Day in current Month of specific date
	 * @param dt Date to be processed
	 * @return Day number
	 */
	public static int getDayOfMonth(Date dt) {		
		return dt.getDate();
	}
	
	/**
	 * Get number of days in current Month of specific date
	 * @param dt Date to be processed
	 * @return Number of days
	 */
	public static int getDaysInMonth(Date dt) {
		Date td = (Date)dt.clone();
		int month = td.getMonth();
		int numDays = 28;
		td.setDate(numDays);		
		while (td.getMonth() == month) {
			numDays++;
			td.setDate(numDays);
		}
		return numDays-1;
	}
	
	/**
	 * Set specific date to the first Day in next Month
	 * @param dt Date to be processed
	 * @return Updated date
	 */
	public static void setNextMonthFirstDay(Date dt) {
		dt.setDate(1);
		int month = dt.getMonth();
		if (month != 11) {
			dt.setMonth(month+1);
		//Dec > Jan, next Year
		} else {
			dt.setMonth(0);
			dt.setYear(dt.getYear()+1);
		}
	}	
	
	/**
	 * Increment the specific date for specific number of days
	 * @param dt Date to be processed
	 * @param num Number of days for increment
	 * @return Updated date
	 */
	public static Date getIncremented(Date dt, int num) {
		Date td = (Date)dt.clone();
		td.setDate(td.getDate() + num);
		return td;
	}
	
	/**
	 * Obtain current year
	 * @return Current year
	 */
	public static int getCurrentYear() {
		return getYear(new Date());
	}

	public static Date slideToWeekDay(Date date) {
		Date newDate = new Date(date.getTime());
		
		while (newDate.getDay() % 6 == 0) {
			newDate.setDate(newDate.getDate() + 1);
		}
		return newDate;
	}
	
	/**
	 * Get number of days between two dates
	 * @param start Start date
	 * @param end End date
	 * @return Number of days between
	 */
	public static int diff(Date start, Date end) {
		long diff = end.getTime() - start.getTime();
	    return (int) (diff / (1000 * 60 * 60 * 24));
	}
	
	//Date formatters
//	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
//	public static final DateFormat DATE_FORMAT_JSON = new SimpleDateFormat("ddMMyyyy");
//	public static final DateFormat DATE_FORMAT_PRINTABLE = new SimpleDateFormat("yyy.MM.dd");
//	public static final DateFormat DATE_FORMAT_DOC = new SimpleDateFormat("dd.MM.yyyy");	
	
	public static final DateFormat getDateFormat() {return new SimpleDateFormat("MM/dd/yyyy");}
	public static final DateFormat getDateFormatJson() {return new SimpleDateFormat("ddMMyyyy");}
	public static final DateFormat getDateFormatFloating() {return new SimpleDateFormat("ddMM");}
	public static final DateFormat getDateFormatFloatingTime() {return new SimpleDateFormat("HH:mm");}
	public static final DateFormat getDateFormatFull() {return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");}
	public static final DateFormat getDateFormatTime() {return new SimpleDateFormat("HH:mm:ss");}
	public static final DateFormat getDateFormatPrintable() {return new SimpleDateFormat("yyy.MM.dd");}
	public static final DateFormat getDateFormatDoc() {return new SimpleDateFormat("dd.MM.yyyy");}		
	
	//Date formatters, considering TimeZone
	public static final DateFormat getDateFormatTZ() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		df.setTimeZone(getTZ());
		return df;
	}	
	public static final DateFormat getDateFormatJsonTZ() {
		DateFormat df = new SimpleDateFormat("ddMMyyyy");
		df.setTimeZone(getTZ());
		return df;
	}
	public static final DateFormat getDateFormatDocTZ() {
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		df.setTimeZone(getTZ());
		return df;
	}
	
	private static TimeZone getTZ() {
		TimeZone tz = TimeZone.getDefault();
		tz.setRawOffset(10800000);
		return tz;
	}
}
