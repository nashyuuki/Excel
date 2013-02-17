package com.excel.android;

import java.util.HashMap;
import java.util.Vector;
import android.os.Bundle;

public class SearchCharts extends DataListActivity
{
	private HashMap <String ,Integer> gameNo=null;
	private Vector<GameInfo> charts=new Vector<GameInfo>();
	public SearchCharts()
	{
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		gameInfos=globalVariable.getGameInfos();
		gameNo=globalVariable.getGameNo();
		dateInfoTable=globalVariable.getDateInfoTable();
		for(GameInfo gameInfo:gameInfos)
		{
		 	int number=gameInfo.getNumber();
		 	if(number!=-1)
		 	{
		 		charts.add(gameInfo);
		 	}
		}
		//氣泡排序法
		for(int i=0;i<charts.size()-1;i++)
		{
			for(int j=0;j<charts.size()-i-1;j++)
			{
				if (charts.get(j+1).getNumber()>charts.get(j).getNumber())
                {
					GameInfo temp=charts.get(j+1);//交換
					charts.remove(j+1);
					charts.insertElementAt(temp, j);
                }
			}
		}
		for(GameInfo chart:charts)
		{
			listNo.add(gameNo.get(chart.getName()));
		}
		this.setNumber(true);
		this.setLists();
		globalVariable.setCharts(charts);
	}

}
