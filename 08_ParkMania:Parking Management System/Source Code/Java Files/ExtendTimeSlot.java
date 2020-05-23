package com.example.admin.signup;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class ExtendTimeSlot extends AppCompatActivity {
    EditText StartTime;
    EditText EndTime;
    EditText Date;
    Button Proceed;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    int currentSeconds;
    int year;
    int month;
    int dayOfMonth;
    String amPm;
    String stime,etime,date;
    String name, email, mobile, passwd,vehicleno;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_time_slot);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        StartTime=(EditText)findViewById(R.id.editText6);
        EndTime=(EditText)findViewById(R.id.editText7);
        Date=(EditText)findViewById(R.id.editText14);
        etime=EndTime.getText().toString();
        stime=StartTime.getText().toString();
        date=Date.getText().toString();
        Proceed=(Button)findViewById(R.id.button5);
try {
    DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            ParkingData3 parkingData3 = dataSnapshot.child("Date & Time Slot").getValue(ParkingData3.class);
            Date.setText(parkingData3.getDate());
            Date.setEnabled(false);
            StartTime.setText(parkingData3.getEtime());
            StartTime.setEnabled(false);


        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}catch (NullPointerException ignored)
{

}
        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    AddParkingData();
                    Intent intent=new Intent(getApplicationContext(),ExtendPricing.class);
                    startActivity(intent);
                }
            }
        });

        EndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(ExtendTimeSlot.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        if (hourOfDay >= 12 && minutes>=0) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }

                       EndTime.setText(String.format("%02d:%02d", hourOfDay, minutes) );
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

    }

    public void AddParkingData() {
        //rg.AddData();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference6 = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
     // ParkingData6 pd6=new ParkingData6(name,mobile,email,passwd,etime);
        ParkingData3 pd3=new ParkingData3(name,mobile,email,passwd,vehicleno,stime,etime,date);
        System.out.println("Date in ExtendTimeSlot: "+pd3.getDate());
        System.out.println("stime in ExtendTimeSlot: "+pd3.getStime());
        System.out.println("etime in ExtendTimeSlot: "+pd3.getEtime());
        reference6.child("Date & Time Slot").setValue(pd3);
        //reference6.child("Etime").setValue(pd3);
    }

    private Boolean validate(){
        Boolean result=false;
        stime=StartTime.getText().toString();
        etime=EndTime.getText().toString();
        date=Date.getText().toString();
        if (date.isEmpty() || stime.isEmpty() || etime.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter date and time duration", Toast.LENGTH_SHORT).show();
        }
        else if(stime.equals(etime)){
            Toast.makeText(getApplicationContext(), "Enter a valid time duration", Toast.LENGTH_SHORT).show(); //else if (stime.length() > 2) {
        }
        else if((etime.compareTo("00:00")>=0) && etime.compareTo("07:00")<0){
            Toast.makeText(getApplicationContext(), "Parking hours are closed between 12 AM to 7 AM", Toast.LENGTH_SHORT).show();
        }

        else {
            result=true;
        }
        return result;

    }

}
