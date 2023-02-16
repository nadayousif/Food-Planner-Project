package com.example.foodplanner.meal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.APIconnection.RetrofitClient;
import com.example.foodplanner.Helper.CheckConnection;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.databinding.ActivityMealBinding;
import com.example.foodplanner.databinding.ActivitySearchResultBinding;
import com.example.foodplanner.meal.presenter.CommunicationMeal;
import com.example.foodplanner.meal.presenter.PresenterMeal;
import com.example.foodplanner.searchresult.AdapterSearchResult;
import com.example.foodplanner.searchresult.presenter.PresenterSearchResult;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MealActivity extends AppCompatActivity  implements CommunicationMeal {
    private ActivityMealBinding binding;
    AdapterInstructions adapterInstructions;
    ImageView mealPhoto;
    TextView mealName;
    TextView mealCategory;
    TextView mealCountry;
    AdapterIngredientMeasure adapterIngredientMeasure;
    //Meal meal = new Meal("52772", "Teriyaki Chicken Casserole", null, "Chicken", "Japanese", "Preheat oven to 350° F. Spray a 9x13-inch baking pan with non-stick spray.\r\nCombine soy sauce, ½ cup water, brown sugar, ginger and garlic in a small saucepan and cover. Bring to a boil over medium heat. Remove lid and cook for one minute once boiling.\r\nMeanwhile, stir together the corn starch and 2 tablespoons of water in a separate dish until smooth. Once sauce is boiling, add mixture to the saucepan and stir to combine. Cook until the sauce starts to thicken then remove from heat.\r\nPlace the chicken breasts in the prepared pan. Pour one cup of the sauce over top of chicken. Place chicken in oven and bake 35 minutes or until cooked through. Remove from oven and shred chicken in the dish using two forks.\r\n*Meanwhile, steam or cook the vegetables according to package directions.\r\nAdd the cooked vegetables and rice to the casserole dish with the chicken. Add most of the remaining sauce, reserving a bit to drizzle over the top when serving. Gently toss everything together in the casserole dish until combined. Return to oven and cook 15 minutes. Remove from oven and let stand 5 minutes before serving. Drizzle each serving with remaining sauce. Enjoy!", "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg", "https://www.youtube.com/watch?v=4aZr5hZXP_s", "soy sauce", "water", "brown sugar", "ground ginger", "minced garlic", "cornstarch", "chicken breasts", "stir-fry vegetables", "brown rice", "", "", "", "", "", "", null, null, null, null, null, "3/4 cup", "1/2 cup", "1/4 cup", "1/2 teaspoon", "1/2 teaspoon", "4 Tablespoons", "2", "1 (12 oz.)", "3 cups", "", "", "", "", "", "", null, null, null, null, null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_meal);
        mealPhoto = findViewById(R.id.iv_meal);
        mealName = findViewById(R.id.tv_mael_name);
        mealCategory = findViewById(R.id.tv_meal_category);
        mealCountry=findViewById(R.id.tv_country);
        RecyclerView recIngrMeas = findViewById(R.id.rc_meal_Ingredient_Measure);
        RecyclerView recInst = findViewById(R.id.rec_mael_instructions);

        if (getIntent() != null && CheckConnection.isConnect(this)) {
            String id = getIntent().getStringExtra(getString(R.string.mealID));
            PresenterMeal presenterMeal = new PresenterMeal(RetrofitClient.getInstance(), this);
            presenterMeal.getMeal(id);
        }else {
            Snackbar.make(findViewById(android.R.id.content), "no internet connection pls try again ", Snackbar.LENGTH_LONG)
                    .show();
        }

        recIngrMeas.setLayoutManager(new StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL));
        adapterIngredientMeasure= new AdapterIngredientMeasure();
        recIngrMeas.setAdapter(adapterIngredientMeasure);
        recInst.setLayoutManager(new LinearLayoutManager(this));
        adapterInstructions = new AdapterInstructions();
        recInst.setAdapter(adapterInstructions);


    }

    public void finishActivity(View view) {
        finish();
    }

    public void addToFavorite(View view) {
    }

    public void addToPLan(View view) {
    }
    public void setError(String message){
        Snackbar.make(binding.getRoot(), "something wrong pls try again", Snackbar.LENGTH_LONG)
                .show();
    }
    public void setMeal(String idMeal, String strMeal, String strMealThumb, String strMealCategory, ArrayList<String> strIngredients, ArrayList<String> strMeasures, String[]   strInstructions,String strCountry,String strYouTube){
        adapterIngredientMeasure.setIngredients(strIngredients);
        adapterIngredientMeasure.setMeasures(strMeasures);
        adapterIngredientMeasure.notifyDataSetChanged();
        strInstructions= Arrays.stream(strInstructions).filter(i->(!i.trim().isEmpty())||(i.trim().contains("Step"))).toArray(String[]::new);
        adapterInstructions.setInstructions(strInstructions);
        adapterInstructions.notifyDataSetChanged();
        mealName.setText(strMeal);
        Glide.with(this).load(strMealThumb).into(mealPhoto);
        mealCategory.setText(strMealCategory);
        mealCountry.setText(strCountry);
    }
}
