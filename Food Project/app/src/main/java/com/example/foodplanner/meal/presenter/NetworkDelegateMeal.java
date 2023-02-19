package com.example.foodplanner.meal.presenter;

import com.example.foodplanner.Model.Meal;

import java.util.ArrayList;

public interface NetworkDelegateMeal {
    void onResponseMeal(Meal meal,Boolean isLocal);
    void onFailure(String message);

    void sus();

    void onFailureToAdd( String message);
}
