package com.pabji.wheatherapp.data.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper{

    private static DBHelper instance;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WeatherDB.db";

    private String[] SQL_CREATE_TABLES = DBSQLTables.SQL_CREATE_TABLES;
    private String[] SQL_DELETE_TABLES = DBSQLTables.SQL_DELETE_TABLES;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        for(String SQL : SQL_CREATE_TABLES){
            db.execSQL(SQL);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        for(String SQL : SQL_DELETE_TABLES){
            db.execSQL(SQL);
        }
        onCreate(db);
    }

    public static synchronized DBHelper getInstance(Context context){
        if(instance == null){
            instance = new DBHelper(context);
        }
        return instance;
    }
}
