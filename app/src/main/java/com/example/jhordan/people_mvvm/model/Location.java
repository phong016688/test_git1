package com.example.jhordan.people_mvvm.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jhordan on 02/03/16.
 */
public class Location {

    @SerializedName("street")
    private String mStreet;

    @SerializedName("city")
    private String mCity;

    @SerializedName("state")
    private String mState;

    @SerializedName("zip")
    private String mZip;

    public String getStreet() {
        return mStreet;
    }

    public String getCity() {
        return mCity;
    }

    public String getState() {
        return mState;
    }

    public String getZip() {
        return mZip;
    }


}
