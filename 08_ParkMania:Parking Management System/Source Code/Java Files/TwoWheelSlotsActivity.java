package com.example.admin.signup;

import android.app.AlarmManager;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TwoWheelSlotsActivity extends AppCompatActivity {
    public Button A01;
    private Button A02;
    private Button A03;
    private Button A04;
    private Button A05;
    private Button A06;
    private Button A07;
    private Button A08;
    private Button Proceed;
    String vehicleType, zone, slot;
    String slot1,slot2,slot3,slot4,slot5,slot6,slot7,slot8;
    String name, email, mobile, passwd,vehicleno;
    String s1,s2,s3,s4,s5,s6,s7,s8,ss1,ss2,ss3,ss4,ss5,ss6,ss7,ss8;

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase1;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DataSnapshot dataSnapshot;
    boolean clicked=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_wheel_slots);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        A01=(Button)findViewById(R.id.btn1);
        A02=(Button)findViewById(R.id.button15);
        A03=(Button)findViewById(R.id.button16);
        A04=(Button)findViewById(R.id.button17);
        A05=(Button)findViewById(R.id.button18);
        A06=(Button)findViewById(R.id.button19);
        A07=(Button)findViewById(R.id.button20);
        A08=(Button)findViewById(R.id.button21);
        Proceed=(Button)findViewById(R.id.button23);

       mDatabase1 = firebaseDatabase.getInstance().getReference("Parking Slots 2");
       mDatabase1.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               ss1=dataSnapshot.child("Slots").child("A01").getValue(String.class);
               if(ss1.equalsIgnoreCase("N")){
                   A01.setEnabled(false);
                   A01.setBackgroundColor(Color.parseColor("#ffcc0000"));
               }
               ss2=dataSnapshot.child("Slots").child("A02").getValue(String.class);
               if(ss2.equalsIgnoreCase("N")){
                   A02.setEnabled(false);
                   A02.setBackgroundColor(Color.parseColor("#ffcc0000"));
               }
               ss3=dataSnapshot.child("Slots").child("A03").getValue(String.class);
               if(ss3.equalsIgnoreCase("N")){
                   A03.setEnabled(false);
                   A03.setBackgroundColor(Color.parseColor("#ffcc0000"));
               }
               ss4=dataSnapshot.child("Slots").child("A04").getValue(String.class);
               if(ss4.equalsIgnoreCase("N")){
                   A04.setEnabled(false);
                   A04.setBackgroundColor(Color.parseColor("#ffcc0000"));
               }
               ss5=dataSnapshot.child("Slots").child("A05").getValue(String.class);
               if(ss5.equalsIgnoreCase("N")){
                   A05.setEnabled(false);
                   A05.setBackgroundColor(Color.parseColor("#ffcc0000"));
               }
               ss6=dataSnapshot.child("Slots").child("A06").getValue(String.class);
               if(ss6.equalsIgnoreCase("N")){
                   A06.setEnabled(false);
                   A06.setBackgroundColor(Color.parseColor("#ffcc0000"));
               }
               ss7=dataSnapshot.child("Slots").child("A07").getValue(String.class);
               if(ss7.equalsIgnoreCase("N")){
                   A07.setEnabled(false);
                   A07.setBackgroundColor(Color.parseColor("#ffcc0000"));
               }
               ss8=dataSnapshot.child("Slots").child("A08").getValue(String.class);
               if(ss8.equalsIgnoreCase("N")){
                   A08.setEnabled(false);
                   A08.setBackgroundColor(Color.parseColor("#ffcc0000"));
               }
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });

        /*DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ParkingData10 parkingData10=dataSnapshot.child()
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (validate()) {
                    Intent intent = new Intent(TwoWheelSlotsActivity.this, TimeActivity1.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    // Intent intent=new Intent(getApplicationContext(),TimeActivity1.class);
                    // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
          }
        });
        //mDatabase= FirebaseDatabase.getInstance().getReference();


/*try {
    DatabaseReference reference = firebaseDatabase.getReference("Parking Slots 2");
    reference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            s1 = dataSnapshot.child("Slots").child("A01").getValue(String.class);
            if (s1.equalsIgnoreCase("N")) {
                A01.setEnabled(false);
                A01.setBackgroundColor(Color.parseColor("#ffcc0000"));
                Toast.makeText(getApplicationContext(), "Slot is booked", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}catch (NullPointerException ignored){

}*/





        A01.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       clicked=true;

                                       mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                                       mDatabase.addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(DataSnapshot dataSnapshot) {
                                               s1=dataSnapshot.child("Slots").child("A01").getValue(String.class);
                                               // System.out.println("S1 value is :"+s1);

                                               if(s1.equalsIgnoreCase("Y")){
                                                   slot=A01.getText().toString();
                                                  // ProceedToTime();
                                                 //  A01.setEnabled(true);
                                                   AddParkingData();
                                                   Toast.makeText(getApplicationContext(),"Slot A01 is selected",Toast.LENGTH_SHORT).show();

                                                   //  mDatabase.child("Slots").child("A07").setValue("N");
                                               }
                                               else{
                                                   A01.setEnabled(false);
                                                   A01.setBackgroundColor(Color.parseColor("#ffcc0000"));
                                                   Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();}
                                           }

                                           @Override
                                           public void onCancelled(DatabaseError databaseError) {

                                           }
                                       });
               /* slot=A07.getText().toString();
                ProceedToTime();
                AddParkingData();*/
                                       //A07.setEnabled(false);
                                       //Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();
                                   }
        });
                                     /*  try {
                                           DatabaseReference reference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
                                           reference.addValueEventListener(new ValueEventListener() {
                                               @Override
                                               public void onDataChange(DataSnapshot dataSnapshot) {
                                                   final ParkingData3 parkingData3 = dataSnapshot.child("Date & Time Slot").getValue(ParkingData3.class);

                                                   Date c = Calendar.getInstance().getTime();
                                                   System.out.println("Current time => " + c);
                                                   SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                                   final String currDate = df.format(c);
                                                   System.out.println("Current Date => " + currDate);
                                                   SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                                                   String strTime = sdf.format(new Date());
                                                   System.out.println("Current 24-hr time => " + strTime);

                                                   mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots 2");
                                                   //md start
                                                   mDatabase.addValueEventListener(new ValueEventListener() {
                                                       @Override
                                                       public void onDataChange(DataSnapshot dataSnapshot) {


                                                           s1 = dataSnapshot.child("Slots").child("A01").getValue(String.class);
                                                           //ss1=dataSnapshot.child("slot").child("A01").getValue(String.class);
                                                           //  ss1=dataSnapshot.child("slot").child("A01").getValue(String.class);
                                                           System.out.println("S1 value is :" + s1);
                                                           if (s1.equalsIgnoreCase("Y")) {
                                                               slot = A01.getText().toString();
                                                               if (parkingData3.getDate().compareTo(currDate) < 0) {
                                                                   System.out.println("Hello in If");
                                                                   AddParkingData();
                                                                   ProceedToTime();

                                                               } else {
                                                                   System.out.println("Hello in Else");
                                                                   Intent intent = new Intent(TwoWheelSlotsActivity.this, MainActivity.class);
                                                                   startActivity(intent);
                                                                   firebaseAuth.signOut();
                                                                   finish();
                                                               }
                                                               //mDatabase.child("Slots").child("A01").setValue("N");
                                                               System.out.println("In A01 if: ");

                                                           }


                                                           else {
                                                               System.out.println("In A01 else: ");
                                                               A01.setEnabled(false);
                                                               A01.setBackgroundColor(Color.parseColor("#ffcc0000"));
                                                               //  A01.setBackgroundColor(Color.parseColor("#fff17a0a"));
                                                               Toast.makeText(getApplicationContext(), "Slot is booked", Toast.LENGTH_SHORT).show();
                                                           }
                                                       }

                                                       @Override
                                                       public void onCancelled(DatabaseError databaseError) {

                                                       }
                                                   });
                                                   //md end

                                               }//catch (NullPointerException ignored){}


                                               @Override
                                               public void onCancelled(DatabaseError databaseError) {

                                               }


                                               //  }catch (NullPointerException ignored){

                                               //}


                                               // mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots");

                                               //}
                                           });
                                       }catch (NullPointerException ignored){

                                       }*/


        A07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s7=dataSnapshot.child("Slots").child("A07").getValue(String.class);
                       // System.out.println("S1 value is :"+s1);
                        if(s7.equalsIgnoreCase("Y")){
                            slot=A07.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                            Toast.makeText(getApplicationContext(),"Slot A07 is selected",Toast.LENGTH_SHORT).show();

                            //  mDatabase.child("Slots").child("A07").setValue("N");
                        }
                        else{
                            A07.setEnabled(false);
                            A07.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
               /* slot=A07.getText().toString();
                ProceedToTime();
                AddParkingData();*/
                //A07.setEnabled(false);
                //Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();
            }
        });

        A08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s8=dataSnapshot.child("Slots").child("A08").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s8.equalsIgnoreCase("Y")){
                            slot=A08.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                            Toast.makeText(getApplicationContext(),"Slot A08 is selected",Toast.LENGTH_SHORT).show();

                            // mDatabase.child("Slots").child("A08").setValue("N");
                        }
                        else{
                            A08.setEnabled(false);
                            A08.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

               /* slot=A08.getText().toString();
                ProceedToTime();
                AddParkingData();*/
                //A08.setEnabled(false);
                //Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();
            }
        });


        A02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      s2 = dataSnapshot.child("Slots").child("A02").getValue(String.class);
                        if (s2.equalsIgnoreCase("Y")) {
                            slot = A02.getText().toString();

                           // ProceedToTime();
                            AddParkingData();
                            Toast.makeText(getApplicationContext(),"Slot A02 is selected",Toast.LENGTH_SHORT).show();

                            //  mDatabase.child("Slots").child("A02").setValue("N");

                        }
/*
                        else if(s2.equalsIgnoreCase("SY")){
                            slot = A02.getText().toString();
                            ProceedToTime();
                            AddParkingData();
                        }
*/

                        /*if (s2.equals("AVAILABLE")) {
                            slot = A02.getText().toString();
                            ProceedToTime();
                            AddParkingData();
                            mDatabase.child("Slots").child("A02").setValue("N");
                            A02.setEnabled(false);
                            A02.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(), "Slot is booked", Toast.LENGTH_SHORT).show();
                        }*/


                        /*else if(s2.equals("TEST")){
                            slot=A02.getText().toString();
                            ProceedToTime();
                            AddParkingData();
                            mDatabase.child("Slots").child("A02").setValue("N");

                        }*/

                   else

                    {
                        A02.setEnabled(false);
                        A02.setBackgroundColor(Color.parseColor("#ffcc0000"));
                       Toast.makeText(getApplicationContext(), "Slot is booked", Toast.LENGTH_SHORT).show();
                    }
                }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
               /* mDatabase1=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });*/





            }
        });
        A03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        s3=dataSnapshot.child("Slots").child("A03").getValue(String.class);
                        if(s3.equalsIgnoreCase("Y")){
                            slot=A03.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                            Toast.makeText(getApplicationContext(),"Slot A03 is selected",Toast.LENGTH_SHORT).show();

                            //mDatabase.child("Slots").child("A03").setValue("N");

                        }
                        else{
                            A03.setEnabled(false);
                            A03.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();}
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
     /*   if(!A04.isPressed()){
            A04.setBackgroundColor(Color.parseColor("#FF5C9F30"));
        }
        else{
            A04.setBackgroundColor(Color.parseColor("#ffcc0000"));
        }*/
       /* if(!A04.isPressed())
        {
            A04.setBackgroundColor(Color.parseColor("#ffcc0000"));
        }*/

        A04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;

                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        s4=dataSnapshot.child("Slots").child("A04").getValue(String.class);
                        if(s4.equalsIgnoreCase("Y")){
                            slot=A04.getText().toString();
                           // ProceedToTime();
                            AddParkingData();
                            Toast.makeText(getApplicationContext(),"Slot A04 is selected",Toast.LENGTH_SHORT).show();

                            //  mDatabase.child("Slots").child("A04").setValue("N");


                        }
                       /* else if(!A04.isPressed()){
                            A04.setBackgroundColor(Color.parseColor("#ffcc0000"));
                        }*/
                        else{
                            A04.setEnabled(false);
                            A04.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();}
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        /*A04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               *//* slot=A04.getText().toString();
                ProceedToTime();
                AddParkingData();*//*
            }
        });*/
        A05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s5=dataSnapshot.child("Slots").child("A05").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s5.equalsIgnoreCase("Y")){
                            slot=A05.getText().toString();
                          //  ProceedToTime();
                            AddParkingData();
                            Toast.makeText(getApplicationContext(),"Slot A05 is selected",Toast.LENGTH_SHORT).show();

                            //  mDatabase.child("Slots").child("A05").setValue("N");

                        }


                        else{
                            A05.setEnabled(false);
                            A05.setBackgroundColor(Color.parseColor("#ffcc0000"));
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

               /* slot=A05.getText().toString();
                ProceedToTime();
                AddParkingData();*/
            }
        });

        A06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s6=dataSnapshot.child("Slots").child("A06").getValue(String.class);
                        //System.out.println("S1 value is :"+s1);
                        if(s6.equalsIgnoreCase("Y")){
                            slot=A06.getText().toString();
                            //Toast.makeText(getApplicationContext(),"Select Date & Time",Toast.LENGTH_SHORT).show();
                           // ProceedToTime();
                            AddParkingData();
                            Toast.makeText(getApplicationContext(),"Slot A06 is selected",Toast.LENGTH_SHORT).show();

                            //mDatabase.child("Slots").child("A06").setValue("N");
                        }

                        else{
                          //  Toast.makeText(getApplicationContext(),"Select Date & Time",Toast.LENGTH_SHORT).show();

                           A06.setEnabled(false);
                            A06.setBackgroundColor(Color.parseColor("#ffcc0000"));
                           // Toast.makeText(getApplicationContext(),"Select Date & Time",Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"Slot is booked",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                /*slot=A06.getText().toString();
                ProceedToTime();
                AddParkingData();*/
            }
        });
//query
      /*  A06.setEnabled(false);
        A06.setBackgroundColor(Color.parseColor("#ffcc0000"));
        Toast.makeText(getApplicationContext(),"Slot is occupied",Toast.LENGTH_SHORT).show();*/
        //mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots");

      /*  mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                checkStatus(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
    private void ProceedToTime() {
            Intent intent = new Intent(TwoWheelSlotsActivity.this, TimeActivity1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // Intent intent=new Intent(getApplicationContext(),TimeActivity1.class);
            // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            //finish();
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

       }catch (NullPointerException ignored){

       }
   }


/*public void onBackPressed(){
    mDatabase.child("Slots").child("A01").setValue("Y");
}*/



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
                Intent intent=new Intent(TwoWheelSlotsActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(TwoWheelSlotsActivity.this,ViewProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}


