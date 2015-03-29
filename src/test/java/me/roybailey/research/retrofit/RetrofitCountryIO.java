package me.roybailey.research.retrofit;

import retrofit.http.GET;

import java.util.Map;

/**
 * Created by roybailey on 29/03/2015.
 */
public interface RetrofitCountryIO {

    @GET("/names.json")
    Map<String, String> getCountryCodes();
}
