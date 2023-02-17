package com.example.foodplanner.plan.dialog.search.presenter;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface NetworkDelegateSearchPlan {
    void onResponseSearch(String[] names);

    void onFailure(String message);

    void onResponseListOfMeals(List<Meal> list);

    void onComplete();
}
