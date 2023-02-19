package com.example.foodplanner.meal.presenter;

import com.example.foodplanner.APIconnection.RemoteDataSource;
import com.example.foodplanner.DBConnection.localdatabase.localdb.LocalDataSource;
import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public class PresenterMeal implements NetworkDelegateMeal {
    RemoteDataSource remoteDataSource;
    CommunicationMeal communicationMeal;
    LocalDataSource localDataSource;

    public PresenterMeal(RemoteDataSource remoteDataSource, CommunicationMeal communicationMeal,LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.communicationMeal = communicationMeal;
        this.localDataSource = localDataSource;
    }

  public void onResponseMeal(Meal meal,Boolean isLocal ){
       communicationMeal.setMeal(meal,isLocal);
    }
    public void onFailure(String message){
        communicationMeal.setError(message);

    }
    public void addToFav(FavoriteMeal tag) {
        localDataSource.addToFavorite(tag,this);
    }

    public void removeFromFav(FavoriteMeal meal) {
        localDataSource.removeFromFavorite(meal,this);
    }



    public void getMeal(String id) {
        remoteDataSource.getMeal(id,this);
    }

    public void sus() {
        communicationMeal.susToAdd();
    }


    public void onFailureToAdd( String message) {
        communicationMeal.onFailureToAdd( message);
    }

    public void getMealLocal(String id, String email) {
      localDataSource.getMealPlan(id,email,this);
    }

    public void getMealLocalFav(String id, String email) {
        localDataSource.getMealFav(id,email,this);
    }

    public void addToPlan(List<Meal> list) {
        localDataSource.addMealsToPlan(list);
    }
}
