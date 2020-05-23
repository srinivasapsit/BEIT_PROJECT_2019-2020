package com.example.admin.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Main1Activity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Attempts;
    Button Login;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    String name,passwd;
    private DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase;
    public String inputSlot;
    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        Name = (EditText) findViewById(R.id.editText12);
        Password = (EditText) findViewById(R.id.editText13);
        Login = (Button) findViewById(R.id.button8);
        Attempts=(TextView)findViewById(R.id.textView60);
  Attempts.setText("No of attempts remaining: 3");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(Main1Activity.this);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            finish();
            startActivity(new Intent(Main1Activity.this, ExtendTimeSlot.class));
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Validate(Name.getText().toString(), Password.getText().toString());

            }
        });




}
    private void Validate(String userName, String userPassword) {
        //if(userName.isEmpty() || userPassword.isEmpty())
        //{
        //  Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
        // }

        if (validate()) {
            progressDialog.setMessage("Please wait untill you are logged-in");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        // Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        // startActivity(intent);
                        checkEmailVerification();

                    } else {
                        Toast.makeText(Main1Activity.this, "Login Unsuccessfull", Toast.LENGTH_SHORT).show();
                        counter--;
                        progressDialog.dismiss();
                        Attempts.setText("No of remaining attempts:" + String.valueOf(counter));
                        if (counter == 0) {
                            Login.setEnabled(false);
                        }
                    }
                }
            });

        }
    }
    private Boolean validate(){
        Boolean result=false;
        name=Name.getText().toString();
        passwd=Password.getText().toString();
        if(name.isEmpty() || passwd.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter all the details",Toast.LENGTH_SHORT).show();
        }else{
            result=true;
        }
        return result;

    }
    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        //startActivity(new Intent(MainActivity.this, SecondActivity.class));
        if(emailflag){
            finish();
            Toast.makeText(Main1Activity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Main1Activity.this, ExtendTimeSlot.class));
        }
     /*   else if(mDatabase.child("Slots").child(inputSlot).equals("Y")){
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
        */
        else{
            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
    public void DontLogin(){
        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ParkingData2 parkingData2 = dataSnapshot.child("slot").getValue(ParkingData2.class);
                inputSlot = parkingData2.getSlot();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase = firebaseDatabase.getInstance().getReference("Parking Slots 2");
        if(mDatabase.child("Slots").child(inputSlot).equals("Y")){
            Login.setEnabled(false);
        }
    }
}
