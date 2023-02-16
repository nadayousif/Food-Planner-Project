package com.example.foodplanner.APIconnection;

import com.example.foodplanner.home.presenter.NetworkDelegateRandomMeal;
import com.example.foodplanner.home.presenter.PresenterRandomMeal;
import com.example.foodplanner.plan.dialog.search.presenter.NetworkDelegateSearchPlan;
import com.example.foodplanner.plan.dialog.search.presenter.presenterSearchDialog;
import com.example.foodplanner.meal.presenter.NetworkDelegateMeal;
import com.example.foodplanner.meal.presenter.PresenterMeal;
import com.example.foodplanner.searchresult.presenter.NetworkDelegateSearchResult;
import com.example.foodplanner.serach.presenter.NetworkDelegateSearch;

public interface RemoteDataSource {

    void clearDisposable();
    void getCuisines(NetworkDelegateSearch delegate);

    void getIngredient(NetworkDelegateSearch networkDelegateSearch);

    void getCountry(NetworkDelegateSearch networkDelegateSearch);

    void getSearchList(NetworkDelegateSearch networkDelegateSearch,String name);

    void getSearchResultList(String name,NetworkDelegateSearchResult networkDelegateSearchResult);

    void getSearchListCountry(String name,NetworkDelegateSearchResult  networkDelegateSearchResult);

    void getSearchListCuisines(String name,NetworkDelegateSearchResult  networkDelegateSearchResult);

    void getSearchListIngredient(String name, NetworkDelegateSearchResult  networkDelegateSearchResult);

    void get(NetworkDelegateSearchResult nada);//3

    void getRandomMeal(NetworkDelegateRandomMeal networkDelegateRandomMeal );


    void getMealSearchPlan(String query, NetworkDelegateSearchPlan networkDelegateSearchPlan);

    void getSearchList(String newText, NetworkDelegateSearchPlan networkDelegateSearchPlan);

    void getMeal(String id, NetworkDelegateMeal networkDelegateMeal);

}
