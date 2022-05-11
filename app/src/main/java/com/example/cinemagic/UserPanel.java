package com.example.cinemagic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class UserPanel extends AppCompatActivity {

    ImageButton movieMgmt,userMgmt,paymentMgmt,bookingMgmt,log_out_btn;

    SessionManager session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

//        movieMgmt = (ImageButton) findViewById(R.id.movie_mgmt);
        userMgmt = (ImageButton) findViewById(R.id.user_mgmt);
        paymentMgmt = (ImageButton) findViewById(R.id.payment_mgmt);
        bookingMgmt = (ImageButton) findViewById(R.id.booking_mgmt);
        log_out_btn = (ImageButton) findViewById(R.id.log_out);


//        movieMgmt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), movieManagement.class);
//                startActivity(intent);
//            }
//        });
//
        userMgmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserProfileView.class);
                startActivity(intent);
            }
        });
//
        paymentMgmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PayementHistory.class);
                startActivity(intent);
            }
        });
//
//        bookingMgmt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), CreateBooking.class);
//                startActivity(intent);
//            }
//        });

        log_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), UserLogin.class);
//                startActivity(intent);
                session.logoutUser();
            }
        });

    }
}