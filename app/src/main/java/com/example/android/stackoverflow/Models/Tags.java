package com.example.android.stackoverflow.Models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Tags {
    @SerializedName("name")
    String tag;

    public String getTag() {
        return tag;
    }
}
