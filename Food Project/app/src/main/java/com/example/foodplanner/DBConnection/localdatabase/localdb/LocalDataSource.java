package com.example.foodplanner.DBConnection.localdatabase.localdb;

import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.plan.dialog.favorite.NetworkDelegateFavDialog;
import com.example.foodplanner.plan.dialog.favorite.PresenterFavoriteDialog;
import com.example.foodplanner.plan.dialog.search.presenter.NetworkDelegateSearchPlan;
import com.example.foodplanner.plan.presenter.NetworkDelegatePlan;
import com.example.foodplanner.searchresult.OnViewClickSearchPlan;
import com.example.foodplanner.searchresult.presenter.NetworkDelegateSearchResult;

import java.util.List;

public interface LocalDataSource {
    void deleteProduct(Meal meal);

    void insertProduct(Meal  meal);

    void getMeal(String email, NetworkDelegatePlan networkDelegatePlan);

    void deleteMeal(String idMeal, String email, NetworkDelegatePlan networkDelegatePlan);

    void clear();

    void setMeals(NetworkDelegateSearchPlan NetworkDelegateSearchPlan, List<Meal> listMeals);

    void addToFav(FavoriteMeal tag, OnViewClickSearchPlan onViewClickSearchPlan, NetworkDelegateSearchResult networkDelegateSearchResult);

    void removeFav(FavoriteMeal meal, OnViewClickSearchPlan onViewClickSearchPlan,NetworkDelegateSearchResult networkDelegateSearchResult);

    void getListFav(String email, NetworkDelegateFavDialog networkDelegateFavDialog);
     void setMealsFromFav(NetworkDelegateFavDialog networkDelegateFavDialog, List<Meal> listMeals);

}
