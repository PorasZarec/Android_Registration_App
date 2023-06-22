package com.example.trackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button button;
    TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.LoginButton);
        tvSignUp = findViewById(R.id.tv_SignUp);


        // SIGN UP TEXT IS CLICKED ACTIVITY
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


        // REGISTER BUTTON IS CLICKED ACTIVITY
       button.setOnClickListener(view -> {
           Intent intent = new Intent(LoginActivity.this, OperatorActivity.class);
           startActivity(intent);
       });
    }
}