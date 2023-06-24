package com.example.trackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button,btn_passenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.bt_operator_main);

        // OPERATOR BUTTON IS CLICKED
        button.setOnClickListener(view -> {
           Intent intent = new Intent(MainActivity.this, LoginActivity.class);
           startActivity(intent);
        });

        btn_passenger = findViewById(R.id.bt_passenger_main);

        // PASSENGER BUTTON IS CLICKED
        btn_passenger.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PassengerActivity.class);
            startActivity(intent);
        });
    }
}