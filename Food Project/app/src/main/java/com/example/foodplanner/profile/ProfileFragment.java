package com.example.foodplanner.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodplanner.DBConnection.localdatabase.localdb.ConcreteLocalData;
import com.example.foodplanner.DBConnection.localdatabase.localdb.LocalDataSource;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.Welcome.WelcomeActivity;
import com.example.foodplanner.helper.MySharedPreference;
import com.example.foodplanner.helper.MyUser;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    private static final String TAG = "TAGG";
    Button logout;
    GoogleSignInClient gsc;
    GoogleSignInOptions gso;
    private DatabaseReference mDatabase;

    LocalDataSource localDataSource;

    // Write a message to the database


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,
                container, false);
        TextView textView = view.findViewById(R.id.tv_profile);
        textView.setText(MyUser.getInstance().getEmail());
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getContext(), gso);
        logout = view.findViewById(R.id.b_logout);
        Button backup = view.findViewById(R.id.b_backup);
        localDataSource= ConcreteLocalData.getInstance(getContext());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySharedPreference.clear(getActivity());
                gsc.signOut();
                LoginManager.getInstance().logOut();
                startActivity(new Intent(getActivity(), WelcomeActivity.class));
                getActivity().finish();

            }
        });
// ...
        /*mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        myRef.setValue("nada");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/


        backup.setOnClickListener(i -> {
//            localDataSource.deleteAll(MyUser.getInstance().getEmail());
            if (MyUser.getInstance().isLogin())
                FirebaseDataBase.readData(getContext());
        });


        return view;


    }


}