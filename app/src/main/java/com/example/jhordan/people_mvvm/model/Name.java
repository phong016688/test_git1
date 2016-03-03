package com.example.jhordan.people_mvvm.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jhordan on 02/03/16.
 */
public class Name {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("first")
    private String mFirts;

    @SerializedName("last")
    private String mLast;

    public String getTitle() {
        return mTitle;
    }

    public String getFirts() {
        return mFirts;
    }

    public String getLast() {
        return mLast;
    }

}
