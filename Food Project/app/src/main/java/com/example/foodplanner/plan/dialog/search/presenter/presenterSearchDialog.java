package com.example.foodplanner.plan.dialog.search.presenter;

import com.example.foodplanner.APIconnection.RemoteDataSource;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public class presenterSearchDialog implements NetworkDelegateSearchPlan {
    RemoteDataSource remoteDataSource;
    CommunicationSearchDialog communicationSearchDialog;
    public presenterSearchDialog(RemoteDataSource instance, CommunicationSearchDialog searchDialog) {
     remoteDataSource=instance;
     communicationSearchDialog=searchDialog;
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
}
