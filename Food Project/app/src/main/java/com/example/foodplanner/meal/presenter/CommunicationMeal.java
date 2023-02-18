package com.example.foodplanner.meal.presenter;

import com.example.foodplanner.Model.Meal;

import java.util.ArrayList;

public interface CommunicationMeal {
    void setMeal(Meal meal,String idMeal, String strMeal, String strMealThumb, String strMealCategory, ArrayList<String> strIngredients, ArrayList<String> strMeasures,String[] strInstructions,String strCountry,String strYouTube);

    void setError(String message);

    void setMealView(Meal meal);
}
