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

package com.example.jhordan.people_mvvm;


import com.example.jhordan.people_mvvm.data.FakeRandomUserGeneratorAPI;
import com.example.jhordan.people_mvvm.data.PeopleResponse;
import com.example.jhordan.people_mvvm.data.PeopleService;
import com.example.jhordan.people_mvvm.model.People;
import com.example.jhordan.people_mvvm.viewmodel.MainViewModel;
import com.example.jhordan.people_mvvm.viewmodel.MainViewModelContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import rx.schedulers.Schedulers;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;


/**
 * Notes for Mac!!
 * <p>
 * If you are on a Mac, you will probably need to configure the
 * default JUnit test runner configuration in order to work around a bug where IntelliJ / Android Studio
 * does not set the working directory to the module being tested. This can be accomplished by editing
 * the run configurations, Defaults -> JUnit and changing the working directory value to $MODULE_DIR$
 * <p>
 * You have to specify  sdk < 23 (Robolectric does not support API level 23.)
 * <p>
 * https://github.com/robolectric/robolectric/issues/1648
 **/

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainViewModelTest {

    private static final String URL_TEST = "http://api.randomuser.me/?results=10&nat=en";

    @Mock
    private PeopleService mPeopleService;
    @Mock
    private MainViewModelContract.MainView mMainView;

    @Before
    public void setUp() {
        // inject the mocks
        MockitoAnnotations.initMocks(this);

        // Mock the PeopleService so we don't call the Random User Generator API directly
        // and all observables will now run on the same thread
        PeopleApplication peopleApplication = (PeopleApplication) RuntimeEnvironment.application;
        peopleApplication.setPeopleService(mPeopleService);
        peopleApplication.setScheduler(Schedulers.immediate());

        // TODO I will write the more test
        MainViewModel mainViewModel = new MainViewModel(mMainView, peopleApplication);


    }

    @Test
    public void getRandomUserGeneratorList() {

        // Mock Responses of Random User Generator API
        List<PeopleResponse.User> mockUserList = FakeRandomUserGeneratorAPI.getUserList();
        List<People> mockPeopleList = FakeRandomUserGeneratorAPI.getPeopleList();

        //No interactions with mMainView and method loadData 'couse that must convert user objects type from PeopleResponse.User
        // to People like production code observable
        doReturn(rx.Observable.just(mockUserList)).when(mPeopleService).fetchPeople(URL_TEST);
        verify(mMainView, never()).loadData(mockPeopleList);
        verifyZeroInteractions(mMainView);


    }

}
