/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.example.jhordan.people_mvvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jhordan.people_mvvm.model.People;
import com.example.jhordan.people_mvvm.view.PeopleDetailActivity;


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
