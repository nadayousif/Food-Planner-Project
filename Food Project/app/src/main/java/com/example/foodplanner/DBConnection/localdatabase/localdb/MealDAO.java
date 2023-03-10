package com.example.foodplanner.DBConnection.localdatabase.localdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealDAO {
    @Query("Select * from FavoriteMeal where email = :email")
    Single<List<FavoriteMeal>> getFavoriteMeals(String email);

    @Query("Select * from Meal where email = :email")
    Single<List<Meal>> getPlanMeals(String email);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMeal(Meal meal);

    @Query("DELETE FROM FavoriteMeal WHERE idMeal = :ID and email = :email ")
    Completable deletePlanMealFromFavorite(String ID, String email);

    @Query("DELETE FROM Meal WHERE idMeal = :ID and email = :email ")
    Completable deletePlanMeal(String ID, String email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable setMeals(List<Meal> listMeals);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToFav(FavoriteMeal tag);

    @Delete
    Completable removeFav(FavoriteMeal meal);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addMealPlan(Meal meal);

    @Delete
    Completable deleteMealPlan(Meal meal);

    @Query("select * from  favoritemeal  where idMeal =  :id and email = :email")
    Single<FavoriteMeal> getFav(String id, String email);

    @Query("select * from  meal  where idMeal =  :id and email = :email")
    Single<Meal> getMeal(String id, String email);

    @Query("delete  from meal where email = :email")
    Completable deletePlan(String email);

    @Query("delete  from favoritemeal where email = :email")
    Completable deleteFav(String email);
}
