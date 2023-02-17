package com.example.foodplanner.plan.dialog.favorite;

import com.example.foodplanner.Model.FavoriteMeal;

import java.util.List;

public interface CommunicationFavoriteDialog {
    void setError(String message);

    void setList(List<FavoriteMeal> favoriteMeals);

    void sus();
}
