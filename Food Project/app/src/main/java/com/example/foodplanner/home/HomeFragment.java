package com.example.foodplanner.home;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.foodplanner.APIconnection.RetrofitClient;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.helper.CheckConnection;
import com.example.foodplanner.databinding.FragmentHomeBinding;
import com.example.foodplanner.helper.Converter;
import com.example.foodplanner.helper.MySharedPreference;
import com.example.foodplanner.home.presenter.CommunicationRandomMeal;
import com.example.foodplanner.home.presenter.PresenterRandomMeal;
import com.example.foodplanner.meal.MealActivity;
import com.example.foodplanner.searchresult.AdapterSearchResult;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;


public class HomeFragment extends Fragment implements CommunicationRandomMeal {
    private static final String TAG = "TAGG";
    private FragmentHomeBinding binding;
    static Meal meal;
    PresenterRandomMeal presenterRandomMeal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (CheckConnection.isConnect(getContext())) {
            Meal meal = MySharedPreference.getMeal(getActivity());
            presenterRandomMeal = new PresenterRandomMeal(RetrofitClient.getInstance(), this);
            if (meal != null && meal.getDay() != null && meal.getDay().equals( String.valueOf(new Date().getDay()))) {
                setMealLocal(meal);
                this.meal = meal;
            } else {
            presenterRandomMeal.getRandomMeal();
            }
            binding.cvMealHome.setOnClickListener(i -> {
                Intent intent = new Intent(getContext(), MealActivity.class);
                intent.putExtra(getString(R.string.mealID), this.meal.getIdMeal());
                intent.putExtra(getString(R.string.isLocal), false);
                startActivity(intent);
            });
        } else {
            binding.cvMealHome.setVisibility(View.GONE);
            binding.animationView.setVisibility(View.VISIBLE);
        }
        return root;
    }

    private void setMealLocal(Meal meal) {

        Bitmap bmp = BitmapFactory.decodeByteArray(meal.getImage(), 0, meal.getImage().length);
        binding.imageView2.setImageBitmap(bmp);
        binding.tvNameMealHome.setText(meal.getStrMeal());
        binding.tvCountryHome.setText(meal.getStrArea());
        binding.tvCatogaryHome.setText(meal.getStrCategory());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setMeal(Meal meal) {
        Glide.with(getContext())
                .asBitmap()
                .load(meal.getStrMealThumb())
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        binding.imageView2.setImageBitmap(resource);
                        meal.setImage(Converter.getBytesFromBitmap(resource));
                        meal.setDay(String.valueOf(new Date().getDay()));
                        binding.tvNameMealHome.setText(meal.getStrMeal());
                        binding.tvCountryHome.setText(meal.getStrArea());
                        binding.tvCatogaryHome.setText(meal.getStrCategory());
                        MySharedPreference.saveMeal(getActivity(), meal);
                       HomeFragment.meal =meal;
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }

    @Override
    public void setError(String message) {
        Snackbar.make(binding.getRoot(), "something wrong pls try again", Snackbar.LENGTH_LONG)
                .show();
    }


}
