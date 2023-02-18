package com.example.foodplanner.plan.dialog.search.searchresult;

import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;

public interface OnClickItem {

    void saveMealToPlan(Meal meal);

    void removeMealToPlan(Meal meal);
}
