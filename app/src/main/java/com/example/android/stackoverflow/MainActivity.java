package com.example.android.stackoverflow;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.android.stackoverflow.Adapters.QuestionsAdapter;
import com.example.android.stackoverflow.Models.Question;
import com.example.android.stackoverflow.Models.QuestionsList;
import com.example.android.stackoverflow.rest.StackClient;
import com.example.android.stackoverflow.rest.TaggedQuestionAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    RecyclerView homeRecyclerView;
    ArrayList<Question> questionList;
    TaggedQuestionAPI apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TagsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        homeRecyclerView = findViewById(R.id.home_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeRecyclerView.setLayoutManager(layoutManager);

        questionList = new ArrayList<Question>();

        apiService=StackClient.getClient().create(TaggedQuestionAPI.class);
        displayQuestionList();
    }

    public void displayQuestionList(){
        Call<QuestionsList> call = apiService.fetchQuestions("{android}");
        call.enqueue(new Callback<QuestionsList>() {
            @Override
            public void onResponse(Call<QuestionsList> call, Response<QuestionsList> response) {
                questionList.addAll(response.body().getItems());
                int dispSize = questionList.size();
                Log.v(LOG_TAG,Integer.toString(dispSize));
                homeRecyclerView.setAdapter(new QuestionsAdapter(questionList,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<QuestionsList> call, Throwable t) {
                Log.e(LOG_TAG,"Error fetching Questions List");
            }
        });

        Call<QuestionsList> call1 = apiService.fetchQuestions("java");
        call1.enqueue(new Callback<QuestionsList>() {
            @Override
            public void onResponse(Call<QuestionsList> call, Response<QuestionsList> response) {
                questionList.addAll(response.body().getItems());
                int dispSize = questionList.size();
                Log.v(LOG_TAG,Integer.toString(dispSize));
                homeRecyclerView.setAdapter(new QuestionsAdapter(questionList,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<QuestionsList> call, Throwable t) {
                Log.e(LOG_TAG,"Error fetching Questions List");
            }
        });

        Call<QuestionsList> call2 = apiService.fetchQuestions("c");
        call2.enqueue(new Callback<QuestionsList>() {
            @Override
            public void onResponse(Call<QuestionsList> call, Response<QuestionsList> response) {
                questionList.addAll(response.body().getItems());
                int dispSize = questionList.size();
                Log.v(LOG_TAG,Integer.toString(dispSize));
                homeRecyclerView.setAdapter(new QuestionsAdapter(questionList,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<QuestionsList> call, Throwable t) {
                Log.e(LOG_TAG,"Error fetching Questions List");
            }
        });

        Call<QuestionsList> call3 = apiService.fetchQuestions("c#");
        call3.enqueue(new Callback<QuestionsList>() {
            @Override
            public void onResponse(Call<QuestionsList> call, Response<QuestionsList> response) {
                questionList.addAll(response.body().getItems());
                int dispSize = questionList.size();
                Log.v(LOG_TAG,Integer.toString(dispSize));
                homeRecyclerView.setAdapter(new QuestionsAdapter(questionList,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<QuestionsList> call, Throwable t) {
                Log.e(LOG_TAG,"Error fetching Questions List");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_login){
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
