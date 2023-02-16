package com.example.foodplanner.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.R;

public class SplashActivity extends AppCompatActivity {
LottieAnimationView food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        food = findViewById(R.id.splash);
        food.animate().translationX(2000).setDuration(2000).setStartDelay(2900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              Intent i = new Intent(getApplicationContext(), SecondSplashActivity.class);
              startActivity(i);
              finish();
            }
        },5000);
    }
}