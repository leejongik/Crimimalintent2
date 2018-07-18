package com.example.jonghee.crimimalintent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jonghee.crimimalintent.Crime;

public class CrimeBaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public CrimeBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + CrimeDBSchema.CrimeTable.NAME + "("+
                " _id integer primary key autoincrement, " +
                CrimeDBSchema.CrimeTable.Cols.UUID + ","+
                CrimeDBSchema.CrimeTable.Cols.TITLE +","+
                CrimeDBSchema.CrimeTable.Cols.DATE +","+
                CrimeDBSchema.CrimeTable.Cols.SOLVED +
                ")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }
}
