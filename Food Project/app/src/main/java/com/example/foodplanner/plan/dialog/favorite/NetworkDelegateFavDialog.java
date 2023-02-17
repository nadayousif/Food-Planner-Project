package com.example.foodplanner.plan.dialog.favorite;

import com.example.foodplanner.Model.FavoriteMeal;

import java.util.List;

public interface NetworkDelegateFavDialog {
    void setList(List<FavoriteMeal> favoriteMeals);

    void onError(String message);

    void sus();
}
