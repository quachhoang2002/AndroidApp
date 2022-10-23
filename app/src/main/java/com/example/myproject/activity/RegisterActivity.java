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

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, email, phone,confirm_password;
    Button register ;
    TextView sign_in_nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        confirm_password = findViewById(R.id.confirm_password);
        register = findViewById(R.id.register_button);
        sign_in_nav = findViewById(R.id.sign_in_nav);
        register.setOnClickListener(v -> {
            //register
            registerOnClick();
        });
        sign_in_nav.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }

    //register on click
    private void registerOnClick(){
        UserDatabaseHelper db = new UserDatabaseHelper(RegisterActivity.this);
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        String email = this.email.getText().toString();
        String phone = this.phone.getText().toString();
        String confirm_password = this.confirm_password.getText().toString();
        if(username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty() || confirm_password.isEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }else{
            if(password.equals(confirm_password)){
                boolean result = db.register(username, password, email, phone);
                if(result){
                    Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Password and confirm password does not match", Toast.LENGTH_SHORT).show();
            }
        }

    }
}