package com.example.jhordan.people_mvvm.data;

import com.example.jhordan.people_mvvm.model.People;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jhordan on 02/03/16.
 */
public class PeopleResponse {

    @SerializedName("results")
    private List<User> mPeopleList;

    public List<User> getPeopleList() {
        return mPeopleList;
    }

    public static class User {
        @SerializedName("user")
        private People mPeople;

        public People getPeople() {
            return mPeople;
        }

        public void setPeople(People people) {
            mPeople = people;
        }
    }
}
