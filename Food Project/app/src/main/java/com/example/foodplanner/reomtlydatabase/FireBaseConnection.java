package com.example.foodplanner.reomtlydatabase;


import androidx.annotation.NonNull;

import com.example.foodplanner.Login.NetworkDelegate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FireBaseConnection implements DatabaseConnection {
     FirebaseAuth firebaseAuth = null;

    public FireBaseConnection() {
        this.firebaseAuth =FirebaseAuth.getInstance();
    }


    @Override
    public void login(String email, String password, NetworkDelegate networkDelegate) {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    networkDelegate.onSuccessful();
                } else {
                    networkDelegate.onFailure(task.getException().toString());
                }

            }
        });

    }
}
