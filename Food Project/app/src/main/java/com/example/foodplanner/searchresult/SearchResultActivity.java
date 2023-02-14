package com.example.foodplanner.searchresult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.content.Intent;
import android.os.Bundle;

import com.example.foodplanner.APIconnection.RetrofitClient;
import com.example.foodplanner.helper.CheckConnection;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.databinding.ActivitySearchResultBinding;
import com.example.foodplanner.meal.MealActivity;
import com.example.foodplanner.searchresult.presenter.CommunicationSearchResult;
import com.example.foodplanner.searchresult.presenter.PresenterSearchResult;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity implements OnClickItem, CommunicationSearchResult {
    private ActivitySearchResultBinding binding;
    AdapterSearchResult adapterSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent() != null && CheckConnection.isConnect(this)) {
            PresenterSearchResult presenterSearchResult = new PresenterSearchResult(RetrofitClient.getInstance(), this);
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
        binding.recSearchResult.setLayoutManager(new LinearLayoutManager(this));
        adapterSearchResult = new AdapterSearchResult(this);
        binding.recSearchResult.setAdapter(adapterSearchResult);


    }

    @Override
    public void onClick(String id) {
        Intent intent = new Intent(this, MealActivity.class);
        startActivity(intent);
    }

    @Override
    public void setList(List<Meal> list) {
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
}