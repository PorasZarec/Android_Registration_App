package com.example.trackerapp;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class OperatorActivity extends AppCompatActivity {
    FloatingActionButton fab;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);

        Toolbar toolbar = findViewById(R.id.toolbar_operator);
        // Set the toolbar as the activity's action bar
        setSupportActionBar(toolbar);

        // Setting the default fragment to launch
        fragmentManager = getSupportFragmentManager();
        openFragment(new MapFragment()); // set to MapFragment

        // DECLARING THE FLOATING ACTION BUTTON AS 'fab' VARIABLE
        fab = findViewById(R.id.fab);

        // WHEN 'fab' IS CLICKED LAUNCH THE DRAWER
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TEMPORARY DISPLAYING A POPUP TOAST FOR FLOATING ACTION BUTTON
                Toast.makeText(OperatorActivity.this, "Floating Button Is Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    // LAUNCHING THE CUSTOMIZED TOOLBAR
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    // Handle toolbar menu item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.tb_operatorProfile) {
            launchProfileActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // Launch the ProfileActivity
    private void launchProfileActivity() {
        Intent intent = new Intent(OperatorActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    // Handles the replacing of fragments
    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}


    // METHOD WHEN 'fab' IS CLICKED
//    private void showBottomDialog() {
//        final Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.bottomsheetlayout);
//
//        ImageView cancelButton = dialog.findViewById(R.id.cancelButton); // DECLARING 'cancelButton' - [ic_baseline_clear]
//
//        // WHEN THE 'X' IS CLICKED DRAWER WILL DISMISS
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 2000); // Set the height to 2000 pixels
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        dialog.getWindow().setGravity(Gravity.BOTTOM);
//
//        // WHEN CARD VIEW IN BOTTOM SHEET LAYOUT IS CLICKED
//        cardView = dialog.findViewById(R.id.cv_one);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(OperatorActivity.this, ProfileActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

//            @Override
//            public void onClick(View view) {
//                showBottomDialog();
//            }
