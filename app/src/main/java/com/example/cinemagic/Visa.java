package com.example.cinemagic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Visa extends AppCompatActivity {

    EditText name, expDate, type, Cvv, cardNo;
    Button savebtn, cancelbtn;

    PaymentDBHelper PaymentDB;
    SessionManager session;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa);

        name = findViewById(R.id.PaymentName);
        expDate = findViewById(R.id.paymentExpDate);
        type = findViewById(R.id.payementType);
        Cvv = findViewById(R.id.paymentCVV);
        cardNo = findViewById(R.id.PaymentCardNo);
        savebtn = findViewById(R.id.paymentSave);
        cancelbtn = findViewById(R.id.paymentCancel);

        PaymentDB = new PaymentDBHelper(this);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        HashMap<String, String> user = session.getUserDetails();

        // name
        userName = user.get(SessionManager.KEY_NAME);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EName = name.getText().toString();
                String EExpDate = expDate.getText().toString();
                String Etype = type.getText().toString();
                String Ecvv = Cvv.getText().toString();
                String ECardNo = cardNo.getText().toString();

                if(EName.equals("") || EExpDate.equals("") || Etype.equals("") || Ecvv.equals("") || ECardNo.equals("")){
                    Toast.makeText(Visa.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }else{

                        Boolean userCheckResult = PaymentDB.checkCardNo(ECardNo);
                        if(userCheckResult == false){
                            Boolean regResult = PaymentDB.insertData(EName, Etype, EExpDate, ECardNo, userName);
                            if(regResult == true){
                                Toast.makeText(Visa.this, "Registration Successful !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), PayementHistory.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Visa.this, "Registration Unsuccessful.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Visa.this, "User already exists ! Sign in", Toast.LENGTH_SHORT).show();
                        }

                }
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserPanel.class);
                startActivity(intent);
            }
        });

    }
}