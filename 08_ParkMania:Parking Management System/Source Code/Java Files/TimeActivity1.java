package com.example.admin.signup;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class TimeActivity1 extends AppCompatActivity {
    EditText SelectDate;
    EditText chooseStartTime;
    EditText chooseEndTime;
    Button Proceed;
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    int currentSeconds;
    int year;
    int month;
    int dayOfMonth;
    String amPm;
    String stime,etime,date;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    //String vehicleType, zone, slot;
    String name, email, mobile, passwd,vehicleno;
  //  String StartTime,EndTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time1);
        firebaseAuth = FirebaseAuth.getInstance();

        chooseStartTime = (EditText)findViewById(R.id.etChooseStartTime);
        stime=chooseStartTime.getText().toString();
        chooseEndTime=(EditText)findViewById(R.id.etChooseEndTime);
        etime=chooseEndTime.getText().toString();
        SelectDate=(EditText)findViewById(R.id.editText8);
        date=SelectDate.getText().toString();
        Proceed=(Button)findViewById(R.id.button29);
        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                Intent intent=new Intent(getApplicationContext(),PricingActivity.class);
                startActivity(intent);
                    AddParkingData();

            }
            }
        });
        chooseStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               calendar = Calendar.getInstance();
                int minTime = 16;
                currentHour = calendar.get(Calendar.HOUR_OF_DAY) ;
                currentMinute = calendar.get(Calendar.MINUTE);
                //if(currentHour < minTime) currentHour = 0;
                //else currentHour = calendar.get(Calendar.HOUR_OF_DAY) ;
                //currentSeconds=calendar.get(Calendar.SECOND);

                timePickerDialog = new TimePickerDialog(TimeActivity1.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                       /* if((hourOfDay<(calendar.get(Calendar.HOUR_OF_DAY)))&&(minutes<=(calendar.get(Calendar.MINUTE)))){
                            Toast.makeText(getApplicationContext(),"Wrong Time",Toast.LENGTH_SHORT).show();
                           Proceed.setEnabled(false);
                        }*/

                      if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                      /*  else if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else if(hourOfDay<12){
                            amPm = "AM";
                        }
*/

                        chooseStartTime.setText(String.format("%02d:%02d", hourOfDay, minutes) );
                    }
                }, currentHour, currentMinute,false);


                timePickerDialog.show();

            }
        });

        chooseEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(TimeActivity1.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        /*if((hourOfDay<=(calendar.get(Calendar.HOUR_OF_DAY)))&&(minutes<=(calendar.get(Calendar.MINUTE)))){
                            Toast.makeText(getApplicationContext(),"Wrong Time",Toast.LENGTH_SHORT).show();
                            Proceed.setEnabled(false);
                        }*/

                        if (hourOfDay >= 12 && minutes>=0) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        /*else if (hourOfDay >= 12 && minutes>=0) {
                            amPm = "PM";
                        } else if (hourOfDay<12 && minutes<=59){
                            amPm = "AM";
                        }*/

                        //chooseEndTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                        chooseEndTime.setText(String.format("%02d:%02d", hourOfDay, minutes));
                    }
               }, currentHour, currentMinute, false);


                timePickerDialog.show();
            }
        });
        SelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                System.out.println("year: "+year);
                System.out.println("month: "+month);
                System.out.println("day: "+dayOfMonth);
                if((dayOfMonth>=1 && dayOfMonth<=9) && (month>=1 && month<=9)) {
                    System.out.println("Within zero concatenate if");
                    datePickerDialog = new DatePickerDialog(TimeActivity1.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                    SelectDate.setText("0" + day + "/" + "0" + (month + 1) + "/" + year);
                                }
                            }, year, month, dayOfMonth);
                }
                else {
                    System.out.println("Within zero concatenate else");
                    datePickerDialog = new DatePickerDialog(TimeActivity1.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                    SelectDate.setText(day + "/" + "0" + (month + 1) + "/" + year);
                                }
                            }, year, month, dayOfMonth);
                }

               // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
    }

    private Boolean validate(){
        Boolean result=false;
        stime=chooseStartTime.getText().toString();
        etime=chooseEndTime.getText().toString();
        date=SelectDate.getText().toString();
        if (date.isEmpty() || stime.isEmpty() || etime.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter date and time duration", Toast.LENGTH_SHORT).show();
        }
        else if(stime.equals(etime)){
            Toast.makeText(getApplicationContext(), "Start and End time cannot be same", Toast.LENGTH_SHORT).show(); //else if (stime.length() > 2) {
        }
        //else if(stime.equalsIgnoreCase("00:00") || stime.equalsIgnoreCase("")){
        else if((stime.compareTo("00:00")>=0) && stime.compareTo("07:00")<0){
            Toast.makeText(getApplicationContext(), "Parking hours are closed between 12 AM to 7 AM", Toast.LENGTH_SHORT).show();
        }
        else {
            result=true;
            //startActivity(new Intent(getApplicationContext(), PaymentOptionsActivity.class));
        }
        return result;

    }
    public void AddParkingData() {
        //rg.AddData();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference6 = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        ParkingData3 pd3 = new ParkingData3(name, email, mobile, passwd,vehicleno, stime,etime,date);
        reference6.child("Date & Time Slot").setValue(pd3);
        //reference6.child("Etime").setValue(pd3);
    }





   /* @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.logoutMenu:{
                firebaseAuth.signOut();
                finish();
                Intent intent=new Intent(TimeActivity1.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                //startActivity(new Intent(TimeActivity1.this,MainActivity.class));
                //onBackPressed();
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(TimeActivity1.this,ViewProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}


