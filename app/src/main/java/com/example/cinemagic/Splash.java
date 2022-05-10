package com.example.cinemagic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Button button = findViewById(R.id.splash_btn);
        button.setOnClickListener(view -> UserRegister());
    }
    public void UserRegister(){
        Intent intent = new Intent(this, UserLogin.class);
        startActivity(intent);
    }
}