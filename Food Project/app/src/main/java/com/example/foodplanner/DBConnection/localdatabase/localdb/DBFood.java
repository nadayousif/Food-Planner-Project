package com.example.foodplanner.DBConnection.localdatabase.localdb;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;

@Database(entities = {Meal.class, FavoriteMeal.class}, version = 5)
public abstract class DBFood extends RoomDatabase {
    private static DBFood instance = null;
    public abstract MealDAO mealDAO();

    public static synchronized DBFood getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), DBFood.class, "mealDB").fallbackToDestructiveMigration().build();
        return instance;
    }



}
