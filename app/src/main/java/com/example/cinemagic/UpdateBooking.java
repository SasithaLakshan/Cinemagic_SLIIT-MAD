package com.example.cinemagic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateBooking extends AppCompatActivity {

    EditText name_update,date_update,theatre_update,showtime_update,tickets_update;
    Button update_button,delete_button;

    String id,name,date,theatre,showtime,tickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_booking);

        name_update = findViewById(R.id.name_update);
        date_update = findViewById(R.id.date_update);
        theatre_update = findViewById(R.id.theatre_update);
        showtime_update = findViewById(R.id.showtime_update);
        tickets_update = findViewById(R.id.tickets_update);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookingDBHelper myDB = new BookingDBHelper(UpdateBooking.this);
                name = name_update.getText().toString().trim();
                date = date_update.getText().toString().trim();
                theatre = theatre_update.getText().toString().trim();
                showtime = showtime_update.getText().toString().trim();
                tickets = tickets_update.getText().toString().trim();
                myDB.updateData(id,name,date,theatre,showtime,tickets);
            }
        });
        
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("date") && getIntent().hasExtra("theatre") && getIntent().hasExtra("showtime") && getIntent().hasExtra("ticketcount")){
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            date = getIntent().getStringExtra("date");
            theatre = getIntent().getStringExtra("theatre");
            showtime = getIntent().getStringExtra("showtime");
            tickets = getIntent().getStringExtra("ticketcount");

            name_update.setText(name);
            date_update.setText(date);
            theatre_update.setText(theatre);
            showtime_update.setText(showtime);
            tickets_update.setText(tickets);
        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
    
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
           BookingDBHelper myDB = new BookingDBHelper(UpdateBooking.this);
           myDB.deleteOneRow(id);
           finish();
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(UpdateBooking.this, "No changes made to " + name, Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
}