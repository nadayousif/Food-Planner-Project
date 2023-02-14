package com.example.foodplanner.home.presenter;

import com.example.foodplanner.APIconnection.RemoteDataSource;
import com.example.foodplanner.Model.Meal;

import java.util.List;


public class PresenterRandomMeal implements NetworkDelegateRandomMeal {
    RemoteDataSource remoteDataSource;
    CommunicationRandomMeal communicationRandomMeal;

    public PresenterRandomMeal(RemoteDataSource remoteDataSource, CommunicationRandomMeal communicationRandomMeal) {
        this.remoteDataSource = remoteDataSource;
        this.communicationRandomMeal = communicationRandomMeal;
    }

    public void getRandomMeal() {
        remoteDataSource.getRandomMeal(this);
    }
    public void onResponseRandomMeal(String idMeal,String strMeal,String strMealThumb) {
        communicationRandomMeal.setMeal(idMeal,strMeal,strMealThumb);
    }

    @Override
    public void onFailure(String message) {
        communicationRandomMeal.setError(message);

    }
}
