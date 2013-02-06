package com.excel.android;

import java.util.LinkedHashMap;
import java.util.Vector;

import android.app.Application;

public class GlobalVariable extends Application
{
	private GameInfo[] gameInfos = null;
	private LinkedHashMap<String ,Vector<DateInfo>> dateInfoTable= null;
	public GlobalVariable()
	{
		
	}
	public GameInfo[] getGameInfos()
	{
		return gameInfos;
	}
	public void setGameInfos(GameInfo[] gameInfos)
	{
		this.gameInfos = gameInfos;
	}
	public LinkedHashMap<String, Vector<DateInfo>> getDateInfoTable()
	{
		return dateInfoTable;
	}
	public void setDateInfoTable(LinkedHashMap<String, Vector<DateInfo>> dateInfoTable)
	{
		this.dateInfoTable = dateInfoTable;
	}
}
