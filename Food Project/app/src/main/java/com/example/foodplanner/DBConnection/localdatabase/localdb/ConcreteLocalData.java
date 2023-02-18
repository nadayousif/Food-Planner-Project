package com.example.foodplanner.DBConnection.localdatabase.localdb;

import android.content.Context;
import android.util.Log;
import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.favorite.presenter.NetworkDelegateFavMeal;
import com.example.foodplanner.plan.dialog.favorite.NetworkDelegateFavDialog;
import com.example.foodplanner.plan.dialog.favorite.presenter.NetworkDelegateFavDialog;
import com.example.foodplanner.plan.dialog.search.presenter.NetworkDelegateSearchPlan;
import com.example.foodplanner.plan.presenter.NetworkDelegatePlan;
import com.example.foodplanner.searchresult.OnViewClickSearchPlan;
import com.example.foodplanner.searchresult.presenter.NetworkDelegateSearchResult;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ConcreteLocalData implements LocalDataSource {
    private static final String TAG = "TAGG";
    private final Context context;
    MealDAO dao;

    private static ConcreteLocalData concreteLocalData = null;

    CompositeDisposable disposable;

    public ConcreteLocalData(Context context) {
        this.context = context;
        DBFood db = DBFood.getInstance(this.context.getApplicationContext());
        dao = db.mealDAO();

        disposable = new CompositeDisposable();
    }

    public static ConcreteLocalData getInstance(Context context) {
        if (concreteLocalData == null) concreteLocalData = new ConcreteLocalData(context);
        return concreteLocalData;
    }

    }
    public void insertMeal(Meal meal){
        dao.insertMeal(meal).subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {
                

            }
        });
    }


    @Override
    public void getMeal(String email, NetworkDelegatePlan networkDelegatePlan) {
        dao.getPlanMeals(email).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Meal>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<Meal> planMeals) {
                        if (planMeals != null) {
//                            Log.i(TAG, "onSuccess: "+planMeals.get(0).getIdMeal());
                            networkDelegatePlan.onResponse(planMeals);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegatePlan.onError(e.getMessage());
                    }
                });
    }

    @Override
    public void deleteMeal(String idMeal, String email, NetworkDelegatePlan networkDelegatePlan) {
        dao.deletePlanMeal(idMeal, email).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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

    @Override
    public void setMeals(NetworkDelegateSearchPlan networkDelegateSearchPlan, List<Meal> listMeals) {
        dao.setMeals(listMeals).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        networkDelegateSearchPlan.onComplete();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegateSearchPlan.onFailure(e.getMessage().toString());
                        Log.i(TAG, "onError: " + e.getMessage());
                    }
                });
    }

    @Override
    public void addToPlan(FavoriteMeal tag, OnViewClickSearchPlan onViewClickSearchPlan, NetworkDelegateSearchResult networkDelegateSearchResult) {
        dao.addToFav(tag).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(disposable);
                    }

                    @Override
                    public void onComplete() {
                        networkDelegateSearchResult.sus(onViewClickSearchPlan);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegateSearchResult.onFailureToAdd(onViewClickSearchPlan, e.getMessage());
                    }
                });
    }

    @Override
    public void removeFav(FavoriteMeal meal, OnViewClickSearchPlan onViewClickSearchPlan, NetworkDelegateSearchResult delegateSearchResult) {
        dao.removeFav(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(disposable);
                    }

                    @Override
                    public void onComplete() {
                        delegateSearchResult.sus(onViewClickSearchPlan);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        delegateSearchResult.onFailureToAdd(onViewClickSearchPlan, e.getMessage());
                    }
                });
    }

    @Override
    public void getListFav(String email, NetworkDelegateFavDialog networkDelegateFavDialog) {
        dao.getFavoriteMeals(email).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FavoriteMeal>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<FavoriteMeal> favoriteMeals) {
                        networkDelegateFavDialog.setList(favoriteMeals);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegateFavDialog.onError(e.getMessage());
                    }
                });
    }


    @Override
    public void getListFavMeal(String email, NetworkDelegateFavMeal networkDelegateFavMeal) {
        dao.getFavoriteMeals(email).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FavoriteMeal>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<FavoriteMeal> favoriteMeals) {
                        networkDelegateFavMeal.setList(favoriteMeals);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegateFavMeal.onError(e.getMessage());
                    }
                });
    }
    @Override
    public void setMealsFromFav(NetworkDelegateFavDialog networkDelegateFavDialog, List<Meal> listMeals) {
        dao.setMeals(listMeals).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        networkDelegateFavDialog.sus();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegateFavDialog.onError(e.getMessage().toString());
                        Log.i(TAG, "onError: " + e.getMessage());
                    }
                });
    }

    @Override
    public void addToPlan(Meal meal) {
        dao.addMealPlan(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @Override
    public void deleteMealFormPlan(Meal meal) {
        dao.deleteMealPlan(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
    @Override
    public void setMealsFromFavMeal(NetworkDelegateFavMeal networkDelegateFavMeal, List<Meal> listMeals) {
        dao.setMeals(listMeals).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        networkDelegateFavMeal.sus();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegateFavMeal.onError(e.getMessage().toString());
                        Log.i(TAG, "onError: "+e.getMessage());
                    }
                });
    }
}
