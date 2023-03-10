package com.example.foodplanner.plan.dialog.favorite.presenter;

import com.example.foodplanner.DBConnection.localdatabase.localdb.LocalDataSource;
import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public class PresenterFavoriteDialog implements NetworkDelegateFavDialog{
    LocalDataSource localDataSource;
    CommunicationFavoriteDialog communicationFavoriteDialog;
    public PresenterFavoriteDialog(CommunicationFavoriteDialog communicationFavoriteDialog, LocalDataSource concreteLocalData) {
        this.localDataSource=concreteLocalData;
        this.communicationFavoriteDialog=communicationFavoriteDialog;
    }

    public void getListFav( String email){
        localDataSource.getListFav(email,this);

    }

    @Override
    public void setList(List<FavoriteMeal> favoriteMeals) {
        communicationFavoriteDialog.setList(favoriteMeals);
    }

    @Override
    public void onError(String message) {
        communicationFavoriteDialog.setError(message);
    }

    @Override
    public void sus() {
communicationFavoriteDialog.sus();
    }



    public void deleteMealFromPlan(Meal meal) {
        localDataSource.deleteMealFormPlan(meal);
    }

    public void addMealToPlan(Meal meal) {
        localDataSource.addToPlan(meal);

    }
}
