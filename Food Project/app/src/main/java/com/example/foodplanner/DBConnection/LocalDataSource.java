package com.example.foodplanner.DBConnection;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.DBModel.FavoriteMeal;

import java.util.List;

public interface LocalDataSource {
    void deleteProduct(FavoriteMeal meal);

    void insertProduct(FavoriteMeal  meal);

    LiveData<List<FavoriteMeal >> getLdProduct() ;
}
