package com.example.admin.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class PaymentOptionsActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);
        firebaseAuth = FirebaseAuth.getInstance();
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
                Intent intent=new Intent(PaymentOptionsActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                //startActivity(new Intent(TimeActivity1.this,MainActivity.class));
                //onBackPressed();
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(PaymentOptionsActivity.this,ViewProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
