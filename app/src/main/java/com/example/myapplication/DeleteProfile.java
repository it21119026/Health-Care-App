package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class DeleteProfile extends AppCompatActivity {


    private EditText emailEditText;
    private Button deleteProfile;
    private ProgressBar progressBar;


    FirebaseAuth auth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_profile);

        emailEditText =(EditText) findViewById(R.id.Email);
        deleteProfile = (Button) findViewById(R.id.deleteProfile);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);



        auth = FirebaseAuth.getInstance();

        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProfile();
            }
        });

    }

    private void deleteProfile() {
        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()){
            emailEditText.setError("Please provide valid email!");
            emailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Please provide valid email!");
            emailEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                    Toast.makeText(DeleteProfile.this, "Check your email to Delete your Profile!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(DeleteProfile.this, "Try again ! Something wrong happened!", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

}