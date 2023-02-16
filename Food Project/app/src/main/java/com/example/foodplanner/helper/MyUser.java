package com.example.foodplanner.helper;



public class MyUser {

    private String email;
    private static MyUser instance = null;

    private MyUser() {
        this.email="";

    }

    public static synchronized MyUser getInstance() {
        if (instance == null) {
            instance = new MyUser();
        }

        return instance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public boolean isLogin() {
        return !email.isEmpty();
    }
}
