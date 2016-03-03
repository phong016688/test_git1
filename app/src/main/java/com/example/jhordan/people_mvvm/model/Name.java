package com.example.jhordan.people_mvvm.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jhordan on 02/03/16.
 */
public class Name implements Serializable {

    @SerializedName("title")
    public String mTitle;

    @SerializedName("first")
    public String mFirts;

    @SerializedName("last")
    public String mLast;



}
