package com.example.foodplanner.meal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;

public class MealActivity extends AppCompatActivity {
    Meal meal = new Meal("52772", "Teriyaki Chicken Casserole", null, "Chicken", "Japanese", "Preheat oven to 350° F. Spray a 9x13-inch baking pan with non-stick spray.\r\nCombine soy sauce, ½ cup water, brown sugar, ginger and garlic in a small saucepan and cover. Bring to a boil over medium heat. Remove lid and cook for one minute once boiling.\r\nMeanwhile, stir together the corn starch and 2 tablespoons of water in a separate dish until smooth. Once sauce is boiling, add mixture to the saucepan and stir to combine. Cook until the sauce starts to thicken then remove from heat.\r\nPlace the chicken breasts in the prepared pan. Pour one cup of the sauce over top of chicken. Place chicken in oven and bake 35 minutes or until cooked through. Remove from oven and shred chicken in the dish using two forks.\r\n*Meanwhile, steam or cook the vegetables according to package directions.\r\nAdd the cooked vegetables and rice to the casserole dish with the chicken. Add most of the remaining sauce, reserving a bit to drizzle over the top when serving. Gently toss everything together in the casserole dish until combined. Return to oven and cook 15 minutes. Remove from oven and let stand 5 minutes before serving. Drizzle each serving with remaining sauce. Enjoy!", "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg", "https://www.youtube.com/watch?v=4aZr5hZXP_s", "soy sauce", "water", "brown sugar", "ground ginger", "minced garlic", "cornstarch", "chicken breasts", "stir-fry vegetables", "brown rice", "", "", "", "", "", "", null, null, null, null, null, "3/4 cup", "1/2 cup", "1/4 cup", "1/2 teaspoon", "1/2 teaspoon", "4 Tablespoons", "2", "1 (12 oz.)", "3 cups", "", "", "", "", "", "", null, null, null, null, null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        RecyclerView recIngrMeas = findViewById(R.id.rc_meal_Ingredient_Measure);
        RecyclerView recInst = findViewById(R.id.rec_mael_instructions);
        recIngrMeas.setLayoutManager(new StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL));
        recIngrMeas.setAdapter(new AdapterIngredientMeasure(meal.getIngredients(), meal.getMeasures()));
        recInst.setLayoutManager(new LinearLayoutManager(this));
        recInst.setAdapter(new AdapterInstructions(meal.getStrInstructions()));
        Log.i("TAGA", "onCreate: " + meal.getStrYoutube());





    }

    public void finishActivity(View view) {
        finish();
    }

    public void addToFavorite(View view) {
    }

    public void addToPLan(View view) {
    }
}