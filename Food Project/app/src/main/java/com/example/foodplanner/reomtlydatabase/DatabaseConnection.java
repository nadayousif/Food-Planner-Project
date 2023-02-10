package com.example.foodplanner.reomtlydatabase;

import android.app.Activity;

import com.example.foodplanner.Login.NetworkDelegate;


public interface DatabaseConnection {

    void login(String email, String password, NetworkDelegate networkDelegate);


}
