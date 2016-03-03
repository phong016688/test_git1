package com.example.jhordan.people_mvvm.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jhordan on 02/03/16.
 */
public class Picture implements Serializable {

    @SerializedName("large")
    public String large;

    @SerializedName("medium")
    public String mMedium;

    @SerializedName("thumbnail")
    public String mThumbnail;


}
