package com.example.foodplanner.DBConnection.localdatabase;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.DBConnection.DBModel.FavoriteMeal;

import java.util.List;

public interface LocalDataSource {
    void deleteProduct(FavoriteMeal meal);

    void insertProduct(FavoriteMeal  meal);

    LiveData<List<FavoriteMeal >> getLdProduct() ;
}
