package com.example.foodplanner.favorite.presenter;


import com.example.foodplanner.DBConnection.localdatabase.localdb.LocalDataSource;
import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;


import java.util.List;


public class PresenterFavoriteMeal implements NetworkDelegateFavMeal {
    LocalDataSource localDataSource;
    CommunicationFavoriteMeal communicationFavoriteMeal;

    public PresenterFavoriteMeal(CommunicationFavoriteMeal communicationFavoriteMeal, LocalDataSource concreteLocalData) {
        this.localDataSource=concreteLocalData;
        this.communicationFavoriteMeal=communicationFavoriteMeal;
    }
    public void getListFav( String email){
        localDataSource.getListFavMeal(email,this);

    }

    @Override
    public void setList(List<FavoriteMeal> favoriteMeals) {
        communicationFavoriteMeal.setList(favoriteMeals);
    }

    @Override
    public void onError(String message) {
        communicationFavoriteMeal.setError(message);
    }

    @Override
    public void sus() {
        communicationFavoriteMeal.sus();
    }
   /* public void deleteMeal(String idMeal, String email) {
        localDataSource.deleteMeal(idMeal,email,this);
    }*/

}
