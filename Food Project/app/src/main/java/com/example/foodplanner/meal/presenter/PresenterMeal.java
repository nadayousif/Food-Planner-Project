package com.example.foodplanner.meal.presenter;

import com.example.foodplanner.APIconnection.RemoteDataSource;
import com.example.foodplanner.searchresult.presenter.CommunicationSearchResult;
import com.example.foodplanner.searchresult.presenter.NetworkDelegateSearchResult;

import java.util.ArrayList;

public class PresenterMeal implements NetworkDelegateMeal {
    RemoteDataSource remoteDataSource;
    CommunicationMeal communicationMeal;

    public PresenterMeal(RemoteDataSource remoteDataSource, CommunicationMeal communicationMeal) {
        this.remoteDataSource = remoteDataSource;
        this.communicationMeal = communicationMeal;
    }

   /* public void getInstructions(String id) {
        remoteDataSource.getInstructions(id,this);
    }*/
  public void onResponseMeal(String idMeal, String strMeal, String strMealThumb, String strMealCategory, ArrayList<String> strIngredients, ArrayList<String>  strMeasures,String[]  strInstructions,String strCountry,String strYouTube){
       communicationMeal.setMeal(idMeal,strMeal,strMealThumb,strMealCategory,strIngredients,strMeasures,strInstructions,strCountry,strYouTube);
   }
    public void onFailure(String message){
        communicationMeal.setError(message);

    }

    public void getMeal(String id) {
        remoteDataSource.getMeal(id,this);
    }
}
