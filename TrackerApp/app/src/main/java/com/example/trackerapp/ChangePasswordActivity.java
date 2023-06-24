package com.example.trackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {

    Button ConfirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ConfirmButton = findViewById(R.id.btn_confirm);

        // CONFIRM BUTTON IS CLICKED ACTIVITY
        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TEMPORARY DISPLAYING A POPUP TOAST FOR CONFIRM BUTTON
                Toast.makeText(ChangePasswordActivity.this, "Confirm Button Is Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}