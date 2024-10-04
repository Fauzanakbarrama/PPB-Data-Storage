package com.example.signuplogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText usernameLogin, passwordLogin;
    Button buttonLogin;
    TextView signupRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameLogin = findViewById(R.id.username_login);
        passwordLogin = findViewById(R.id.password_login);
        buttonLogin = findViewById(R.id.button_login);
        signupRedirectText = findViewById(R.id.signupRedirectText);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()){
                } else {
                    checkUser();
                }
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(a);
            }
        });
    }

    private void checkUser() {
    }

    public Boolean validateUsername() {
        String val = usernameLogin.getText().toString();
        if (val.isEmpty()){
            usernameLogin.setError("Username cannot be empty");
            return false;
        } else {
            usernameLogin.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = passwordLogin.getText().toString();
        if (val.isEmpty()){
            passwordLogin.setError("Password cannot be empty");
            return false;
        } else {
            passwordLogin.setError(null);
            return true;
        }
    }
    
}