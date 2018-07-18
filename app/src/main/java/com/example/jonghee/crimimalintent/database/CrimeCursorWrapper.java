package com.example.jonghee.crimimalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.jonghee.crimimalintent.Crime;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor){
        super(cursor);
    }
    public Crime getCrime(){
        String uuidString = getString(getColumnIndex(CrimeDBSchema.CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeDBSchema.CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeDBSchema.CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeDBSchema.CrimeTable.Cols.SOLVED));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setData(new Date(date));
        crime.setSolved(isSolved !=0);

        return crime;
    }
}
