package com.excel.android;

import java.util.LinkedHashMap;
import java.util.Vector;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class SearchDate extends TabActivity
{
	public LinkedHashMap <String ,Vector<DateInfo>> dateInfoTable;
	public SearchDate()
	{
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		GlobalVariable globalVariable = (GlobalVariable)this.getApplicationContext();
		super.onCreate(savedInstanceState);
		dateInfoTable=globalVariable.getDateInfoTable();
		final TabHost tabHost = getTabHost();
		String[] keys = dateInfoTable.keySet().toArray(new String[dateInfoTable.size()]);
//        Arrays.sort(keys);
        for(int i=keys.length-1;i>=0;i--) 
        {  
        	Intent intent=new Intent(this, DateListActivity.class);
        	intent.putExtra("value", keys[i]);
        	tabHost.addTab(tabHost.newTabSpec("tab"+keys[i])
                    .setIndicator(keys[i])
                    .setContent(intent)); 
        }
	}
}
