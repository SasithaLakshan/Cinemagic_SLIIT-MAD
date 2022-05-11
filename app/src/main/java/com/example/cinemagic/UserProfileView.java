package com.example.cinemagic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class UserProfileView extends AppCompatActivity {

    TextView name, email, mobile;
//    TextView birthday, gender;
    Button updatebtn, deletebtn;

    SessionManager session;
    String userName ;
    UserDBHelper UserDB;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_view);

        UserDB = new UserDBHelper(this);
        session = new SessionManager(getApplicationContext());

        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        userName = user.get(SessionManager.KEY_NAME);

        name = findViewById(R.id.userProfViewName);
        email = findViewById(R.id.userProfViewEmail);
        mobile = findViewById(R.id.userProfViewMobile);
//        birthday = findViewById(R.id.userProfViewBirthday);
//        gender = findViewById(R.id.userProfViewGender);

        updatebtn = findViewById(R.id.userProfUpdate);
        deletebtn = findViewById(R.id.userProfViewDelete);

        builder = new AlertDialog.Builder(this);

        showAllData();

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setMessage("Do you want to delete the user?") .setTitle("Delete the user");

                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to delete your profile ? ")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                boolean checkDelete = UserDB.deleteData(userName);
                                if (checkDelete == true) {
                                    Toast.makeText(UserProfileView.this, "User deleted Successfully", Toast.LENGTH_SHORT).show();
                                    session.logoutUser();
                                } else {
                                    Toast.makeText(UserProfileView.this, "Couldnt delete the user", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Delete Profile");
                alert.show();
            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserProfileEdit.class);
                startActivity(intent);
            }
        });

    }

    private void showAllData() {
        Cursor res = UserDB.getData(userName);
        name.setText(res.getString(0));
        email.setText(res.getString(1));
        mobile.setText(res.getString(2));

    }
}