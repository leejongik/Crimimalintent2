package com.example.jonghee.crimimalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jonghee.crimimalintent.database.CrimeBaseHelper;
import com.example.jonghee.crimimalintent.database.CrimeCursorWrapper;
import com.example.jonghee.crimimalintent.database.CrimeDBSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context){
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }
    private CrimeLab(Context context){
        mContext =context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext)
                .getWritableDatabase();
    }

    public void addCrime(Crime c){
        ContentValues values = getContentValue(c);
        mDatabase.insert(CrimeDBSchema.CrimeTable.NAME, null, values);
    }

    public List<Crime> getCrimes(){
        List<Crime>crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return crimes;
    }

    public Crime getCrime(UUID id){
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeDBSchema.CrimeTable.Cols.UUID + "=?",
                new String[]{id.toString()}
        );

        try{
            if (cursor.getCount()==0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCrime();
        }finally {
            cursor.close();
        }
    }

    public void updateCrime(Crime crime){
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValue(crime);

        mDatabase.update(CrimeDBSchema.CrimeTable.NAME, values,
                CrimeDBSchema.CrimeTable.Cols.UUID + "=?",
                new String[]{ uuidString});
    }
    private static ContentValues getContentValue(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeDBSchema.CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeDBSchema.CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeDBSchema.CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeDBSchema.CrimeTable.Cols.SOLVED, crime.isSolved() ? 1:0);

        return values;
    }
    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CrimeDBSchema.CrimeTable.NAME,
                null, // 테이블 열(Columns)-null인 ㄱㅇ우 테이블의 모든 열을 의미
                whereClause,
                whereArgs,
                null, // SQL Select 명령문의 groupBy
                null, // having
                null // orderBy
        );
        return new CrimeCursorWrapper(cursor);
    }
}
