package com.example.think.fruitlog.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

public class SettingUtil {
    public static boolean checkLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders=null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            Log.e("locationProviders:",locationProviders);
            return locationProviders.contains("gps");
        }
    }
}
