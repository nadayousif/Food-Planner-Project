package com.example.foodplanner.searchresult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.foodplanner.APIconnection.RetrofitClient;
import com.example.foodplanner.DBConnection.localdatabase.localdb.ConcreteLocalData;
import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.databinding.ActivitySearchResultBinding;
import com.example.foodplanner.helper.CheckConnection;
import com.example.foodplanner.helper.Converter;
import com.example.foodplanner.helper.MyUser;
import com.example.foodplanner.meal.MealActivity;
import com.example.foodplanner.profile.FirebaseDataBase;
import com.example.foodplanner.profile.MealFirebase;
import com.example.foodplanner.searchresult.presenter.CommunicationSearchResult;
import com.example.foodplanner.searchresult.presenter.PresenterSearchResult;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SearchResultActivity extends AppCompatActivity implements OnClickItem, CommunicationSearchResult {
    private static final String TAG = "TAGG";
    private ActivitySearchResultBinding binding;
    AdapterSearchResult adapterSearchResult;
PresenterSearchResult presenterSearchResult;
    private int progress;
    private int amount;
    private Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent() != null && CheckConnection.isConnect(this)) {
            presenterSearchResult = new PresenterSearchResult(RetrofitClient.getInstance(), this, ConcreteLocalData.getInstance(this));
            String name = getIntent().getStringExtra(getString(R.string.resultSearch));
            presenterSearchResult.showNada();//1st
            String typeOfSearch = getIntent().getStringExtra(getString(R.string.typeOfSearch));

            binding.tvSearchResultName.setText(name);

            if (typeOfSearch.equals(getString(R.string.searchByName)))
                presenterSearchResult.getList(name);
            else if (typeOfSearch.equals(getString(R.string.searchByCountry)))
                presenterSearchResult.getCountryList(name);
            else if (typeOfSearch.equals(getString(R.string.searchByCuisines)))
                presenterSearchResult.getCuisinesList(name);
            else
                presenterSearchResult.getIngredient(name);
        }
        else {
            Snackbar.make(findViewById(android.R.id.content), "no internet connection pls try again ", Snackbar.LENGTH_LONG)
                    .show();
        }
        binding.recSearchResult.setLayoutManager(new GridLayoutManager(this,2));
        adapterSearchResult = new AdapterSearchResult(this,presenterSearchResult);
        binding.recSearchResult.setAdapter(adapterSearchResult);


    }

    @Override
    public void onClick(String id) {
        Intent intent = new Intent(this, MealActivity.class);
        intent.putExtra(getString(R.string.mealID),id);
        intent.putExtra(getString(R.string.isLocal),false);
        startActivity(intent);
    }

    @Override
    public void addToFav(OnViewClickSearchPlan onViewClickSearchPlan, Meal tag) {
        this.meal=tag;
        FavoriteMeal meal=Converter.convertMealToFav(tag);
        meal.setEmail(MyUser.getInstance().getEmail());
        presenterSearchResult.addToFav(meal,onViewClickSearchPlan);

        
    }

    @Override
    public void removeFromFav(OnViewClickSearchPlan onViewClickSearchPlan, Meal tag) {
        this.meal=tag;
        FavoriteMeal meal=Converter.convertMealToFav(tag);
        meal.setEmail(MyUser.getInstance().getEmail());
        presenterSearchResult.removeFromFav(meal,onViewClickSearchPlan);
        MealFirebase fireBaseRecord = new MealFirebase();
        fireBaseRecord.setIdMeal(meal.getIdMeal());
        fireBaseRecord.setEmail(meal.getEmail());
        fireBaseRecord.setStrArea(meal.getStrArea());
        fireBaseRecord.setStrCategory(meal.getStrCategory());
        fireBaseRecord.setStrMeal(meal.getStrMeal());
        fireBaseRecord.setStrIngredient(meal.getStrIngredient());
        fireBaseRecord.setStrInstructions(meal.getStrInstructions());
        fireBaseRecord.setStrMealThumb(meal.getStrMealThumb());
        fireBaseRecord.setStrYoutube(meal.getStrYoutube());
        fireBaseRecord.setIngredients(meal.getIngredients());
        fireBaseRecord.setMeasures(meal.getMeasures());
        fireBaseRecord.setImages(IntStream.range(0, meal.getImage().length).mapToObj(i -> (int) meal.getImage()[i]).collect(Collectors.toList()));
        FirebaseDataBase.removeFavouriteFromFirebase(this,fireBaseRecord);
    }

    @Override
    public void setList(List<Meal> list) {
        binding.recSearchResult.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
        adapterSearchResult.setArr(list);
        adapterSearchResult.notifyDataSetChanged();
    }

    @Override
    public void setError(String message) {
        Snackbar.make(binding.getRoot(), "something wrong pls try again", Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void nadaTwo() {

    }//9

    @Override
    public void susToAdd(OnViewClickSearchPlan onViewClickSearchPlan) {
        onViewClickSearchPlan.setEnable();
        MealFirebase fireBaseRecord = new MealFirebase();
        fireBaseRecord.setIdMeal(meal.getIdMeal());
        fireBaseRecord.setDay(meal.getDay());
        fireBaseRecord.setEmail(meal.getEmail());
        fireBaseRecord.setStrArea(meal.getStrArea());
        fireBaseRecord.setStrCategory(meal.getStrCategory());
        fireBaseRecord.setStrMeal(meal.getStrMeal());
        fireBaseRecord.setStrIngredient(meal.getStrIngredient());
        fireBaseRecord.setStrInstructions(meal.getStrInstructions());
        fireBaseRecord.setStrMealThumb(meal.getStrMealThumb());
        fireBaseRecord.setStrYoutube(meal.getStrYoutube());
        fireBaseRecord.setIngredients(meal.getIngredients());
        fireBaseRecord.setMeasures(meal.getMeasures());
        fireBaseRecord.setImages(IntStream.range(0, meal.getImage().length).mapToObj(i -> (int) meal.getImage()[i]).collect(Collectors.toList()));
        FirebaseDataBase.addPlanToFirebase(this,fireBaseRecord);
    }

    @Override
    public void onFailureToAdd(OnViewClickSearchPlan onViewClickSearchPlan, String message) {
        onViewClickSearchPlan.setEnable();
        onViewClickSearchPlan.undo();
        Log.i(TAG, "onFailureToAdd: "+message);
        Toast.makeText(this, "failure pls try again", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSize(int size) {
        amount=100/(size*2);
        progress=amount;
    }

    @Override
    public void upDateProgressBar() {

        if (progress<100) {
            binding.progressBar.setProgress(progress);
            progress+=amount;
        }
    }


}