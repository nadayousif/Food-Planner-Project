package com.example.foodplanner.searchresult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.content.Intent;
import android.os.Bundle;

import com.example.foodplanner.Model.RandomMeal;
import com.example.foodplanner.databinding.ActivitySearchResultBinding;
import com.example.foodplanner.meal.MealActivity;

import java.util.ArrayList;
public class SearchResultActivity extends AppCompatActivity implements OnClickItem{
    private ActivitySearchResultBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySearchResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<RandomMeal> list=new ArrayList<>();
        list.add(new RandomMeal("2343","Breakfast Potatoes","slkdfjklsdf"));
        list.add(new RandomMeal("2343","Breakfast Potatoes","slkdfjklsdf"));
        list.add(new RandomMeal("2343","Breakfast Potatoes","slkdfjklsdf"));
        binding.recSearchResult.setLayoutManager(new LinearLayoutManager(this));
        binding.recSearchResult.setAdapter(new AdapterSearchResult(this,list));


    }

    @Override
    public void onClick(String id) {
        Intent intent=new Intent(this, MealActivity.class);
        startActivity(intent);
    }
}