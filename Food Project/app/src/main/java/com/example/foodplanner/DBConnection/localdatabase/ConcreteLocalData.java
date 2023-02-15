package com.example.foodplanner.DBConnection.localdatabase;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.DBConnection.DBModel.FavoriteMeal;
import com.example.foodplanner.DBConnection.DBModel.PlanMeal;
import com.example.foodplanner.plan.presenter.NetworkDelegatePlan;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ConcreteLocalData implements LocalDataSource{
    private final Context context;
    MealDAO dao;

    private static ConcreteLocalData concreteLocalData= null;

    CompositeDisposable disposable;
    private ConcreteLocalData(Context context){
        this.context=context;
        DBFood db= DBFood.getInstance(this.context.getApplicationContext());
        dao=db.mealDAO();

        disposable=new CompositeDisposable();
    }
    public static ConcreteLocalData getInstance(Context context){
        if (concreteLocalData==null)concreteLocalData=new ConcreteLocalData(context);
        return concreteLocalData;
    }
    public void deleteProduct(FavoriteMeal meal){
        new Thread(()->{dao.deleteProduct(meal);}).start();

    }
    public void insertProduct(FavoriteMeal meal){
        new Thread(()->{dao.insertProduct(meal);}).start();
    }

    @Override
    public void getPlanMeals(String email,NetworkDelegatePlan networkDelegatePlan) {
        dao.getPlanMeals(email).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<PlanMeal>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<PlanMeal> planMeals) {
                        networkDelegatePlan.onResponse(planMeals);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @Override
    public void deletePlanMeal(String idMeal, String email, NetworkDelegatePlan networkDelegatePlan) {
        dao.deletePlanMeal(idMeal,email).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        networkDelegatePlan.onComplete();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegatePlan.onError(e.getMessage().toString());
                    }
                });
    }

    @Override
    public void clear() {
         disposable.clear();
    }
}
