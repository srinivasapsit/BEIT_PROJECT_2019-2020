package com.example.admin.signup;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PhoneOTPActivity extends AppCompatActivity {

    private EditText Name,Email,Vehicle,Password;
    String name,email,mobile,passwd,cnfpasswd,vehicleno,mobileno;
    private Spinner spinner;
    private EditText editText;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_otp);

        Name=(EditText)findViewById(R.id.editText18);
        Email=(EditText)findViewById(R.id.editText21);
        Vehicle=(EditText)findViewById(R.id.editText22);
        Password=(EditText)findViewById(R.id.editText24);

        name=getIntent().getExtras().getString("Name");
        Name.setText(name);

        email=getIntent().getExtras().getString("E-mail");
        Email.setText(email);

        vehicleno=getIntent().getExtras().getString("Vehicle No");
        Vehicle.setText(vehicleno);

        passwd=getIntent().getExtras().getString("Password");
        Password.setText(passwd);

        firebaseAuth=FirebaseAuth.getInstance();
        spinner = (Spinner) findViewById(R.id.spinnerCountries);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        editText = (EditText) findViewById(R.id.editTextPhone);

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();

                String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

                String mobileno = editText.getText().toString().trim();

                if (mobileno.isEmpty() || mobileno.length() < 10) {
                    Toast.makeText(getApplicationContext(),"Valid number is required",Toast.LENGTH_SHORT).show();
                    //  editText.setError("Valid number is required");
                   // editText.requestFocus();
                    //return;
                }

                String phoneNumber = "+" + code + mobileno;

                Intent intent = new Intent(PhoneOTPActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("phonenumber", phoneNumber);
                intent.putExtra("Mobile No",mobileno);
                intent.putExtra("Name",name);
                intent.putExtra("E-mail",email);
                intent.putExtra("Vehicle No",vehicleno);
                intent.putExtra("Password",passwd);
                startActivity(intent);
                System.out.print("Mobile number outside loop is:" + mobileno);
                //AddData();

            }
        });
    }
    public void AddData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        try {
            DatabaseReference reference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
           UserProfile userProfile=new UserProfile(mobileno,name,email,passwd,vehicleno);
           reference.setValue(userProfile);
            //System.out.print("Mobile number is:" + mobileno);
            //ParkingData9 pd9 = new ParkingData9(mobile, name, email, passwd,vehicleno,mobileno);
            //reference.child("User Mobile").setValue(pd9);
        }catch (NullPointerException ignored){

        }
    }
    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, SuccessPageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
}
