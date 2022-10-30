package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;


    private String userID;

    private Button logout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logout = (Button) findViewById(R.id.signOut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        final TextView fullNameTextView = (TextView) findViewById(R.id.fullName);
        final TextView emailTextView = (TextView) findViewById(R.id.emailAddress);
        final TextView dateOfBirthTextView = (TextView) findViewById(R.id.dateOfBirth);
        final TextView ageTextView = (TextView) findViewById(R.id.age);
        final TextView mobileNumberTextView = (TextView) findViewById(R.id.mobileNumber);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprofile = snapshot.getValue(User.class);

                if (userprofile != null) {
                    String fullName = userprofile.fullName;
                    String email = userprofile.email;
                    String dateOfBirth = userprofile.dob;
                    String age = userprofile.age;
                    String mobileNumber = userprofile.mobile;

                    greetingTextView.setText("Welcome," + fullName + "!");
                    fullNameTextView.setText(fullName);
                    emailTextView.setText(email);
                    dateOfBirthTextView.setText(dateOfBirth);
                    ageTextView.setText(age);
                    mobileNumberTextView.setText(mobileNumber);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ProfileActivity.this, "Something wrong happened", Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.common_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_update_profile) {
            Intent intent = new Intent(ProfileActivity.this, UpdateProfile.class);
            startActivity(intent);
        } else if (id == R.id.menu_delete_profile) {
            Intent intent = new Intent(ProfileActivity.this, DeleteProfile.class);
            startActivity(intent);
        } else if (id == R.id.menu_logout) {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(ProfileActivity.this, "Logged Out", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(ProfileActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();

        }
        return super.onOptionsItemSelected(item);
    }
}