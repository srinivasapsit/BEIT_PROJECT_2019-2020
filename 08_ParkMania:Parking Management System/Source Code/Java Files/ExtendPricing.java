package com.example.admin.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExtendPricing extends AppCompatActivity {
    private TextView Stime,Etime,TimeDuration,Price,PricingInfo;
    private Button Proceed;
    String name, email, mobile, passwd,vehicleno;
    String stime,etime;
    // String TDuration;
    String hour,min;
    int hours1,mins1,seconds1;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    //String stime,etime;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_pricing);
        TimeDuration = (TextView) findViewById(R.id.textView59);

        //TDuration=TimeDuration.getText().toString();
        Price=(TextView)findViewById(R.id.textView61);
        Proceed=(Button)findViewById(R.id.button10);

        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddParkingData();
                startActivity(new Intent(getApplicationContext(),ExtendDisplayAll.class));
                //startActivity(new Intent(getApplicationContext(),QRGenerator.class));


            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                ParkingData3 parkingData3=null;
                try {

                    System.out.println("Before Parking Data3");
                    parkingData3 = dataSnapshot.getValue(ParkingData3.class);
                }catch (DatabaseException ignored){

                }
                try {
                    System.out.println("Start Time in try: " + parkingData3.getStime());
                    System.out.println("End Time in try: " + parkingData3.getEtime());


                    SimpleDateFormat format = new SimpleDateFormat("hh:mm");
                    System.out.println("Etime: " + parkingData3.getEtime());
                    System.out.println("Stime: " + parkingData3.getStime());

                    Date date1 = format.parse(parkingData3.getEtime());
                    Date date2 = format.parse(parkingData3.getStime());

                    long mills = date1.getTime() - date2.getTime();

                    hours1= (int) (mills / (1000 * 60 * 60));
                    mins1= (int) (mills / (1000 * 60)) % 60;
                    seconds1 = (int) mills / 1000 % 60;
                    //}

                    String diff = hours1 + ":" + mins1; // updated value every1 second
                    if(hours1<0 || mins1<0){
                        TimeDuration.setText("Invalid Time Duration");
                        Price.setText("Price Not Applicable");
                        //PricingInfo.setEnabled(false);
                        Proceed.setEnabled(false);
                    }




                    else {

                        TimeDuration.setText("Time Duration: " + hours1 + " hours " + mins1 + " mins " + seconds1 + " seconds ");

                        int price;
                        if ((hours1<1 && mins1<=59) || (hours1==1 && mins1==0)){price=30;
                            Price.setText("Price: "+price+ " Rs ");}
                        else if ((hours1>=1) && ((hours1<=3 && mins1<=59)
                                || (hours1==4 && mins1==0)))
                        {   price=60;
                            Price.setText("Price: "+price+ " Rs ");}
                        else if ((hours1>=4) && ((hours1<=7 && mins1<=59) ||
                                (hours1==8 && mins1==0))){price=90;
                            Price.setText("Price: "+price+ " Rs ");}
                        else{
                            Price.setText("Exceeded parking hours");
                            Proceed.setEnabled(false);
                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (1 == 1) return;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void AddParkingData() {
        //rg.AddData();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference5 = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        ParkingData7 pd7 = new ParkingData7(name, email, mobile, passwd,vehicleno, hours1 , mins1, seconds1);
        reference5.child("Time Duration(Extended)").setValue(pd7);



    }

}
