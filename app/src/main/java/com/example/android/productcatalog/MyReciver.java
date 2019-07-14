package com.example.android.productcatalog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyReciver extends BroadcastReceiver {
    public boolean connection ;
    public int reload=0;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    @Override
    public void onReceive(Context context, Intent intent) {
        if(checkInternet(context))
        {
            Toast.makeText(context, "Network Available",Toast.LENGTH_LONG).show();
            connection=true;
            if(reload==1) {
                Intent startIntent = new Intent(context, MainActivity.class);
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(startIntent);
            }

        }
        else
        {
            Toast.makeText(context, "Network Unvailable ,check your connection",Toast.LENGTH_LONG).show();
            reload=1;
            connection=false;
        }
    }

    boolean checkInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }

}