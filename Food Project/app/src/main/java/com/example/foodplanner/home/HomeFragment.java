package com.example.foodplanner.home;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.foodplanner.APIconnection.RetrofitClient;
import com.example.foodplanner.helper.CheckConnection;
import com.example.foodplanner.Model.RandomMeal;
import com.example.foodplanner.R;
import com.example.foodplanner.databinding.FragmentHomeBinding;
import com.example.foodplanner.home.presenter.CommunicationRandomMeal;
import com.example.foodplanner.home.presenter.PresenterRandomMeal;
import com.google.android.material.snackbar.Snackbar;


public class HomeFragment extends Fragment implements OnClickRandomMeal, CommunicationRandomMeal {
    private static RandomMeal randomMeal;
    private FragmentHomeBinding binding;
    //AdapterRandomMeal adapterRandomMeal;
    PresenterRandomMeal presenterRandomMeal;
    TextView mealName;
    ImageView mealPhoto;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mealName = (TextView) root.findViewById(R.id.tx_random_name_meal);
        mealPhoto = (ImageView) root.findViewById(R.id.iv_random_imgae_meal);
        if (CheckConnection.isConnect(getContext())){
            presenterRandomMeal = new PresenterRandomMeal(RetrofitClient.getInstance(), this);
            presenterRandomMeal.getRandomMeal();

        }
        //randomMeal = new RandomMeal("4554", "Checken", "htlsjadflskjflk;sdjflk");
        //binding.cardView.setTag(randomMeal.getIdMeal());


        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void setMeal(String idMeal,String strMeal,String strMealThumb) {
        mealName.setText(strMeal);
        Glide.with(getContext()).load(strMealThumb).into(mealPhoto);
        mealPhoto.setTag(idMeal);
    }

    @Override
    public void setError(String message) {
        Snackbar.make(binding.getRoot(), "something wrong pls try again", Snackbar.LENGTH_LONG)
                .show();
    }
    @Override
    public void onClick(String id) {

    }


}
