package com.jeiglobal.hk.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JeiCommonUtils {

	/**
	 * 달차이
	 * @param day1 yyyymm "2014-03-02" "201403" 등등
 	 * @param day2 yyyymm "2014-01-01" "201401" 등등
 	 * @param options "-" "2014-01-01" 등등
	 * @return 2
	 */
	public static int getMonthDiff(String day1,String day2,String options){

		if(options != null && !options.isEmpty()){
			day1 = day1.replaceAll(options,"");
			day2 = day2.replaceAll(options,"");
		}
		int year1 = Integer.parseInt(day1.substring(0,4));		
		int year2 = Integer.parseInt(day2.substring(0,4));
		
		int month1 = Integer.parseInt(day1.substring(4,5));
		int month2 = Integer.parseInt(day2.substring(4,5));
		
		int diffMonth = (year1 - year2) * 12  + (month1 - month2);
		
		return diffMonth;
	}
	
	/**
	 * 포멧에 맞는 오늘 날짜 출력
	 * @param format "yyyymmdd" "20140101"
	 * @return
	 */
	public static String getToday(String format){
		Calendar cal = Calendar.getInstance(); 
		SimpleDateFormat formatter = new SimpleDateFormat(format);        
		return formatter.format(cal.getTime());    
	}
	
	/**
	 * 포멧에 맞는 diffDay만큼 후에 날짜 출력?
	 * @param format
	 * @param ipheiDay
	 * @param diffDay
	 * @return
	 */
	public static String getDiffDay(String format,String ipheiDay,int diffDay){
		String setDay[] = ipheiDay.split("-");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(setDay[0]),Integer.parseInt(setDay[1])-1,Integer.parseInt(setDay[2]));
		cal.add(Calendar.DATE,diffDay);
		SimpleDateFormat formatter = new SimpleDateFormat(format);   
		return formatter.format(cal.getTime());
	}
	
	/**
	 * 특정 일이 어떤 요일인지 숫자로 반환한다.
	 *
	 * @param	year
	 * @param	month
	 * @param	day
	 * @return	int : 1(일)~7(토)
	 */
	public static int getWeekNum(String someDay) {
		String days[] = someDay.split("-");
		
		int year = Integer.parseInt(days[0]);
		int month = Integer.parseInt(days[1]);
		int day = Integer.parseInt(days[2]);
		
		Calendar cal = Calendar.getInstance();
		cal.set(year, month -1, day);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	
	/**
	 * 그달의 마지막 날짜 구하기
	 * @param days yyyy-MM-dd
	 * @return yyyy-MM-dd
	 */
	public static String getLastDateOfMonth(String days) {
		String day[] = days.split("-");
		
		Calendar cal = Calendar.getInstance();
		
		cal.set(Integer.parseInt(day[0]),Integer.parseInt(day[1])-1,1);

		
		return day[0]+"-"+day[1]+"-"+cal.getActualMaximum(Calendar.DATE);
	}
	
}