package com.example.cinemagic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    
    EditText username,password;
    Button adminSignIn,adminSignUp,goBackSignIn;
    
    AdminDBHelper AdminDB;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        
        username = (EditText) findViewById(R.id.admin_sign_in_name);
        password = (EditText) findViewById(R.id.admin_sign_in_pw);
        
        adminSignIn = (Button) findViewById(R.id.admin_sign_in_btn);
        adminSignUp = (Button) findViewById(R.id.admin_sign_up_btn);
        goBackSignIn = (Button) findViewById(R.id.back_to_sign_in);
        
        AdminDB = new AdminDBHelper(this);
        
        adminSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                
                if(user.equals("") || pass.equals("")){
                    Toast.makeText(AdminLogin.this, "Please enter admin credentials", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean results = AdminDB.checkUserNamePassword(user,pass);
                    if(results == true){
                        Intent intent = new Intent(getApplicationContext(), AdminPanel.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(AdminLogin.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        adminSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AdminRegister.class);
                startActivity(intent);
            }
        });

        goBackSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserLogin.class);
                startActivity(intent);
            }
        });
    }
}