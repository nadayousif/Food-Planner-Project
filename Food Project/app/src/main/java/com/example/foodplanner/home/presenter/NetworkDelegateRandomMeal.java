package com.example.foodplanner.home.presenter;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface NetworkDelegateRandomMeal {
    void onResponseRandomMeal(String idMeal,String strMeal,String strMealThumb);

    void onFailure(String message);
}
