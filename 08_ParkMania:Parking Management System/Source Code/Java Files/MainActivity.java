package com.example.admin.signup;




import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView ForgotPassword;
    private TextView Attempts;
    private Button Login;
    private int counter = 3;
    private TextView Signup;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    String name,passwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = (EditText) findViewById(R.id.editText);
        Password = (EditText) findViewById(R.id.editText3);
        Attempts = (TextView) findViewById(R.id.textView3);
        Login = (Button) findViewById(R.id.button);
        Signup = (TextView) findViewById(R.id.textView);
        ForgotPassword=(TextView)findViewById(R.id.textView32);
        Attempts.setText("No of attempts remaining: 3");
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(MainActivity.this);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            finish();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validate(Name.getText().toString(), Password.getText().toString());
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
                                      }
                                  });

        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ResetPasswordActivity.class);
                startActivity(intent);
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
                        Toast.makeText(MainActivity.this, "Login Unsuccessfull", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
       }else{
           Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
           firebaseAuth.signOut();
      }
        }





    }