package com.example.foodplanner.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.foodplanner.R;

public class MySharedPreference {
    private static SharedPreferences sharedPreferences = null;
    private final static String NAME = "MySharedPreferenceNA";

    private MySharedPreference() {


    }

    private static SharedPreferences getSharedPreferences(Activity activity) {
        if (sharedPreferences == null)
            sharedPreferences = activity.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public static void saveInShared(Activity activity, String email) {
        getSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(activity.getString(R.string.userEmail), email);
        editor.apply();
    }

    public static boolean isLogined(Activity activity) {
        String email = sharedPreferences.getString(activity.getString(R.string.userEmail),"");
        if(email.isEmpty())return false;
        else return true;

    }
    public static void clear(Activity activity){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(activity.getString(R.string.userEmail), "");
        editor.apply();
    }
    public static String getEmail(Activity activity) {
        String email = sharedPreferences.getString(activity.getString(R.string.userEmail),"");
        return email;

    }

}
