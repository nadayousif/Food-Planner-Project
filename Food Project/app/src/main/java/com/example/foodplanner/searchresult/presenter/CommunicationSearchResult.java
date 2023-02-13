package com.example.foodplanner.searchresult.presenter;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface CommunicationSearchResult {
    void setList(List<Meal> list);

    void setError(String message);
}
