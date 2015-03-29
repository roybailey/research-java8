package me.roybailey.research.retrofit;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import java.util.Map;

/**
 * Retrofit interface to JIRA
 */
public interface RetrofitJiraRestApi {

    @GET("/rest/api/2/issue/{key}")
    Response getIssue(@Path("key") String key);

    @GET("/rest/api/2/search")
    Response search( @Query("jql") String query);

}
