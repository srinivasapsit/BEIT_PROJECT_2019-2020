package com.example.admin.signup;

import android.content.Intent;
import android.support.annotation.CheckResult;
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

import static com.example.admin.signup.R.id.radioButton;
import static com.example.admin.signup.R.id.radioButton2;
import static com.example.admin.signup.R.id.radioVType;

public class ThirdActivity extends AppCompatActivity {
    public RadioButton Wheeler;
    public RadioGroup VehicleType;
    public Button Proceed;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
   String vehicleType,Zone,slot;
    String name,email,mobile,passwd,vehicleno;
    //RegistrationActivity rg=new RegistrationActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        firebaseAuth=FirebaseAuth.getInstance();
        addListener();
    }
    public void addListener(){
        VehicleType=(RadioGroup)findViewById(radioVType);

        //fourWheeler = (RadioButton) findViewById(R.id.radioButton2);

        Proceed = (Button) findViewById(R.id.button6);
        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = VehicleType.getCheckedRadioButtonId();
                Wheeler=(RadioButton)findViewById(selectedId);
                vehicleType=Wheeler.getText().toString();
                Toast.makeText(getApplicationContext(),
                        Wheeler.getText(), Toast.LENGTH_SHORT).show();
                if(Wheeler.getId()==radioButton){
                    OnProceedToTwoWheeler();
                    AddParkingData();
                }else if(Wheeler.getId()==radioButton2){
                    OnProceedToFourWheeler();
                    AddParkingData();
                }
                else{return;}




                //OnProceedToTwoWheeler();
            }
        });
    }
        //fourWheeler.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //OnProceedToFourWheeler();



    private void OnProceedToTwoWheeler() {
        //if (twoWheeler.isChecked()) {
            Intent intent = new Intent(getApplicationContext(), FourthActivity.class);
            startActivity(intent);
        } //else {
            //OnProceedToFourWheeler();
            //Toast.makeText(getApplicationContext(), "Please select something", Toast.LENGTH_SHORT).show();



    private void OnProceedToFourWheeler() {
        //if (fourWheeler.isChecked()) {
            Intent intent = new Intent(getApplicationContext(), Car2Activity.class);
            startActivity(intent);
        } //else {
           // Toast.makeText(getApplicationContext(), "Please select something", Toast.LENGTH_SHORT).show();
        //}


    /*public void AddParkingData(){
        DatabaseReference reference=null;

        try{
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
            firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
            ParkingData pd=new ParkingData(name,email,mobile,passwd,vehicleType);
            reference.setValue(pd);

        }catch (NullPointerException ignored){

        //name=Name.getText().toString();



    }*/
    public void AddParkingData(){
        //rg.AddData();
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference reference1= firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
            ParkingData pd=new ParkingData(name,email,mobile,passwd,vehicleno,vehicleType);

       // reference.setValue(rg);
        //reference.push().setValue(rg);
        /*reference1.child("name").setValue(pd);
        reference1.child("email").setValue(pd);
        reference1.child("mobile").setValue(pd);
        reference1.child("passwd").setValue(pd);*/
        reference1.child("vehicleType").setValue(pd);


        }

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
                Intent intent=new Intent(ThirdActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(ThirdActivity.this,ViewProfileActivity.class));
                break;

            case R.id.CheckTrans:
                startActivity(new Intent(ThirdActivity.this,CheckLastTransaction.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }




}


