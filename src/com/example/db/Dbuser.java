package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbuser extends SQLiteOpenHelper{

    final static int version = 1;
    final static String dbName = "login";

    public Dbuser(Context context){
        super(context,dbName,null,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE [user] ("
                + "[Username] varchar(200)," 
                + "[Password] varchar(500))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}
