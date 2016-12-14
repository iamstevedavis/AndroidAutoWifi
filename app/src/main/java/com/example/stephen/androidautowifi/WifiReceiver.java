package com.example.stephen.androidautowifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by stephen on 11/12/16.
 */

public class WifiReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("WifiReceiver", "Got here!");
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMan.getActiveNetworkInfo();
        if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI && netInfo.isConnected()) {
            Log.d("WifiReceiver", "Have Wifi Connection");
            MyTestReceiver.setWifiConnected(true);
        } else {
            Log.d("WifiReceiver", "Don't have Wifi Connection");
        }
    }
}
