package com.example.foodplanner.favorite;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.foodplanner.Model.RandomMeal;

import com.example.foodplanner.Model.Meal;

import com.example.foodplanner.R;
import com.example.foodplanner.databinding.FragmentFavoriteBinding;
import com.example.foodplanner.meal.MealActivity;
import com.example.foodplanner.searchresult.AdapterSearchResult;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    RecyclerView recycleView;
    LinearLayoutManager layoutManager;
    FavoriteMealsAdapter myAdapter ;
    Meal[] meals = {new Meal("52772", "Teriyaki Chicken Casserole", null, "Chicken", "Japanese", "Preheat oven to 350° F. Spray a 9x13-inch baking pan with non-stick spray.\r\nCombine soy sauce, ½ cup water, brown sugar, ginger and garlic in a small saucepan and cover. Bring to a boil over medium heat. Remove lid and cook for one minute once boiling.\r\nMeanwhile, stir together the corn starch and 2 tablespoons of water in a separate dish until smooth. Once sauce is boiling, add mixture to the saucepan and stir to combine. Cook until the sauce starts to thicken then remove from heat.\r\nPlace the chicken breasts in the prepared pan. Pour one cup of the sauce over top of chicken. Place chicken in oven and bake 35 minutes or until cooked through. Remove from oven and shred chicken in the dish using two forks.\r\n*Meanwhile, steam or cook the vegetables according to package directions.\r\nAdd the cooked vegetables and rice to the casserole dish with the chicken. Add most of the remaining sauce, reserving a bit to drizzle over the top when serving. Gently toss everything together in the casserole dish until combined. Return to oven and cook 15 minutes. Remove from oven and let stand 5 minutes before serving. Drizzle each serving with remaining sauce. Enjoy!", "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg", "https://www.youtube.com/watch?v=4aZr5hZXP_s", "soy sauce", "water", "brown sugar", "ground ginger", "minced garlic", "cornstarch", "chicken breasts", "stir-fry vegetables", "brown rice", "", "", "", "", "", "", null, null, null, null, null, "3/4 cup", "1/2 cup", "1/4 cup", "1/2 teaspoon", "1/2 teaspoon", "4 Tablespoons", "2", "1 (12 oz.)", "3 cups", "", "", "", "", "", "", null, null, null, null, null)};
    // TODO: Rename parameter arguments, choose names that match

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycleView= (RecyclerView) view.findViewById(R.id.RV_Id);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleView.setLayoutManager(layoutManager);
        myAdapter = new FavoriteMealsAdapter(getContext() , meals);
        recycleView.setAdapter(myAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_favorite, container, false);

    }
}
