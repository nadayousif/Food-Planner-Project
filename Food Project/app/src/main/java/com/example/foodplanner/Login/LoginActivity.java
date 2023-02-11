package com.example.foodplanner.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.Helper.CheckConnection;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.SignUp.SignUpActivity;
import com.example.foodplanner.DBConnection.reomtlydatabase.FireBaseConnection;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements Communication {
    TextView signup;
    TextInputEditText email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signup = findViewById(R.id.newAccount);
        email = findViewById(R.id.ti_email);
        password = findViewById(R.id.ti_passwrod);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    public boolean patternMatches(String emailAddress) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern).matcher(emailAddress).find();
    }

    public void onClick(View view) {
        if (password.getText().toString().length() < 6) {
            password.setError("must be 6 characters");
            password.requestFocus();
        } else if (!patternMatches(email.getText().toString())) {
            email.setError("invalid email");
            email.requestFocus();

        } else {

            if (CheckConnection.isConnect(getApplicationContext())) {
                PresenterLogin presenterLogin = new PresenterLogin(new FireBaseConnection(), this);
                presenterLogin.login(email.getText().toString(), password.getText().toString());
            } else {
                Snackbar.make(email, "No Internet Connection", Snackbar.LENGTH_LONG)
                        .show();
            }
        }


    }

    @Override
    public void login() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.userEmail), email.getText().toString());
        editor.apply();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(this, "email or password are invalid", Toast.LENGTH_SHORT).show();

    }

}