package com.example.mad_2nd_year_2nd_semester;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insert_tickets_admin extends AppCompatActivity {
    DatabaseHelperTickets myDB;

    EditText editType_ticket, editHall_name_ticket,editprice_ticket ,edittextId_ticket,editcount_ticket;
    Button btnAddData_ticket;
    Button btnviewAll_ticket;
    Button btnUpdate_ticket;
    Button btnDelete_ticket;
    EditText tickettime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_tickets_admin);
        myDB= new DatabaseHelperTickets(this);


        editType_ticket=(EditText) findViewById(R.id.ticket_type);
        editHall_name_ticket=(EditText) findViewById(R.id.ticket_hallname);
        editprice_ticket=(EditText) findViewById(R.id.ticket_price);
        editcount_ticket=(EditText) findViewById(R.id.ticket_count);
        edittextId_ticket=(EditText) findViewById(R.id.ticket_id);
        tickettime=(EditText)findViewById(R.id.ticket_time);
        btnAddData_ticket=(Button) findViewById(R.id.ticket_insert_button);
        btnviewAll_ticket=(Button) findViewById(R.id.ticket_view_button);
        btnUpdate_ticket=(Button)findViewById(R.id.ticket_update_button);
        btnDelete_ticket=(Button)findViewById(R.id.ticket_delete_button);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();


    }

    public void DeleteData(){
        btnDelete_ticket.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = myDB.deleteData2(edittextId_ticket.getText().toString());
                        if(deleteRows>0)
                            Toast.makeText(insert_tickets_admin.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(insert_tickets_admin.this,"Data not Deleted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void UpdateData(){
        btnUpdate_ticket.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate= myDB.updateData2(edittextId_ticket.getText().toString(),
                                editType_ticket.getText().toString(),
                                editHall_name_ticket.getText().toString(),
                                editprice_ticket.getText().toString(),
                                editcount_ticket.getText().toString(),
                                tickettime.getText().toString()
                        );
                        if(isUpdate==true)
                            Toast.makeText(insert_tickets_admin.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(insert_tickets_admin.this,"Data not updated",Toast.LENGTH_LONG).show();


                    }
                }

        );

    }
    public void AddData(){
        btnAddData_ticket.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted= myDB.insertDATA2(
                                editType_ticket.getText().toString(),
                                editHall_name_ticket.getText().toString(),
                                editprice_ticket.getText().toString(),
                                editcount_ticket.getText().toString(),
                                tickettime.getText().toString()
                                );
                        if(isInserted=true)
                            Toast.makeText(insert_tickets_admin.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(insert_tickets_admin.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void viewAll(){
        btnviewAll_ticket.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Cursor res=myDB.getAllData2();
                        if(res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer =new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id :"+res.getString(0)+"\n");
                            buffer.append("Type :"+res.getString(1)+"\n");
                            buffer.append("Hall_name :"+res.getString(2)+"\n");
                            buffer.append("price :"+res.getString(3)+"\n");
                            buffer.append("count :"+res.getString(4)+"\n");
                            buffer.append("time :"+res.getString(5)+"\n\n");
                        }
                        showMessage("Data", buffer.toString());
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