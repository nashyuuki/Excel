package com.excel.android;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SearchName extends DataListActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		gameInfos=globalVariable.getGameInfos();
		dateInfoTable=globalVariable.getDateInfoTable();
		// Get the intent, verify the action and get the query
		Intent intent = getIntent();
		if (Intent.ACTION_SEARCH.equals(intent.getAction()))
		{
			String query = intent.getStringExtra(SearchManager.QUERY);
			this.search(query);
			this.setTitle("·j´M:"+query);
			Log.v("TEST", query);
		}
	}
	private void search(String query)
	{
		int length=gameInfos.length;
		for(int i=0;i<length;i++)
		{
			if(gameInfos[i].getName().indexOf(query)!=-1)
			{
				listNo.add(i);
			}
		}
		this.setLists();
	}
}
