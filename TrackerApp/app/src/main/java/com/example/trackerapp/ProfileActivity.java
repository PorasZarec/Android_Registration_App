package com.example.trackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    Button editProfile;
    TextView tvChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        // CHANGE PASSWORD TEXT IS CLICKED ACTIVITY
        tvChangePassword = findViewById(R.id.tv_user_change_password);
        tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                // TEMPORARY DISPLAYING A POPUP TOAST
                Toast.makeText(ProfileActivity.this, "Change Password Is Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        // EDIT PROFILE BUTTON IS CLICKED ACTIVITY
        editProfile = findViewById(R.id.btn_edit_profile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TEMPORARY DISPLAYING A POPUP TOAST FOR EDIT PROFILE BUTTON
                Toast.makeText(ProfileActivity.this, "Edit Profile Button Is Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}