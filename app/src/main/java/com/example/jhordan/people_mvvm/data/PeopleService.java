package com.example.jhordan.people_mvvm.data;

import retrofit.http.GET;
import retrofit.http.Url;
import rx.Observable;

/**
 * Created by Jhordan on 02/03/16.
 */
public interface PeopleService {

    @GET
    Observable<PeopleResponse> fetchPeople(@Url String url);
}
