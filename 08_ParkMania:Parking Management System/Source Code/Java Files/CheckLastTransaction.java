package com.example.admin.signup;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.admin.signup.TwoWheelSlotsActivity.*;
import com.example.admin.signup.Constants.*;


public class CheckLastTransaction extends AppCompatActivity {
    public Button ConfirmOrder;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabase;
    TextView vType,zone,slot,stime,etime,date;
    String s1,s2,s3,s4,s5,s6,s7,s8;
    private Button A01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_last_transaction);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        ConfirmOrder = (Button) findViewById(R.id.button7);
        vType = (TextView) findViewById(R.id.textView50);
        zone = (TextView) findViewById(R.id.textView52);
        slot = (TextView) findViewById(R.id.textView53);
        date=(TextView)findViewById(R.id.textView56);
        stime = (TextView) findViewById(R.id.textView54);
        etime = (TextView) findViewById(R.id.textView55);
        A01=(Button)findViewById(R.id.btn1);
        //this.A01 = A01;
        // FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        try {
            DatabaseReference reference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ParkingData parkingData = dataSnapshot.child("vehicleType").getValue(ParkingData.class);
                    ParkingData1 parkingData1 = dataSnapshot.child("zone").getValue(ParkingData1.class);
                    ParkingData2 parkingData2 = dataSnapshot.child("slot").getValue(ParkingData2.class);
                    ParkingData3 parkingData3 = dataSnapshot.child("Date & Time Slot").getValue(ParkingData3.class);
                    try {
                        vType.setText("Vehicle Type: " + parkingData.getVehicleType());
                        zone.setText("Zone: " + parkingData1.getZone());
                        slot.setText("Slot No. " + parkingData2.getSlot());
                        date.setText("Date: " + parkingData3.getDate());
                        stime.setText("Stime: " + parkingData3.getStime());
                        etime.setText("Etime: " + parkingData3.getEtime());
              /* UserProfile userProfile=dataSnapshot.getValue(UserProfile.class);
               name.setText("Name: " + userProfile.getUserName());
               email.setText("Email: " + userProfile.getUserEmail());
               mobile.setText("Mobile: " + userProfile.getUserMN());*/

                    } catch (NullPointerException e) {
                        Toast.makeText(CheckLastTransaction.this, "There are no previous records, since you are a new user", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }catch (NullPointerException ignored){

        }

        /*ConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
         *//*s1 = dataSnapshot.child("Slots").child("A01").getValue(String.class);
         s2 = dataSnapshot.child("Slots").child("A02").getValue(String.class);
         if (s1.equals("A01Y")) {*//*
                        try {
                            DatabaseReference uidreference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
                            uidreference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    ParkingData2 parkingData2 = dataSnapshot.child("slot").getValue(ParkingData2.class);
                                    startActivity(new Intent(getApplicationContext(), CounterActivity.class));
                                    mDatabase.child("Slots").child(parkingData2.getSlot()).setValue("N");

                                }


                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }


                            });
                        }catch (NullPointerException ignored){

                        }


                        //} else if (s2.equals("A02Y")) {
                        //startActivity(new Intent(getApplicationContext(), CounterActivity.class));
                        //mDatabase.child("Slots").child("A02").setValue("N");
                        //}

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                    *//* Toast.makeText(getApplicationContext(), "Slot is booked", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), CounterActivity.class));*//*


               *//* mDatabase.child("Slots").child("A01").setValue("N");
                    mDatabase.child("Slots").child("A02").setValue("N");
*//*
                *//*    A01.setEnabled(false);
                    A01.setBackgroundColor(Color.parseColor("#ffcc0000"));*//*


                                               *//* Toast.makeText(getApplicationContext(), "Slot is booked", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), CounterActivity.class));*//*

                });
            }
        });*/}

            /*    @Override
                public void onCancelled(DatabaseError databaseError) {

                }
                });*/
           /* mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 3");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    s1=dataSnapshot.child("Slots").child("A01").getValue(String.class);
                    s2=dataSnapshot.child("Slots").child("A02").getValue(String.class);
                    //s3=dataSnapshot.child("Slots").child("A03").getValue(String.class);
                   *//* s4=dataSnapshot.child("Slots").child("A04").getValue(String.class);
                    s5=dataSnapshot.child("Slots").child("A05").getValue(String.class);
                    s6=dataSnapshot.child("Slots").child("A06").getValue(String.class);
                    s7=dataSnapshot.child("Slots").child("A07").getValue(String.class);
                    s8=dataSnapshot.child("Slots").child("A08").getValue(String.class);*//*
                  //  System.out.println("S1 value is :"+s1);
                   validate1();
                 *//*  else {
                        mDatabase.child("Slots").child("A02").setValue("N");
                        startActivity(new Intent(getApplicationContext(),CounterActivity.class));

                    }*//*
                   *//* else{
                        Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_SHORT).show();
                    }*//*

                   *//* if(s3.equals("Y")){
                        mDatabase.child("Slots").child("A03").setValue("N");
                        startActivity(new Intent(getApplicationContext(),CounterActivity.class));

                    }*//*

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }

            *//*mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    s1=dataSnapshot.child("Slots").child("A01").getValue(String.class);
                    System.out.println("S1 value is :"+s1);
                    if(s1.equals("Y")){
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
           *//**//* mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
            mDatabase.child("Slots").child("A01").setValue("N");

            mDatabase.child("Slots").child("A07").setValue("N");*//**//*

         *//**//*   startActivity(new Intent(getApplicationContext(),CounterActivity.class));*//**//*
        }
    });*//*
    });*/

    /*      }
      });
  }
*/
    public void validate1(){

        if(s1.equals("A01 Available")){
            mDatabase.child("Slots").child("A01").setValue("N");
            startActivity(new Intent(getApplicationContext(),CounterActivity.class));
        }
        else if(s2.equals("A02 Available")){
            mDatabase.child("Slots").child("A02").setValue("N");
            startActivity(new Intent(getApplicationContext(),CounterActivity.class));
            /*Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_SHORT).show();*/
        }
    }


}



