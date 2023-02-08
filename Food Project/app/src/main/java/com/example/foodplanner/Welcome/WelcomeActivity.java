package com.example.foodplanner.Welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodplanner.Login.LoginActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.SignUp.SignUpActivity;

public class WelcomeActivity extends AppCompatActivity {
     Button signUP;
     TextView have_Account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        signUP=findViewById(R.id.signup);
        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });
        have_Account=findViewById(R.id.haveAccount);
        have_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

    }
}