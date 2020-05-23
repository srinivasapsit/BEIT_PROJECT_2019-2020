package com.example.admin.signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProfileActivity extends AppCompatActivity {
    private TextView name, email, mobile, vehicleno;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        name = (TextView) findViewById(R.id.textView21);
        email = (TextView) findViewById(R.id.textView26);
        mobile = (TextView) findViewById(R.id.textView27);
        vehicleno = (TextView) findViewById(R.id.textView70);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        try {


            DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                    name.setText("Name: " + userProfile.getUserName());
                    email.setText("Email: " + userProfile.getUserEmail());
                    mobile.setText("Mobile: " + userProfile.getUserMN());
                    vehicleno.setText("Vehicle No: " + userProfile.getUserVehicleNo());


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), databaseError.getCode(), Toast.LENGTH_SHORT).show();

                }
            });

        }catch (NullPointerException ignored){

        }
    }}
