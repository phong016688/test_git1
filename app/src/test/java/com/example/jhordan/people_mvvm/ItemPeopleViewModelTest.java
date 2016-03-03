package com.example.jhordan.people_mvvm;

import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.view.View;

import com.example.jhordan.people_mvvm.model.People;
import com.example.jhordan.people_mvvm.viewmodel.ItemPeopleViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
/**
 * Created by Jhordan on 03/03/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ItemPeopleViewModelTest {

    PeopleApplication mPeopleApplication;

    @Before
    public void setUp() {
        mPeopleApplication = (PeopleApplication) RuntimeEnvironment.application;
    }

    @Test
    public void shouldGetPeopleCell() {
        People people = new People();
        people.mCell = "0177-6155420";
        ItemPeopleViewModel itemPeopleViewModel = new ItemPeopleViewModel(people, mPeopleApplication);
        assertEquals(people.mCell, itemPeopleViewModel.getCell());
    }

    @Test
    public void shouldGetPeopleMail() {
        People people = new People();
        people.mMail = "theodor.kaufmann@example.com";
        ItemPeopleViewModel itemPeopleViewModel = new ItemPeopleViewModel(people, mPeopleApplication);
        assertEquals(people.mMail, itemPeopleViewModel.getMail());
    }

    @Test
    public void shouldStartPeopleDetailActivityOnItemClick() {
        People people = new People();
        Context mockContext = mock(Context.class);
        ItemPeopleViewModel itemPeopleViewModel = new ItemPeopleViewModel(people,mockContext);
        itemPeopleViewModel.onItemClick(new View(mPeopleApplication));
        verify(mockContext).startActivity(any(Intent.class));
    }

    @Test
    public void shouldNotifyPropertyChangeWhenSetPeople() {
        People people = new People();
        ItemPeopleViewModel itemPeopleViewModel = new ItemPeopleViewModel(people,mPeopleApplication);
        Observable.OnPropertyChangedCallback mockCallback = mock(Observable.OnPropertyChangedCallback.class);
        itemPeopleViewModel.addOnPropertyChangedCallback(mockCallback);
        itemPeopleViewModel.setPeople(people);
        verify(mockCallback).onPropertyChanged(any(Observable.class), anyInt());
    }

}
