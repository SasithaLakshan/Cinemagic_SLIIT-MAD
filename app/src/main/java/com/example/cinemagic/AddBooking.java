package com.example.cinemagic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBooking extends AppCompatActivity {

    EditText name_input,date_input,theatre_input,showtime_input,tickets_input;
    Button create_button;
    Boolean formCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_booking);

        name_input = findViewById(R.id.name_input);
        date_input = findViewById(R.id.date_input);
        theatre_input = findViewById(R.id.theatre_input);
        showtime_input = findViewById(R.id.showtime_input);
        tickets_input = findViewById(R.id.tickets_input);
        create_button = findViewById(R.id.create_button);

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formCheck = CheckAllFields();
                if(CheckAllFields()){
                    BookingDBHelper bookingDB = new BookingDBHelper(AddBooking.this);
                    bookingDB.addBooking(name_input.getText().toString().trim(),
                            date_input.getText().toString().trim(),
                            theatre_input.getText().toString().trim(),
                            showtime_input.getText().toString().trim(),
                            Integer.valueOf(tickets_input.getText().toString().trim()));
                }else{
                    Toast.makeText(AddBooking.this, "Please fill mandatory fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean CheckAllFields(){
        if(name_input.length() == 0){
            name_input.setError("Name is required");
            return false;
        }
        if(date_input.length() == 0){
            date_input.setError("Date is required");
            return false;
        }
        if(theatre_input.length() == 0){
            theatre_input.setError("Theatre is required");
            return false;
        }
        if(showtime_input.length() == 0){
            showtime_input.setError("Show time is required");
            return false;
        }
        if(tickets_input.length() == 0){
            tickets_input.setError("No of Tickets is required");
            return false;
        }
        return true;
    }
}