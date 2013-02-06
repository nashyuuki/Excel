package com.excel.android;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Vector;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.DateFormats;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ExcelAndroidActivity extends DataListActivity
{
	private WritableCellFormat cellFormat;
	private WritableCellFormat dateFormat;
	private String FILE_NAME = "list2.xls";
	private File file;
	private Workbook workbook = null;
	private boolean isSave=true;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.initCellFormat();
		this.initWorkBook();
		this.initGameInfo();
		this.initDateInfo();
		globalVariable.setGameInfos(gameInfos);
		//�NgameInfo�������ܼƨѨ�LActicity�ϥ� �n�]�wAndroidManifest.xml
		globalVariable.setDateInfoTable(dateInfoTable);
		this.setListNo();
		this.setLists();
		getListView().setTextFilterEnabled(true);
		// // ���o�~���x�s�C�骺���A
		// String state = Environment.getExternalStorageState();
		// // �P�_���A
		// if (Environment.MEDIA_MOUNTED.equals(state))
		// {
		// Log.v("TEST", "�i�HŪ�g");
		// } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
		// {
		// Log.v("TEST", "�u�i�HŪ���A�L�k�g�J");
		// } else
		// {
		// Log.v("TEST", "�L�kŪ�g");
		// }
	}
	private void initDateInfo()
	{
		dateInfoTable=new LinkedHashMap<String ,Vector<DateInfo>>();
		for(int i=1;i<workbook.getNumberOfSheets();i++)
		{
			Sheet sheet = workbook.getSheet(i);
			String name=sheet.getName();
			int col = sheet.getColumns();
			int row = sheet.getRows();
			Vector<DateInfo> dateInfos=new Vector<DateInfo>();
			dateInfoTable.put(name, dateInfos);
			for (int j = 0; j < row; j++)
			{
				DateInfo dateInfo=new DateInfo();
				for(int k=0;k<col;k++)
				{
					Cell cell = sheet.getCell(k, j);
					if(k==0)
					{
						dateInfo.setName(cell.getContents());
					}
					else if(k==1)
					{
						dateInfo.setDate(this.getDate(cell));
						dateInfo.fixTimeZone();
					}
				}
				dateInfos.add(dateInfo);
			}
		}
	}
	private void setListNo()
	{
		for(int i=0;i<gameInfos.length;i++)
		{
			listNo.add(i);
		}
	}
	private File loadfile(String filename)
	{
		String path = Environment.getExternalStorageDirectory().getPath();
		return new File(path, filename);
	}
	private void initWorkBook()
	{//Ū����~���ɮת��ɭ� �|�hŪraw�̪��ɮ״��ե�
		file = this.loadfile(FILE_NAME);
		try
		{
			workbook = Workbook.getWorkbook(file);
		} 
		catch (BiffException e1)
		{
			Log.v("TEST", "BiffException");
		} 
		catch (IOException e1)
		{
			isSave=false;
			this.setTitle("testing data");
			InputStream file = this.getResources().openRawResource(R.raw.list2);
			try
			{
				workbook =Workbook.getWorkbook(file);
			} 
			catch (BiffException e)
			{
				e.printStackTrace();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			Log.v("TEST", "IOException");
		}
	}
	private void initGameInfo()
	{
		Sheet sheet = workbook.getSheet(0);
		int col = sheet.getColumns();
		int row = sheet.getRows();
		gameInfos = new GameInfo[row];
		for (int i = 0; i < gameInfos.length; i++)
		{
			gameInfos[i] = new GameInfo();
		}
		for (int j = 0; j < col; j++)
		{
			for (int i = 0; i < row; i++)
			{
				Cell cell = sheet.getCell(j, i);
				if (j == 0)
				{
					gameInfos[i].setName(cell.getContents());
				} 
				else if (j == 1)
				{
					gameInfos[i].setPeople(cell.getContents());
				} 
				else if (j == 2)
				{
					gameInfos[i].setTime(cell.getContents());
				} 
				else if (j == 3)
				{
					gameInfos[i].setYear(cell.getContents());
				} 
				else if (j == 4)
				{
					if (cell.getContents() != "")
					{
						gameInfos[i].setNumber(Integer.valueOf(cell
								.getContents()));
					}
				}
			}
		}
	}
	
	private void initCellFormat()
	{
		// Lets create a times font
		WritableFont times12pt = new WritableFont(WritableFont.TIMES, 12);
		// Define the cell format
		cellFormat = new WritableCellFormat(times12pt);
		// Lets automatically wrap the cells
		try
		{
			cellFormat.setWrap(true);
		} catch (WriteException e)
		{
			e.printStackTrace();
		}
		dateFormat=new WritableCellFormat(DateFormats.FORMAT9);
	}
	private void addName(WritableSheet sheet, int column, int row,
			String name) throws WriteException, RowsExceededException
	{
		Label label = new Label(column, row, name, cellFormat);
	    sheet.addCell(label);
	}
	private void addNumber(WritableSheet sheet, int column, int row,
			Integer integer) throws WriteException, RowsExceededException
	{
		Number number;
		number = new Number(column, row, integer, cellFormat);
		sheet.addCell(number);
	}
	private void addDate (WritableSheet sheet, int column, int row,
			Date date) throws WriteException, RowsExceededException
	{
		DateTime dateTime=new DateTime(column, row,date,dateFormat);
		sheet.addCell(dateTime);
	}
	private Date getDate(Cell cell)
	{
		if(cell.getType()==CellType.DATE)
		{
			return ((DateCell)cell).getDate();
		}
		else
		{
			return null;
		}
	}
	@Override
	protected void onDestroy()
	{
		if(isSave)
		{
			try
			{
				WritableWorkbook wbsheet;
				wbsheet = Workbook.createWorkbook(file, workbook);
				WritableSheet excelSheet = wbsheet.getSheet(0);
				for (int i = 0; i < gameInfos.length; i++)
				{
					if (gameInfos[i].getNumber() != -1)
					{
						addNumber(excelSheet, 4, i, gameInfos[i].getNumber());
					}
				}
				String[] keys = dateInfoTable.keySet().toArray(new String[dateInfoTable.size()]);
		        Arrays.sort(keys);  
		        for(String key:keys) 
		        {
		        	WritableSheet dateSheet=wbsheet.getSheet(key);
		        	if(dateSheet==null)
		        	{
		        		wbsheet.createSheet(key, wbsheet.getNumberOfSheets()+1);
		        		dateSheet=wbsheet.getSheet(key);
		        	}
		        	for(int i=dateSheet.getRows();i<dateInfoTable.get(key).size();i++)
		        	{
		        		DateInfo dateInfo=dateInfoTable.get(key).get(i);
		        		this.addName(dateSheet,0,i,dateInfo.getName());
		        		this.addDate(dateSheet, 1, i, dateInfo.getDate());
		        	}
		        }
				wbsheet.write();
				wbsheet.close();
			} 
			catch (RowsExceededException e)
			{
				e.printStackTrace();
			} catch (WriteException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		super.onDestroy();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.search_name:
			onSearchRequested();
			break;
		case R.id.number_1:
		case R.id.number_2:
		case R.id.number_2_:
		case R.id.number_3:
		case R.id.number_4:
		case R.id.number_5:
		case R.id.number_6:
		case R.id.number_7:
		case R.id.number_8:
		case R.id.number_9:
		case R.id.number_10:
		case R.id.number_11:
		case R.id.number_12:
			Intent mIntent = new Intent(this, SearchPeople.class);
			mIntent.putExtra("value", item.getItemId());
			this.startActivity(mIntent);
			break;
		case R.id.search_date:
			Intent intent=new Intent(this,SearchDate.class);
			this.startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public boolean onSearchRequested()
	{
		Log.v("TEST", "onSearchRequested");
		startSearch("", false, null, false);
		return true;
	}
	@Override
	protected void onRestart()
	{
		Log.v("TEST", "onRestart");
		this.setLists();
		super.onRestart();
	}
}