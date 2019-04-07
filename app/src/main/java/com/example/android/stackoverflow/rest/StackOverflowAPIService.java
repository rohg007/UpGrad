package com.example.android.stackoverflow.rest;

import com.example.android.stackoverflow.Models.TagsList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StackOverflowAPIService {
    @GET("/2.2/tags?order=desc&sort=popular&site=stackoverflow")
    Call<TagsList>  fetchTags();
}
