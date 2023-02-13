package com.example.foodplanner.serach.presenter;

import com.example.foodplanner.APIconnection.RemoteDataSource;

import java.util.List;

public class PresenterSearch implements NetworkDelegateSearch {
    RemoteDataSource remoteDataSource;
    CommunicationSearch communicationSearch;

    public PresenterSearch(RemoteDataSource remoteDataSource, CommunicationSearch communicationSearch) {
        this.remoteDataSource = remoteDataSource;
        this.communicationSearch = communicationSearch;
    }
    public void getCuisines(){
        remoteDataSource.getCuisines(this);
    }
    public void getCountry(){
        remoteDataSource.getCountry(this);
    }
    public void getIngredient(){
        remoteDataSource.getIngredient(this);
    }
    public void search(String name){
        remoteDataSource.getSearchList(this,name);
    }

    @Override
    public void onResponse(List<String> names) {
        communicationSearch.setList(names);
    }

    @Override
    public void onFailure(String msg) {
        communicationSearch.setError(msg);
    }

    @Override
    public void onResponseSearch(String[] meals) {
       communicationSearch.setListSearch(meals);
    }


    public void clear() {
        remoteDataSource.clearDisposable();
    }
}
