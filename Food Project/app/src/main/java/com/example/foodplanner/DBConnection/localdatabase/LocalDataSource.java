package com.example.foodplanner.DBConnection.localdatabase;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.DBConnection.DBModel.FavoriteMeal;
import com.example.foodplanner.plan.presenter.NetworkDelegatePlan;

import java.util.List;

public interface LocalDataSource {
    void deleteProduct(FavoriteMeal meal);

    void insertProduct(FavoriteMeal  meal);

    void getPlanMeals(String email, NetworkDelegatePlan networkDelegatePlan);

    void deletePlanMeal(String idMeal, String email, NetworkDelegatePlan networkDelegatePlan);

    void clear();
}
