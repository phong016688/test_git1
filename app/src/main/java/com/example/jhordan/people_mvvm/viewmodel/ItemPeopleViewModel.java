package com.example.jhordan.people_mvvm.viewmodel;

import android.databinding.BaseObservable;

import com.example.jhordan.people_mvvm.model.People;

/**
 * Created by Jhordan on 02/03/16.
 */
public class ItemPeopleViewModel extends BaseObservable {

    private People mPeople;

    public ItemPeopleViewModel(People people) {
        mPeople = people;
    }

    public String getUserName() {
        return mPeople.mUserName;
    }

    public void setPeople(People people) {
        mPeople = people;
        notifyChange();
    }


}
