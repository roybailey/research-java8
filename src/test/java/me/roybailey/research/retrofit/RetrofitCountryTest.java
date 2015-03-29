package me.roybailey.research.retrofit;

import org.junit.Test;
import retrofit.RestAdapter;

import java.util.Map;

/**
 * Created by roybailey on 29/03/2015.
 */
public class RetrofitCountryTest {

    @Test
    public void testCountryNames() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://country.io")
                .setRequestInterceptor((request) -> request.addHeader("User-Agent", "RetrofitCountryTest"))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        RetrofitCountryIO retrofitCountryIO = restAdapter.create(RetrofitCountryIO.class);

        Map<String, String> countryCodes = retrofitCountryIO.getCountryCodes();
        System.out.println("--- Country Codes ---");
        System.out.println(countryCodes);
    }
}
