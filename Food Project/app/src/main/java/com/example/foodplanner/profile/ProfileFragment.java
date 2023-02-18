package com.example.foodplanner.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.Welcome.WelcomeActivity;
import com.example.foodplanner.helper.MySharedPreference;
import com.example.foodplanner.helper.MyUser;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class ProfileFragment extends Fragment {
    Button logout;
    GoogleSignInClient gsc;
    GoogleSignInOptions gso;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,
                container, false);
        TextView textView=view.findViewById(R.id.tv_profile);
        textView.setText(MyUser.getInstance().getEmail());
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getContext(), gso);
        logout =  view.findViewById(R.id.b_logout);
        Button backup=view.findViewById(R.id.b_backup);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySharedPreference.clear(getActivity());
                gsc.signOut();
                LoginManager.getInstance().logOut();
                startActivity(new Intent(getActivity(),WelcomeActivity.class));
                getActivity().finish();

            }
        });


        backup.setOnClickListener(i->{


        });
        return view;


    }


}