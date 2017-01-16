package com.ptlearnpoint.www.jsonwithretrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by PT on 1/17/2017.
 */
public interface ApiService {



    @GET("person_array.json")
    Call<ArrayList<Person>> getPersonList();

    @GET("person_object.json")
    Call<Person> getPerson();
}
