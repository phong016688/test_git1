package com.example.jhordan.people_mvvm.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jhordan on 02/03/16.
 */
public class People implements Serializable {

    @SerializedName("gender")
    public String mGender;

    @SerializedName("name")
    public Name mName;

    @SerializedName("location")
    public Location mLocation;

    @SerializedName("email")
    public String mMail;

    @SerializedName("username")
    public String mUserName;

    @SerializedName("phone")
    public String mPhone;

    @SerializedName("cell")
    public String mCell;

    @SerializedName("picture")
    public Picture mPicture;

    public String mFullName;


}
