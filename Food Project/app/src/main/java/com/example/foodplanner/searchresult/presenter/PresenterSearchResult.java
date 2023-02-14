package com.example.foodplanner.searchresult.presenter;

import com.example.foodplanner.APIconnection.RemoteDataSource;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public class PresenterSearchResult implements NetworkDelegateSearchResult{
    RemoteDataSource remoteDataSource;
    CommunicationSearchResult  communicationSearchResult;

    public PresenterSearchResult(RemoteDataSource remoteDataSource, CommunicationSearchResult communicationSearchResult) {
        this.remoteDataSource = remoteDataSource;
        this.communicationSearchResult = communicationSearchResult;
    }

    public void getList(String name) {
        remoteDataSource.getSearchResultList( name,this);
    }

    public void getCountryList(String name) {
        remoteDataSource.getSearchListCountry(name,this);
    }

    public void getCuisinesList(String name) {
        remoteDataSource.getSearchListCuisines(name,this);
    }

    public void getIngredient(String name) {
        remoteDataSource.getSearchListIngredient(name,this);
    }

    @Override
    public void onResponse(List<Meal> list) {
        communicationSearchResult.setList(list);
    }

    @Override
    public void onFailure(String message) {
        communicationSearchResult.setError(message);

    }

    @Override
    public void onNada() {
        communicationSearchResult.nadaTwo();
    }//7

    public void showNada() {
        remoteDataSource.get(this);
    }//2
}
