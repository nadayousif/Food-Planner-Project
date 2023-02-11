package com.example.foodplanner.APIconnection;

import com.example.foodplanner.Model.Meal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClient implements RemoteDataSource{
    private static final String BASE_URL ="www.themealdb.com/api/json/v1/1";

    private static RetrofitClient instance = null;
    private Service service;
    static CompositeDisposable disposable;
    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service =retrofit.create(Service.class);
    }
    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }
   private Service getService() {
        return service;
    }

    private void getCuisines(NetworkDelegate notifyApi){
        getService().getCuisines().subscribeOn(Schedulers.io()).map(i->i.getList()).flatMap(i->Observable.fromIterable(i)).
                map(i->i.getStrMeal()).subscribe(new Observer<String>() {
                    List<String> names=new ArrayList<>();
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        names.add(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        notifyApi.onResponse(names);
                    }
                });

    }

    @Override
    public void enqueueCall(NetworkDelegate networkDelegate) {

    }

    @Override
    public void clearDisposable() {
        disposable.clear();

    }

}
