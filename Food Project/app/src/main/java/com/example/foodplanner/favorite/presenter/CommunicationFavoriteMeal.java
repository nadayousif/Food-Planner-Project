package com.example.foodplanner.favorite.presenter;

import com.example.foodplanner.Model.FavoriteMeal;

import java.util.List;

public interface CommunicationFavoriteMeal {
    void setError(String message);
    void setList(List<FavoriteMeal> favoriteMeals);
    void sus();

    void onComplete();
}
