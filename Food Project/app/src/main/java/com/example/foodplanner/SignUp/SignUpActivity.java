package com.example.foodplanner.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.DBConnection.reomtlydatabase.FireBaseConnection;
import com.example.foodplanner.Helper.CheckConnection;
import com.example.foodplanner.Login.Communication;
import com.example.foodplanner.Login.LoginActivity;
import com.example.foodplanner.Login.PresenterLogin;
import com.example.foodplanner.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements Communication {
    TextView login;
    TextInputEditText email, password, confirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        login = findViewById(R.id.logInID);
        email = findViewById(R.id.ti_email_signup);
        password = findViewById(R.id.ti_password_signup);
        confirmPassword = findViewById(R.id.ti_confirm_password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });


    }

    public void onClick(View view) {
        if (password.getText().toString().length() < 6) {
            password.setError("must be 6 characters");
            password.requestFocus();
        } else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            confirmPassword.setError("password must match");
            confirmPassword.requestFocus();

        } else if (!patternMatches(email.getText().toString())) {
            email.setError("invalid email");
            email.requestFocus();

        } else {

            if (CheckConnection.isConnect(getApplicationContext())) {
                PresenterLogin presenterLogin = new PresenterLogin(new FireBaseConnection(), this);
                presenterLogin.signup(email.getText().toString(), password.getText().toString());
            } else {
                Snackbar.make(email, "No Internet Connection", Snackbar.LENGTH_LONG)
                        .show();
            }
        }
    }

    @Override
    public void login() {
        finish();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(this, "email is invalid", Toast.LENGTH_SHORT).show();
    }

    public boolean patternMatches(String emailAddress) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern).matcher(emailAddress).find();
    }
}