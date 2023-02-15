package com.example.foodplanner.plan.presenter;

import com.example.foodplanner.DBConnection.DBModel.PlanMeal;

import java.util.List;

public interface NetworkDelegatePlan {
    void onComplete();

    void onError(String s);

    void onResponse(List<PlanMeal> planMeals);
}
