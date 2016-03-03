package com.example.jhordan.people_mvvm.viewmodel;

import android.databinding.ObservableInt;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.jhordan.people_mvvm.model.People;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhordan on 02/03/16.
 */
public class MainViewModel {

    public ObservableInt mProgressPeople;
    public ObservableInt mListPeople;

    private List<People> mPeopleList = new ArrayList<>();
    private MainViewModelContract.MainView mMainView;

    public  MainViewModel(@NonNull MainViewModelContract.MainView mainView){

        mMainView = mainView;
        mProgressPeople= new ObservableInt(View.GONE);
        mListPeople = new ObservableInt(View.GONE);

    }



    public void onClickFabLoad(View view){

        mProgressPeople.set(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMainView.loadData(getPeopleList());
                mListPeople.set(View.VISIBLE);
                mProgressPeople.set(View.GONE);
            }
        }, 2000);


    }


    private List<People> getPeopleList() {


        for(int i = 0; i < 10; i++){
            People people = new People();
            people.mUserName = "ERIK";
            mPeopleList.add(people);
        }
        return mPeopleList;
    }
}
