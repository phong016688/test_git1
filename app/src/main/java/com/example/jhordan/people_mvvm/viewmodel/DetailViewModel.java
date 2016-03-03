package com.example.jhordan.people_mvvm.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jhordan.people_mvvm.model.People;

/**
 * Created by Jhordan on 03/03/16.
 */
public class DetailViewModel {

    private Context mContext;
    private People mPeople;

    public DetailViewModel(Context context, People people) {
        mContext = context;
        mPeople = people;

    }

    public String getFullUserName() {
        mPeople.mFullName = mPeople.mName.mTitle + "." + mPeople.mName.mFirts + " " + mPeople.mName.mLast;
        return mPeople.mFullName;
    }

    public String getUserName(){
        return mPeople.mUserName;
    }

    public String getEmail(){
        return mPeople.mMail;
    }

    public String getCell(){
        return mPeople.mCell;
    }

    public String getPictureProfile(){
        return mPeople.mPicture.large;
    }

    public String getAddress(){
        return mPeople.mLocation.mStreet+ " "+ mPeople.mLocation.mCity+ " " + mPeople.mLocation.mState;
    }

    public String getGender(){
        return mPeople.mGender;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }
}
