package com.example.foodplanner.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.google.gson.Gson;

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
        String email = sharedPreferences.getString(activity.getString(R.string.userEmail), "");
        return !email.isEmpty();


    }

    public static void clear(Activity activity) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(activity.getString(R.string.userEmail), "");
        editor.apply();
    }

    public static String getEmail(Activity activity) {
        String email = sharedPreferences.getString(activity.getString(R.string.userEmail), "");
        return email;

    }


    public static String getListOfHistory(Activity activity) {
        String s = sharedPreferences.getString(activity.getString(R.string.historySearch), "");
        return s;
    }

    public static void saveInHistory(Activity activity, String history) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(activity.getString(R.string.historySearch), history);
        editor.apply();

    }

    public static void saveMeal(Activity activity, Meal meal) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(activity.getString(R.string.MealOfDay), new Gson().toJson(meal));
        editor.putString(activity.getString(R.string.arrayImageMealOfDay), new Gson().toJson(meal.getImage()));
        editor.apply();

    }
    public static Meal getMeal(Activity activity) {
        String s=sharedPreferences.getString(activity.getString(R.string.MealOfDay),"");
        if (!s.isEmpty())
            return new Gson().fromJson(s, Meal.class);
        else
            return null;

    }


}
