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

import com.google.android.gms.maps.MapsInitializer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import static com.example.admin.signup.R.id.button9;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.admin.signup.R.id.intent_action;
import static com.example.admin.signup.R.id.textView;
import static com.example.admin.signup.R.id.textView6;
//import static com.example.admin.signup.R.id.textViewLog;

public class SecondActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databasereference;
    private DatabaseReference mDatabase;
    private Button BookASlot;
    private Button NearBy;
    private Button Nearest;
    Calendar calendar;
    Date date;
   // private Button Login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        BookASlot = (Button) findViewById(R.id.button3);
        //Login=(Button)findViewById(button9);
        NearBy = (Button) findViewById(R.id.button12);
        Nearest = (Button) findViewById(R.id.button40);
        firebaseAuth = FirebaseAuth.getInstance();

        Nearest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Maps2Activity.class));
            }
        });


        NearBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, MapsActivity.class));
            }
        });
        //Login.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //public void onClick(View v) {
        //  firebaseAuth.signOut();
        //finish();
        //startActivity(new Intent(SecondActivity.this,MainActivity.class));
        //}
        //});
        BookASlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToOtherPage();

           /*     DatabaseReference reference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ParkingData3 parkingData3 = dataSnapshot.child("Date & Time Slot").getValue(ParkingData3.class);

                        Date c = Calendar.getInstance().getTime();
                        System.out.println("Current time => " + c);
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate = df.format(c);
                        System.out.println("Current Date => " + strDate);
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        String strTime = sdf.format(new Date());
                        System.out.println("Current 24-hr time => " + strTime);

                        try {
                            System.out.println("Db Date " + parkingData3.getDate());
                            System.out.println("Db Time " + parkingData3.getEtime());

                            if (strDate.equalsIgnoreCase(parkingData3.getDate())) {
                                System.out.println("Within if strDate");
                                if (strTime.compareTo(parkingData3.getEtime()) <= 0) {
                                    System.out.println("Success");
                                    BookASlot.setEnabled(false);
                                   // firebaseAuth.signOut();
                                 //   finish();
                                    Toast.makeText(getApplicationContext(), "You have already booked one slot", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                GoToOtherPage();

                            }
                        }
                        catch(Exception e){
                            GoToOtherPage();

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
*/
                /*mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                       String s1 = dataSnapshot.child("Slots").child("A01").getValue(String.class);
                          if(s1.equalsIgnoreCase("SY")){
                              mDatabase.child("Slots").child("A01").setValue("Y");
                          }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
*/

             //  GoToOtherPage();
            }
        });
    }
       //   GoToOtherPage();

           /// }
     //   });
 //   }
    private void GoToOtherPage(){
        Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
   //  firebaseAuth.signOut();
    //  finish();
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
                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;

            }
           case R.id.profileMenu:
               startActivity(new Intent(SecondActivity.this,ViewProfileActivity.class));
               break;

            case R.id.QRCode:
                startActivity(new Intent(SecondActivity.this,QRGenerator.class));
                break;

            case R.id.CheckTrans:
                startActivity(new Intent(SecondActivity.this,CheckLastTransaction.class));
                break;

           /* case R.id.CheckTime:
                startActivity(new Intent(SecondActivity.this,CounterActivity.class));
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }


}
