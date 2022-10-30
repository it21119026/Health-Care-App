package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class InsuaranceMainFunc extends AppCompatActivity {

    ImageView about ;
    ImageView privacy ;
    ImageView support ;
    ImageView enrol ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insuarance_main_func);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        about = findViewById(R.id.aboutu);
        // Apply OnClickListener  to imageView to
        // switch from one activity to another
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(InsuaranceMainFunc.this, Aboutus.class);
                startActivity(intent);
            }
        });

        privacy = findViewById(R.id.privaa);
        // Apply OnClickListener  to imageView to
        // switch from one activity to another
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(InsuaranceMainFunc.this, Privacy_Policy.class);
                startActivity(intent);
            }
        });


        support = findViewById(R.id.suppp);
        // Apply OnClickListener  to imageView to
        // switch from one activity to another
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(InsuaranceMainFunc.this, Support.class);
                startActivity(intent);
            }
        });


        enrol = findViewById(R.id.enrol);
        // Apply OnClickListener  to imageView to
        // switch from one activity to another
        enrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(InsuaranceMainFunc.this, Free7_days.class);
                startActivity(intent);
            }
        });










    }
}