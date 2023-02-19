package com.example.foodplanner.meal.presenter;

import com.example.foodplanner.Model.Meal;

import java.util.ArrayList;
import java.util.List;

public interface CommunicationMeal {

    void setError(String message);

    void susToAdd();

    void onFailureToAdd( String message);

    void setMeal(Meal meal, Boolean isLocal);

    void updateDB(List<String> list);
}
