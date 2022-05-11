package com.example.cinemagic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class PayementHistory extends AppCompatActivity {

    Button addbtn;
    TextView method, expDate, name, cardNo;

    SessionManager session;
    String userName ;
    PaymentDBHelper PaymentDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payement_history);

        PaymentDB = new PaymentDBHelper(this);
        session = new SessionManager(getApplicationContext());

        Log.i("info", "before sessions");

        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        userName = user.get(SessionManager.KEY_NAME);

        Log.i("info", userName);

        method = findViewById(R.id.paymentDetailsMethod);
        expDate = findViewById(R.id.paymentDetailsExpiryDate);
        name = findViewById(R.id.paymentDetailsName);
        cardNo = findViewById(R.id.paymentDetailsCardNumber);

        addbtn = findViewById(R.id.paymentAdd);

        showAllData();

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Visa.class);
                startActivity(intent);
            }
        });
    }

    private void showAllData() {
        Cursor res = PaymentDB.getData(userName);
        Log.i("info", userName);
        Log.i("info", res.getString(0));
        if (res != null) {
            name.setText(res.getString(0));
            method.setText(res.getString(1));
            expDate.setText(res.getString(2));
            cardNo.setText(res.getString(3));
        }


    }
}