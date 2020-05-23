package com.example.admin.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.admin.signup.R.id.radioButton4;
import static com.example.admin.signup.R.id.radioButton7;
import static com.example.admin.signup.R.id.radioVType;

public class TwoWheelerNearByZoneActivity extends AppCompatActivity {
    public Button Proceed;
    public RadioButton ZoneBK;
    private RadioGroup VehicleType;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    String vehicleType,zone,slot;
    String name,email,mobile,passwd,vehicleno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_wheeler_near_by_zone);

        firebaseAuth= FirebaseAuth.getInstance();
        //Proceed=(Button)findViewById(R.id.button41);
       // ZoneBK=(RadioButton)findViewById(R.id.radioButton7);
        addListener();


    }
    public void addListener(){

        VehicleType = (RadioGroup) findViewById(radioVType);
        Proceed=(Button)findViewById(R.id.button41);
    Proceed.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int selectedId = VehicleType.getCheckedRadioButtonId();
            ZoneBK = (RadioButton) findViewById(selectedId);
            try {
                zone = ZoneBK.getText().toString();


                Toast.makeText(getApplicationContext(),
                        ZoneBK.getText(), Toast.LENGTH_SHORT).show();

                if (ZoneBK.getId() == radioButton7) {
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
    if(!ZoneBK.isChecked()){

       Toast.makeText(getApplicationContext(),"Please select zone",Toast.LENGTH_SHORT).show();
    }
    else{

  Intent intent=new Intent(getApplicationContext(),NearByBikes1Activity.class);
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
                Intent intent=new Intent(TwoWheelerNearByZoneActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(TwoWheelerNearByZoneActivity.this,ViewProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
