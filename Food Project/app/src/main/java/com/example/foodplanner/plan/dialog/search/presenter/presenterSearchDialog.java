package com.example.foodplanner.plan.dialog.search.presenter;

import com.example.foodplanner.APIconnection.RemoteDataSource;
import com.example.foodplanner.DBConnection.localdatabase.localdb.LocalDataSource;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public class presenterSearchDialog implements NetworkDelegateSearchPlan {
    private final LocalDataSource localDataSource;
    RemoteDataSource remoteDataSource;
    CommunicationSearchDialog communicationSearchDialog;
    public presenterSearchDialog(RemoteDataSource instance, CommunicationSearchDialog searchDialog, LocalDataSource localDataSource) {
     remoteDataSource=instance;
     communicationSearchDialog=searchDialog;
     this.localDataSource=localDataSource;
    }

    public void search(String newText) {
        remoteDataSource.getSearchList(newText,this);
    }

    public void getMeals(String query) {
        remoteDataSource.getMealSearchPlan(query,this);

    }

    @Override
    public void onResponseSearch(String[] names) {
        communicationSearchDialog.setListHistory(names);
    }

    @Override
    public void onFailure(String message) {
        communicationSearchDialog.onFailure(message);

    }

    @Override
    public void onResponseListOfMeals(List<Meal> list) {
            communicationSearchDialog.setListMeals(list);
    }

    @Override
    public void onComplete() {
        communicationSearchDialog.onComplete();
    }

    public void setMealsInPlan(List<Meal> listMeals) {
        localDataSource.setMeals(this,listMeals);
    }
}
