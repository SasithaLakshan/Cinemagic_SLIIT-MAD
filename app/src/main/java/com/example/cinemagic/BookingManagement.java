package com.example.cinemagic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BookingManagement extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    BookingDBHelper myDB;
    ArrayList<String> booking_id, booking_name,booking_date,booking_theatre,booking_showtime,booking_ticketcount;
    BookingCustomAdapter bookingCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_management);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingManagement.this, AddBooking.class);
                startActivity(intent);
            }
        });

        myDB = new BookingDBHelper(BookingManagement.this);
        booking_id = new ArrayList<>();
        booking_name = new ArrayList<>();
        booking_date = new ArrayList<>();
        booking_theatre = new ArrayList<>();
        booking_showtime = new ArrayList<>();
        booking_ticketcount = new ArrayList<>();

        storeDataInArrays();

        bookingCustomAdapter = new BookingCustomAdapter(BookingManagement.this,this, booking_id,booking_name,booking_date,booking_theatre,booking_showtime,booking_ticketcount);
        recyclerView.setAdapter(bookingCustomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(BookingManagement.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else {
            while (cursor.moveToNext()){
                booking_id.add(cursor.getString(0));
                booking_name.add(cursor.getString(1));
                booking_date.add(cursor.getString(2));
                booking_theatre.add(cursor.getString(3));
                booking_showtime.add(cursor.getString(4));
                booking_ticketcount.add(cursor.getString(5));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All ?");
        builder.setMessage("Are you sure that you want to delete all data ?");
        builder.setPositiveButton("Yes",(dialogInterface, i) -> {
            BookingDBHelper myDB = new BookingDBHelper(BookingManagement.this);
            myDB.deleteAllData();
            Intent intent = new Intent(BookingManagement.this,BookingManagement.class);
            startActivity(intent);
            finish();
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(BookingManagement.this, "No changes made to data", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

}