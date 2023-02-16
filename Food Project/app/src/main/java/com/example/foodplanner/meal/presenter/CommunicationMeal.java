package com.example.foodplanner.meal.presenter;

import java.util.ArrayList;

public interface CommunicationMeal {
    void setMeal(String idMeal, String strMeal, String strMealThumb, String strMealCategory, ArrayList<String> strIngredients, ArrayList<String> strMeasures,String[] strInstructions,String strCountry,String strYouTube);

    void setError(String message);
}
