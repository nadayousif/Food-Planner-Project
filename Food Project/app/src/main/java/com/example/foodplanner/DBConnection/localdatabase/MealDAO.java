package com.example.foodplanner.DBConnection.localdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodplanner.DBConnection.DBModel.FavoriteMeal;
import com.example.foodplanner.DBConnection.DBModel.PlanMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealDAO {
    @Query("Select * from FavoriteMeals")
    List<FavoriteMeal> getFavoriteMeals();

    @Query("Select * from PlanMeals where email = :email")
    Single<List<PlanMeal>> getPlanMeals(String email);

    @Insert
    void insertProduct(FavoriteMeal meal);

    @Delete
    void deleteProduct(FavoriteMeal meal);

    @Query("DELETE FROM PlanMeals WHERE idMeal = :ID and email = :email ")
    Completable deletePlanMeal(String ID,String email);


}
