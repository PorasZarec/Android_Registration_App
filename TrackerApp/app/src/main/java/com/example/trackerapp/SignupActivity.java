package com.example.trackerapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Document;

public class SignupActivity extends AppCompatActivity {
    EditText edName,edPlate,edNumber,edPassword;
    Button signUpButton;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    ProgressBar progressBar;
    String Userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Create firebase instance
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        edName = findViewById(R.id.et_username);
        edPassword = findViewById(R.id.et_confirm_password);
        progressBar = findViewById(R.id.progressBar);

        // Sign up button on-click
        signUpButton = findViewById(R.id.btn_signUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                SignUpUser();
            }
        });
    }
    private void SignUpUser() {
        String email = edName.getText().toString().trim();
        String password = edPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            Toast.makeText(SignupActivity.this, "Please fill all Fields.", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        // store all user data into Firebase Firestore
                        // get current user id after creating user
                        if (firebaseAuth.getCurrentUser() != null) {
                            // make global variable for getting user id
                            Userid = firebaseAuth.getCurrentUser().getUid();
                        }
                        // now after getting current user id we will store all data into database
                        // create documents ref..
                        // here we also need Firestore reference
                        DocumentReference UserInfo = firestore.collection("Users").document(Userid);

                        // set all data into model class
                        userModel model = new userModel(email, password, Userid);
                        UserInfo.set(model, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(SignupActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignupActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
    }
}