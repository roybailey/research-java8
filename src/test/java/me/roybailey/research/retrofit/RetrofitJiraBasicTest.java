package me.roybailey.research.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import retrofit.RestAdapter;
import retrofit.client.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Access JIRA REST API using Retrofit and Basic Authentication.
 */
public class RetrofitJiraBasicTest {

    @Test
    public void testCountryNames() throws IOException {
        String credentials = System.getProperty("login");
        if(credentials==null) {
            System.out.println("must provide jira credentials as -Dlogin=<username>:<password> for basic authentication");
            System.exit(-1);
        }
        String base64login = new String(Base64.getEncoder().encode(credentials.getBytes()));
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://localhost:9090")
                .setRequestInterceptor((request) -> {
                    request.addHeader("User-Agent", "RetrofitJiraBasicTest");
                    request.addHeader("Authorization", "Basic " + base64login);
                    request.addHeader("Content-Type", "application/json");
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        RetrofitJiraRestApi jira = restAdapter.create(RetrofitJiraRestApi.class);
        Gson gson = new GsonBuilder().create();
        {
            Response response = jira.getIssue("RES-29");
            Map<String, Object> issue = new HashMap<>();
            System.out.println("--- JIRA Response ---");
            System.out.println(response.getStatus());
            issue = gson.fromJson(
                    new InputStreamReader(
                            response.getBody().in()), issue.getClass());
            System.out.println(gson.toJson(issue));
        }
        {
            Response response = jira.search("remainingEstimate > 0");
            Map<String, Object> results = new HashMap<>();
            System.out.println("--- JIRA Response ---");
            System.out.println(response.getStatus());
            results = gson.fromJson(
                    new InputStreamReader(
                            response.getBody().in()), results.getClass());
            System.out.println(gson.toJson(results));
        }
    }
}
