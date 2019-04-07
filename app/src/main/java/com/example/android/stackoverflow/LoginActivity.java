package com.example.android.stackoverflow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.stackoverflow.Models.Login;
import com.example.android.stackoverflow.Models.User;
import com.example.android.stackoverflow.rest.LoginAPIService;
import com.example.android.stackoverflow.rest.StackClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    LoginAPIService apiService;
    EditText email;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email_edit_text);
        password=findViewById(R.id.password_edit_text);

        apiService=StackClient.getClient().create(LoginAPIService.class);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(email.getText().toString().trim(),password.getText().toString().trim());
                Call<Login> call = apiService.login(user);
                call.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if(response.isSuccessful()){
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            Toast.makeText(getApplicationContext(),response.body().getToken(),Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
