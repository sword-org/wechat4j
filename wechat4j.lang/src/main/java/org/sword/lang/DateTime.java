/**
 * 
 */
package org.sword.lang;

import java.util.Calendar;
import java.util.Date;

/**
 * @author ChengNing
 * @date   2014年10月30日
 */
public class DateTime extends Date {
	
	
	public static final DateTime MAX_DATE_TIME = new DateTime(9999, 12, 31, 23, 59, 59);
	public static final DateTime MIN_DATE_TIME = new DateTime(0001, 1, 1, 00, 00, 0);

	private static final long serialVersionUID = 1L;
	private Date date;
	private Calendar calendar;

	public DateTime(long ticks){
		this.date = new Date(ticks);
	}
	
	public DateTime(int year,int month,int day){
		calendar.set(year, month, day);
		this.date = calendar.getTime();
	}
	
	public DateTime(int year,int month,int day,int hour,int minute,int second){
		calendar.set(year, month, day,hour,minute,second);
		this.date = calendar.getTime();
	}
	
	public DateTime(String dateString){
		this.date = DateTimeUtil.getDate(dateString);
	}
	
	public String toString(){
		return DateTimeUtil.toDateTimeStr(this.date);
	}
	
	public String toString(String format){
		return DateTimeUtil.toDateString(this.date, format);
	}
	
	public Date toDate(){
		return this.date;
	}


	
	
}
