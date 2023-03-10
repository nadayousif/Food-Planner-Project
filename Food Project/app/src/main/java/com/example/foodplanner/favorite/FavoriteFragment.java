package com.example.foodplanner.favorite;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.foodplanner.APIconnection.RetrofitClient;
import com.example.foodplanner.DBConnection.localdatabase.localdb.ConcreteLocalData;
import com.example.foodplanner.Model.FavoriteMeal;

import com.example.foodplanner.Model.Meal;

import com.example.foodplanner.R;
import com.example.foodplanner.databinding.FragmentFavoriteBinding;
import com.example.foodplanner.databinding.FragmentHomeBinding;
import com.example.foodplanner.favorite.presenter.CommunicationFavoriteMeal;
import com.example.foodplanner.favorite.presenter.PresenterFavoriteMeal;
import com.example.foodplanner.helper.CheckConnection;
import com.example.foodplanner.helper.Converter;
import com.example.foodplanner.helper.MyUser;

import com.example.foodplanner.meal.MealActivity;
import com.example.foodplanner.profile.FirebaseDataBase;
import com.example.foodplanner.profile.MealFirebase;
import com.example.foodplanner.searchresult.AdapterSearchResult;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FavoriteFragment extends Fragment implements CommunicationFavoriteMeal,OnClickFavoriteItem {
    private static final String TAG = "TAGG";

    FavoriteMealsAdapter myAdapter;

    private FragmentFavoriteBinding binding;
    PresenterFavoriteMeal presenterFavoriteMeal;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.RVId.setLayoutManager(new GridLayoutManager(getContext(), 2));
        myAdapter = new FavoriteMealsAdapter(this);
        binding.RVId.setAdapter(myAdapter);
        presenterFavoriteMeal = new PresenterFavoriteMeal(this, ConcreteLocalData.getInstance(getContext()));
        presenterFavoriteMeal.getListFav(MyUser.getInstance().getEmail());


        return root;

    }


    public void onClick(FavoriteMeal  meal, boolean isClose) {
        if (isClose) {
            presenterFavoriteMeal.deleteMeal(meal.getIdMeal(), MyUser.getInstance().getEmail());
            FirebaseDataBase.removeFavouriteFromFirebase(getContext(), Converter.mealFirebaseFav(meal));
        }
        else {
            Intent intent = new Intent(getActivity(), MealActivity.class);
            intent.putExtra(getString(R.string.mealID), meal.getIdMeal());
            intent.putExtra(getString(R.string.isLocal), true);
            intent.putExtra(getString(R.string.isFav), true);
            startActivity(intent);
        }
    }
    @Override
    public void onComplete() {
        Toast.makeText(getContext(), "delete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setError(String message) {
        Snackbar.make(binding.getRoot(), "something wrong pls try again", Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void setList(List<FavoriteMeal> favoriteMeals) {
        myAdapter.setArr(favoriteMeals);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void sus() {
        Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();
    }
}
