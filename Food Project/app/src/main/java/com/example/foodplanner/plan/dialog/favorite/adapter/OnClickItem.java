package com.example.foodplanner.plan.dialog.favorite.adapter;

import com.example.foodplanner.Model.FavoriteMeal;

public interface OnClickItem {
    void deleteMeal(FavoriteMeal favoriteMeal);

    void saveMeal(FavoriteMeal favoriteMeal);
}
