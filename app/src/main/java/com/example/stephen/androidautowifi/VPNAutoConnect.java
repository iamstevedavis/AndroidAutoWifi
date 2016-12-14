package com.example.stephen.androidautowifi;

import android.app.IntentService;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by stephen on 11/12/16.
 */

public class VPNAutoConnect extends IntentService {
    public VPNAutoConnect() {
        super("VPNAutoConnect");
    }

    @Override
    public void onCreate() {
        MyTestReceiver.setWifiListener(new MyTestReceiver.WifiConnectionChange()
        {
            @Override
            public void wifiConnected(boolean connected)
            {
                connectToVPN();
            }
        });
        Log.i("VPNAutoConnect", "Got to onCreate!");
        super.onCreate();
    }

    protected void test() {
        WifiManager wifiMgr = (WifiManager) this.getSystemService(this.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();

        String wirelessNetworkName = wifiInfo.getSSID();
        Log.d("BSSID ==> ", wirelessNetworkName);
    }

    public void connectToVPN() {
        Intent openVPN = new Intent("android.intent.action.VIEW");
        openVPN.setPackage("net.openvpn.openvpn");
        openVPN.setClassName("net.openvpn.openvpn", "net.openvpn.openvpn.OpenVPNClient");
        openVPN.putExtra("net.openvpn.openvpn.AUTOSTART_PROFILE_NAME", "us-east");
        openVPN.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openVPN);
    }

    public void disconnectVPN() {
        Intent openVPN = new Intent("android.intent.action.VIEW");
        openVPN.setPackage("net.openvpn.openvpn");
        openVPN.setClassName("net.openvpn.openvpn", "net.openvpn.openvpn.OpenVPNDisconnect");
        openVPN.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openVPN);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("VPNAutoConnect", "Service running");
        Log.d("VPNAutoConnect", "Got here!");
        Bundle mybundle = intent.getExtras();
        Log.d("Did this work?", mybundle.get("foo").toString());
    }
}
