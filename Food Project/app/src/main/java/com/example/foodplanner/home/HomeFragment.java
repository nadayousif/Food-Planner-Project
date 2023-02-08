package com.example.foodplanner.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodplanner.Helper.Tags;
import com.example.foodplanner.Model.RandomMeal;
import com.example.foodplanner.databinding.FragmentHomeBinding;

import java.util.Random;

public class HomeFragment extends Fragment {
    private static RandomMeal randomMeal;
    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        randomMeal = new RandomMeal("4554", "Checken", "htlsjadflskjflk;sdjflk");
        binding.cardView.setTag(randomMeal.getIdMeal());
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}