package com.example.regappqrgen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Registered extends AppCompatActivity {

    Button bt_goto_Main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        bt_goto_Main = findViewById(R.id.bt_Registered_Continue);

        bt_goto_Main.setOnClickListener(view -> {
            Intent intent = new Intent(Registered.this, MainActivity.class);
            startActivity(intent);
        });


    }
}