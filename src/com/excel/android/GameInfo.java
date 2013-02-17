package com.excel.android;

import java.util.GregorianCalendar;
import java.util.Vector;

public class GameInfo
{
	private String name;
	private String people;
	private String time;
	private String year;
	private Vector<GregorianCalendar> record;
	public GameInfo()
	{
		record=new Vector<GregorianCalendar>();
	}
	public void putRecord(GregorianCalendar calendar)
	{
		record.add(calendar);
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPeople()
	{
		return people;
	}
	public void setPeople(String people)
	{
		this.people = people;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public String getYear()
	{
		return year;
	}
	public void setYear(String year)
	{
		this.year = year;
	}
	public String[] getRecord()
	{
		String[] value=new String[record.size()];
		for(int i=0;i<value.length;i++)
		{
			value[i]=DateInfo.getDateString(record.get(i));
		}
	   return value;
	}
	public int getNumber()
	{
		int number=-1;
		if(record.size()!=0)
		{
			number=record.size();
		}
		return number;
	}
	public String getInfo()
	{
		int number=this.getNumber();
		if(number==-1)
		{
			return "计:"+people+" 丁:"+time+" 烦计:"+year;
		}
		else
		{
			return "计:"+people+" 丁:"+time+" 烦计:"+year+" Ω计:"+number;
		}
	}
}
