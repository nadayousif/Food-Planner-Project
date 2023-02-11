package com.example.foodplanner.DBConnection.localdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodplanner.DBConnection.DBModel.FavoriteMeal;

import java.util.List;
@Dao
public interface MealDAO {
    @Query("Select * from FavoriteMeals")
    List<FavoriteMeal> getFavoriteMeals();

    @Insert
    void insertProduct(FavoriteMeal meal);

    @Delete
    void deleteProduct(FavoriteMeal meal);

}
