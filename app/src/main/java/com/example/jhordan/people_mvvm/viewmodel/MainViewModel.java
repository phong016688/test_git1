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
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.jhordan.people_mvvm.PeopleApplication;
import com.example.jhordan.people_mvvm.R;
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


public class MainViewModel implements MainViewModelContract.ViewModel {

    public ObservableInt mPeopleProgress;
    public ObservableInt mPeopleList;
    public ObservableInt mPeopleLabel;
    public ObservableField<String> mMessageLabel;

    private MainViewModelContract.MainView mMainView;
    private Context mContext;
    private Subscription mSubscription;

    public MainViewModel(@NonNull MainViewModelContract.MainView mainView, @NonNull Context context) {

        mMainView = mainView;
        mContext = context;
        mPeopleProgress = new ObservableInt(View.GONE);
        mPeopleList = new ObservableInt(View.GONE);
        mPeopleLabel = new ObservableInt(View.VISIBLE);
        mMessageLabel = new ObservableField<>(context.getString(R.string.default_loading_people));

    }

    public void onClickFabLoad(View view) {
        mPeopleLabel.set(View.GONE);
        mPeopleList.set(View.GONE);
        mPeopleProgress.set(View.VISIBLE);
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

                                mPeopleProgress.set(View.GONE);
                                mPeopleLabel.set(View.GONE);
                                mPeopleList.set(View.VISIBLE);

                                if (mMainView != null)
                                    mMainView.loadData(peoples);
                            }
                        });

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        mMessageLabel.set(mContext.getString(R.string.error_loading_people));
                        mPeopleProgress.set(View.GONE);
                        mPeopleLabel.set(View.VISIBLE);
                        mPeopleList.set(View.GONE);
                    }
                });

    }


    private Observable<List<People>> converterUserObjectToPeople(final List<PeopleResponse.User> users) {

        return Observable.create(new Observable.OnSubscribe<List<People>>() {
            @Override
            public void call(Subscriber<? super List<People>> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    final List<People> mPeople = new ArrayList<>();
                    for (PeopleResponse.User user : users) {
                        People people = new People();
                        people.mGender = user.getPeople().mGender;
                        people.mName = user.getPeople().mName;
                        people.mLocation = user.getPeople().mLocation;
                        people.mMail = user.getPeople().mMail;
                        people.mUserName = user.getPeople().mUserName;
                        people.mPhone = user.getPeople().mPhone;
                        people.mCell = user.getPeople().mCell;
                        people.mPicture = user.getPeople().mPicture;
                        mPeople.add(people);
                    }
                    subscriber.onNext(mPeople);
                    subscriber.onCompleted();
                }

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
