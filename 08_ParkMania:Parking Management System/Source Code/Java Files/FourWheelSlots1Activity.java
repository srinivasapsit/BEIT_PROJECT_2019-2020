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

public class FourWheelSlots1Activity extends AppCompatActivity {
    private Button C01;
    private Button C02;
    private Button C03;
    private Button C04;
    private Button C05;
    private Button C06;
    private Button C07;
    private Button C08;
    private Button Proceed;
    String s1,s2,s3,s4,s5,s6,s7,s8;
    String ss1,ss2,ss3,ss4,ss5,ss6,ss7,ss8;

    String vehicleType, zone, slot;
    String name, email, mobile, passwd,vehicleno;

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase1;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    boolean clicked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_wheel_slots1);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        C01=(Button)findViewById(R.id.btn10);
        C02=(Button)findViewById(R.id.btn11);
        C03=(Button)findViewById(R.id.btn12);
        C04=(Button)findViewById(R.id.btn13);
        C05=(Button)findViewById(R.id.btn14);
        C06=(Button)findViewById(R.id.btn15);
        C07=(Button)findViewById(R.id.btn16);
        C08=(Button)findViewById(R.id.button88);
        Proceed=(Button)findViewById(R.id.button55);




        mDatabase1 = firebaseDatabase.getInstance().getReference("Parking Slots 2");
        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ss1=dataSnapshot.child("Slots").child("C01").getValue(String.class);
                if(ss1.equalsIgnoreCase("N")){
                    C01.setEnabled(false);
                    C01.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss2=dataSnapshot.child("Slots").child("C02").getValue(String.class);
                if(ss2.equalsIgnoreCase("N")){
                    C02.setEnabled(false);
                    C02.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss3=dataSnapshot.child("Slots").child("C03").getValue(String.class);
                if(ss3.equalsIgnoreCase("N")){
                    C03.setEnabled(false);
                    C03.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss4=dataSnapshot.child("Slots").child("C04").getValue(String.class);
                if(ss4.equalsIgnoreCase("N")){
                    C04.setEnabled(false);
                    C04.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss5=dataSnapshot.child("Slots").child("C05").getValue(String.class);
                if(ss5.equalsIgnoreCase("N")){
                    C05.setEnabled(false);
                    C05.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss6=dataSnapshot.child("Slots").child("C06").getValue(String.class);
                if(ss6.equalsIgnoreCase("N")){
                    C06.setEnabled(false);
                    C06.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss7=dataSnapshot.child("Slots").child("C07").getValue(String.class);
                if(ss7.equalsIgnoreCase("N")){
                    C07.setEnabled(false);
                    C07.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss8=dataSnapshot.child("Slots").child("C08").getValue(String.class);
                if(ss8.equalsIgnoreCase("N")){
                    C08.setEnabled(false);
                    C08.setBackgroundColor(Color.parseColor("#ffcc0000"));
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
                    Intent intent = new Intent(FourWheelSlots1Activity.this, TimeActivity1.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    // Intent intent=new Intent(getApplicationContext(),TimeActivity1.class);
                    // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });



        C01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s1=dataSnapshot.child("Slots").child("C01").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s1.equalsIgnoreCase("Y")){
                            slot=C01.getText().toString();
                         //   ProceedToTime();
                            AddParkingData();
                            Toast.makeText(getApplicationContext(),"Slot C01 is selected",Toast.LENGTH_SHORT).show();

                            //mDatabase.child("Slots").child("C01").setValue("N");

                        }


                        else{
                            C01.setEnabled(false);
                            C01.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                /*slot=C01.getText().toString();
                ProceedToTime();
                AddParkingData();*/
            }
        });
        C02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s2=dataSnapshot.child("Slots").child("C02").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s2.equalsIgnoreCase("Y")){
                            slot=C02.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                            Toast.makeText(getApplicationContext(),"Slot C02 is selected",Toast.LENGTH_SHORT).show();

                            //mDatabase.child("Slots").child("C02").setValue("N");

                        }


                        else{
                            C02.setEnabled(false);
                            C02.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


               /* slot=C02.getText().toString();
                ProceedToTime();
                AddParkingData();*/
            }
        });
        C03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s3=dataSnapshot.child("Slots").child("C03").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s3.equalsIgnoreCase("Y")){
                            slot=C03.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                            Toast.makeText(getApplicationContext(),"Slot C03 is selected",Toast.LENGTH_SHORT).show();
                          //  mDatabase.child("Slots").child("C03").setValue("N");

                        }


                        else{
                            C03.setEnabled(false);
                            C03.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


               /* slot=C03.getText().toString();
                ProceedToTime();
                AddParkingData();*/
            }
        });
        C04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s4=dataSnapshot.child("Slots").child("C04").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s4.equalsIgnoreCase("Y")){
                            slot=C04.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                            Toast.makeText(getApplicationContext(),"Slot C04 is selected",Toast.LENGTH_SHORT).show();
                          //  mDatabase.child("Slots").child("C04").setValue("N");

                        }


                        else{
                            C04.setEnabled(false);
                            C04.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


              /*  C04.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();*/
            }
        });
        C05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s5=dataSnapshot.child("Slots").child("C05").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s5.equalsIgnoreCase("Y")){
                            slot=C05.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                            Toast.makeText(getApplicationContext(),"Slot C05 is selected",Toast.LENGTH_SHORT).show();
                            //mDatabase.child("Slots").child("C05").setValue("N");

                        }


                        else{
                            C05.setEnabled(false);
                            C05.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


              /*  C05.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();*/
            }
        });
        C06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s6=dataSnapshot.child("Slots").child("C06").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s6.equalsIgnoreCase("Y")){
                            slot=C06.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                            Toast.makeText(getApplicationContext(),"Slot C06 is selected",Toast.LENGTH_SHORT).show();
                           // mDatabase.child("Slots").child("C06").setValue("N");

                        }


                        else{
                            C06.setEnabled(false);
                            C06.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



               /* slot=C06.getText().toString();
                ProceedToTime();
                AddParkingData();*/
            }
        });
        C07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s7=dataSnapshot.child("Slots").child("C07").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s7.equalsIgnoreCase("Y")){
                            slot=C07.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                            Toast.makeText(getApplicationContext(),"Slot C07 is selected",Toast.LENGTH_SHORT).show();
                            //mDatabase.child("Slots").child("C07").setValue("N");

                        }


                        else{
                            C07.setEnabled(false);
                            C07.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


               /* slot=C07.getText().toString();
                ProceedToTime();
                AddParkingData();*/
            }
        });
        C08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s8=dataSnapshot.child("Slots").child("C08").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s8.equalsIgnoreCase("Y")){
                            slot=C08.getText().toString();
                           // ProceedToTime();
                            AddParkingData();
                            Toast.makeText(getApplicationContext(),"Slot C08 is selected",Toast.LENGTH_SHORT).show();
                           // mDatabase.child("Slots").child("C08").setValue("N");

                        }


                        else{
                            C08.setEnabled(false);
                            C08.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


               /* C08.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();*/
            }
        });
    }
    //public void ProceedToDateTime(){
      //  Intent intent=new Intent(getApplicationContext(),DateTimeActivity.class);
        //startActivity(intent);
   // }
    public void ProceedToTime(){
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


    public void AddParkingData() {
        //rg.AddData();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        try {
            DatabaseReference reference4 = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
            ParkingData2 pd2 = new ParkingData2(name, email, mobile, passwd,vehicleno, slot);
            reference4.child("slot").setValue(pd2);
        } catch (NullPointerException ignored) {

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
                Intent intent=new Intent(FourWheelSlots1Activity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(FourWheelSlots1Activity.this,ViewProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}

