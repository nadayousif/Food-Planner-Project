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
import com.example.foodplanner.Model.RandomMeal;

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

//    Meal[] meals = {new Meal("52772", "Teriyaki Chicken Casserole", null, "Chicken", "Japanese", "Preheat oven to 350° F. Spray a 9x13-inch baking pan with non-stick spray.\r\nCombine soy sauce, ½ cup water, brown sugar, ginger and garlic in a small saucepan and cover. Bring to a boil over medium heat. Remove lid and cook for one minute once boiling.\r\nMeanwhile, stir together the corn starch and 2 tablespoons of water in a separate dish until smooth. Once sauce is boiling, add mixture to the saucepan and stir to combine. Cook until the sauce starts to thicken then remove from heat.\r\nPlace the chicken breasts in the prepared pan. Pour one cup of the sauce over top of chicken. Place chicken in oven and bake 35 minutes or until cooked through. Remove from oven and shred chicken in the dish using two forks.\r\n*Meanwhile, steam or cook the vegetables according to package directions.\r\nAdd the cooked vegetables and rice to the casserole dish with the chicken. Add most of the remaining sauce, reserving a bit to drizzle over the top when serving. Gently toss everything together in the casserole dish until combined. Return to oven and cook 15 minutes. Remove from oven and let stand 5 minutes before serving. Drizzle each serving with remaining sauce. Enjoy!", "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg", "https://www.youtube.com/watch?v=4aZr5hZXP_s", "soy sauce", "water", "brown sugar", "ground ginger", "minced garlic", "cornstarch", "chicken breasts", "stir-fry vegetables", "brown rice", "", "", "", "", "", "", null, null, null, null, null, "3/4 cup", "1/2 cup", "1/4 cup", "1/2 teaspoon", "1/2 teaspoon", "4 Tablespoons", "2", "1 (12 oz.)", "3 cups", "", "", "", "", "", "", null, null, null, null, null)};

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        recycleView= (RecyclerView) view.findViewById(R.id.RV_Id);
//        layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        recycleView.setLayoutManager(layoutManager);
//        myAdapter = new FavoriteMealsAdapter(getContext() , meals);
//        recycleView.setAdapter(myAdapter);
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

    public void onClick(String idMeal, boolean isClose) {
        if (isClose)
            presenterFavoriteMeal.deleteMeal(idMeal, MyUser.getInstance().getEmail());

        else {
            Intent intent = new Intent(getActivity(), MealActivity.class);
            intent.putExtra(getString(R.string.mealID), idMeal);
            intent.putExtra(getString(R.string.isLocal), true);
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
