package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class month_1_page extends AppCompatActivity {

    Button button ;
    Button montth1btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month1_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        button = findViewById(R.id.button_free_7_free7days) ;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(month_1_page.this, Free7_days.class);
                startActivity(intent);
            }
        });



        montth1btn = findViewById(R.id.month1btn) ;

        montth1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(month_1_page.this, payment.class);
                startActivity(intent);
            }
        });











    }
}