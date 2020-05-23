package com.example.admin.signup;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {
    private static final Pattern VEHICLE_PATTERN=Pattern.compile("^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$");
    private EditText Name;
    private EditText Email;
    private EditText MobileNo;
    private EditText Password;
    private EditText ConfirmPassword;
    private Button Signup;
    private TextView Loggedin;
    private EditText VehicleNo;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private Spinner spinner;
    //private FirebaseDatabase firebaseDatabase;
    String name,email,mobile,passwd,cnfpasswd,vehicleno;
   // private Dialog otpdialog;
    String enPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        assign();
        firebaseAuth=FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(RegistrationActivity.this);

     //   spinner = (Spinner) findViewById(R.id.spinnerCountries);
      //  spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        /*otpdialog = new Dialog(RegistrationActivity.this);
        otpdialog.setContentView(R.layout.activity_otpauthentication);
        otpdialog.setCancelable(false);

*/
        Signup.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          if (validate()) {
                                              Intent intent = new Intent(RegistrationActivity.this, PhoneOTPActivity.class);
                                              intent.putExtra("Name", name);
                                              intent.putExtra("E-mail", email);
                                              intent.putExtra("Vehicle No", vehicleno);
                                              intent.putExtra("Password", passwd);
                                              startActivity(intent);
                                              firebaseAuth.signOut();
                                              finish();
                                          }
                                      }
       /*         String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

                // String mobile = editText.getText().toString().trim();
                mobile=MobileNo.getText().toString();




                name=Name.getText().toString();
                passwd=Password.getText().toString();
                cnfpasswd=ConfirmPassword.getText().toString();
                email=Email.getText().toString();
                //   mobile=MobileNo.getText().toString();
                vehicleno=VehicleNo.getText().toString();

                if(name.isEmpty() || passwd.isEmpty() || email.isEmpty() || mobile.isEmpty() || cnfpasswd.isEmpty() || vehicleno.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter all the details",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(getApplicationContext(),"Please enter valid email id.",Toast.LENGTH_SHORT).show();
                return;
                }
  *//* else if(!Patterns.PHONE.matcher(mobile).matches()){
            Toast.makeText(getApplicationContext(),"Please enter valid mobile no.",Toast.LENGTH_SHORT).show();
        }*//*

                else if (mobile.isEmpty() || mobile.length() < 10) {
                    MobileNo.setError("Valid number is required");
                    MobileNo.requestFocus();
                    return;
                }



                else if(mobile.length()<10 || mobile.length()>10){
                    Toast.makeText(getApplicationContext(),"Please enter valid mobile no.",Toast.LENGTH_SHORT).show();
                    return;
                }



                else if(!VEHICLE_PATTERN.matcher(vehicleno).matches()) {
                    Toast.makeText(getApplicationContext(),"Please enter valid vehicle no.",Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(passwd.length()<6 || passwd.length()>10){
                    Toast.makeText(getApplicationContext(),"Please enter valid password.",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!cnfpasswd.equals(passwd)){
                    Toast.makeText(getApplicationContext(),"Both passwords should match",Toast.LENGTH_SHORT).show();
                    return;
                }








           *//*  else if (mobile.isEmpty() || mobile.length() < 10) {
                    MobileNo.setError("Valid number is required");
                    MobileNo.requestFocus();
                    return;
                }*//*

                String phoneNumber = "+" + code + mobile;

               Intent intent = new Intent(RegistrationActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("phonenumber", phoneNumber);
                startActivity(intent);

                // }
*/

       /*         if(validate()){
                    progressDialog.setMessage("Please wait untill your account is being created");
                    progressDialog.show();

                    String user_email=Email.getText().toString().trim();
                    String user_password=Password.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                sendEmailVerification();

                              //  firebaseAuth.signOut();
                               // finish();
                              //  startActivity(new Intent(RegistrationActivity.this, PhoneOTPActivity.class));
                               // otpdialog.show();
                              //AddData();
                                // firebaseAuth.signOut();

                              //  Toast.makeText(getApplicationContext(),"Registration Successfull",Toast.LENGTH_SHORT).show();
                               // finish();
                                progressDialog.dismiss();
                                //startActivity(new Intent(RegistrationActivity.this,MainActivity.class));

                            } else{
                                Toast.makeText(getApplicationContext(),"Registration Unsuccessfull",Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });

                }

            }
        });*/
                                  });

        Loggedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ReturnToLogin();

            }
        });
    }


    private void assign(){
        Name=(EditText) findViewById(R.id.editText2);
        Email=(EditText) findViewById(R.id.editText4);
       // MobileNo=(EditText)findViewById(R.id.editText9);
        Password=(EditText) findViewById(R.id.editText5);
        VehicleNo=(EditText)findViewById(R.id.editText15);
        ConfirmPassword=(EditText)findViewById(R.id.editText10);
        Signup=(Button) findViewById(R.id.button2);
        Loggedin=(TextView) findViewById(R.id.textView6);



    }
    private Boolean validate(){
        Boolean result=false;
        name=Name.getText().toString();
        passwd=Password.getText().toString();
        cnfpasswd=ConfirmPassword.getText().toString();
        email=Email.getText().toString();
     //   mobile=MobileNo.getText().toString();
        vehicleno=VehicleNo.getText().toString();

        if(name.isEmpty() || passwd.isEmpty() || email.isEmpty() || cnfpasswd.isEmpty() || vehicleno.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(getApplicationContext(),"Please enter valid email id.",Toast.LENGTH_SHORT).show();
        }
  /* else if(!Patterns.PHONE.matcher(mobile).matches()){
            Toast.makeText(getApplicationContext(),"Please enter valid mobile no.",Toast.LENGTH_SHORT).show();
        }*/

      /*  else if (mobile.isEmpty() || mobile.length() < 10) {
            MobileNo.setError("Valid number is required");
            MobileNo.requestFocus();

        }*/



     /*   else if(mobile.length()<10 || mobile.length()>10){
            Toast.makeText(getApplicationContext(),"Please enter valid mobile no.",Toast.LENGTH_SHORT).show();
        }
*/


        else if(!VEHICLE_PATTERN.matcher(vehicleno).matches()) {
            Toast.makeText(getApplicationContext(),"Please enter valid vehicle no.",Toast.LENGTH_SHORT).show();
        }

        else if(passwd.length()<6 || passwd.length()>10){
            Toast.makeText(getApplicationContext(),"Please enter valid password.",Toast.LENGTH_SHORT).show();
        }
        else if(!cnfpasswd.equals(passwd)){
            Toast.makeText(getApplicationContext(),"Both passwords should match",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }
        return result;

    }


    private void ReturnToLogin(){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    public void AddData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        try {
            DatabaseReference reference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
            UserProfile userProfile = new UserProfile(mobile, name, email, passwd,vehicleno);
            //reference.setValue(userProfile);
            reference.child("User Profile").setValue(userProfile);
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
                        Toast.makeText(RegistrationActivity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                   //     startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                        Intent intent=new Intent(RegistrationActivity.this,PhoneOTPActivity.class);
                        intent.putExtra("Name",name);
                        intent.putExtra("E-mail",email);
                        intent.putExtra("Vehicle No",vehicleno);
                        intent.putExtra("Password",passwd);
                        startActivity(intent);
                        //startActivity(new Intent(RegistrationActivity.this, PhoneOTPActivity.class));
                    }else{
                        Toast.makeText(RegistrationActivity.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



}