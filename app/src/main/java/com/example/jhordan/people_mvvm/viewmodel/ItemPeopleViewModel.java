package com.example.jhordan.people_mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jhordan.people_mvvm.model.People;

/**
 * Created by Jhordan on 02/03/16.
 */
public class ItemPeopleViewModel extends BaseObservable {

    private People mPeople;

    public ItemPeopleViewModel(People people) {
        mPeople = people;
    }

    public String getFullName() {
        mPeople.mFullName = mPeople.mName.mTitle + "." + mPeople.mName.mFirts + " " + mPeople.mName.mLast;
        return mPeople.mFullName;
    }

    public String getCell(){
        return mPeople.mCell;
    }

    public String getMail(){
        return mPeople.mMail;
    }

    public String getPictureProfile(){
        return mPeople.mPicture.large;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }


    public void setPeople(People people) {
        mPeople = people;
        notifyChange();
    }


}
