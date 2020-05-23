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

import static com.example.admin.signup.R.id.radioButton;
import static com.example.admin.signup.R.id.radioButton13;
import static com.example.admin.signup.R.id.radioButton7;
import static com.example.admin.signup.R.id.radioVType;

public class FourWheelerNearByZoneActivity extends AppCompatActivity {
    public Button Proceed;
    public RadioButton ZoneCR;
    private RadioGroup VehicleType;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    String vehicleType,zone,slot;
    String name,email,mobile,passwd,vehicleno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_wheeler_near_by_zone);
        firebaseAuth= FirebaseAuth.getInstance();
        addListener();
    }
    public void addListener(){
        VehicleType = (RadioGroup) findViewById(radioVType);
        Proceed=(Button)findViewById(R.id.button45);
        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = VehicleType.getCheckedRadioButtonId();
                ZoneCR = (RadioButton) findViewById(selectedId);
                try {
                    zone = ZoneCR.getText().toString();


                    Toast.makeText(getApplicationContext(),
                            ZoneCR.getText(), Toast.LENGTH_SHORT).show();

                    if (ZoneCR.getId() == radioButton13) {
                        Check();
                        //Intent intent=new Intent(getApplicationContext(),NearByBikesActivity.class);
                        //startActivity(intent);
                        AddParkingData();
                    } else {
                        Toast.makeText(getBaseContext(), "Please select a particular zone", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }catch (NullPointerException ignored){

                }

            }
        });
            }
    public void AddParkingData(){
        //rg.AddData();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference reference2= firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        ParkingData1 pd1=new ParkingData1(name,email,mobile,passwd,vehicleno,zone);
        reference2.child("zone").setValue(pd1);

    }
    public void Check(){
        if(!ZoneCR.isChecked()){

            Toast.makeText(getApplicationContext(),"Please select zone",Toast.LENGTH_SHORT).show();
        }
        else{

            Intent intent=new Intent(getApplicationContext(),NearByCars1Activity.class);
            startActivity(intent);
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
                Intent intent=new Intent(FourWheelerNearByZoneActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(FourWheelerNearByZoneActivity.this,ViewProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    }

