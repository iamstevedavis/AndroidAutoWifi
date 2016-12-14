package com.example.stephen.androidautowifi;

/**
 * Created by stephen on 11/12/16.
 */

public class MyTestReceiver {
    private static boolean isConnectedToWifi;
    private static WifiConnectionChange sListener;

    public interface WifiConnectionChange {
        void wifiConnected(boolean connected);
    }

    public static void setWifiConnected(boolean connected) {
        isConnectedToWifi = connected;
        if (sListener!=null)
        {
            sListener.wifiConnected(connected);
            sListener = null;
        }
    }

    public static void setWifiListener(WifiConnectionChange listener) {
        sListener = listener;
    }
}
