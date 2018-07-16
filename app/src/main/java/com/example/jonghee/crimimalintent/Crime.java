package com.example.jonghee.crimimalintent;

import java.util.UUID;

public class Crime {

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    private UUID mId;
    private String mTitle;

    public Crime(){
        mId = UUID.randomUUID();
    }
}
