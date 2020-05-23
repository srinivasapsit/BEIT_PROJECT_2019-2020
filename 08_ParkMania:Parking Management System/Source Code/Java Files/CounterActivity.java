package com.example.admin.signup;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.R.attr.name;

public class CounterActivity extends AppCompatActivity {

    private String EVENT_DATE_TIME = "2019-10-21 10:30:00";
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private LinearLayout linear_layout_1, linear_layout_2;
    private TextView tv_days, tv_hour, tv_minute, tv_second;
    private Handler handler = new Handler();
    private Runnable runnable;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    public String etime,stime;
    public String str;
    public int i,fi_min,cal_min;
    public int hours,mins,seconds;
    public long min;
    public TextView tv;
    public String inputSlot;
    public Button Cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        tv=(TextView)findViewById(R.id.tv12);
        Cancel=(Button)findViewById(R.id.button50);


        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        DatabaseReference databaseReference1=firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        databaseReference.child("Time Duration").addValueEventListener(new ValueEventListener() {
            //databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ParkingData5 parkingData5 = null;

                //ParkingData3 parkingData3 = dataSnapshot.child("Date & Time Slot").getValue(ParkingData3.class);

                try {
                    //for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // parkingData5 = snapshot.getValue(ParkingData5.class);
                    parkingData5 = dataSnapshot.getValue(ParkingData5.class);
                    //}

                }catch (DatabaseException ignored){

                }
                try{
                    hours = parkingData5.getHours();
                    mins = parkingData5.getMins();
                    seconds = parkingData5.getSeconds();}
                catch(NullPointerException ignored){}
                System.out.println("Hours in call:"+hours);
                System.out.println("Mins in call:"+mins);
                System.out.println("Seconds in call:"+seconds);

                //System.out.println("parkingData3.getDate(): "+parkingData3.getDate());


                cal_min = ((hours * 60) + mins);
                /*Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String currDate = df.format(c);
                System.out.println("Current Date => " + currDate);

                if(parkingData3.getDate().compareTo(currDate)!=0){
                    cal_min = ((hours * 60) + mins);
                }
                else if(parkingData3.getDate().compareTo(currDate)==0) {
                    Integer c_min;
                    try {
                         c_min = ((parkingData5.getHours() * 60) + parkingData5.getMins());
                    } catch (Exception e) {
                         c_min = 0;
                    }
                    Integer im_min = (hours * 60) + mins;
                    cal_min = (im_min + c_min - im_min);
                }
                else{
                    System.out.println("Invalid Date Comparison");
                }*/

                System.out.println("Cal min: "+cal_min);
                //fi_min=Integer.parseInt(cal_min);
                min=cal_min*60*1000;
                //min=fi_min*60*1000;
                counter(min);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        System.out.println("Hours in onc:"+hours);
        System.out.println("Mins in onc:"+mins);
        System.out.println("Seconds in onc:"+seconds);

    }
    int seconds_ct;
    int minutes_ct;
    int hours_ct;
    private void counter(final long min) {
        final CountDownTimer timer = new CountDownTimer(min, 1000) {
            public void onTick(long millisUntilFinished) {
                seconds_ct = (int) (millisUntilFinished / 1000) % 60;
                minutes_ct = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                hours_ct = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);
                tv.setText(String.format("%d:%d:%d", hours_ct, minutes_ct, seconds_ct));

                //if(minutes_ct == 2 && seconds_ct==0 ){
                if(minutes_ct == 15 && seconds_ct==0 ){
                    DisplayNotification();
                }
/*Cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        cancel();
        mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots 2");
        mDatabase.child("Slots").child(inputSlot).setValue("Y");
        Toast.makeText(getApplicationContext(),"Your booking has been cancelled",Toast.LENGTH_SHORT).show();
    }
});*/
                //if(minutes_ct == 1 && seconds_ct==0){
                // cancel();
                //   }

                //if(minutes_ct == 0 && seconds_ct==2){
                //  if(minutes_ct == 1 && seconds_ct==0){
                if(minutes_ct == 0 && seconds_ct==2){

                    cancel();
                    mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots 2");
                    mDatabase.child("Slots").child(inputSlot).setValue("Y");
                    //DisplayNotification();
                    //  firebaseAuth.signOut();
                    //  finish();


                /*   Intent intent=new Intent(CounterActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
*/
                    //finish();

                    //finish();
                    //onPause();
                    //moveTaskToBack(true);

                    //finish();
                    // firebaseAuth.signOut();
                    //  Intent intent=new Intent(CounterActivity.this,MainActivity.class);
                    // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    //  startActivity(intent);

                    // finish();

                    // mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots 2");
                    //   mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots 3");
                    // mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots");
                    //mDatabase.child("Slots").child(inputSlot).setValue("SY");
                    //cancel();
                    // firebaseAuth.signOut();
                    //  finish();
                    // Intent intent=new Intent(CounterActivity.this,ExtendDisplayAll.class);
                    // startActivity(intent);
                    //finish();
                    //DisplayNotification();
                    //   firebaseAuth.signOut();
                    // cancel();
                }

               /* if(minutes_ct == 1 && seconds_ct==0){
                    DisplayNotification();
                    cancel();
                }*/


                //My <code></code>

                //mDatabase=firebaseDatabase.getInstance().getReference("Parking Slots 2");

                /*if(hours==0 && minutes==0 && seconds==1) {
                    mDatabase.child("Slots").child("A02").setValue("Y");
                }*/
                //  timerComplete();

            }

            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Your time has been completed",
                        Toast.LENGTH_LONG).show();
                //timerComplete();
                System.out.println("finish starts:");
              /*  mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.child("Slots").child(inputSlot).setValue("Y");
                //DisplayNotification();
               firebaseAuth.signOut();*/
                //finish();
                //Intent intent=new Intent(CounterActivity.this,MainActivity.class);
                //startActivity(intent);



                //mDatabase1.child("Slots").child("A02").setValue("AVAILABLE");
                //timer.cancel();
                System.out.println("After finish");
            }
        };
        timerComplete();
        timer.start();
        System.out.println("seconds_ct: "+seconds_ct);
        System.out.println("minutes_ct: "+minutes_ct);

    }



    public void timerComplete() {
        //FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference uidreference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        uidreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ParkingData2 parkingData2 = dataSnapshot.child("slot").getValue(ParkingData2.class);
                inputSlot = parkingData2.getSlot();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

   /* protected void onPause(){
        super.onResume();
        Toast.makeText(getApplicationContext(), "onPause called", Toast.LENGTH_LONG).show();
    }
protected void onStop(){
    super.onStop();  // Always call the superclass method first
    Toast.makeText(getApplicationContext(), "onStop called", Toast.LENGTH_LONG).show();
}*/

    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"You cannot press back from here",Toast.LENGTH_SHORT).show();
    }

    private void DisplayNotification(){
        long[] v = {500,1000};

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent= new Intent(this,Main1Activity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,(int) System.currentTimeMillis(),intent,0);
        // NotificationCompat notificationCompat=new NotificationCompat(MainActivity.this, CHANNEL_ID);
        Notification notification=new Notification.Builder(this).setSmallIcon(R.drawable.ic_notification).setSound(soundUri).setVibrate(v).setContentTitle("Want to stay here for more?").setContentText("Book Again").setContentIntent(pendingIntent).build();
        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notification.flags |=Notification.FLAG_AUTO_CANCEL;
        // notification.defaults |= Notification.DEFAULT_SOUND;


        notificationManager.notify(0, notification);
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
                Intent intent=new Intent(CounterActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(CounterActivity.this,ViewProfileActivity.class));
                break;
            case R.id.QRCode:
                startActivity(new Intent(CounterActivity.this,QRGenerator.class));
                break;
            case R.id.CheckTrans:
                startActivity(new Intent(CounterActivity.this,CheckLastTransaction.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
