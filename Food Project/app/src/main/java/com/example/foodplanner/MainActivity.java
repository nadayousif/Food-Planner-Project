package com.example.foodplanner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplanner.Helper.Tags;
import com.example.foodplanner.databinding.ActivityMainBinding;
import com.example.foodplanner.meal.MealActivity;
import com.example.foodplanner.serach.SearchFragment;

public class MainActivity extends AppCompatActivity {
    NavController navController;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        NavigationUI.setupWithNavController(binding.navView, navController);




    }
    public void goToMeal(View view) {
        Intent intent=new Intent(this, MealActivity.class);
        intent.putExtra(Tags.TAG_ID_INTENT,view.getTag().toString());
        startActivity(intent);

    }
    public void addToPlan(View view) {
//       navController.navigate(R.id.action_navigation_plan_to_navigation_search);
        navController.navigate(R.id.navigation_search);


    }

}