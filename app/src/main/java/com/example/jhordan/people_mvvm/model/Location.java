package com.example.jhordan.people_mvvm.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jhordan on 02/03/16.
 */
public class Location implements Serializable {

    @SerializedName("street")
    public String mStreet;

    @SerializedName("city")
    public String mCity;

    @SerializedName("state")
    public String mState;

    @SerializedName("zip")
    public String mZip;



}
