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

public class NearByBikesActivity extends AppCompatActivity {
    private Button BK01;
    private Button BK02;
    private Button BK03;
    private Button BK04;
    String vehicleType, zone, slot;
    String name, email, mobile, passwd,vehicleno;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_bikes);
        BK01=(Button)findViewById(R.id.btn41);
        BK02=(Button)findViewById(R.id.btn45);
        BK03=(Button)findViewById(R.id.btn46);
        BK04=(Button)findViewById(R.id.btn47);

        BK01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slot=BK01.getText().toString();
                ProceedToTime();
                AddParkingData();
            }
        });

        BK02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slot=BK02.getText().toString();
                ProceedToTime();
                AddParkingData();
            }
        });
        BK03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slot=BK03.getText().toString();
                ProceedToTime();
                AddParkingData();
            }
        });

        BK04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slot=BK04.getText().toString();
                //ProceedToTime();
                //AddParkingData();
                BK04.setEnabled(false);

                Toast.makeText(getApplicationContext(), "Slot is occupied", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ProceedToTime(){
        Intent intent=new Intent(getApplicationContext(),TimeActivity1.class);
        startActivity(intent);
    }

        //rg.AddData();
        public void AddParkingData() {
            //rg.AddData();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference reference4 = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
            ParkingData2 pd2 = new ParkingData2(name, email, mobile, passwd,vehicleno, slot);
            reference4.child("slot").setValue(pd2);
        }
}
