package com.example.admin.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.admin.signup.R.id.radioButton71;
import static com.example.admin.signup.R.id.radioButton8;

public class Car2Activity extends AppCompatActivity {
    private RadioButton FourWheelerZone;
    private RadioGroup FourWheelerVehicle;
    private Button Proceed1;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    String vehicleType, zone, slot;
    String name, email, mobile, passwd,vehicleno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car2);
        firebaseAuth = FirebaseAuth.getInstance();
        addListner1();
    }

    private void addListner1() {
        FourWheelerVehicle = (RadioGroup) findViewById(R.id.radioVehicleType);
        Proceed1 = (Button) findViewById(R.id.button71);
        Proceed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectid1 = FourWheelerVehicle.getCheckedRadioButtonId();
                FourWheelerZone = (RadioButton) findViewById(selectid1);
                zone = FourWheelerZone.getText().toString();
                Toast.makeText(getApplicationContext(), FourWheelerZone.getText(), Toast.LENGTH_SHORT).show();
                if (FourWheelerZone.getId() == radioButton71) {
                    ProceedToFourWheelSlots();
                    AddParkingData();
                }else if(FourWheelerZone.getId()==radioButton8) {
                   OnProceedToZoneD();
                    AddParkingData();
                }
                else{

                    Toast.makeText(getApplicationContext(), "Select a zone", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void ProceedToFourWheelSlots() {
        Intent intent = new Intent(getApplicationContext(), FourWheelSlots1Activity.class);
        startActivity(intent);
    }
   private void OnProceedToZoneD() {
       Intent intent = new Intent(getApplicationContext(), ZoneDActivity.class);
       startActivity(intent);
   }



    public void AddParkingData(){
        //rg.AddData();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference3 = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        ParkingData1 pd1 = new ParkingData1(name, email, mobile, passwd,vehicleno,zone);
        reference3.child("zone").setValue(pd1);
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
                Intent intent=new Intent(Car2Activity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(Car2Activity.this,ViewProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }







}
