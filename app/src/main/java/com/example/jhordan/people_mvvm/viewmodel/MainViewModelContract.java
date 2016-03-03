package com.example.jhordan.people_mvvm.viewmodel;

import com.example.jhordan.people_mvvm.model.People;

import java.util.List;

/**
 * Created by Jhordan on 02/03/16.
 */
public interface MainViewModelContract {

    interface MainView {
        void loadData(List<People> peoples);
    }
}
