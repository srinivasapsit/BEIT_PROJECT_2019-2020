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

import java.text.SimpleDateFormat;
import java.util.Date;


public class DisplayAllInfo extends AppCompatActivity {
    public Button ConfirmOrder;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabase;
    TextView vType,zone,slot,stime,etime;
    String s1,s2,s3,s4,s5,s6,s7,s8;
    private Button A01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_info);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        ConfirmOrder = (Button) findViewById(R.id.button7);
        vType = (TextView) findViewById(R.id.textView10);
        zone = (TextView) findViewById(R.id.textView11);
        slot = (TextView) findViewById(R.id.textView13);
        stime = (TextView) findViewById(R.id.textView22);
        etime = (TextView) findViewById(R.id.textView23);
        A01=(Button)findViewById(R.id.btn1);
        //this.A01 = A01;
        // FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ParkingData parkingData = dataSnapshot.child("vehicleType").getValue(ParkingData.class);
                ParkingData1 parkingData1 = dataSnapshot.child("zone").getValue(ParkingData1.class);
                ParkingData2 parkingData2 = dataSnapshot.child("slot").getValue(ParkingData2.class);
                ParkingData3 parkingData3 = dataSnapshot.child("Date & Time Slot").getValue(ParkingData3.class);
                vType.setText("Vehicle Type: " + parkingData.getVehicleType());
                zone.setText("Zone: " + parkingData1.getZone());
                slot.setText("Slot No. " + parkingData2.getSlot());
                stime.setText("Stime: " + parkingData3.getStime());
                etime.setText("Etime: " + parkingData3.getEtime());
              /* UserProfile userProfile=dataSnapshot.getValue(UserProfile.class);
               name.setText("Name: " + userProfile.getUserName());
               email.setText("Email: " + userProfile.getUserEmail());
               mobile.setText("Mobile: " + userProfile.getUserMN());*/

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots 2");
                //   mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots 3");
                //    mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
         /*s1 = dataSnapshot.child("Slots").child("A01").getValue(String.class);
         s2 = dataSnapshot.child("Slots").child("A02").getValue(String.class);
         if (s1.equals("A01Y")) {*/
                        try {
                            DatabaseReference uidreference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
                            uidreference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    ParkingData2 parkingData2 = dataSnapshot.child("slot").getValue(ParkingData2.class);
                                    ParkingData3 parkingData3 = dataSnapshot.child("Date & Time Slot").getValue(ParkingData3.class);
                                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                                    String currTime = sdf.format(new Date());
                                    System.out.println("Current 24-hr time in DisplayInfo => " +currTime);
                                    //String stime = parkingData3.getStime().substring(0,parkingData3.getStime().length()-2);
                                    String stime = parkingData3.getStime();
                                    System.out.println("Parking Data3 time DisplayInfo =>" +stime);
                                    //
                                    //
                                    if (currTime.compareTo(stime)==0){
                                        //if (currTime.compareTo(stime)>=0){
                                        startActivity(new Intent(getApplicationContext(), CounterActivity.class));
                                        // startActivity(new Intent(getApplicationContext(), FinalActivity.class));

                                        mDatabase.child("Slots").child(parkingData2.getSlot()).setValue("N");
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "Timer will start at the specified time", Toast.LENGTH_SHORT).show();
                                    }
                                    //}

                                    //startActivity(new Intent(getApplicationContext(), CounterActivity.class));
                                    //mDatabase.child("Slots").child(parkingData2.getSlot()).setValue("N");

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

                    /* Toast.makeText(getApplicationContext(), "Slot is booked", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), CounterActivity.class));*/


               /* mDatabase.child("Slots").child("A01").setValue("N");
                    mDatabase.child("Slots").child("A02").setValue("N");
*/
                /*    A01.setEnabled(false);
                    A01.setBackgroundColor(Color.parseColor("#ffcc0000"));*/


                                               /* Toast.makeText(getApplicationContext(), "Slot is booked", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), CounterActivity.class));*/

                });
            }
        });}

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

    /*@Override
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
                Intent intent=new Intent(DisplayAllInfo.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                //startActivity(new Intent(TimeActivity1.this,MainActivity.class));
                //onBackPressed();
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(DisplayAllInfo.this,ViewProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}


