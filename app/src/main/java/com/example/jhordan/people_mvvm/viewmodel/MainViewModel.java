package com.example.jhordan.people_mvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.jhordan.people_mvvm.PeopleApplication;
import com.example.jhordan.people_mvvm.data.PeopleResponse;
import com.example.jhordan.people_mvvm.data.PeopleService;
import com.example.jhordan.people_mvvm.model.People;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Jhordan on 02/03/16.
 */
public class MainViewModel implements MainViewModelContract.ViewModel {

    public ObservableInt mProgressPeople;
    public ObservableInt mListPeople;


    private List<People> mPeopleList = new ArrayList<>();
    private MainViewModelContract.MainView mMainView;
    private Context mContext;
    private Subscription mSubscription;

    public MainViewModel(@NonNull MainViewModelContract.MainView mainView, @NonNull Context context) {

        mMainView = mainView;
        mContext = context;
        mProgressPeople = new ObservableInt(View.GONE);
        mListPeople = new ObservableInt(View.GONE);

    }


    public void onClickFabLoad(View view) {

        mListPeople.set(View.INVISIBLE);
        mProgressPeople.set(View.VISIBLE);
        fetchPeopleList();
    }


    private void fetchPeopleList() {

        final String URL = "http://api.randomuser.me/?results=10&nat=en";
        unSubscribeFromObservable();
        PeopleApplication peopleApplication = PeopleApplication.create(mContext);
        PeopleService peopleService = peopleApplication.getPeopleService();
        mSubscription = peopleService.fetchPeople(URL)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(peopleApplication.subscribeScheduler())
                .map(new Func1<PeopleResponse, List<PeopleResponse.User>>() {
                    @Override
                    public List<PeopleResponse.User> call(PeopleResponse peopleResponse) {
                        return peopleResponse.getPeopleList();
                    }
                }).subscribe(new Action1<List<PeopleResponse.User>>() {
                    @Override
                    public void call(List<PeopleResponse.User> users) {

                        converterUserObjectToPeople(users).subscribe(new Action1<List<People>>() {
                            @Override
                            public void call(List<People> peoples) {

                                mProgressPeople.set(View.GONE);
                                mListPeople.set(View.VISIBLE);

                                if (mMainView != null)
                                    mMainView.loadData(peoples);
                            }
                        });

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

    }


    private Observable<List<People>> converterUserObjectToPeople(List<PeopleResponse.User> users) {

        final List<People> mPeople = new ArrayList<>();
        for (PeopleResponse.User user : users) {
            People people = new People();
            people.mGender = user.getPeople().mUserName;
            people.mName = user.getPeople().mName;
            people.mLocation = user.getPeople().mLocation;
            people.mMail = user.getPeople().mMail;
            people.mUserName = user.getPeople().mUserName;
            people.mPhone = user.getPeople().mPhone;
            people.mCell = user.getPeople().mCell;
            people.mPicture = user.getPeople().mPicture;
            mPeople.add(people);
        }

        return Observable.create(new Observable.OnSubscribe<List<People>>() {
            @Override
            public void call(Subscriber<? super List<People>> subscriber) {
                if (!subscriber.isUnsubscribed())
                    subscriber.onNext(mPeople);
                subscriber.onCompleted();
            }
        });
    }


    @Override
    public void destroy() {
        reset();
    }

    private void unSubscribeFromObservable() {
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }

    private void reset() {
        unSubscribeFromObservable();
        mSubscription = null;
        mContext = null;
        mMainView = null;
    }
}
