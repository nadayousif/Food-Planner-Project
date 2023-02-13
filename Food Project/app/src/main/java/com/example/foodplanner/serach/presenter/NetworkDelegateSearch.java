package com.example.foodplanner.serach.presenter;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface NetworkDelegateSearch {
    void onResponse(List<String> names);
    void onFailure(String msg);
    void onResponseSearch(String[] meals);

}
