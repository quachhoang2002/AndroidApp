package com.example.myproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.service.UserDatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    TextView sign_up_nav;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        collectData();
        sign_up_nav = findViewById(R.id.sign_up_nav);
        sign_up_nav.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
        login.setOnClickListener(v -> {
            loginOnClick();
        });
    }

    private void collectData() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_button);
    }

    private void loginOnClick() {
        //login
        UserDatabaseHelper db = new UserDatabaseHelper(LoginActivity.this);
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        boolean result = db.login(username, password);
        if (result) {
            Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
        }
    }
}