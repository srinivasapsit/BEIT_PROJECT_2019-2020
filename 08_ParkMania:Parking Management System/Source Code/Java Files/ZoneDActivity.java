package com.example.admin.signup;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ZoneDActivity extends AppCompatActivity {
    private Button D01;
    private Button D02;
    private Button D03;
    private Button D04;
    private Button D05;
    private Button D06;
    private Button D07;
    private Button D08;
    private Button Proceed;

    String s1,s2,s3,s4,s5,s6,s7,s8;

    String vehicleType, zone, slot;
    String name, email, mobile, passwd,vehicleno;
    String ss1,ss2,ss3,ss4,ss5,ss6,ss7,ss8;

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase1;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    boolean clicked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_d);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        D01=(Button)findViewById(R.id.button9);
        D02=(Button)findViewById(R.id.button30);
        D03=(Button)findViewById(R.id.button31);
        D04=(Button)findViewById(R.id.button32);
        D05=(Button)findViewById(R.id.button33);
        D06=(Button)findViewById(R.id.button34);
        D07=(Button)findViewById(R.id.button35);
        D08=(Button)findViewById(R.id.button36);
        Proceed=(Button)findViewById(R.id.button57);



        mDatabase1 = firebaseDatabase.getInstance().getReference("Parking Slots 2");
        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ss1=dataSnapshot.child("Slots").child("D01").getValue(String.class);
                if(ss1.equalsIgnoreCase("N")){
                    D01.setEnabled(false);
                    D01.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss2=dataSnapshot.child("Slots").child("D02").getValue(String.class);
                if(ss2.equalsIgnoreCase("N")){
                    D02.setEnabled(false);
                    D02.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss3=dataSnapshot.child("Slots").child("D03").getValue(String.class);
                if(ss3.equalsIgnoreCase("N")){
                    D03.setEnabled(false);
                    D03.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss4=dataSnapshot.child("Slots").child("D04").getValue(String.class);
                if(ss4.equalsIgnoreCase("N")){
                    D04.setEnabled(false);
                    D04.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss5=dataSnapshot.child("Slots").child("D05").getValue(String.class);
                if(ss5.equalsIgnoreCase("N")){
                    D05.setEnabled(false);
                    D05.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss6=dataSnapshot.child("Slots").child("D06").getValue(String.class);
                if(ss6.equalsIgnoreCase("N")){
                    D06.setEnabled(false);
                    D06.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss7=dataSnapshot.child("Slots").child("D07").getValue(String.class);
                if(ss7.equalsIgnoreCase("N")){
                    D07.setEnabled(false);
                    D07.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss8=dataSnapshot.child("Slots").child("D08").getValue(String.class);
                if(ss8.equalsIgnoreCase("N")){
                    D08.setEnabled(false);
                    D08.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    Intent intent = new Intent(ZoneDActivity.this, TimeActivity1.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    // Intent intent=new Intent(getApplicationContext(),TimeActivity1.class);
                    // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });



        D01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s1=dataSnapshot.child("Slots").child("D01").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s1.equalsIgnoreCase("Y")){
                            slot=D01.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                           // mDatabase.child("Slots").child("D01").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot D01 is selected",Toast.LENGTH_SHORT).show();

                        }


                        else{
                            D01.setEnabled(false);
                            D01.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


               /* slot=D01.getText().toString();
                ProceedToTime();
                AddParkingData();
*/
            }
        });

        D02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s2=dataSnapshot.child("Slots").child("D02").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s2.equalsIgnoreCase("Y")){
                            slot=D02.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                            //mDatabase.child("Slots").child("D02").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot D02 is selected",Toast.LENGTH_SHORT).show();

                        }


                        else{
                            D02.setEnabled(false);
                            D02.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                /*slot=D02.getText().toString();
                ProceedToTime();
                AddParkingData();*/

            }
        });

        D03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s3=dataSnapshot.child("Slots").child("D03").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s3.equalsIgnoreCase("Y")){
                            slot=D03.getText().toString();
                           // ProceedToTime();
                            AddParkingData();
                           // mDatabase.child("Slots").child("D03").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot D03 is selected",Toast.LENGTH_SHORT).show();

                        }


                        else{
                            D03.setEnabled(false);
                            D03.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


              /*  slot=D03.getText().toString();
                ProceedToTime();
                AddParkingData();
*/
            }
        });

        D04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s4=dataSnapshot.child("Slots").child("D04").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s4.equalsIgnoreCase("Y")){
                            slot=D04.getText().toString();
                           // ProceedToTime();
                            AddParkingData();
                            //mDatabase.child("Slots").child("D04").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot D04 is selected",Toast.LENGTH_SHORT).show();

                        }


                        else{
                            D04.setEnabled(false);
                            D04.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                /*D04.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();*/
            }
        });

        D05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s5=dataSnapshot.child("Slots").child("D05").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s5.equalsIgnoreCase("Y")){
                            slot=D05.getText().toString();
                           // ProceedToTime();
                            AddParkingData();
                            //mDatabase.child("Slots").child("D05").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot D05 is selected",Toast.LENGTH_SHORT).show();

                        }


                        else{
                            D05.setEnabled(false);
                            D05.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

               /* slot=D05.getText().toString();
                ProceedToTime();
                AddParkingData();*/

            }
        });

        D06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s6=dataSnapshot.child("Slots").child("D06").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s6.equalsIgnoreCase("Y")){
                            slot=D06.getText().toString();
                           // ProceedToTime();
                            AddParkingData();
                           // mDatabase.child("Slots").child("D06").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot D06 is selected",Toast.LENGTH_SHORT).show();

                        }


                        else{
                            D06.setEnabled(false);
                            D06.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


             /*   D06.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();*/
            }
        });

        D07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s7=dataSnapshot.child("Slots").child("D07").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s7.equalsIgnoreCase("Y")){
                            slot=D07.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                          //  mDatabase.child("Slots").child("D07").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot D07 is selected",Toast.LENGTH_SHORT).show();

                        }


                        else{
                            D07.setEnabled(false);
                            D07.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

             /*   D07.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();*/
            }
        });

        D08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s8=dataSnapshot.child("Slots").child("D08").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s8.equalsIgnoreCase("Y")){
                            slot=D08.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                           // mDatabase.child("Slots").child("D08").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot D08 is selected",Toast.LENGTH_SHORT).show();

                        }


                        else{
                            D08.setEnabled(false);
                            D08.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

              /*  D08.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();*/
            }
        });

    }

    private void ProceedToTime(){
        Intent intent=new Intent(getApplicationContext(),TimeActivity1.class);
        startActivity(intent);
    }


    private Boolean validate(){
        Boolean result=false;
   /* slot1=A01.getText().toString();
    slot2=A02.getText().toString();
    slot3=A03.getText().toString();
    slot4=A04.getText().toString();
    slot5=A05.getText().toString();
    slot6=A06.getText().toString();
    slot7=A07.getText().toString();
    slot8=A08.getText().toString();*/

        //if(!A01.isSelected() && !A02.isSelected() && !A03.isSelected() && !A04.isSelected()
        //      && !A05.isSelected() && !A06.isSelected() && !A07.isSelected() && !A08.isSelected()){
        //if(!A01.isSelected()||!A02.isSelected()||!A03.isSelected()||!A04.isSelected()||!A05.isSelected()||!A06.isSelected()||!A07.isSelected()||!A08.isSelected()){
        //  if(!A01.isClickable()||!A02.isPressed()||!A03.isPressed()||!A04.isPressed()||!A05.isPressed()||!A06.isPressed()||!A07.isPressed()||!A08.isPressed()){
        if(!clicked){
            System.out.println("Within validate: "+!clicked);
            Toast.makeText(getApplicationContext(),"Please select slot",Toast.LENGTH_SHORT).show();
            //Proceed.setEnabled(false);
        }
        else {
            result=true;
        }
        return result;
    }



    /* private void addData1(){
         FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
         DatabaseReference reference=firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
         AddSlot addSlot=new AddSlot();
         reference.setValue(userProfile);
     */
    public void AddParkingData() {
        //rg.AddData();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        try {
            DatabaseReference reference5 = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
            ParkingData2 pd2 = new ParkingData2(name, email, mobile, passwd, vehicleno,slot);
            reference5.child("slot").setValue(pd2);


        } catch (NullPointerException ignored) {

        }
    }




/*
    @Override
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
                Intent intent=new Intent(ZoneDActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(ZoneDActivity.this,ViewProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }






}
