package com.example.foodplanner.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.foodplanner.Model.RandomMeal;
import com.example.foodplanner.databinding.FragmentHomeBinding;


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
