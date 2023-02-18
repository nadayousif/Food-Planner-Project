package com.example.foodplanner.meal.presenter;

import com.example.foodplanner.APIconnection.RemoteDataSource;
import com.example.foodplanner.DBConnection.localdatabase.localdb.LocalDataSource;
import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.meal.OnViewClickFavorite;
import com.example.foodplanner.searchresult.OnViewClickSearchPlan;
import com.example.foodplanner.searchresult.presenter.CommunicationSearchResult;
import com.example.foodplanner.searchresult.presenter.NetworkDelegateSearchResult;

import java.util.ArrayList;

public class PresenterMeal implements NetworkDelegateMeal {
    RemoteDataSource remoteDataSource;
    CommunicationMeal communicationMeal;
    LocalDataSource localDataSource;

    public PresenterMeal(RemoteDataSource remoteDataSource, CommunicationMeal communicationMeal,LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.communicationMeal = communicationMeal;
        this.localDataSource = localDataSource;
    }

   /* public void getInstructions(String id) {
        remoteDataSource.getInstructions(id,this);
    }*/
  public void onResponseMeal(Meal meal, String idMeal, String strMeal, String strMealThumb, String strMealCategory, ArrayList<String> strIngredients, ArrayList<String>  strMeasures,String[]  strInstructions,String strCountry,String strYouTube){
       communicationMeal.setMeal(meal,idMeal,strMeal,strMealThumb,strMealCategory,strIngredients,strMeasures,strInstructions,strCountry,strYouTube);
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
    public  void onResponseMealView(Meal meal){
      communicationMeal.setMealView(meal);
    }

    public  void insertMeal(Meal meal) {
        localDataSource.insertMeal(meal);
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
}
