package com.example.foodplanner.serach;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodplanner.APIconnection.RetrofitClient;
import com.example.foodplanner.helper.CheckConnection;
import com.example.foodplanner.R;
import com.example.foodplanner.helper.MySharedPreference;
import com.example.foodplanner.searchresult.SearchResultActivity;
import com.example.foodplanner.databinding.FragmentSearchBinding;
import com.example.foodplanner.serach.category.AdapterCategory;
import com.example.foodplanner.serach.category.OnClickItemCategory;
import com.example.foodplanner.serach.historyserach.AdapterHistory;
import com.example.foodplanner.serach.historyserach.OnClickItemHistory;
import com.example.foodplanner.serach.presenter.CommunicationSearch;
import com.example.foodplanner.serach.presenter.PresenterSearch;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class SearchFragment extends Fragment implements OnClickItemCategory, CommunicationSearch, OnClickItemHistory {

    private FragmentSearchBinding binding;
    PresenterSearch presenterSearch;
    AdapterCategory adapterCategory;
    AdapterHistory adapterHistory;
    String typeOfTab;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.recCatogryItems.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapterCategory = new AdapterCategory(this);
        binding.recCatogryItems.setAdapter(adapterCategory);

        binding.recLastSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterHistory = new AdapterHistory(this);
        binding.recLastSearch.setAdapter(adapterHistory);
        typeOfTab = "Ingredient";
        if (CheckConnection.isConnect(getContext())) {
            presenterSearch = new PresenterSearch(RetrofitClient.getInstance(), this);
            presenterSearch.getIngredient();
            binding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    typeOfTab = (String) tab.getText();
                    if (tab.getText().equals("Ingredient")) presenterSearch.getIngredient();
                    else if (tab.getText().equals("Country")) presenterSearch.getCountry();
                    else presenterSearch.getCuisines();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            binding.searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        showHistorySearch();
                        showHistorySearchList();
                        Toast.makeText(getContext(), "hi", Toast.LENGTH_SHORT).show();

                    } else {
                        hiddenHistorySearch();
                    }
                }
            });
            binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    saveIntoShare(query);
                    Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                    intent.putExtra(getString(R.string.resultSearch), query);
                    intent.putExtra(getString(R.string.typeOfSearch), getString(R.string.searchByName));
                    startActivity(intent);

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (!newText.isEmpty()) {
                        presenterSearch.search(newText);
                    } else {
                        showHistorySearchList();
                    }

                    return false;
                }
            });

        } else
            Snackbar.make(getActivity().findViewById(android.R.id.content), "no internet connection pls try again ", Snackbar.LENGTH_LONG)
                    .show();


        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        presenterSearch.clear();
    }

    @Override
    public void onClick(String id) {
        Intent intent = new Intent(getContext(), SearchResultActivity.class);
        intent.putExtra(getString(R.string.resultSearch), id);
        if (typeOfTab.equals("Ingredient"))
            intent.putExtra(getString(R.string.typeOfSearch), getString(R.string.searchByIngredient));
        else if (typeOfTab.equals("Country"))
            intent.putExtra(getString(R.string.typeOfSearch), getString(R.string.searchByCountry));
        else
            intent.putExtra(getString(R.string.typeOfSearch), getString(R.string.searchByCuisines));
        startActivity(intent);

    }

    @Override
    public void setList(List<String> names) {
        if (names != null) {
            adapterCategory.setStrings(names);
            adapterCategory.notifyDataSetChanged();
        }
    }

    @Override
    public void setError(String msg) {
        Snackbar.make(binding.getRoot(), "something wrong pls try again", Snackbar.LENGTH_LONG)
                .show();
        Log.i("TAGG", "setError: " + msg);
    }

    @Override
    public void setListSearch(String[] names) {
        if (names != null) {
            adapterHistory.setStrings(names);
            adapterHistory.notifyDataSetChanged();
        }
    }

    void showHistorySearch() {
        binding.recLastSearch.setVisibility(View.VISIBLE);
        binding.recCatogryItems.setVisibility(View.GONE);
        binding.tablayout.setVisibility(View.GONE);
        binding.divider2.setVisibility(View.GONE);

    }

    void showHistorySearchList() {
        String[] arr = new String[0];
        String s = MySharedPreference.getListOfHistory(getActivity());
        if (!s.isEmpty()) {
            List<String> list = Arrays.stream(s.split(",")).filter(i -> !i.isEmpty()).collect(Collectors.toList());
            Collections.reverse(list);
            if (list.size() > 10)
                list=list.subList(0, 10);
            arr = list.stream().toArray(String[]::new);

        }
        adapterHistory.setStrings(arr);
        adapterHistory.notifyDataSetChanged();
    }

    void hiddenHistorySearch() {
        binding.recLastSearch.setVisibility(View.GONE);
        binding.recCatogryItems.setVisibility(View.VISIBLE);
        binding.tablayout.setVisibility(View.VISIBLE);
        binding.divider2.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClickHistory(String name) {
        saveIntoShare(name);
        Intent intent = new Intent(getContext(), SearchResultActivity.class);
        intent.putExtra(getString(R.string.resultSearch), name);
        intent.putExtra(getString(R.string.typeOfSearch), getString(R.string.searchByName));
        startActivity(intent);
    }

    void saveIntoShare(String name) {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String s = sharedPref.getString(getString(R.string.historySearch), "");
        boolean status = !Arrays.stream(s.split(",")).filter(i -> !i.isEmpty()).anyMatch(i -> i.equals(name));
        if (status)
            s += name + ",";
        editor.putString(getString(R.string.historySearch), s);
        editor.apply();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterSearch.clear();
    }
}