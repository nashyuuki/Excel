package com.excel.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class DateListActivity extends ListActivity
{
	public String NAME = "name";
	public String INFO = "info";
	public LinkedHashMap <String ,Vector<DateInfo>> dateInfoTable;
	public DateListActivity()
	{
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		GlobalVariable globalVariable = (GlobalVariable)this.getApplicationContext();
		super.onCreate(savedInstanceState);
		dateInfoTable=globalVariable.getDateInfoTable();
		String value=getIntent().getExtras().getString("value");
		Vector<DateInfo> dateInfos=dateInfoTable.get(value);
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for(DateInfo dateInfo:dateInfos)
		{
			Map<String, Object> item = new HashMap<String, Object>();
			item.put(NAME, dateInfo.getName());
			item.put(INFO, dateInfo.getInfo());
			items.add(item);
		}
		this.setAdapter(items);
		this.setSelection(items.size()-1);
	}
	public void setAdapter(List<Map<String, Object>> items)
	{
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, items,
				android.R.layout.simple_list_item_2,
				new String[] { NAME, INFO }, new int[] { android.R.id.text1,
						android.R.id.text2 });
		setListAdapter(simpleAdapter);
	}
}
