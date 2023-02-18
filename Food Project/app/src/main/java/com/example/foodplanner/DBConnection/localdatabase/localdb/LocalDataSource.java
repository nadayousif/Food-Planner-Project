package com.example.foodplanner.DBConnection.localdatabase.localdb;

import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.favorite.presenter.NetworkDelegateFavMeal;
import com.example.foodplanner.favorite.presenter.PresenterFavoriteMeal;

import com.example.foodplanner.meal.OnViewClickFavorite;
import com.example.foodplanner.meal.presenter.NetworkDelegateMeal;
import com.example.foodplanner.meal.presenter.PresenterMeal;
import com.example.foodplanner.plan.dialog.favorite.presenter.NetworkDelegateFavDialog;
import com.example.foodplanner.plan.dialog.search.presenter.NetworkDelegateSearchPlan;
import com.example.foodplanner.plan.presenter.NetworkDelegatePlan;
import com.example.foodplanner.searchresult.OnViewClickSearchPlan;
import com.example.foodplanner.searchresult.presenter.NetworkDelegateSearchResult;

import java.util.List;

public interface LocalDataSource {


    void getMeal(String email, NetworkDelegatePlan networkDelegatePlan);

    void deleteMeal(String idMeal, String email, NetworkDelegatePlan networkDelegatePlan);

    void clear();

    void setMeals(NetworkDelegateSearchPlan NetworkDelegateSearchPlan, List<Meal> listMeals);

    void addToPlan(FavoriteMeal tag, OnViewClickSearchPlan onViewClickSearchPlan, NetworkDelegateSearchResult networkDelegateSearchResult);

    void removeFav(FavoriteMeal meal, OnViewClickSearchPlan onViewClickSearchPlan,NetworkDelegateSearchResult networkDelegateSearchResult);

    void getListFav(String email, NetworkDelegateFavDialog networkDelegateFavDialog);
     void setMealsFromFav(NetworkDelegateFavDialog networkDelegateFavDialog, List<Meal> listMeals);

    void getListFavMeal(String email, NetworkDelegateFavMeal networkDelegateFavMeal);

    void setMealsFromFavMeal(NetworkDelegateFavMeal networkDelegateFavMeal, List<Meal> list);

    void addToPlan(Meal meal);

    void deleteMealFormPlan(Meal meal);

    void insertMeal(Meal meal);

    void deleteMealFromFavorite(String idMeal, String email, NetworkDelegateFavMeal networkDelegateFavMeal);

    void addToFavorite(FavoriteMeal tag, NetworkDelegateMeal networkDelegateMeal);

    void removeFromFavorite(FavoriteMeal meal, NetworkDelegateMeal networkDelegateMeal);
}
