package com.example.foodplanner.APIconnection;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MyObject;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("list.php?c=list")
    Observable<MyObject> getCuisines();
}
