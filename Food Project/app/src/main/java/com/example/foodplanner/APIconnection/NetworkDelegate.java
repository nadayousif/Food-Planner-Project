package com.example.foodplanner.APIconnection;

import com.example.foodplanner.Model.Meal;

import java.util.ArrayList;
import java.util.List;

public interface NetworkDelegate {
    void onResponse(List<String> names);
    void onFailure(String msg);

}
