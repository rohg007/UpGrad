package com.example.android.stackoverflow;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.android.stackoverflow.Adapters.SelectedAdapter;
import com.example.android.stackoverflow.Adapters.TagAdapter;
import com.example.android.stackoverflow.Models.Tags;
import com.example.android.stackoverflow.Models.TagsList;
import com.example.android.stackoverflow.rest.StackClient;
import com.example.android.stackoverflow.rest.StackOverflowAPIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TagsActivity extends AppCompatActivity {

    private static final String LOG_TAG = TagsActivity.class.getSimpleName();

    private ArrayList<String> selectedTagsList;
    private StackOverflowAPIService apiService;
    private RecyclerView selectedTags;
    private RecyclerView tagList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        selectedTags = findViewById(R.id.selected_tag_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        selectedTags.setLayoutManager(linearLayoutManager);

        tagList = findViewById(R.id.list_tags);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tagList.setLayoutManager(linearLayoutManager1);
        
        selectedTagsList = new ArrayList<String>();

        SelectedAdapter adapter = new SelectedAdapter(selectedTagsList,this);
        selectedTags.setAdapter(adapter);

        apiService = StackClient.getClient().create(StackOverflowAPIService.class);
        displayTagsList();
    }

    private void displayTagsList(){
        Call<TagsList> call = apiService.fetchTags();
        call.enqueue(new Callback<TagsList>() {
            @Override
            public void onResponse(Call<TagsList> call, Response<TagsList> response) {
                ArrayList<Tags> data = new ArrayList<Tags>();
                data.addAll(response.body().getItems());
                tagList.setAdapter(new TagAdapter(data,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<TagsList> call, Throwable t) {
                Log.e(LOG_TAG,"Error in fetching Tags List:",t);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(TagsActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TagsActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
