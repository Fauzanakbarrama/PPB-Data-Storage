package com.example.signuplogin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileName, profileEmail, profileUsername, profilePassword, tittleName, tittleUsername;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileUsername = findViewById(R.id.profileUsername);
        profilePassword = findViewById(R.id.profilePassword);
        tittleName = findViewById(R.id.nameTitle);
        tittleUsername = findViewById(R.id.usernameTitle);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        String userId = "akuser";

        getUserData(userId);
    }

    private void getUserData(String userId){
        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String username = dataSnapshot.child("username").getValue(String.class);
                    String password = dataSnapshot.child("password").getValue(String.class);

                    profileName.setText(name);
                    profileEmail.setText(email);
                    profileUsername.setText(username);
                    profilePassword.setText(password);
                    tittleName.setText(name);
                    tittleUsername.setText(username);
                } else {
                    Toast.makeText(ProfileActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, "Failed To Load Data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}