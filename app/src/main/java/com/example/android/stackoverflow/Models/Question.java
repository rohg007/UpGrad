package com.example.android.stackoverflow.Models;

import com.google.gson.annotations.SerializedName;

public class Question {
    @SerializedName("title")
    private String question;

    public String getQuestion() {
        return question;
    }
}
