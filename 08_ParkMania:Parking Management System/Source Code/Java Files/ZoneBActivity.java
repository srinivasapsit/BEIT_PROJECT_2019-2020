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

public class ZoneBActivity extends AppCompatActivity {
    private Button B01;
    private Button B02;
    private Button B03;
    private Button B04;
    private Button B05;
    private Button B06;
    private Button B07;
    private Button B08;

    String s1,s2,s3,s4,s5,s6,s7,s8;

    String vehicleType, zone, slot;
    String name, email, mobile, passwd,vehicleno;
    String ss1,ss2,ss3,ss4,ss5,ss6,ss7,ss8;
    private Button Proceed;

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase1;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    boolean clicked=false;

    //private DatabaseReference mDatabase;
    //private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_b);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        B01=(Button)findViewById(R.id.button13);
        B02=(Button)findViewById(R.id.button14);
        B03=(Button)findViewById(R.id.button22);
        B04=(Button)findViewById(R.id.button24);
        B05=(Button)findViewById(R.id.button25);
        B06=(Button)findViewById(R.id.button26);
        B07=(Button)findViewById(R.id.button27);
        B08=(Button)findViewById(R.id.button28);
        Proceed=(Button)findViewById(R.id.button50);
       // mDatabase= FirebaseDatabase.getInstance().getReference();



        mDatabase1 = firebaseDatabase.getInstance().getReference("Parking Slots 2");
        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ss1=dataSnapshot.child("Slots").child("B01").getValue(String.class);
                if(ss1.equalsIgnoreCase("N")){
                    B01.setEnabled(false);
                    B01.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss2=dataSnapshot.child("Slots").child("B02").getValue(String.class);
                if(ss2.equalsIgnoreCase("N")){
                    B02.setEnabled(false);
                    B02.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss3=dataSnapshot.child("Slots").child("B03").getValue(String.class);
                if(ss3.equalsIgnoreCase("N")){
                    B03.setEnabled(false);
                    B03.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss4=dataSnapshot.child("Slots").child("B04").getValue(String.class);
                if(ss4.equalsIgnoreCase("N")){
                    B04.setEnabled(false);
                    B04.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss5=dataSnapshot.child("Slots").child("B05").getValue(String.class);
                if(ss5.equalsIgnoreCase("N")){
                    B05.setEnabled(false);
                    B05.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss6=dataSnapshot.child("Slots").child("B06").getValue(String.class);
                if(ss6.equalsIgnoreCase("N")){
                    B06.setEnabled(false);
                    B06.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss7=dataSnapshot.child("Slots").child("B07").getValue(String.class);
                if(ss7.equalsIgnoreCase("N")){
                    B07.setEnabled(false);
                    B07.setBackgroundColor(Color.parseColor("#ffcc0000"));
                }
                ss8=dataSnapshot.child("Slots").child("B08").getValue(String.class);
                if(ss8.equalsIgnoreCase("N")){
                    B08.setEnabled(false);
                    B08.setBackgroundColor(Color.parseColor("#ffcc0000"));
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
                    Intent intent = new Intent(ZoneBActivity.this, TimeActivity1.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    // Intent intent=new Intent(getApplicationContext(),TimeActivity1.class);
                    // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });


        B01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s1=dataSnapshot.child("Slots").child("B01").getValue(String.class);
                        // System.out.println("S1 value is :"+s1);
                        if(s1.equalsIgnoreCase("Y")){
                            slot=B01.getText().toString();
                            //ProceedToTime();
                            AddParkingData();
                           // mDatabase.child("Slots").child("B01").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot B01 is selected",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            B01.setEnabled(false);
                            B01.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                /*slot=B01.getText().toString();
                ProceedToTime();
                AddParkingData();*/
            }
        });
        B02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s2=dataSnapshot.child("Slots").child("B02").getValue(String.class);
                        // System.out.println("S1 value is :"+s1);
                        if(s2.equalsIgnoreCase("Y")){
                            slot=B02.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                           // mDatabase.child("Slots").child("B02").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot B02 is selected",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            B02.setEnabled(false);
                            B02.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

             /*   slot=B02.getText().toString();
                ProceedToTime();
                AddParkingData();*/
            }
        });
        B05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s5=dataSnapshot.child("Slots").child("B05").getValue(String.class);
                        // System.out.println("S1 value is :"+s1);
                        if(s5.equalsIgnoreCase("Y")){
                            slot=B05.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                            //mDatabase.child("Slots").child("B05").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot B05 is selected",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            B05.setEnabled(false);
                            B05.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

             /*   slot=B05.getText().toString();
                ProceedToTime();
                AddParkingData();*/
            }
        });

        B07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s7=dataSnapshot.child("Slots").child("B07").getValue(String.class);
                        // System.out.println("S1 value is :"+s1);
                        if(s7.equalsIgnoreCase("Y")){
                            slot=B07.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                           // mDatabase.child("Slots").child("B07").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot B07 is selected",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            B07.setEnabled(false);
                            B07.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

               /* slot=B07.getText().toString();
                ProceedToTime();
                AddParkingData();*/

            }
        });


        B08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s8=dataSnapshot.child("Slots").child("B08").getValue(String.class);
                        // System.out.println("S1 value is :"+s1);
                        if(s8.equalsIgnoreCase("Y")){
                            slot=B08.getText().toString();
                         //   ProceedToTime();
                            AddParkingData();
                            //mDatabase.child("Slots").child("B08").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot B08 is selected",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            B08.setEnabled(false);
                            B08.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                /*B08.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();*/
            }
        });

       // B02.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
                //mDatabase.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue("A02");
             //   ProceedToTime();
          //  }
      //  });
        B03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s3=dataSnapshot.child("Slots").child("B03").getValue(String.class);
                        // System.out.println("S1 value is :"+s1);
                        if(s3.equalsIgnoreCase("Y")){
                            slot=B03.getText().toString();
                         //   ProceedToTime();
                            AddParkingData();
                           // mDatabase.child("Slots").child("B03").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot B03 is selected",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            B03.setEnabled(false);
                            B03.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                /*B03.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();*/
            }
        });
        B04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s4=dataSnapshot.child("Slots").child("B04").getValue(String.class);
                        // System.out.println("S1 value is :"+s1);
                        if(s4.equalsIgnoreCase("Y")){
                            slot=B04.getText().toString();
                         //   ProceedToTime();
                            AddParkingData();
                           // mDatabase.child("Slots").child("B04").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot B04 is selected",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            B04.setEnabled(false);
                            B04.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

             /*   slot=B04.getText().toString();
                ProceedToTime();
                AddParkingData();*/
            }
        });
        B06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s6=dataSnapshot.child("Slots").child("B06").getValue(String.class);
                        // System.out.println("S1 value is :"+s1);
                        if(s6.equalsIgnoreCase("Y")){
                            slot=B06.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                          //  mDatabase.child("Slots").child("B06").setValue("N");
                            Toast.makeText(getApplicationContext(),"Slot B06 is selected",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            B06.setEnabled(false);
                            B06.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

               /* B06.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();*/
            }
        });

        //B07.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View v) {
            //    ProceedToTime();
           // }
       // });

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

    private void ProceedToTime(){
        Intent intent=new Intent(getApplicationContext(),TimeActivity1.class);
        startActivity(intent);
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
           ParkingData2 pd2 = new ParkingData2(name, email, mobile, passwd,vehicleno, slot);
           reference5.child("slot").setValue(pd2);
       } catch (NullPointerException ignored) {

       }
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
                Intent intent=new Intent(ZoneBActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(ZoneBActivity.this,ViewProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}

