package com.example.foodplanner.favorite.presenter;

import com.example.foodplanner.Model.FavoriteMeal;

import java.util.List;

public interface NetworkDelegateFavMeal {
    void setList(List<FavoriteMeal> favoriteMeals);

    void onError(String message);

    void sus();
}
