package com.example.jhordan.people_mvvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jhordan.people_mvvm.model.People;
import com.example.jhordan.people_mvvm.view.PeopleDetailActivity;

/**
 * Created by Jhordan on 02/03/16.
 */
public class ItemPeopleViewModel extends BaseObservable {

    private People mPeople;
    private Context mContext;

    public ItemPeopleViewModel(People people , Context context) {
        mPeople = people;
        mContext = context;
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
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

    public void onItemClick(View view){
        mContext.startActivity(PeopleDetailActivity.launchDetail(view.getContext(),mPeople));
    }
    public void setPeople(People people) {
        mPeople = people;
        notifyChange();
    }


}
