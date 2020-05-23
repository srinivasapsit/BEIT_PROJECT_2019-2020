package com.example.admin.signup;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ExtendedCounterActivity extends AppCompatActivity {
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
    public int hours1,mins1,seconds1;
    public long min;
    public TextView tv;
    public String inputSlot,TD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_counter);

        tv=(TextView)findViewById(R.id.textView63);
        // TD=tv.getText().toString();

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        databaseReference.child("Time Duration(Extended)").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ParkingData7 parkingData7 = null;

                //ParkingData3 parkingData3 = dataSnapshot.child("Date & Time Slot").getValue(ParkingData3.class);

                try {
                    //for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // parkingData5 = snapshot.getValue(ParkingData5.class);
                    parkingData7 = dataSnapshot.getValue(ParkingData7.class);
                    //}

                }catch (DatabaseException ignored){

                }

                try{
                    hours1 = parkingData7.getHours1();
                    mins1 = parkingData7.getMins1();
                    seconds1 = parkingData7.getSeconds1();}
                catch(NullPointerException ignored){}
                System.out.println("Hours in extended call:"+hours1);
                System.out.println("Mins in extended call:"+mins1);
                System.out.println("Seconds in extended call:"+seconds1);

                //System.out.println("parkingData3.getDate(): "+parkingData3.getDate());


                cal_min = ((hours1 * 60) + mins1);
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
    }


    private void counter(final long min) {
        final CountDownTimer timer = new CountDownTimer(min, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                //minutes = minutes + 1;
                int hours = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);
                tv.setText(String.format("%d:%d:%d", hours, minutes, seconds));

              /*  if(minutes == 0 && seconds==2){
                    mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots 2");
                    mDatabase.child("Slots").child(inputSlot).setValue("SY");
                    firebaseAuth.signOut();
                }
*/

            /*
           if(minutes == 1 && seconds==0){
                 //   DisplayNotification();
                    //   firebaseAuth.signOut();
                }
*/
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
                mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots 2");
                mDatabase.child("Slots").child(inputSlot).setValue("Y");
                //DisplayNotification();
                firebaseAuth.signOut();
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

    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"You cannot press back from here",Toast.LENGTH_SHORT).show();
    }

    private void DisplayNotification(){
        long[] v = {500,1000};

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent= new Intent(this,Main1Activity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,(int) System.currentTimeMillis(),intent,0);
        //Intent second_intent=new Intent(this,Main1Activity.class);

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
                Intent intent=new Intent(ExtendedCounterActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(ExtendedCounterActivity.this,ViewProfileActivity.class));
                break;
            case R.id.QRCode:
                startActivity(new Intent(ExtendedCounterActivity.this,QRGenerator.class));
                break;
            case R.id.CheckTrans:
                startActivity(new Intent(ExtendedCounterActivity.this,CheckLastTransaction.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
