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

public class FavoriteFragment extends Fragment {
    private FragmentFavoriteBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentFavoriteBinding.inflate(getLayoutInflater(),container,false);
        View view=binding.getRoot();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}