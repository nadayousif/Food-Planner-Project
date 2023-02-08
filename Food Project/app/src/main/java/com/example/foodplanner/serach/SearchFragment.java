package com.example.foodplanner.serach;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.foodplanner.searchresult.SearchResultActivity;
import com.example.foodplanner.databinding.FragmentSearchBinding;


public class SearchFragment extends Fragment implements OnClickItem {

    private FragmentSearchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.recSearchCountries.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        binding.recSearchCountries.setAdapter(new AdapterSearch(this,new String[]{"Beef","Breakfast","Chicken","Dessert","Beef","Breakfast","Chicken","Dessert","Beef","Breakfast","Chicken","Dessert","Beef","Breakfast","Chicken","Dessert"}));
        binding.recSearchIngredients.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        binding.recSearchIngredients.setAdapter(new AdapterSearch(this,new String[]{"Beef","Breakfast","Chicken","Dessert","Beef","Breakfast","Chicken","Dessert","Beef","Breakfast","Chicken","Dessert","Beef","Breakfast","Chicken","Dessert"}));
        binding.recSearchCuisines.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        binding.recSearchCuisines.setAdapter(new AdapterSearch(this,new String[]{"Beef","Breakfast","Chicken","Dessert","Beef","Breakfast","Chicken","Dessert","Beef","Breakfast","Chicken","Dessert","Beef","Breakfast","Chicken","Dessert"}));


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(String id) {
        Intent intent=new Intent(getContext(), SearchResultActivity.class);
        startActivity(intent);

    }
}