package com.example.foodplanner.favorite;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.Model.RandomMeal;
import com.example.foodplanner.R;
import com.example.foodplanner.databinding.FragmentFavoriteBinding;
import com.example.foodplanner.meal.MealActivity;
import com.example.foodplanner.searchresult.AdapterSearchResult;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment implements OnClickItem{

    private FragmentFavoriteBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentFavoriteBinding.inflate(getLayoutInflater(),container,false);
        View view=binding.getRoot();

        binding.rvFavorite.setLayoutManager(new LinearLayoutManager(container.getContext()));
        ArrayList<RandomMeal> list=new ArrayList<>();
        list.add(new RandomMeal("2343","Breakfast Potatoes","slkdfjklsdf"));
        list.add(new RandomMeal("2343","Breakfast Potatoes","slkdfjklsdf"));
        list.add(new RandomMeal("2343","Breakfast Potatoes","slkdfjklsdf"));
        binding.rvFavorite.setAdapter(new AdapterFavorite(this,list));

        return view;
    }

    @Override
    public void onClick(String id) {

        Intent intent=new Intent(getContext(), MealActivity.class);
        startActivity(intent);

    }
}