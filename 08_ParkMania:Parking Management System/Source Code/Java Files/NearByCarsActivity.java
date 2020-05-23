package com.example.admin.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NearByCarsActivity extends AppCompatActivity {
    private Button CR01;
    private Button CR02;
    private Button CR03;
    private Button CR04;
    String vehicleType, zone, slot;
    String name, email, mobile, passwd,vehicleno;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_cars);
        CR01=(Button)findViewById(R.id.button40);
        CR02=(Button)findViewById(R.id.button42);
        CR03=(Button)findViewById(R.id.button43);
        CR04=(Button)findViewById(R.id.button44);

        CR01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slot=CR01.getText().toString();
                ProceedToTime();
                AddParkingData();
            }
        });

        CR02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slot=CR02.getText().toString();
                //ProceedToTime();
                //AddParkingData();
                CR02.setEnabled(false);

                Toast.makeText(getApplicationContext(), "Slot is occupied", Toast.LENGTH_SHORT).show();
            }
        });
        CR03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slot=CR03.getText().toString();
                //ProceedToTime();
                //AddParkingData();
                CR03.setEnabled(false);

                Toast.makeText(getApplicationContext(), "Slot is occupied", Toast.LENGTH_SHORT).show();
            }
        });
        CR04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slot=CR04.getText().toString();
                ProceedToTime();
                AddParkingData();
            }
        });
    }
    private void ProceedToTime(){
        Intent intent=new Intent(getApplicationContext(),TimeActivity1.class);
        startActivity(intent);
    }
    public void AddParkingData() {
        //rg.AddData();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //DatabaseReference reference5 = firebaseDatabase.getReference("Parking Slots 2");
        try{
        DatabaseReference reference8 = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        ParkingData2 pd2 = new ParkingData2(name, email, mobile, passwd,vehicleno, slot);
        reference8.child("slot").setValue(pd2);
    }catch (NullPointerException ignored) {
        }
        }}

