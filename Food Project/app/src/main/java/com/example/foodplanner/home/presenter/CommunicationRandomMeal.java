package com.example.foodplanner.home.presenter;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface CommunicationRandomMeal {
    void setMeal(Meal meal);

    void setError(String message);
}
