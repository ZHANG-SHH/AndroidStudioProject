package com.example.trail;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG="DatabaseHelper";
    /**
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION_CODE);
    }
    @Override

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG,"创建数据库");

        String sql1="create table "+Constants.TABLE_NAME1+"( name varchar,room varchar,weekDay varchar,classFrom integer,classTo integer,weekFrom integer,weekTo integer)";
        sqLiteDatabase.execSQL(sql1);
        String sql2="create table "+Constants.TABLE_NAME2+" ( year integer,month integer,day integer)";
        sqLiteDatabase.execSQL(sql2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        Log.d(TAG,"升级数据库");
    }
    /*public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION_CODE);
    }
    @Override

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG,"创建数据库");

        String sql="create table "+Constants.TABLE_NAME1+"( name varchar,room varchar,weekDay varchar,classFrom integer,classTo integer,weekFrom integer,weekTo integer)";
        sqLiteDatabase.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        Log.d(TAG,"升级数据库");
    }*/
}
