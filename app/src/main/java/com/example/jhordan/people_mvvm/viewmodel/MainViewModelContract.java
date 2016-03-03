package com.example.jhordan.people_mvvm.viewmodel;

import android.content.Context;

import com.example.jhordan.people_mvvm.model.People;

import java.util.List;

/**
 * Created by Jhordan on 02/03/16.
 */
public interface MainViewModelContract {

    interface MainView {

        Context getContext();

        void loadData(List<People> peoples);
    }

    interface ViewModel {

        void destroy();
    }
}
