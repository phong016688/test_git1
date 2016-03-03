package com.example.jhordan.people_mvvm.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jhordan on 02/03/16.
 */
public class Picture {

    @SerializedName("large")
    private String large;

    @SerializedName("medium")
    private String mMedium;

    @SerializedName("thumbnail")
    private String mThumbnail;

    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return mMedium;
    }

    public String getThumbnail() {
        return mThumbnail;
    }


}
