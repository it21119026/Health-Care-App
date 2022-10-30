package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfile extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private Button  UpdateProfile;


    private String userID;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        UpdateProfile = (Button) findViewById(R.id.signOut);
        UpdateProfile.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 FirebaseAuth.getInstance().signOut();
                                                 startActivity(new Intent(UpdateProfile.this, MainActivity.class));
                                             }
                                         });

                user = FirebaseAuth.getInstance().getCurrentUser();





        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        final TextView textview_fullNameTitle = (TextView) findViewById(R.id.edittext_fullName);
        final TextView textview_emailAddressTitle = (TextView) findViewById(R.id.edittext_emailAddress);
        final TextView textview_dateOfBirthTitle = (TextView) findViewById(R.id.edittext_dateOfBirth);
        final TextView textview_ageTitle = (TextView) findViewById(R.id.edittext_age);
        final TextView textview_mobileNumberTitle = (TextView) findViewById(R.id.edittext_mobileNumber);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprofile = snapshot.getValue(User.class);
                if (userprofile != null ){

                    String textFullName = userprofile.fullName;
                    String textEmail = userprofile.email;
                    String textDob = userprofile.dob;
                    String textAge = userprofile.age;
                    String textMobile = userprofile.mobile;

                    greetingTextView.setText("Welcome," + textFullName + "!");
                    textview_fullNameTitle.setText(textFullName);
                    textview_emailAddressTitle.setText(textEmail);
                    textview_dateOfBirthTitle.setText(textDob);
                    textview_ageTitle.setText(textAge);
                    textview_mobileNumberTitle.setText(textMobile);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(UpdateProfile.this, "Something wrong happened", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void updateProfile(FirebaseUser user) {

    }


}