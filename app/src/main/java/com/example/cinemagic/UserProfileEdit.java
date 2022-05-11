package com.example.cinemagic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class UserProfileEdit extends AppCompatActivity {

    TextInputEditText name, email, mobile, oldPass, newPass;
    Button updatebtn, cancelbtn;

    String _NAME, _EMAIL, _MOBILE, _OLDPASS, _NEWPASS;

    SessionManager session;
    String userName ;
    UserDBHelper UserDB;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_edit);

        UserDB = new UserDBHelper(this);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        userName = user.get(SessionManager.KEY_NAME);

        name = findViewById(R.id.userProfEditName);
        email = findViewById(R.id.userProfEditEmail);
        mobile = findViewById(R.id.userProfEditMobile);
        oldPass = findViewById(R.id.userProfEditOldPass);
        newPass = findViewById(R.id.userProfEditNewPass);

        updatebtn = findViewById(R.id.userProfEditUpdate);
        cancelbtn = findViewById(R.id.userProfEditCancel);

        showAllData();

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _NAME = name.getText().toString();
                _EMAIL = email.getText().toString();
                _MOBILE = mobile.getText().toString();
                String enteredOldPass = oldPass.getText().toString();
                _NEWPASS = newPass.getText().toString();

                if(_NEWPASS.equals("")) {
                    _NEWPASS = _OLDPASS;
                }

                if(!enteredOldPass.equals(_OLDPASS)) {
                    Toast.makeText(UserProfileEdit.this, "Please enter the correct Old Password", Toast.LENGTH_SHORT).show();
                } else {
                    if (!userName.equals(_NAME)) {
                        if (!UserDB.checkUserName(_NAME)) {
                            boolean isUpdate = UserDB.updateData(_NAME, _EMAIL, _MOBILE, _NEWPASS, userName);
                            if (isUpdate == true) {
                                Toast.makeText(getApplicationContext(), "Profile Details Updates Successfully", Toast.LENGTH_SHORT).show();
//                                Intent in = new Intent(getApplicationContext(), UserProfileView.class);
//                                startActivity(in);
                                session.logoutUser();
                            } else {
                                Toast.makeText(UserProfileEdit.this, "Couldnt update the profile", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(UserProfileEdit.this, "Cannot use this new User Name", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        boolean isUpdate = UserDB.updateData(_NAME, _EMAIL, _MOBILE, _NEWPASS, userName);
                        if (isUpdate) {
                            Toast.makeText(UserProfileEdit.this, "Profile Details Updates Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), UserProfileView.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(UserProfileEdit.this, "Couldnt update the profile", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserProfileView.class);
                startActivity(intent);
            }
        });
    }

    private void showAllData() {

        Cursor res = UserDB.getData(userName);
        _NAME = res.getString(0);
        _EMAIL = res.getString(1);
        _MOBILE = res.getString(2);
        _OLDPASS = res.getString(3);

        name.setText(_NAME);
        email.setText(_EMAIL);
        mobile.setText(_MOBILE);
    }

    private boolean isNameChange() {
        if(!_NAME.equals(name.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isEmailChanged() {
        if(!_EMAIL.equals(name.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }
}