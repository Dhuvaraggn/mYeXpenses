package com.example.originals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Databaseex extends SQLiteOpenHelper {

    private static final String dbname="expensed";
    private static final String tbname="expenseddb";
    private static final String dateof="dateofexpensed";
    private static final String monthof="monthofexpensed";
    private static final String food="food";
    private static final String sundry="sundry";
    private static final String loan="loan";
    private static final String educa="education";
    private static final String groce="groceries";
    private static final String petrol="petrol";
    private static final String total="total";


    Databaseex(Context context) {
        super(context, dbname,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tbname+"("+dateof+" TEXT primary key,"+food+"  integer ,"+groce+" integer,"+sundry+" integer ,"+educa+" integer ,"+petrol+" integer ,"+loan+" integer ,"+total+" integer,"+monthof+" Text)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table IF NOT EXISTS  " + tbname + "("+dateof+" TEXT primary key," + food + "  integer,"+groce+" integer,"+sundry+" integer ,"+educa+" integer ,"+petrol+" integer ,"+loan+" integer ,"+total+" integer,"+monthof+" Text)");


    }
    Cursor outdatedata(String date)
    {
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor cursor;
        cursor = db1.rawQuery("select * from "+tbname+" where "+dateof+"=?" ,new String[] {String.valueOf(date)});
    return cursor;


    }

    Cursor outdatedata1(String date)
    {
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor cursor1;
        cursor1 = db1.rawQuery("select * from "+tbname+" where "+dateof+"=?" ,new String[] {String.valueOf(date)});
        return cursor1;

    }
    boolean insertdata(String dated,int foodd,int groced ,int sundryd,int educad,int petrold,int loand,int totald,String monthd)
    {
        SQLiteDatabase db1=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(dateof,dated);
        contentValues.put(food,foodd);
        contentValues.put(groce,groced);
        contentValues.put(sundry,sundryd);
        contentValues.put(educa,educad);
        contentValues.put(petrol,petrold);
        contentValues.put(loan,loand);
        contentValues.put(total,totald);
        contentValues.put(monthof,monthd);
        long result= db1.insert(tbname,null,contentValues);

        return result != -1;
    }
    Cursor outmonthdata(String month)
    {
        SQLiteDatabase db2 = this.getWritableDatabase();
        Cursor cursor;
         cursor = db2.rawQuery("SELECT * FROM "+tbname+" WHERE "+monthof+"=?",new String[] {String.valueOf(month)});

        return cursor;


    }
    boolean deldatedata1(String date)
    {
        SQLiteDatabase db1 = this.getWritableDatabase();
      return db1.delete(tbname,dateof +"=?",new String[]{date})>0;

    }

}