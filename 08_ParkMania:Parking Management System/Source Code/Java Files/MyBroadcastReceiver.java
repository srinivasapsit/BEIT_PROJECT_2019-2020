package com.example.admin.signup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Admin on 11-04-2020.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        intent = new Intent(context.getApplicationContext(), CounterActivity.class);
        //PendingIntent.getBroadcast(context.getApplicationContext(),0,intent1,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        // PendingIntent.getActivity(context.getApplicationContext(),0,intent1,0);
        context.startActivity(intent);


        Toast.makeText(context.getApplicationContext(),"You have moved to Counter activity",Toast.LENGTH_SHORT).show();
    }
}
