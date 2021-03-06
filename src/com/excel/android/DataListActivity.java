package com.excel.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DataListActivity extends ListActivity
{
	public String NAME = "name";
	public String INFO = "info";
	public GameInfo[] gameInfos = null;
	public LinkedHashMap <String ,Vector<DateInfo>> dateInfoTable=null;
	
	public GlobalVariable globalVariable;
	public Vector<Integer> listNo=new Vector<Integer>();//用來紀錄目前顯示gameInfos的編號 
	private boolean isNumber=false;//顯示編號 用在排行榜
	//DataListActivity 子類別 SearchName SearchPeople ExcelAndroidActivity 顯示的gameInfo都不同
	//this.setListNo(); this.setLists();
	public DataListActivity()
	{
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		globalVariable = (GlobalVariable)this.getApplicationContext();
	}
	public void setLists()
	{
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < listNo.size(); i++)
		{
			int no=listNo.get(i);
			Map<String, Object> item = new HashMap<String, Object>();
			if(isNumber)
			{
				item.put(NAME, (i+1)+". "+gameInfos[no].getName());
			}
			else
			{
				item.put(NAME, gameInfos[no].getName());
			}
			item.put(INFO, gameInfos[no].getInfo());
			items.add(item);
		}
		this.setAdapter(items);
	}
	public void setAdapter(List<Map<String, Object>> items)
	{
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, items,
				android.R.layout.simple_list_item_2,
				new String[] { NAME, INFO }, new int[] { android.R.id.text1,
						android.R.id.text2 });
		setListAdapter(simpleAdapter);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		// 取得目前List的位置
		View vs = l.getChildAt(0);
		int top = (vs == null) ? 0 : vs.getTop();
		int index = 0;
		index = l.getFirstVisiblePosition();
		super.onListItemClick(l, v, position, id);
		int no=listNo.get(position);
		this.dialog(no, index, top);
	}
	private void dialog(final int no, final int index, final int top)
	{
		String name=gameInfos[no].getName();
		AlertDialog.Builder builder = new Builder(DataListActivity.this);
		if (gameInfos[no].getNumber() == -1)
		{
			builder.setMessage("目前沒有遊戲紀錄");
		} 
		else
		{	
			builder.setItems(gameInfos[no].getRecord(), null);
		}
		builder.setTitle(name+"遊戲紀錄");
		builder.setPositiveButton("新增", new OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				String name=gameInfos[no].getName();
				DateInfo dateInfo=setDateInfo(name);
				gameInfos[no].putRecord(dateInfo.getCalendar());
				setLists();
				getListView().setSelectionFromTop(index, top);
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", new OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
	private DateInfo setDateInfo(String name)
	{
		DateInfo dateInfo=new DateInfo();
		dateInfo.setName(name);
		String month=dateInfo.getMonth();
		Vector<DateInfo> dateInfos =dateInfoTable.get(month);
		if(dateInfos==null)
		{
			dateInfos=new Vector<DateInfo>();
			dateInfoTable.put(month, dateInfos);
		}
		dateInfos.add(dateInfo);
		return dateInfo;
	}
	public boolean isNumber()
	{
		return isNumber;
	}
	public void setNumber(boolean isNumber)
	{
		this.isNumber = isNumber;
	}
}
