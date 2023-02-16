package com.example.foodplanner.DBConnection.localdatabase.localdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealDAO {
    @Query("Select * from Meal")
    List<Meal> getFavoriteMeals();

    @Query("Select * from Meal where email = :email")
    Single<List<Meal>> getPlanMeals(String email);

    @Insert
    void insertProduct(Meal meal);

    @Delete
    void deleteProduct(Meal meal);

    @Query("DELETE FROM Meal WHERE idMeal = :ID and email = :email ")
    Completable deletePlanMeal(String ID,String email);


}
