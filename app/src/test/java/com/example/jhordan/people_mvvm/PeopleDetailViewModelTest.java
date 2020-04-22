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

import android.view.View;
import com.example.jhordan.people_mvvm.data.FakeRandomUserGeneratorAPI;
import com.example.jhordan.people_mvvm.model.People;
import com.example.jhordan.people_mvvm.viewmodel.PeopleDetailViewModel;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PeopleDetailViewModelTest {

    @Test
    public void shouldGetURLPictureProfile() {
        People people = givenPeople();
        PeopleDetailViewModel peopleDetailViewModel = givenPeopleDetailViewModel(people);

        assertEquals(people.getPicture().getLarge(), peopleDetailViewModel.getPictureProfile());
    }

    @Test
    public void shouldGetUserName() {
        People people = givenPeople();
        PeopleDetailViewModel peopleDetailViewModel = givenPeopleDetailViewModel(people);

        assertEquals(people.getLogin().getUserName(), peopleDetailViewModel.getUserName());
    }

    @Test
    public void shouldGetCell() {
        People people = givenPeople();
        PeopleDetailViewModel peopleDetailViewModel = givenPeopleDetailViewModel(people);

        assertEquals(people.getCell(), peopleDetailViewModel.getCell());
    }

    @Test
    public void shouldGetMail() {
        People people = givenPeople();
        PeopleDetailViewModel peopleDetailViewModel = givenPeopleDetailViewModel(people);

        assertEquals(people.getMail(), peopleDetailViewModel.getEmail());
    }

    @Test
    public void shouldGetGender() {
        People people = givenPeople();
        PeopleDetailViewModel peopleDetailViewModel = givenPeopleDetailViewModel(people);

        assertEquals(people.getGender(), peopleDetailViewModel.getGender());
    }

    @Test
    public void shouldGetAddress() {
        People people = givenPeople();
        PeopleDetailViewModel peopleDetailViewModel = givenPeopleDetailViewModel(people);

        String fakeAddress = people.getLocation().getStreet().getName() + " " +
                people.getLocation().getStreet().getNumber() + " " +
                people.getLocation().getCity() + " " + people.getLocation().getState();

        assertEquals(fakeAddress, peopleDetailViewModel.getAddress());
    }

    @Test
    public void givenTheEmailVisibilityVisible() {
        People people = givenPeople();
        PeopleDetailViewModel peopleDetailViewModel = givenPeopleDetailViewModel(people);

        assertEquals(View.VISIBLE, peopleDetailViewModel.getEmailVisibility());
    }

    @Test
    public void givenTheEmailVisibilityGone() {
        People people = givenPeople();
        people.setMail(null);
        PeopleDetailViewModel peopleDetailViewModel = givenPeopleDetailViewModel(people);

        assertEquals(View.GONE, peopleDetailViewModel.getEmailVisibility());
    }

    private People givenPeople() {
        return FakeRandomUserGeneratorAPI.getPeople();
    }

    private PeopleDetailViewModel givenPeopleDetailViewModel(People people) {
        return new PeopleDetailViewModel(people);
    }
}
