package com.example.admin.signup;

import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtendDisplayAll extends AppCompatActivity {
    public Button ConfirmOrder;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabase;
    TextView vType,zone,slot,stime,etime,date;
    String s1,s2,s3,s4,s5,s6,s7,s8;
   // private Button A01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_display_all);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        ConfirmOrder = (Button) findViewById(R.id.button11);
        vType = (TextView) findViewById(R.id.textView66);
        zone = (TextView) findViewById(R.id.textView67);
        slot = (TextView) findViewById(R.id.textView68);
        date=(TextView)findViewById(R.id.textView69);
        stime = (TextView) findViewById(R.id.textView71);
        etime = (TextView) findViewById(R.id.textView72);

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
                date.setText("Date: " + parkingData3.getDate());
                stime.setText("Stime: " + parkingData3.getStime());
                etime.setText("Etime: " + parkingData3.getEtime());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots 2");
              //  mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        try {
                            DatabaseReference uidreference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
                            uidreference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    ParkingData2 parkingData2 = dataSnapshot.child("slot").getValue(ParkingData2.class);
                                    ParkingData3 parkingData3 = dataSnapshot.child("Date & Time Slot").getValue(ParkingData3.class);
                                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                                    String currTime = sdf.format(new Date());
                                    System.out.println("Current 24-hr time in ExtendedDisplayInfo => " + currTime);
                                    //String stime = parkingData3.getStime().substring(0,parkingData3.getStime().length()-2);
                                    String stime = parkingData3.getStime();
                                    System.out.println("Parking Data3 time ExtendedDisplayInfo =>" + stime);
                                     if (currTime.compareTo(stime)==0){
                                    //if (currTime.compareTo(stime)>=0){

                                    Intent intent = new Intent(ExtendDisplayAll.this, ExtendedCounterActivity.class);
                                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    mDatabase.child("Slots").child(parkingData2.getSlot()).setValue("N");
                                    // startActivity(new Intent(getApplicationContext(), ExtendedCounterActivity.class));
                                }
                                       // mDatabase.child("slot").child(parkingData2.getSlot()).setValue("EN");
                                //    }
                                  else{
                                        Toast.makeText(getApplicationContext(), "Timer will start at the specified time", Toast.LENGTH_SHORT).show();
                                  }

                                    //startActivity(new Intent(getApplicationContext(), CounterActivity.class));
                                    //mDatabase.child("Slots").child(parkingData2.getSlot()).setValue("N");
                                }


                              @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }


                            });
                        }catch (NullPointerException ignored){

                        }

                    }
                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }

                    });

                  /*  @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }*/

            }

    });
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
                Intent intent=new Intent(ExtendDisplayAll.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                //startActivity(new Intent(TimeActivity1.this,MainActivity.class));
                //onBackPressed();
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(ExtendDisplayAll.this,ViewProfileActivity.class));
                break;
            case R.id.QRCode:
                startActivity(new Intent(ExtendDisplayAll.this,QRGenerator.class));
                break;
            case R.id.CheckTrans:
                startActivity(new Intent(ExtendDisplayAll.this,CheckLastTransaction.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
