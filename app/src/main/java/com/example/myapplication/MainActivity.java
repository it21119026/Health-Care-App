package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView register, forgotPassword  ;
    private EditText editTextEmail , editTextPassword;
    private Button signIn ;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.signin);
        signIn.setOnClickListener(this);

        editTextEmail =(EditText) findViewById(R.id.Email);
        editTextPassword =(EditText) findViewById(R.id.Password);

        progressBar =(ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        forgotPassword =(TextView) findViewById(R.id.forgotpassword);
        forgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent( this , RegisterUser.class));
                break;

            case R.id.signin:
                userLogin();
                break;

            case R.id.forgotpassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;


        }
    }

    private void userLogin() {

        String Email = editTextEmail.getText().toString().trim();
        String Password = editTextPassword.getText().toString().trim();


        if(Email.isEmpty()){
            editTextEmail.setError("email is required !");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            editTextEmail.setError("Please enter a valid email !");
            editTextEmail.requestFocus();
            return;
        }

        if(Password.isEmpty()){
            editTextPassword.setError("Password is required !");
            editTextPassword.requestFocus();
            return;
        }

        if(Password.length() < 6){
            editTextPassword.setError(" Min Password length is 6 characters !");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()){
                        //redirect to user profile
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));

                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this,"Check your email to verify account !", Toast.LENGTH_LONG).show();

                    }
                }else {
                    Toast.makeText(MainActivity.this,"Failed to login! please check your credentials",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}