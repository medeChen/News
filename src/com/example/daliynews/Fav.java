package com.example.daliynews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.db.Dbservice;
import com.example.pages.View1;

public class Fav extends Activity {
	Dbservice dbhelper;
	private Map<Integer, String> title = new HashMap<Integer, String>();
	private Map<Integer, String> url = new HashMap<Integer, String>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fav);
		dbhelper = new Dbservice(this);
		SQLiteDatabase dbs = dbhelper.getWritableDatabase();
		Cursor cursor = dbs.rawQuery("select * from fav", null);
		int i = 0;
		while (cursor.moveToNext()) {
			title.put(i, cursor.getString(0));
			url.put(i, cursor.getString(1));
			i++;
		}
		List<Map<String, String>> listItems = new ArrayList<Map<String, String>>();
		for (int j = 0; j < i; j++) {
			Map<String, String> listItem = new HashMap<String, String>();
			listItem.put("Title", title.get(j));
			listItems.add(listItem);
		}
		SimpleAdapter simple = new SimpleAdapter(this, listItems,
				R.layout.simple, new String[] { "Title" },
				new int[] { R.id.title});
		ListView lv = (ListView) findViewById(R.id.list);
		lv.setAdapter(simple);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(Fav.this, Web.class);
				Bundle bundle = new Bundle();
				bundle.putString("url", url.get(position));
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		});
	}

}
