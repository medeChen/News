package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbservice extends SQLiteOpenHelper{

    final static int version = 1;
    final static String dbName = "news";

    public Dbservice(Context context){
        super(context,dbName,null,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE [fav] ("
                + "[Title] varchar(200)," 
                + "[Url] varchar(500))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}
