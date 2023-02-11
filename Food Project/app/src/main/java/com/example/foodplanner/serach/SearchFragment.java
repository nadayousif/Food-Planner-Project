package com.example.foodplanner.serach;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodplanner.searchresult.SearchResultActivity;
import com.example.foodplanner.databinding.FragmentSearchBinding;
import com.google.android.material.tabs.TabLayout;


public class SearchFragment extends Fragment implements OnClickItem {

    private FragmentSearchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.recCatogryItems.setLayoutManager(new GridLayoutManager(getContext(), 2));
        AdapterCategory adapterCategory =new AdapterCategory(this);
        binding.recCatogryItems.setAdapter(adapterCategory);
        adapterCategory.setStrings(new String[]{"Beef","Breakfast","Chicken","Dessert","Beef","Breakfast","Chicken","Dessert","Beef","Breakfast","Chicken","Dessert","Beef","Breakfast","Chicken","Dessert"});
        adapterCategory.notifyDataSetChanged();
        binding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getContext(), tab.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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