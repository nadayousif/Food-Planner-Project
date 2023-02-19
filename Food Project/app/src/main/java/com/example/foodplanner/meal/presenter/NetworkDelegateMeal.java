package com.example.foodplanner.meal.presenter;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.meal.OnViewClickFavorite;
import com.example.foodplanner.searchresult.OnViewClickSearchPlan;

import java.util.ArrayList;

public interface NetworkDelegateMeal {
    void onResponseMeal(Meal meal,String idMeal, String strMeal, String strMealThumb, String strMealCategory, ArrayList<String> strIngredients,ArrayList<String> strMeasures, String[]  strInstructions,String strCountry,String strYouTube);

    void onFailure(String message);

    void onResponseMealView(Meal meal);
    void sus();

    void onFailureToAdd( String message);
}
