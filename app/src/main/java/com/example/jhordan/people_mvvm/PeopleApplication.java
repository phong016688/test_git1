package com.example.jhordan.people_mvvm;

import android.app.Application;
import android.content.Context;

import com.example.jhordan.people_mvvm.data.PeopleFactory;
import com.example.jhordan.people_mvvm.data.PeopleService;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by Jhordan on 02/03/16.
 */
public class PeopleApplication extends Application {

    private PeopleService mPeopleService;
    private Scheduler mScheduler;

    private static PeopleApplication get(Context context) {
        return (PeopleApplication) context.getApplicationContext();
    }

    public static PeopleApplication create(Context context){
        return PeopleApplication.get(context);
    }

    public PeopleService getPeopleService() {
        if (mPeopleService == null)
            mPeopleService = PeopleFactory.create();

        return mPeopleService;
    }

    public Scheduler subscribeScheduler() {
        if (mScheduler == null)
            mScheduler = Schedulers.io();

        return mScheduler;
    }

    public void setPeopleService(PeopleService peopleService) {
        mPeopleService = peopleService;
    }

    public void setScheduler(Scheduler scheduler) {
        mScheduler = scheduler;
    }




}
