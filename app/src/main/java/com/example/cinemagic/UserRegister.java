package com.example.cinemagic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegister extends AppCompatActivity {

    EditText username,email,mobile,password,re_password;
    Button signUp,signIn,adminSignIn;
    UserDBHelper UserDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        username = (EditText) findViewById(R.id.sign_up_name);
        email = (EditText) findViewById(R.id.sign_up_email);
        mobile = (EditText) findViewById(R.id.sign_up_mobile);
        password = (EditText) findViewById(R.id.sign_up_pw);
        re_password = (EditText) findViewById(R.id.sign_up_re_pw);

        signUp = (Button) findViewById(R.id.sign_up_btn);
        signIn = (Button) findViewById(R.id.sign_in_btn);
        adminSignIn = (Button) findViewById(R.id.register_admin_login);

        UserDB = new UserDBHelper(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String mail = email.getText().toString();
                String mob = mobile.getText().toString();
                String pass = password.getText().toString();
                String repass = re_password.getText().toString();
                
                if(user.equals("") || mail.equals("") || mob.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(UserRegister.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(repass)){
                        Boolean userCheckResult = UserDB.checkUserName(user);
                        if(userCheckResult == false){
                            Boolean regResult = UserDB.insertData(user,mail,mob,pass);
                            if(regResult == true){
                                Toast.makeText(UserRegister.this, "Registration Successful !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), UserPanel.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(UserRegister.this, "Registration Unsuccessful.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(UserRegister.this, "User already exists ! Sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(UserRegister.this, "Password mismatch !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UserLogin.class);
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