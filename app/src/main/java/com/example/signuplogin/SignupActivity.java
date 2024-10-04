package com.example.signuplogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText nameSignup, emailSignup, usernameSignup, passwordSignup;
    TextView loginRedirectText;
    Button buttonSignup;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameSignup = findViewById(R.id.name_signup);
        emailSignup = findViewById(R.id.email_signup);
        usernameSignup = findViewById(R.id.username_signup);
        passwordSignup = findViewById(R.id.password_signup);
        buttonSignup = findViewById(R.id.button_signup);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = nameSignup.getText().toString();
                String email = emailSignup.getText().toString();
                String username = usernameSignup.getText().toString();
                String password = passwordSignup.getText().toString();

                HelperClass helperClass = new HelperClass(name, email, username, password);
                reference.child(username).setValue(helperClass);

                Toast.makeText(SignupActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent x = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(x);
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent z = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(z);
            }
        });
    }
}
