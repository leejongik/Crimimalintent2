package com.example.jonghee.crimimalintent;

import android.provider.ContactsContract;

import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;
    }

    public Date getDate() {
        return mDate;

    }

    public void setData(Date data) {
        mDate = data;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Crime(){
    }

    public Crime(UUID id){
        mId =id;
        mDate = new Date();
    }
}
