package com.excel.android;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateInfo
{
	private String name;
	private GregorianCalendar calendar;
	public DateInfo()
	{
		calendar=new GregorianCalendar();
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public GregorianCalendar getCalendar()
	{
		return calendar;
	}
	public Date getDate()
	{
		return calendar.getTime();
	}
	public void setDate(Date date)
	{
		calendar.setTime(date);
	}
	public void fixTimeZone()
	{
		calendar.add(Calendar.HOUR, -8);
	}
	public String getMonth()
	{
		return (calendar.get(Calendar.MONTH)+1)+"月";
	}
	public static String getDateString(GregorianCalendar gc)
	{
		String am_pm=null;
		if(gc.get(Calendar.AM_PM)==0)
		{
			am_pm="上午";
		}
		else
		{
			am_pm="下午";
		}
		return  (gc.get(Calendar.MONTH)+1)+"月"+
				gc.get(Calendar.DAY_OF_MONTH)+"日"+
				am_pm+
				gc.get(Calendar.HOUR)+"點"+
				gc.get(Calendar.MINUTE)+"分";
	}
	public String getInfo()
	{
		return getDateString(calendar);
	}
}
