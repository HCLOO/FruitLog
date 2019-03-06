package com.example.think.fruitlog.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.think.fruitlog.MyApplication;

public class SharedPreferencesUtil {

    private static final String TAG_PREFERENCE_NAME = "FruitAPP";
    public static final String TAG_PREFERENCE_LOCATION_LATITUDE="location_latitude";
    public static final String TAG_PREFERENCE_LOCATION_LONGITUDE="location_longitude";
    public static final String TAG_PREFERENCE_USERNAME="username";
    public static final String TAG_PREFERENCE_EMAIL="email";
    public static final String TAG_PREFERENCE_SEX="sex";
    public static final String TAG_PREFERENCE_LATEST_TAB_POSITION="latest_tab_position";

    public static void setStringPreference(Context context, String tag, String value) {
        SharedPreferences myPreference = context.getSharedPreferences(TAG_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myPreference.edit();
        editor.putString(tag, value);
        editor.commit();
    }

    public static String getStringPreference(Context context, String tag, String defaultValue) {
        SharedPreferences myPreference = context.getSharedPreferences(TAG_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return myPreference.getString(tag,defaultValue);
    }

    public static void setIntPreference(Context context, String tag, int value) {
        SharedPreferences myPreference = context.getSharedPreferences(TAG_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myPreference.edit();
        editor.putInt(tag, value);
        editor.commit();
    }

    public static int getIntPreference(Context context, String tag, int defaultValue) {
        SharedPreferences myPreference = context.getSharedPreferences(TAG_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return myPreference.getInt(tag,defaultValue);
    }

    public static void setMyLatitude(String la) {
        setStringPreference(MyApplication.getContext(), TAG_PREFERENCE_LOCATION_LATITUDE, la);
    }

    public static String getMyLatitude() {
        return getStringPreference(MyApplication.getContext(), TAG_PREFERENCE_LOCATION_LATITUDE, null);
    }

    public static void setMyLongitude(String lo) {
        setStringPreference(MyApplication.getContext(), TAG_PREFERENCE_LOCATION_LONGITUDE, lo);
    }

    public static String getMyLongitude() {
        return getStringPreference(MyApplication.getContext(), TAG_PREFERENCE_LOCATION_LONGITUDE, null);
    }

    public static void setPreferenceUsername(String username) {
        setStringPreference(MyApplication.getContext(), TAG_PREFERENCE_USERNAME, username);
    }

    public static String getPreferenceUsername() {
        return getStringPreference(MyApplication.getContext(), TAG_PREFERENCE_USERNAME, null);
    }

    public static void setPreferenceEmail(String email) {
        setStringPreference(MyApplication.getContext(), TAG_PREFERENCE_EMAIL, email);
    }

    public static String getPreferenceEmail() {
        return getStringPreference(MyApplication.getContext(), TAG_PREFERENCE_EMAIL, null);
    }

    public static void setPreferenceSex(String sex) {
        setStringPreference(MyApplication.getContext(), TAG_PREFERENCE_SEX, sex);
    }

    public static String getPreferenceSex() {
        return getStringPreference(MyApplication.getContext(), TAG_PREFERENCE_SEX, null);
    }

    public static void setTagPreferenceLatestTabPosition(int position) {
        setIntPreference(MyApplication.getContext(), TAG_PREFERENCE_LATEST_TAB_POSITION, position);
    }

    public static int getTagPreferenceLatestTabPosition() {
        return getIntPreference(MyApplication.getContext(), TAG_PREFERENCE_LATEST_TAB_POSITION, 0);
    }
}
