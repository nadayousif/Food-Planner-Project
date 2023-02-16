package com.example.foodplanner.DBConnection.localdatabase.localdb;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.plan.dialog.search.presenter.presenterSearchDialog;
import com.example.foodplanner.plan.presenter.NetworkDelegatePlan;

import java.util.List;

public interface LocalDataSource {
    void deleteProduct(Meal meal);

    void insertProduct(Meal  meal);

    void getPlanMeals(String email, NetworkDelegatePlan networkDelegatePlan);

    void deletePlanMeal(String idMeal, String email, NetworkDelegatePlan networkDelegatePlan);

    void clear();

    void setMealsInPlan(presenterSearchDialog presenterSearchDialog, List<Meal> listMeals);
}
