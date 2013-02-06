package com.excel.android;

public class GameInfo
{
	private String name;
	private String people;
	private String time;
	private String year;
	private int number;
	public GameInfo()
	{
		number=-1;
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
	public void setNumber(int number)
	{
		this.number = number;
	}
	public int getNumber()
	{
		return number;
	}
	public String getInfo()
	{
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
