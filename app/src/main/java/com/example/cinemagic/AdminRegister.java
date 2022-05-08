package com.example.cinemagic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminRegister extends AppCompatActivity {
    
    EditText username,email,mobile,adminExp,password,re_password;
    Button adminSignUp,goBackAdminLogin;
    AdminDBHelper AdminDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);
        
        username = (EditText) findViewById(R.id.admin_sign_up_name);
        email = (EditText) findViewById(R.id.admin_sign_up_email);
        mobile = (EditText) findViewById(R.id.admin_sign_up_mobile);
        adminExp = (EditText) findViewById(R.id.admin_sign_up_exp);
        password = (EditText) findViewById(R.id.admin_sign_up_pw);
        re_password = (EditText) findViewById(R.id.admin_sign_up_re_pw);
        
        adminSignUp = (Button) findViewById(R.id.admin_sign_up_btn);
        goBackAdminLogin = (Button) findViewById(R.id.back_to_admin_sign_in);
        
        AdminDB = new AdminDBHelper(this);
        
        adminSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String mail = email.getText().toString();
                String mob = mobile.getText().toString();
                String exp = adminExp.getText().toString();
                String pass = password.getText().toString();
                String repass = re_password.getText().toString();
                
                if(user.equals("") || mail.equals("") || mob.equals("") || exp.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(AdminRegister.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }else {
                    if(pass.equals(repass)){
                        Boolean adminCheckResult = AdminDB.checkUserName(user);
                        if(adminCheckResult == false){
                            Boolean regResult = AdminDB.insertData(user,mail,mob,exp,pass);
                            if(regResult == true){
                                Toast.makeText(AdminRegister.this, "Registration Successful !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),AdminPanel.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(AdminRegister.this, "Registration Unsuccessful.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(AdminRegister.this, "User already exists ! Sign In", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AdminRegister.this, "Password mismatch !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}