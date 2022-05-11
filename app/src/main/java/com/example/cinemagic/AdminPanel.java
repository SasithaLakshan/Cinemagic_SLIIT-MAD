package com.example.cinemagic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AdminPanel extends AppCompatActivity {

    ImageButton admin_booking_mgmt,admin_payment_mgmt,admin_movie_mgmt,admin_user_mgmt,admin_ticket_mgmt,admin_log_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
//
        admin_booking_mgmt = (ImageButton) findViewById(R.id.admin_booking_mgmt);
//        admin_payment_mgmt = (ImageButton) findViewById(R.id.admin_payment_mgmt);
//        admin_movie_mgmt = (ImageButton) findViewById(R.id.admin_movie_mgmt);
//        admin_user_mgmt = (ImageButton) findViewById(R.id.admin_user_mgmt);
//        admin_ticket_mgmt = (ImageButton) findViewById(R.id.admin_ticket_mgmt);
//        admin_log_out = (ImageButton) findViewById(R.id.admin_log_out);
//        admin_booking_mgmt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), AdminBookingManagement.class);
//                startActivity(intent);
//            }
//        });
//
//        admin_payment_mgmt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), AdminPaymentManagement.class);
//                startActivity(intent);
//            }
//        });
//
//        admin_movie_mgmt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), AdminMovieManagement.class);
//                startActivity(intent);
//            }
//        });
//
//        admin_user_mgmt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), AdminUserManagement.class);
//                startActivity(intent);
//            }
//        });
//
//        admin_ticket_mgmt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), AdminTicketManagement.class);
//                startActivity(intent);
//            }
//        });
//
//        admin_log_out.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), UserLogin.class);
//                startActivity(intent);
//            }
//        });
    }
}