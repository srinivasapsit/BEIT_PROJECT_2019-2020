package com.example.admin.signup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResetPasswordActivity extends AppCompatActivity {
    private EditText EmailForReset;
    private Button ResetPassword;
    private FirebaseAuth firebaseAuth;
    String name, email, mobile, passwd,vehicleno,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        firebaseAuth=FirebaseAuth.getInstance();
        EmailForReset=(EditText)findViewById(R.id.editText11);
        ResetPassword=(Button)findViewById(R.id.button38);

        ResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailforreset=EmailForReset.getText().toString();
                if(emailforreset.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter your registered email id",Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(emailforreset).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Password reset email sent",Toast.LENGTH_SHORT).show();
                                finish();
                                //AddParkingData();
                                startActivity(new Intent(ResetPasswordActivity.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Error in sending password reset email",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }


   /* public void AddParkingData() {
        //rg.AddData();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        try {
            DatabaseReference reference6 = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
            ParkingData6 pd6 = new ParkingData6(name, email, mobile, passwd, reset);
            reference6.child("Reset Password").setValue(pd6);

        }catch (NullPointerException ignored){

        }
    }*/
}
