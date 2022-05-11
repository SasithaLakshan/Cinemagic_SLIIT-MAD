package com.example.mad_2nd_year_2nd_semester;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insert_film_halls extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText editName, editLocation,editNo_of_seats ,edittextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnUpdate;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_film_halls);
        myDB= new DatabaseHelper(this);

        editName=(EditText) findViewById(R.id.hall_name);
        editLocation=(EditText) findViewById(R.id.hall_location);
        editNo_of_seats=(EditText) findViewById(R.id.no_of_seats);
        edittextId=(EditText) findViewById(R.id.hall_id);
        btnAddData=(Button) findViewById(R.id.insert_button);
        btnviewAll=(Button) findViewById(R.id.view_button);
        btnUpdate=(Button)findViewById(R.id.update_button);
        btnDelete=(Button)findViewById(R.id.delete_button);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }
    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = myDB.deleteData(edittextId.getText().toString());
                        if(deleteRows>0)
                            Toast.makeText(insert_film_halls.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(insert_film_halls.this,"Data not Deleted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }


    public void UpdateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate= myDB.updateData(edittextId.getText().toString(),
                                editName.getText().toString(),
                                editLocation.getText().toString(),
                                editNo_of_seats.getText().toString()
                        );
                        if(isUpdate==true)
                            Toast.makeText(insert_film_halls.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(insert_film_halls.this,"Data not updated",Toast.LENGTH_LONG).show();


                    }
                }

        );

    }

    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted= myDB.insertDATA(
                                editName.getText().toString(),
                                editLocation.getText().toString(),
                                editNo_of_seats.getText().toString() );
                        if(isInserted=true)
                            Toast.makeText(insert_film_halls.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(insert_film_halls.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
    public void viewAll(){
        btnviewAll.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Cursor res=myDB.getAllData();
                        if(res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer =new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id :"+res.getString(0)+"\n");
                            buffer.append("Name :"+res.getString(1)+"\n");
                            buffer.append("Location :"+res.getString(2)+"\n");
                            buffer.append("No of Seats :"+res.getString(3)+"\n\n");
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