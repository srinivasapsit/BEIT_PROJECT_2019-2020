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

import static com.example.admin.signup.R.id.radioButton3;
import static com.example.admin.signup.R.id.radioButton4;
import static com.example.admin.signup.R.id.radioVType;

public class FourthActivity extends AppCompatActivity {
    private RadioButton Zone;
    private RadioGroup VehicleType;
    private Button Proceed;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    String vehicleType,zone,slot;
    String name,email,mobile,passwd,vehicleno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        firebaseAuth=FirebaseAuth.getInstance();
        addListener();
    }
    public void addListener() {
        VehicleType = (RadioGroup) findViewById(radioVType);

        //fourWheeler = (RadioButton) findViewById(R.id.radioButton2);

        Proceed = (Button) findViewById(R.id.button4);
        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = VehicleType.getCheckedRadioButtonId();
                Zone = (RadioButton) findViewById(selectedId);
                zone=Zone.getText().toString();
                Toast.makeText(getApplicationContext(),
                        Zone.getText(), Toast.LENGTH_SHORT).show();
                if (Zone.getId() == radioButton4) {
                    OnProceedToTwoWheelerSlots();
                    AddParkingData();
                }
                else if(Zone.getId()==radioButton3) {
                    OnProceedToZoneB();
                    AddParkingData();
                }
                 else{
                    Toast.makeText(getBaseContext(),"Please select a particular zone",Toast.LENGTH_SHORT).show();
                    return;}
                }




                //OnProceedToTwoWheeler();
            }
        );
    }
    private void OnProceedToTwoWheelerSlots(){
        Intent intent=new Intent(getApplicationContext(),TwoWheelSlotsActivity.class);
        startActivity(intent);

    }
    private void OnProceedToZoneB(){
        Intent intent=new Intent(getApplicationContext(),ZoneBActivity.class);
        startActivity(intent);
    }

    public void AddParkingData(){
        //rg.AddData();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference reference2= firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        ParkingData1 pd1=new ParkingData1(name,email,mobile,passwd,vehicleno,zone);
        reference2.child("zone").setValue(pd1);

}

/*    @Override
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
                Intent intent=new Intent(FourthActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(FourthActivity.this,ViewProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }








}
