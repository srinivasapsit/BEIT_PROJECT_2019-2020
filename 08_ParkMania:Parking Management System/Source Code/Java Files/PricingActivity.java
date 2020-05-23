package com.example.admin.signup;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.datatype.Duration;


public class PricingActivity extends AppCompatActivity {
    private TextView Stime,Etime,TimeDuration,Price,PricingInfo;
    private Button Proceed;
    String name, email, mobile, passwd,vehicleno;
    String stime,etime;
   // String TDuration;
    String hour,min;
    int hours,mins,seconds;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    //String stime,etime;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
   // private DatabaseReference mDatabase;
   // private static boolean isScreenShotTaken = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricing);
        //Stime=(TextView)findViewById(R.id.textView39);
        //Etime=(TextView)findViewById(R.id.textView40);
        TimeDuration = (TextView) findViewById(R.id.textView38);

        //TDuration=TimeDuration.getText().toString();
        Price=(TextView)findViewById(R.id.textView35);
        Proceed=(Button)findViewById(R.id.button39);
       // PricingInfo=(TextView)findViewById(R.id.textView39);

//        PricingInfo.setOnClickListener(new View.OnClickListener() {
  //          @Override
    //        public void onClick(View v) {
      //          startActivity(new Intent(getApplicationContext(),PricingInfoActivity.class));
        //    }
        //});

        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),PaymentOptionsActivity.class));
                startActivity(new Intent(getApplicationContext(),DisplayAllInfo.class));
                //startActivity(new Intent(getApplicationContext(),QRGenerator.class));
                AddParkingData();


            }
        });

        //Pricing=(EditText)findViewById(R.id.editText13);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        databaseReference.addChildEventListener(new ChildEventListener() {




            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {

                Log.d("Child Event,Verse", "onChildChanged:" + dataSnapshot.getKey());
                // String pd3=dataSnapshot.geValue().toString();
                //ParkingData3 parkingData3=dataSnapshot.getValue(ParkingData3.class);

               // if (!isScreenShotTaken) {

                //ParkingData5 parkingData5 = dataSnapshot.child("Time Duration").getValue(ParkingData5.class);



                ParkingData3 parkingData3=null;
                   try {

                       System.out.println("Before Parking Data3");
                       parkingData3 = dataSnapshot.getValue(ParkingData3.class);
                   }catch (DatabaseException ignored){

                   }
               /* DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);

                String startTime = "08:00 AM";
                String endTime = "04:00 PM";

                LocalTime start = LocalTime.parse(startTime, timeFormatter);
                LocalTime end = LocalTime.parse(endTim         Duration diff = Duration.between(start, end);

                long hours = diff.toHours();
                long minutes = diff.minusHours(hours).toMinutes();
                String totalTimeString = String.format("%02d:%02d", hours, minutes);
                System.out.println("TotalTime in Hours and Mins Format is " + totalTimeString);
*/
               /* Timer updateTimer = new Timer();
                updateTimer.schedule(new TimerTask() {
                    public void run() {*/
                        System.out.println("Before try");
                        //Date date1 =null;
                        try {
                            System.out.println("Start Time in try: " + parkingData3.getStime());
                            System.out.println("End Time in try: " + parkingData3.getEtime());

                            /*Stime.setText("Start Time: " + parkingData3.getStime());
                            Etime.setText("End Time: " + parkingData3.getEtime());
*/
                          /*  SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
                            Date edate = format.parse(parkingData3.getEtime());
                            //Toast.makeText(getApplicationContext(),edate.toString(),Toast.LENGTH_SHORT).show();
                            Date sdate = format.parse(parkingData3.getStime())*/
                            ;
                          /* calendar = Calendar.getInstance();
                            currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                            currentMinute = calendar.get(Calendar.MINUTE);*/
                            SimpleDateFormat format = new SimpleDateFormat("hh:mm");
                            System.out.println("Etime: " + parkingData3.getEtime());
                            System.out.println("Stime: " + parkingData3.getStime());

                            Date date1 = format.parse(parkingData3.getEtime());
                            Date date2 = format.parse(parkingData3.getStime());
                            /*Date date1 = format.parse("12:05");
                            Date date2 = format.parse("12:01");*/
                            //Toast.makeText(getApplicationContext(),sdate.toString(),Toast.LENGTH_SHORT).show();
                            long mills = date1.getTime() - date2.getTime();
                            // Toast.makeText(getApplicationContext(),mills,Toast.LENGTH_SHORT).show();
                            /*Log.v("Mills", "" + mills);
                            Log.v("Data1", "" + date1.getTime());
                            Log.v("Data2", "" + date2.getTime());*/


                           /* try{
                                hours = (int) (mills / (1000 * 60 * 60));
                                hours = parkingData5.getHours() + hours;
                                mins = (int) (mills / (1000 * 60)) % 60;
                                mins = parkingData5.getMins() + mins;
                                seconds = (int) mills / 1000 % 60;
                                seconds = parkingData5.getSeconds() + seconds;
                            }

                            catch(Exception e){*/
                                hours = (int) (mills / (1000 * 60 * 60));
                                mins = (int) (mills / (1000 * 60)) % 60;
                                seconds = (int) mills / 1000 % 60;
                        //}

                            String diff = hours + ":" + mins; // updated value every1 second
                            if(hours<0 || mins<0){
                                TimeDuration.setText("Invalid Time Duration");
                                Price.setText("Price Not Applicable");
                                //PricingInfo.setEnabled(false);
                                Proceed.setEnabled(false);
                            }

/*
else if((hours<=currentHour)&&(mins<=currentMinute)){
    TimeDuration.setText("False Time Duration");
    Price.setText("Price Not Applicable");
    //PricingInfo.setEnabled(false);
    Proceed.setEnabled(false);
}
*/
                           /*else if(hours==0 && mins<30){
                                TimeDuration.setText("Oops! Minimum Time Duration is 30 mins");
                                Price.setText("Price Not Applicable");
                                //PricingInfo.setEnabled(false);
                                Proceed.setEnabled(false);
                            }
*/


                            else {

                                TimeDuration.setText("Time Duration: " + hours + " hours " + mins + " mins " + seconds + " seconds ");

                                int price;
                                if ((hours<1 && mins<=59) || (hours==1 && mins==0)){price=30;
                                    Price.setText("Price: "+price+ " Rs ");}
                                else if ((hours>=1) && ((hours<=3 && mins<=59)
                                        || (hours==4 && mins==0)))
                                {   price=60;
                                    Price.setText("Price: "+price+ " Rs ");}
                                else if ((hours>=4) && ((hours<=7 && mins<=59) ||
                                        (hours==8 && mins==0))){price=90;
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


                   // } catch(NullPointerException n){
//System.out.println("Caught exception: "+n);
                   // }
                   // isScreenShotTaken = true;
                }





            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {



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

       // databaseReference.addValueEventListener(new ValueEventListener() {
           // @Override
          //  public void onDataChange(DataSnapshot dataSnapshot) {
            //    ParkingData3 parkingData3=dataSnapshot.getValue(ParkingData3.class);
            //    Stime.setText("Start Time: " + parkingData3.getStime());
             //   Etime.setText("End Time: " + parkingData3.getEtime());
                //TimeDuration.setText(parkingData3.getEtime()-parkingData3.getStime());
              /*  try
                {

                    SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
                    Date edate = format.parse(parkingData3.getEtime());
                    //Toast.makeText(getApplicationContext(),edate.toString(),Toast.LENGTH_SHORT).show();
                    Date sdate = format.parse(parkingData3.getStime());
                    //Toast.makeText(getApplicationContext(),sdate.toString(),Toast.LENGTH_SHORT).show();
                    long mills = edate.getTime() - sdate.getTime();
                   // Toast.makeText(getApplicationContext(),mills,Toast.LENGTH_SHORT).show();
                    Log.v("Mills", ""+mills);
                    Log.v("Data1", ""+edate.getTime());
                    Log.v("Data2", ""+sdate.getTime());
                    int hours = (int) (mills/(1000 * 60 * 60));
                    int mins = (int) (mills/(1000*60)) % 60;

                    String diff = hours + ":" + mins; // updated value every1 second
                    TimeDuration.setText("Diff: " + diff);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }*/
            }


    // @Override
         //   public void onCancelled(DatabaseError databaseError) {
             //   Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
    public void AddParkingData() {
        //rg.AddData();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference5 = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        ParkingData5 pd5 = new ParkingData5(name, email, mobile, passwd,vehicleno, hours , mins, seconds);
        reference5.child("Time Duration").setValue(pd5);


        //reference5.child("Time Duration").setValue(hours+":"+mins+":"+seconds);
      /*  reference5.child("Time Duration").child("Hours").setValue(hours);
        reference5.child("Time Duration").child("Minutes").setValue(mins);
        reference5.child("Time Duration").child("Seconds").setValue(seconds);*/

    }





    /*CountDownTimer cTimer = null;

    //start timer function
    void startTimer() {
        cTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
            }
        };
        cTimer.start();
    }

    //cancel timer
    void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
    }
*/
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
                Intent intent=new Intent(PricingActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(PricingActivity.this,ViewProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }




            }










