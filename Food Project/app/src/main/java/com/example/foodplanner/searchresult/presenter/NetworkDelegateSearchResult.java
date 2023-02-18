package com.example.foodplanner.searchresult.presenter;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.searchresult.OnViewClickSearchPlan;

import java.util.List;

public interface NetworkDelegateSearchResult {
    void onResponse(List<Meal> list);

    void onFailure(String message);

    void onNada();//6(ret to pre)

    void sus(OnViewClickSearchPlan onViewClickSearchPlan);

    void onFailureToAdd(OnViewClickSearchPlan onViewClickSearchPlan, String message);

    void setSizeOfList(int size);

    void upDateProgressBar();
}
