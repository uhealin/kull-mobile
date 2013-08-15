package com.kull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DateTimeHelper {

	public enum DateTimeFormatter{
		DATE_FORMAT_DB("yyyy-MM-dd"),DATE_FORMAT_YMD("yyyy/MM/dd"),DATE_FORMAT_DMY("dd/MM/yyyy")
		,TIME_FORMAT_HMS ("HH:mm:ss"),TIME_FORMAT_HM ("HH:mm"),TIMESTAMP_FORMAT_HMSS("yyyy-MM-dd HH:mm:ss.S")
		,DATETIME_FORMAT_DB("yyyy-MM-dd HH:mm:ss")
		;
		
		String pattern;
		SimpleDateFormat simpleDateFormat;
		DateTimeFormatter(String patern){
			this.pattern=patern;
			this.simpleDateFormat=new SimpleDateFormat(patern);
		}
		public String getPattern() {
			return pattern;
		}
		public SimpleDateFormat getSimpleDateFormat() {
			return simpleDateFormat;
		}
		
	    public String format(){
	    	return format(new Date());
	    }
	    
	    public String format(Date date){
	    	return simpleDateFormat.format(date);
	    }
	    
	    
	}
	
	
	public enum Week{
		SUN,MON,TUE,WED,THU,FRI,SAT;
		
		private int num;
		
	}
	
	
	
	
	public static Set<Date> weekDays(Date pDt)
	{

		if(pDt==null)return null;
		Set<Date> lListReturn=new HashSet<Date>();
		Calendar c=Calendar.getInstance();
		c.setTime(pDt);
		int weekday=c.get(Calendar.DAY_OF_WEEK);
		c.add(Calendar.DAY_OF_WEEK, -(weekday-1));
		int t=0;
		while(t++<7)
		{
			c.add(Calendar.DATE, 1);
			lListReturn.add(c.getTime());
		}
		return lListReturn;
	}
	
	public static Set<Date> weekDays(int year,int weeknum)
	{
		Set<Date> lListReturn=new HashSet<Date>();
		Calendar c=Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, weeknum);
		c.set(Calendar.DAY_OF_WEEK, 0);
		lListReturn=weekDays(c.getTime());
		//System.out.println(c.getTime().toString());
		return lListReturn;
		
	}
	
	public static String format(java.util.Date date,String dateFormat) {
		 if(date == null)
			 return null;
		 return new SimpleDateFormat(dateFormat).format(date);
	}
	
	
	public static int getWeekOfYear(Date date)
	{
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}
	
	
	public static java.util.Date parse(String dateString) throws Exception {
		java.util.Date dateReturn=null;
		try{
			dateReturn=new SimpleDateFormat().parse(dateString);
		}catch(Exception ex){
			String[] listDateFormat={DateTimeFormatter.DATE_FORMAT_DB.getPattern(),DateTimeFormatter.DATETIME_FORMAT_DB.getPattern(),DateTimeFormatter.DATE_FORMAT_DMY.getPattern(),DateTimeFormatter.DATE_FORMAT_YMD.getPattern()};
		    String[] listTimeFormat={DateTimeFormatter.TIME_FORMAT_HMS.getPattern(),DateTimeFormatter.TIME_FORMAT_HMS.getPattern()};
			for (String dateFormat : listDateFormat) {
			try {
				if(dateString.trim().length()!=dateFormat.length())continue;
				Date dateTemp=parse(dateString, dateFormat,java.util.Date.class);
				dateReturn=dateTemp;
			} catch (Exception e) {
				dateReturn=null;
				//e.printStackTrace();
			}
			if(dateReturn!=null)return dateReturn;
		}
			for (String dateFormat : listTimeFormat) {
			try {
				if(dateString.trim().length()!=dateFormat.length())continue;
				Calendar calendar=Calendar.getInstance();
				int yyyy=calendar.get(calendar.YEAR);
				int MM=calendar.get(calendar.MONTH);
				int dd=calendar.get(calendar.DAY_OF_MONTH);
				Date dateTemp=parse(dateString, dateFormat,java.util.Date.class);
				calendar.setTime(dateTemp);
				calendar.set(yyyy, MM, dd);
				dateReturn=calendar.getTime();
			} catch (Exception e) {
				dateReturn=null;
				//e.printStackTrace();
			}
			if(dateReturn!=null)return dateReturn;
		}
		}
		return dateReturn;
	}
	
	
	public static java.util.Date parse(String dateString,String dateFormat) throws Exception {
		return parse(dateString, dateFormat,java.util.Date.class);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends java.util.Date> T parse(String dateString,String dateFormat,Class<T> targetResultType) throws Exception {
		if(StringHelper.isBlank(dateString))
			return null;
		DateFormat df = new SimpleDateFormat(dateFormat);
		long time = df.parse(dateString).getTime();
		java.util.Date t = targetResultType.getConstructor(long.class).newInstance(time);
		Calendar cale=Calendar.getInstance();
		cale.setTime(t);
		if(cale.get(Calendar.YEAR)<1000) throw new Exception();
		return (T)t;

	}
	
	public static Date attr(Date date,int field,int value){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(field, value);
		//date=calendar.getTime();
		return calendar.getTime();
	}
	
	public static Date attr(Date date,Map<Integer,Integer> attrs){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		for(Iterator<Integer> it=attrs.keySet().iterator();it.hasNext();){
			Integer field=it.next();
			Integer value=attrs.get(field);
			calendar.set(field, value);
		}
		//date=calendar.getTime();
		return calendar.getTime();
	}
	
	public static int attr(Date date,int field){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(field);
		//return calendar.getTime();
	}
	
	
	
}
