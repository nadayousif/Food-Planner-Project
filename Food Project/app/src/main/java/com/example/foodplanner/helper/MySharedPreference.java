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

    public static void getSharedPreferences(Activity activity) {
        if (sharedPreferences == null)
            sharedPreferences = activity.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static void saveInShared(Activity activity, String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(activity.getString(R.string.userEmail), email);
        editor.apply();
    }

    public static boolean isLogin(Activity activity) {
        String email = sharedPreferences.getString(activity.getString(R.string.userEmail),"");
        return !email.isEmpty();


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


    public static String getListOfHistory(Activity activity) {
        String s = sharedPreferences.getString(activity.getString(R.string.historySearch), "");
        return s;
    }
     public static void saveInHistory(Activity activity,String history) {
         SharedPreferences.Editor editor = sharedPreferences.edit();
         editor.putString(activity.getString(R.string.historySearch), history);
         editor.apply();

    }
}
