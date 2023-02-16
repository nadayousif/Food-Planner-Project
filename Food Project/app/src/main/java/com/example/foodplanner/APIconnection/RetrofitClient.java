package com.example.foodplanner.APIconnection;

import android.util.Log;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MyObject;
import com.example.foodplanner.Model.RandomMeal;
import com.example.foodplanner.home.presenter.NetworkDelegateRandomMeal;
import com.example.foodplanner.plan.dialog.search.presenter.NetworkDelegateSearchPlan;
import com.example.foodplanner.plan.dialog.search.presenter.presenterSearchDialog;
import com.example.foodplanner.searchresult.presenter.NetworkDelegateSearchResult;
import com.example.foodplanner.serach.presenter.NetworkDelegateSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements RemoteDataSource {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    private static RetrofitClient instance = null;
    private Service service;
    static CompositeDisposable disposable;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build();
        service = retrofit.create(Service.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        disposable = new CompositeDisposable();
        return instance;
    }

    private Service getService() {
        return service;
    }


    @Override
    public void getCuisines(NetworkDelegateSearch notifyApi) {
        Log.i("TAGG", "getCuisines: ");
        getService().getCuisines().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<MyObject>() {
            List<String> names = new ArrayList<>();

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull MyObject myObject) {
                names = myObject.getList().stream().map(i -> i.getStrCategory()).collect(Collectors.toList());
                notifyApi.onResponse(names);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                notifyApi.onFailure(e.getMessage());
            }
        });


    }

    @Override
    public void getIngredient(NetworkDelegateSearch networkDelegateSearch) {
        getService().getIngredient().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<MyObject>() {
            List<String> names = new ArrayList<>();

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull MyObject myObject) {
                names = myObject.getList().stream().map(i -> i.getStrIngredient()).collect(Collectors.toList());
                networkDelegateSearch.onResponse(names);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                networkDelegateSearch.onFailure(e.getMessage());
            }
        });


    }

    @Override
    public void getCountry(NetworkDelegateSearch networkDelegateSearch) {
        getService().getCountry().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<MyObject>() {
            List<String> names = new ArrayList<>();

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull MyObject myObject) {
                names = myObject.getList().stream().map(i -> i.getStrArea()).collect(Collectors.toList());
                networkDelegateSearch.onResponse(names);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                networkDelegateSearch.onFailure(e.getMessage());
            }
        });
    }

    @Override
    public void getSearchList(NetworkDelegateSearch networkDelegateSearch, String name) {

        getService().getSearchList(name).subscribeOn(Schedulers.io()).debounce(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<MyObject>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull MyObject myObject) {
                        if (myObject.getList() != null) {
                            String[] names = myObject.getList().stream().map(Meal::getStrMeal).toArray(String[]::new);
                            networkDelegateSearch.onResponseSearch(names);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegateSearch.onFailure(e.getMessage());
                        Log.i("TAGG", "onError: " + e.fillInStackTrace());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getSearchResultList(String name, NetworkDelegateSearchResult networkDelegateSearchResult) {
        getService().getSearchList(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MyObject>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onNext(@NonNull MyObject myObject) {
                if (myObject.getList() != null)
                    networkDelegateSearchResult.onResponse(myObject.getList());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                networkDelegateSearchResult.onFailure(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getSearchListCountry(String name, NetworkDelegateSearchResult networkDelegateSearchResult) {
        getService().getSearchListCountry(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MyObject>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull MyObject myObject) {
                        if (myObject.getList() != null) {
                            networkDelegateSearchResult.onResponse(myObject.getList());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegateSearchResult.onFailure(e.getMessage());
                    }
                });
    }

    @Override
    public void getSearchListCuisines(String name, NetworkDelegateSearchResult networkDelegateSearchResult) {
        getService().getSearchListCuisines(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MyObject>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull MyObject myObject) {
                        if (myObject.getList() != null) {
                            networkDelegateSearchResult.onResponse(myObject.getList());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegateSearchResult.onFailure(e.getMessage());
                    }
                });
    }

    @Override
    public void getSearchListIngredient(String name, NetworkDelegateSearchResult networkDelegateSearchResult) {
        getService().getSearchListIngredient(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MyObject>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull MyObject myObject) {
                        if (myObject.getList() != null) {
                            networkDelegateSearchResult.onResponse(myObject.getList());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegateSearchResult.onFailure(e.getMessage());
                    }
                });
    }

    @Override
    public void get(NetworkDelegateSearchResult NADA) {
        NADA.onNada();//5
    }//4

    @Override

    public void getRandomMeal(NetworkDelegateRandomMeal networkDelegateRandomMeal) {
        getService().getRandomMeal().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MyObject>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull MyObject myObject) {
                        if (myObject.getList() != null) {
                            Meal randomMeal = myObject.getList().get(0);
                            String idMeal = randomMeal.getIdMeal();
                            String strMeal = randomMeal.getStrMeal();
                            String strMealThumb = randomMeal.getStrMealThumb();
                            networkDelegateRandomMeal.onResponseRandomMeal(idMeal, strMeal, strMealThumb);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegateRandomMeal.onFailure(e.getMessage());
                        Log.i("TAGG", "onError: " + e.fillInStackTrace());

                    }
                });
              /*  .subscribe(new SingleObserver<RandomMeal>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull RandomMeal randomMeal) {
                        if (randomMeal.getIdMeal() != null&& randomMeal.getStrMeal() != null&&randomMeal.getStrMealThumb() != null){
                            String idMeal=randomMeal.getIdMeal();
                            String strMeal=randomMeal.getStrMeal();
                            String strMealThumb=randomMeal.getStrMealThumb();
                            networkDelegateRandomMeal.onResponseRandomMeal(idMeal,strMeal,strMealThumb);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegateRandomMeal.onFailure(e.getMessage());
                        Log.i("TAGG", "onError: " + e.fillInStackTrace());
                    }
                });*/


                /*subscribe(new Observer<RandomMeal>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull RandomMeal randomMeal) {
                        if (randomMeal.getIdMeal() != null&& randomMeal.getStrMeal() != null&&randomMeal.getStrMealThumb() != null){
                            String idMeal=randomMeal.getIdMeal();
                            String strMeal=randomMeal.getStrMeal();
                            String strMealThumb=randomMeal.getStrMealThumb();
                            networkDelegateRandomMeal.onResponseRandomMeal(idMeal,strMeal,strMealThumb);
                        }
                    }



                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegateRandomMeal.onFailure(e.getMessage());
                        Log.i("TAGG", "onError: " + e.fillInStackTrace());
                    }

                    @Override
                    public void onComplete() {

                    }
                });*/

    }

    @Override
    public void getMealSearchPlan(String query, NetworkDelegateSearchPlan networkDelegateSearchPlan) {
        getService().getSearchList(query).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyObject>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull MyObject myObject) {
                        if(myObject.getList()!=null)
                            networkDelegateSearchPlan.onResponseListOfMeals(myObject.getList());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegateSearchPlan.onFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getSearchList(String newText, NetworkDelegateSearchPlan networkDelegateSearchPlan) {
        getService().getSearchList(newText).subscribeOn(Schedulers.io()).debounce(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<MyObject>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull MyObject myObject) {
                        if (myObject.getList() != null) {
                            String[] names = myObject.getList().stream().map(Meal::getStrMeal).toArray(String[]::new);
                            networkDelegateSearchPlan.onResponseSearch(names);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        networkDelegateSearchPlan.onFailure(e.getMessage());
                        Log.i("TAGG", "onError: " + e.fillInStackTrace());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    @Override
    public void clearDisposable() {
        disposable.clear();

    }


}
