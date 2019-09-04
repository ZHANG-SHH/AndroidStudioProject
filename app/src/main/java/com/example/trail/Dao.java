package com.example.trail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Dao {
    private final DatabaseHelper mHelper;
    private static final String TAG= "Dao";
    public String[] thisNames=new String[200];
    public String[] thisRooms=new String[200];
    public String[] thisWeekdays=new String[200];
    public int[] classFroms=new int[200];
    public int[] classTos=new int[200];
    public int[] weekFroms=new int[200];
    public int[] weekTos=new int[200];

    public Dao(Context context){
        //创建数据库
        mHelper = new DatabaseHelper(context);
    }

    //插入
    public void insert(String name,String room,String weekDay,int classFrom,int classTo,int weekFrom,int weekTo){
        SQLiteDatabase db=mHelper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put("name",name);
        contentValues.put("room",room);
        contentValues.put("weekDay",weekDay);
        contentValues.put("classFrom",classFrom);
        contentValues.put("classTo",classTo);
        contentValues.put("weekFrom",weekFrom);
        contentValues.put("weekTo",weekTo);

        db.insert(Constants.TABLE_NAME1,null,contentValues);
        db.close();
    }

    //删除
    public void delete(){
        SQLiteDatabase db=mHelper.getWritableDatabase();

        db.delete(Constants.TABLE_NAME1,null,null);
    }
    public void delete(String name){
        SQLiteDatabase db=mHelper.getWritableDatabase();

        db.delete(Constants.TABLE_NAME1,"name='"+name+"'",null);
    }
    public void delete(String name,String weekDay){
        SQLiteDatabase db=mHelper.getWritableDatabase();

        db.delete(Constants.TABLE_NAME1,"name='"+name+"' and weekDay= '"+weekDay+"'",null);
    }

    //更新
    public void update(String replaceName,String key,String value){
        SQLiteDatabase db=mHelper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(key,value);
        db.update(Constants.TABLE_NAME1,contentValues,"name='"+replaceName+"'",null);
        db.close();
    }
    public void update(String replaceName,String replaceWeekday,String key,String value){
        SQLiteDatabase db=mHelper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(key,value);
        db.update(Constants.TABLE_NAME1,contentValues,"name='"+replaceName+"' and weekDay='"+replaceWeekday+"'",null);
        db.close();

    }

    //查询
    public void query(){
        SQLiteDatabase db=mHelper.getWritableDatabase();
        int i=0;
        Cursor cursor=db.query(false,Constants.TABLE_NAME1,null,null,null,null,null,null,null);

        while(cursor.moveToNext() )
        if (i<allCaseNum())
        {
            String name=cursor.getString(0);
            String room=cursor.getString(1);
            String weekDay=cursor.getString(2);
            int classFrom=Integer.parseInt(cursor.getString(3));
            int classTo=Integer.parseInt(cursor.getString(4));
            int weekFrom=Integer.parseInt(cursor.getString(5));
            int weekTo=Integer.parseInt(cursor.getString(6));

            thisNames[i]=name;
            thisRooms[i]=room;
            thisWeekdays[i]=weekDay;
            classFroms[i]=classFrom;
            classTos[i]=classTo;
            weekFroms[i]=weekFrom;
            weekTos[i]=weekTo;
            Log.d(TAG,"name="+name+" room="+room+" weekDay="+weekDay+i);
            i++;
        }
        cursor.close();
        db.close();
    }
    public long allCaseNum( ){
        SQLiteDatabase sqLiteDatabase=mHelper.getWritableDatabase();
        String sql = "select count(*) from "+Constants.TABLE_NAME1;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        cursor.close();
        return count;
    }
    /*
    private final DatabaseHelper mHelper;
    private static final String TAG= "Dao";
    public String[] thisNames=new String[(int)allCaseNum()];
    public String[] thisRooms=new String[(int)allCaseNum()];
    public String[] thisWeekdays=new String[(int)allCaseNum()];
    public int[] classFroms=new int[(int)allCaseNum()];
    public int[] classTos=new int[(int)allCaseNum()];
    public int[] weekFroms=new int[(int)allCaseNum()];
    public int[] weekTos=new int[(int)allCaseNum()];

    public Dao(Context context){
        //创建数据库
        mHelper = new DatabaseHelper(context);
    }

    //插入语句
    public void insert(String name,String room,String weekDay,int classFrom,int classTo,int weekFrom,int weekTo){
        SQLiteDatabase sqLiteDatabase=mHelper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put("name",name);
        contentValues.put("room",room);
        contentValues.put("weekDay",weekDay);
        contentValues.put("classFrom",classFrom);
        contentValues.put("classTo",classTo);
        contentValues.put("weekFrom",weekFrom);
        contentValues.put("weekTo",weekTo);

        sqLiteDatabase.insert(Constants.TABLE_NAME1,null,contentValues);
        sqLiteDatabase.close();
    }
    public void insert(int year,int month,int day){
        SQLiteDatabase sqLiteDatabase=mHelper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put("year",year);
        contentValues.put("month",month);
        contentValues.put("day",day);

        sqLiteDatabase.insert(Constants.TABLE_NAME2,null,contentValues);
        sqLiteDatabase.close();
    }

    //删除语句
    public void delete(){
        SQLiteDatabase sqLiteDatabase=mHelper.getWritableDatabase();

        sqLiteDatabase.delete(Constants.TABLE_NAME1,null,null);
    }
    public void delete(String name){
        SQLiteDatabase db=mHelper.getWritableDatabase();

        db.delete(Constants.TABLE_NAME1,"name='"+name+"'",null);
    }
    public void delete(String name,String weekDay){
        SQLiteDatabase db=mHelper.getWritableDatabase();

        db.delete(Constants.TABLE_NAME1,"name='"+name+"' and weekDay= '"+weekDay+"'",null);
    }

    //更新语句
    public void update(String key,String replacedValue,String replaceValue,String keyLimit,String valueLimit){
        SQLiteDatabase sqLiteDatabase=mHelper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(key,replaceValue);
        sqLiteDatabase.update(Constants.TABLE_NAME1,contentValues,key+"='"+replacedValue+"' and "+keyLimit+"='"+valueLimit,null);
        sqLiteDatabase.close();

    }

    //查询语句
    public void query(){
        SQLiteDatabase sqLiteDatabase=mHelper.getWritableDatabase();
        int i=0;
        Cursor cursor=sqLiteDatabase.query(false,Constants.TABLE_NAME1,null,null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            String name=cursor.getString(0);
            String room=cursor.getString(1);
            String weekDay=cursor.getString(2);
            int classFrom=Integer.parseInt(cursor.getString(3));
            int classTo=Integer.parseInt(cursor.getString(4));
            int weekFrom=Integer.parseInt(cursor.getString(5));
            int weekTo=Integer.parseInt(cursor.getString(6));

            thisNames[i]=name;
            thisRooms[i]=room;
            thisWeekdays[i]=weekDay;
            classFroms[i]=classFrom;
            classTos[i]=classTo;
            weekFroms[i]=weekFrom;
            weekTos[i]=weekTo;

            Log.d(TAG,"name="+name+" room="+room+" weekDay="+weekDay);
            i++;
        }
        cursor.close();
        sqLiteDatabase.close();
    }
    public long allCaseNum( ){
        SQLiteDatabase sqLiteDatabase=mHelper.getWritableDatabase();
        String sql = "select count(*) from "+Constants.TABLE_NAME1;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        cursor.close();
        return count;
    }*/
}
