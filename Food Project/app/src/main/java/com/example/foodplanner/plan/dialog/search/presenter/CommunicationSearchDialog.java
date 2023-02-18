package com.example.foodplanner.plan.dialog.search.presenter;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface CommunicationSearchDialog {
    void setListHistory(String[] names);

    void onFailure(String message);

    void setListMeals(List<Meal> list);

    void onComplete();
}
