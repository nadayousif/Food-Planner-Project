package com.example.foodplanner.searchresult.presenter;

import com.example.foodplanner.APIconnection.RemoteDataSource;
import com.example.foodplanner.DBConnection.localdatabase.localdb.LocalDataSource;
import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.searchresult.OnViewClickSearchPlan;

import java.util.List;

public class PresenterSearchResult implements NetworkDelegateSearchResult{
    RemoteDataSource remoteDataSource;
    CommunicationSearchResult  communicationSearchResult;
    LocalDataSource localDataSource;

    public PresenterSearchResult(RemoteDataSource remoteDataSource, CommunicationSearchResult communicationSearchResult, LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.communicationSearchResult = communicationSearchResult;
        this.localDataSource=localDataSource;
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

    @Override
    public void sus(OnViewClickSearchPlan onViewClickSearchPlan) {
        communicationSearchResult.susToAdd(onViewClickSearchPlan);
    }

    @Override
    public void onFailureToAdd(OnViewClickSearchPlan onViewClickSearchPlan, String message) {
        communicationSearchResult.onFailureToAdd(onViewClickSearchPlan,message);
    }

    @Override
    public void setSizeOfList(int size) {
        communicationSearchResult.setSize(size);
    }

    @Override
    public void upDateProgressBar() {
        communicationSearchResult.upDateProgressBar();

    }

    public void showNada() {
        remoteDataSource.get(this);
    }//2

    public void addToFav(FavoriteMeal tag, OnViewClickSearchPlan onViewClickSearchPlan) {
        localDataSource.addToPlan(tag,onViewClickSearchPlan,this);

    }

    public void removeFromFav(FavoriteMeal meal, OnViewClickSearchPlan onViewClickSearchPlan) {
        localDataSource.removeFav(meal,onViewClickSearchPlan,this);
    }

    public void clear() {
        localDataSource.clear();
    }
}
