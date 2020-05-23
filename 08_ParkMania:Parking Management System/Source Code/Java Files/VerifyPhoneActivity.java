package com.example.admin.signup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends AppCompatActivity {
    EditText Name,Email,Vehicle,Password,Mobile;
    private TextView tv;
    private String verificationId;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private EditText editText;
    String name,email,mobile,passwd,cnfpasswd,vehicleno,mobileno,phonenumber;
    String MobileValue;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);
        Mobile=(EditText)findViewById(R.id.editText16);
        Name=(EditText)findViewById(R.id.editText17);
        Email=(EditText)findViewById(R.id.editText19);
        Vehicle=(EditText)findViewById(R.id.editText20);
        Password=(EditText)findViewById(R.id.editText23);

        name=getIntent().getExtras().getString("Name");
        Name.setText(name);

        email=getIntent().getExtras().getString("E-mail");
        Email.setText(email);

        vehicleno=getIntent().getExtras().getString("Vehicle No");
        Vehicle.setText(vehicleno);

        passwd=getIntent().getExtras().getString("Password");
        Password.setText(passwd);

        mobileno=getIntent().getExtras().getString("Mobile No");
        Mobile.setText(mobileno);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

//phonenumber=getIntent().getExtras().getString("phonenumber");
      //  tv=(TextView)findViewById(R.id.textView75);
        //mobileno=getIntent().getExtras().getString("Mobile No");

        //tv.setText(mobileno);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        editText = (EditText) findViewById(R.id.editTextCode);


        String phonenumber = getIntent().getStringExtra("phonenumber");
        sendVerificationCode(phonenumber);

        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = editText.getText().toString().trim();

                if (code.isEmpty() || code.length() < 6) {

                    editText.setError("Enter code...");
                    editText.requestFocus();
                    return;
                }
                verifyCode(code);
            }
        });

    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    //private void signInWithCredential(PhoneAuthCredential credential) {
    private void signInWithCredential(PhoneAuthCredential credential) {
        final String user_email=Email.getText().toString().trim();
        final String user_password=Password.getText().toString().trim();
        //firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new O)
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    sendEmailVerification();
                                    AddData();
                                }
                                else {
                                    Toast.makeText(VerifyPhoneActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });









/*
                        if (task.isSuccessful()) {
                            //sendEmailVerification();
                // AddData();
                            *//*Intent intent = new Intent(VerifyPhoneActivity.this, SuccessPageActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(intent);*//*
                           firebaseAuth.signOut();
                            finish();
                            Intent intent = new Intent(VerifyPhoneActivity.this, SuccessPageActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(intent);

                          AddData();
                         //   firebaseAuth.signOut();
                         //   finish();

                            Toast.makeText(getApplicationContext(),"Registration Successfull",Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(VerifyPhoneActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }*/
                    }
                });
    }

    private void sendVerificationCode(String number) {
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                editText.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyPhoneActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    public void AddData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        try {
            DatabaseReference reference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
           UserProfile userProfile=new UserProfile(mobileno,name,email,passwd,vehicleno);
            reference.setValue(userProfile);
          //  reference.setValue(userProfile);
            //ParkingData9 pd9 = new ParkingData9(mobile, name, email, passwd,vehicleno,mobileno);
            //reference.setValue(pd9);
        }catch (NullPointerException ignored){

        }
    }


    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        // sendUserData();
                        Toast.makeText(VerifyPhoneActivity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        //     startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                        Intent intent=new Intent(VerifyPhoneActivity.this,SuccessPageActivity.class);
                        intent.putExtra("Name",name);
                        intent.putExtra("E-mail",email);
                        intent.putExtra("Vehicle No",vehicleno);
                        intent.putExtra("Password",passwd);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        //startActivity(new Intent(RegistrationActivity.this, PhoneOTPActivity.class));
                    }else{
                        Toast.makeText(VerifyPhoneActivity.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}
