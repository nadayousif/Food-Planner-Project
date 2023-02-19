package com.example.foodplanner.meal;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.searchresult.OnViewClickSearchPlan;

public interface OnclickItemMeal {
    void onClick(String id);

    void addToFav(OnViewClickFavorite onViewClickFavorite, Meal tag);

    void removeFromFav(OnViewClickFavorite onViewClickFavorite, Meal tag);
}
