package com.example.foodplanner.searchresult.presenter;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface NetworkDelegateSearchResult {
    void onResponse(List<Meal> list);

    void onFailure(String message);
}
