package com.example.foodplanner.home.presenter;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface NetworkDelegateRandomMeal {

    void onFailure(String message);

    void onResponseRandomMeal(Meal randomMeal);
}
