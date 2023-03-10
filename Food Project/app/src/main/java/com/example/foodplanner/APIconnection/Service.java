package com.example.foodplanner.APIconnection;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MyObject;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface Service {
    @GET("list.php?c=list")
    Single<MyObject> getCuisines();

    @GET("list.php?a=list")
    Single<MyObject> getCountry();
    @GET("list.php?i=list")
    Single<MyObject> getIngredient();

    @GET("filter.php")
    Observable<MyObject> getSearchListCountry(@Query("a") String name);

    @GET("filter.php")
    Observable<MyObject> getSearchListIngredient(@Query("i") String name);

    @GET("filter.php")
    Observable<MyObject> getSearchListCuisines(@Query("c") String name);

    @GET("search.php")
    Observable<MyObject> getSearchList(@Query("s") String name);
    @GET("random.php")
    Single<MyObject> getRandomMeal();
    @GET("lookup.php")
    Single<MyObject> getMeal(@Query("i") String id);
}
