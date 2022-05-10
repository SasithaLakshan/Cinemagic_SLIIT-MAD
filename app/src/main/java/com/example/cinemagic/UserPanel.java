package com.example.cinemagic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class UserPanel extends AppCompatActivity {

    ImageButton movieMgmt,userMgmt,paymentMgmt,bookingMgmt,log_out_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel);

        movieMgmt = findViewById(R.id.movie_mgmt);
        userMgmt = findViewById(R.id.user_mgmt);
        paymentMgmt = findViewById(R.id.payment_mgmt);
        bookingMgmt = findViewById(R.id.booking_mgmt);
        log_out_btn = findViewById(R.id.log_out);


        movieMgmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPanel.this,MovieManagement.class );
                startActivity(intent);
            }
        });

        userMgmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPanel.this,UserManagement.class);
                startActivity(intent);
            }
        });

        paymentMgmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPanel.this,PaymentManagement.class);
                startActivity(intent);
            }
        });

        bookingMgmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPanel.this,BookingManagement.class);
                startActivity(intent);
            }
        });

        log_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Splash.class);
                startActivity(intent);
            }
        });

    }
}