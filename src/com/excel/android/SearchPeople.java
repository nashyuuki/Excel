package com.excel.android;

import android.os.Bundle;

public class SearchPeople extends DataListActivity
{

	public SearchPeople()
	{
	}
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		gameInfos=globalVariable.getGameInfos();
		dateInfoTable=globalVariable.getDateInfoTable();
		int value = getIntent().getExtras().getInt("value");
		switch(value)
		{
			case R.id.number_1:
				search(1);
				break;
			case R.id.number_2:
				searchTwo();
				break;
			case R.id.number_2_:
				search(2);
				break;
			case R.id.number_3:
				search(3);
				break;
			case R.id.number_4:
				search(4);
				break;
			case R.id.number_5:
				search(5);
				break;
			case R.id.number_6:
				search(6);
				break;
			case R.id.number_7:
				search(7);
				break;
			case R.id.number_8:
				search(8);
				break;
			case R.id.number_9:
				search(9);
				break;
			case R.id.number_10:
				search(10);
				break;
			case R.id.number_11:
				search(11);
				break;
			case R.id.number_12:
				search(12);
				break;
		}
	}
	private void searchTwo()
	{
		int length=gameInfos.length;
		for(int i=0;i<length;i++)
		{
			if(gameInfos[i].getPeople().equals("2"))
			{
				listNo.add(i);
			}
		}
		this.setLists();
	}
	private void search(int people)
	{
		this.setTitle("¤H¼Æ:"+people);
		int length=gameInfos.length;
		for(int i=0;i<length;i++)
		{
			if(this.isMatch(gameInfos[i].getPeople(),people))
			{
				listNo.add(i);
			}
		}
		this.setLists();
	}
	private boolean isMatch(String value,int people)
	{
		int index=value.indexOf("~");
		if(index!=-1)
		{
			int upValue=Integer.valueOf(value.substring(0, index));
			int downValue=Integer.valueOf(value.substring(index+1, value.length()).toString());
			if(people>=upValue&&people<=downValue)
			{
				return true;
			}
		}
		index=value.indexOf("+");
		if(index!=-1)
		{
			int upValue=Integer.valueOf(value.substring(0, index));
			if(people>=upValue)
			{
				return true;
			}
		}
		index=value.indexOf("\\");
		if(index!=-1)
		{
			int upValue=Integer.valueOf(value.substring(0, index));
			int downValue=Integer.valueOf(value.substring(index+1, value.length()).toString());
			if(people==upValue||people==downValue)
			{
				return true;
			}
		}
		return false;
	}
}
