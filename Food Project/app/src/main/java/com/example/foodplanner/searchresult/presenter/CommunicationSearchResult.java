package com.example.foodplanner.searchresult.presenter;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.searchresult.OnViewClickSearchPlan;

import java.util.List;

public interface CommunicationSearchResult {
    void setList(List<Meal> list);

    void setError(String message);

    void nadaTwo();//8

    void susToAdd(OnViewClickSearchPlan onViewClickSearchPlan);

    void onFailureToAdd(OnViewClickSearchPlan onViewClickSearchPlan, String message);
}
