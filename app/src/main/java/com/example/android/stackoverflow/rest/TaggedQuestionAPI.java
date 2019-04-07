package com.example.android.stackoverflow.rest;

import com.example.android.stackoverflow.Models.QuestionsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TaggedQuestionAPI {
    @GET("/2.2/questions?order=desc&sort=activity&site=stackoverflow")
    Call<QuestionsList> fetchQuestions(@Query("tagged") String tags);
}
