package com.example.mad_2nd_year_2nd_semester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =findViewById(R.id.btn1);

        button.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v){

                Intent i=new Intent(MainActivity.this,EditUserProfilePage.class);
                startActivity(i);

             }


        });


    }
}