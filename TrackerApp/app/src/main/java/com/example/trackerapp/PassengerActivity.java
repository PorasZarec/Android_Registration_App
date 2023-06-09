package com.example.trackerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PassengerActivity extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton fab;
    FragmentManager fragmentManager;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);

        // Setting the default fragment to launch
        fragmentManager = getSupportFragmentManager();
        openFragment(new MapFragment()); // set to MapFragment

        // DECLARING THE FLOATING ACTION BUTTON AS 'fab' VARIABLE
        fab = findViewById(R.id.fab);
        
        // WHEN 'fab' IS CLICKED LAUNCH THE DRAWER
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });

    }

    // Handles the replacing of fragments
    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    // METHOD WHEN 'fab' IS CLICKED
    private void showBottomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        ImageView cancelButton = dialog.findViewById(R.id.cancelButton); // DECLARING 'cancelButton' - [ic_baseline_clear]

        // WHEN THE 'X' IS CLICKED DRAWER WILL DISMISS
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 2000); // Set the height to 2000 pixels
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        // WHEN CARD VIEW IN BOTTOM SHEET LAYOUT IS CLICKED
        cardView = dialog.findViewById(R.id.cv_one);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PassengerActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}