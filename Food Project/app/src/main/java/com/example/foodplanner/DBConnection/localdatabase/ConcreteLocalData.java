package com.example.foodplanner.DBConnection.localdatabase;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.DBModel.FavoriteMeal;

import java.util.List;

public class ConcreteLocalData implements LocalDataSource{
    private final Context context;
    MealDAO dao;

    public LiveData<List<FavoriteMeal>> ldProduct;

    private static ConcreteLocalData concreteLocalData= null;

    private ConcreteLocalData(Context context){
        this.context=context;
        DBFood db= DBFood.getInstance(this.context.getApplicationContext());
        dao=db.mealDAO();
        dao.getFavoriteMeals();
    }
    public static ConcreteLocalData getInstance(Context context){
        if (concreteLocalData==null)concreteLocalData=new ConcreteLocalData(context);
        return concreteLocalData;
    }
    public void deleteProduct(FavoriteMeal meal){
        new Thread(()->{dao.deleteProduct(meal);}).start();

    }
    public void insertProduct(FavoriteMeal meal){
        new Thread(()->{dao.insertProduct(meal);}).start();
    }

    public LiveData<List<FavoriteMeal>> getLdProduct() {
        return ldProduct;
    }
}
