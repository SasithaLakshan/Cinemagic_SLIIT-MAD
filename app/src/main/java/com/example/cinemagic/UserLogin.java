package com.example.cinemagic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserLogin extends AppCompatActivity {

    EditText username,password;
    Button signIn,signUp,adminSignIn;
    
    UserDBHelper UserDB;
    Context context = this;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        session = new SessionManager(getApplicationContext());
        
        username = (EditText) findViewById(R.id.sign_in_name);
        password = (EditText) findViewById(R.id.sign_in_pw);
        signIn = (Button) findViewById(R.id.sign_in_btn);
        signUp = (Button) findViewById(R.id.sign_up_btn);
        adminSignIn = (Button) findViewById(R.id.admin_login);
        
        UserDB = new UserDBHelper(this);
        
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                
                if(user.equals("") || pass.equals("")){
                    Toast.makeText(UserLogin.this, "Please enter credentials.", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean results = UserDB.checkUserNamePassword(user,pass);
                    if(results == true){

                        session.createLoginSession(user, "anroidhive@gmail.com");

                        Intent intent = new Intent(getApplicationContext(), UserPanel.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(UserLogin.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UserRegister.class);
                startActivity(intent);
            }
        });

        adminSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
                startActivity(intent);
            }
        });
    }
}