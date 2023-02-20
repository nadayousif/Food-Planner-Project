package com.example.foodplanner;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplanner.Welcome.WelcomeActivity;
import com.example.foodplanner.databinding.ActivityMainBinding;
import com.example.foodplanner.helper.MySharedPreference;
import com.example.foodplanner.helper.MyUser;
import com.example.foodplanner.meal.MealActivity;
import com.example.foodplanner.plan.PlanFragment;
import com.example.foodplanner.plan.dialog.search.SearchDialog;
import com.example.foodplanner.profile.FirebaseDataBase;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
     public NavController navController;
    private ActivityMainBinding binding;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private String day;
    public NavHostFragment navHostFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }





    public void addFromFavToPlan(View view) {

        if (MyUser.getInstance().isLogin()) {
            String name = "";
            if (view.getTag().equals("Saturday"))
                name = "Saturday";
            else if (view.getTag().equals("Sunday"))
                name = "Sunday";
            else if (view.getTag().equals("Monday"))
                name = "Monday";
            else if (view.getTag().equals("Tuesday"))
                name = "Tuesday";
            else if (view.getTag().equals("Wednesday"))
                name = "Wednesday";
            else if (view.getTag().equals("Thursday"))
                name = "Thursday";
            else if (view.getTag().equals("Friday"))
                name = "Friday";

            day=name;
            PopupMenu popup = new PopupMenu(this, view);
            MenuInflater inflater = popup.getMenuInflater();
            popup.setOnMenuItemClickListener(this);
            inflater.inflate(R.menu.popup_plan, popup.getMenu());
            popup.show();
        } else {
            Snackbar.make(findViewById(android.R.id.content), "you can't add with out account ", Snackbar.LENGTH_LONG)
                    .setAction(R.string.create_account, i -> {
                        startActivity(new Intent(this, WelcomeActivity.class));
                        finish();
                    })
                    .show();
        }
    }


    @Override
    public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        Fragment fragment = (navHostFragment.getChildFragmentManager().getFragments().get(0));
        if (menuItem.getItemId() == R.id.im_from_favorite) {
            ((PlanFragment)fragment).showFavoriteDialog(day,this);
        } else {
           ((PlanFragment)fragment).showSearchDialog(day,this);

        }
        return false;
    }
}
