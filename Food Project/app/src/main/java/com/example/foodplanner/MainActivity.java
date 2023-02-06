package com.example.foodplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplanner.Helper.Tags;
import com.example.foodplanner.databinding.ActivityMainBinding;
import com.example.foodplanner.meal.MealActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }
    public void goToMeal(View view) {
        Intent intent=new Intent(this, MealActivity.class);
        intent.putExtra(Tags.TAG_ID_INTENT,view.getTag().toString());
        startActivity(intent);

    }

}