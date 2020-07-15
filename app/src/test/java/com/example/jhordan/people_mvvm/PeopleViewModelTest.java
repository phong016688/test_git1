/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jhordan.people_mvvm;

import android.content.Context;
import android.view.View;
import com.example.jhordan.people_mvvm.viewmodel.PeopleViewModel;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class) public class PeopleViewModelTest {

    private Context mockContext = mock(Context.class);

    private PeopleViewModel peopleViewModel;

    @Before public void setUpMainViewModelTest() {
        peopleViewModel = new PeopleViewModel(mockContext);
    }

    @Test public void ensureTheViewsAreInitializedCorrectly() {
        peopleViewModel.initializeViews();

        assertEquals(View.GONE, peopleViewModel.peopleLabel.get());
        assertEquals(View.GONE, peopleViewModel.peopleRecycler.get());
        assertEquals(View.VISIBLE, peopleViewModel.peopleProgress.get());
    }
}
