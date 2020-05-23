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

public class NearByBikes1Activity extends AppCompatActivity {
    public Button BK01;
    public Button BK02;
    public Button BK03;
    public Button BK04;
    private Button Proceed;
    String vehicleType, zone, slot;
    String name, email, mobile, passwd,vehicleno;
    String s1,s2,s3,s4,ss1,ss2,ss3,ss4,ss5,ss6,ss7,ss8;


    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase1;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    boolean clicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_bikes1);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        BK01=(Button)findViewById(R.id.button46);
        BK02=(Button)findViewById(R.id.button47);
        BK03=(Button)findViewById(R.id.button48);
        BK04=(Button)findViewById(R.id.button49);
        Proceed=(Button)findViewById(R.id.button58);


        mDatabase1 = firebaseDatabase.getInstance().getReference("Parking Slots 2");
        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ss1=dataSnapshot.child("Slots").child("BK01").getValue(String.class);
                if(ss1.equalsIgnoreCase("N")){
                    BK01.setEnabled(false);
                    BK01.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss2=dataSnapshot.child("Slots").child("BK02").getValue(String.class);
                if(ss2.equalsIgnoreCase("N")){
                    BK02.setEnabled(false);
                    BK02.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss3=dataSnapshot.child("Slots").child("BK03").getValue(String.class);
                if(ss3.equalsIgnoreCase("N")){
                    BK03.setEnabled(false);
                    BK03.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss4=dataSnapshot.child("Slots").child("BK04").getValue(String.class);
                if(ss4.equalsIgnoreCase("N")){
                    BK04.setEnabled(false);
                    BK04.setBackgroundColor(Color.parseColor("#ffcc0000"));
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
                    Intent intent = new Intent(NearByBikes1Activity.this, TimeActivity1.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    // Intent intent=new Intent(getApplicationContext(),TimeActivity1.class);
                    // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });




        BK01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s1=dataSnapshot.child("Slots").child("BK01").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s1.equalsIgnoreCase("Y")){
                            slot=BK01.getText().toString();
                         //   ProceedToTime();
                            AddParkingData();
                           // mDatabase.child("Slots").child("BK01").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot BK01 is selected",Toast.LENGTH_SHORT).show();
                        }


                        else{
                            BK01.setEnabled(false);
                            BK01.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                /*slot=BK01.getText().toString();
                ProceedToTime();
                AddParkingData();*/
            }
        });
        BK02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s2=dataSnapshot.child("Slots").child("BK02").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s2.equalsIgnoreCase("Y")){
                            slot=BK02.getText().toString();
                         //   ProceedToTime();
                            AddParkingData();
                            //mDatabase.child("Slots").child("BK02").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot BK02 is selected",Toast.LENGTH_SHORT).show();
                        }


                        else{
                            BK02.setEnabled(false);
                            BK02.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

               /* slot=BK02.getText().toString();
                ProceedToTime();
                AddParkingData();*/
            }
        });
        BK03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s3=dataSnapshot.child("Slots").child("BK03").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s3.equalsIgnoreCase("Y")){
                            slot=BK03.getText().toString();
                         //   ProceedToTime();
                            AddParkingData();
                           // mDatabase.child("Slots").child("BK03").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot BK03 is selected",Toast.LENGTH_SHORT).show();
                        }


                        else{
                            BK03.setEnabled(false);
                            BK03.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


              /*  slot=BK03.getText().toString();
                BK03.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();*/
                //ProceedToTime();
                //AddParkingData();
            }
        });
        BK04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s4=dataSnapshot.child("Slots").child("BK04").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s4.equalsIgnoreCase("Y")){
                            slot=BK04.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                         //   mDatabase.child("Slots").child("BK04").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot BK04 is selected",Toast.LENGTH_SHORT).show();
                        }


                        else{
                            BK04.setEnabled(false);
                            BK04.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

               /* slot=BK04.getText().toString();
                BK04.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();*/
                //ProceedToTime();
                //AddParkingData();
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


    public void AddParkingData() {
        //rg.AddData();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        try {
            DatabaseReference reference4 = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
            ParkingData2 pd2 = new ParkingData2(name, email, mobile, passwd, vehicleno,slot);
            reference4.child("slot").setValue(pd2);


        } catch (NullPointerException ignored) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.logoutMenu:{
                firebaseAuth.signOut();
                finish();
                Intent intent=new Intent(NearByBikes1Activity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;

            }
            case R.id.profileMenu:
                startActivity(new Intent(NearByBikes1Activity.this,ViewProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
