package com.example.mad_2nd_year_2nd_semester;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class cinema_hall_display extends AppCompatActivity {
    DatabaseHelper myDB;
    DatabaseHelperTickets db;

   TextView halldetails;
   TextView ticketView;
    Button btnviewAll;
    Button btnviewtickets;
    TextView idInput;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_hall_display);
        myDB= new DatabaseHelper(this);
        db=new DatabaseHelperTickets(this);

        halldetails=(TextView) findViewById(R.id.view_hall_details);
        ticketView=(TextView)findViewById(R.id.view_tickets);
        btnviewAll=(Button) findViewById(R.id.show_available_halls);
        btnviewtickets=(Button)findViewById(R.id.next_step);
        idInput=(TextView) findViewById(R.id.hall_id_to_tickets);


        viewAll();
        viewAll2();


    }



    public void viewAll(){
        btnviewAll.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Cursor res=myDB.getAllData();
                        if(res.getCount()==0){
                           showMessage("","Nothing found");
                            return;
                        }
                        StringBuffer buffer =new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id :"+res.getString(0)+"\n");
                            buffer.append("Name :"+res.getString(1)+"\n");
                            buffer.append("Location :"+res.getString(2)+"\n\n");
                           // buffer.append("No of Seats :"+res.getString(3)+"\n\n");
                        }
                        halldetails.setText(buffer.toString());


                    }
                }
        );



    }

    public void viewAll2(){
        btnviewtickets.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Cursor res=db.viewtickets_by_id(idInput.getText().toString());
                        if(res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer =new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Ticket Id :"+res.getString(0)+"\n");
                            buffer.append("Type :"+res.getString(1)+"\n");
                           // buffer.append("Hall_name :"+res.getString(2)+"\n");
                            buffer.append("price :"+res.getString(3)+"\n");
                           // buffer.append("count :"+res.getString(4)+"\n");
                            buffer.append("time :"+res.getString(5)+"\n\n");
                        }
                        ticketView.setText( buffer.toString());
                    }
                }
        );



    }














    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}