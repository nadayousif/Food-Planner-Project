package com.example.foodplanner.plan.presenter;


import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface NetworkDelegatePlan {
    void onComplete();

    void onError(String s);

    void onResponse(List<Meal> planMeals);
}
