package com.example.foodplanner.APIconnection;

import com.example.foodplanner.searchresult.presenter.NetworkDelegateSearchResult;
import com.example.foodplanner.searchresult.presenter.PresenterSearchResult;
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
}
