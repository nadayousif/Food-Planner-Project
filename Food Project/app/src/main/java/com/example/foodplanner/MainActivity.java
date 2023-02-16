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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplanner.DBConnection.localdatabase.ConcreteLocalData;

import com.example.foodplanner.databinding.ActivityMainBinding;
import com.example.foodplanner.helper.MySharedPreference;
import com.example.foodplanner.meal.MealActivity;
import com.example.foodplanner.plan.dialog.favorite.FavoriteDialog;
import com.example.foodplanner.plan.dialog.search.SearchDialog;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    NavController navController;
    private ActivityMainBinding binding;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    String personEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        NavigationUI.setupWithNavController(binding.navView, navController);
        /*GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            personEmail = acct.getEmail();
            System.out.println(personName);
            System.out.println(personEmail);
            login();

        }*/


        String b = MySharedPreference.getEmail(this);
        Toast.makeText(this, "" + b, Toast.LENGTH_SHORT).show();


    }

    /* public void login() {
         SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
         SharedPreferences.Editor editor = sharedPref.edit();
         editor.putString(getString(R.string.userEmail), personEmail);
         editor.apply();

     }*/
    public void goToMeal(View view) {

        if (view.getTag().toString()!=null){
            Intent intent=new Intent(this, MealActivity.class);

            intent.putExtra(getString(R.string.mealID),view.getTag().toString());
            intent.putExtra(getString(R.string.isLocal),false);
            startActivity(intent);
        }
    }

    public void addToPlan(View view) {
        navController.navigate(R.id.navigation_search);
        new Thread(() -> {
            ConcreteLocalData db;
            db = ConcreteLocalData.getInstance(this);
        }).start();

    }

    public void addFromFavToPlan(View view) {
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
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        popup.setOnMenuItemClickListener(this);
        inflater.inflate(R.menu.popup_plan, popup.getMenu());
        popup.show();
    }


    @Override
    public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.im_from_favorite) {
            DialogFragment newFragment = new FavoriteDialog();
            newFragment.show(getSupportFragmentManager(), "Add From Favorite");
        } else {
            DialogFragment newFragment = new SearchDialog();
            newFragment.show(getSupportFragmentManager(), "Add From Favorite");
        }
        return false;
    }
}
