package com.example.foodplanner.plan.dialog.favorite;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.DBConnection.localdatabase.localdb.ConcreteLocalData;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.helper.Converter;
import com.example.foodplanner.helper.MyUser;
import com.example.foodplanner.plan.CommunicationWithDailog;
import com.example.foodplanner.plan.dialog.favorite.adapter.AdapterFavoriteDialog;
import com.example.foodplanner.plan.dialog.favorite.adapter.OnClickItem;
import com.example.foodplanner.plan.dialog.favorite.presenter.CommunicationFavoriteDialog;
import com.example.foodplanner.plan.dialog.favorite.presenter.PresenterFavoriteDialog;

import java.util.List;
import java.util.stream.Collectors;

public class FavoriteDialog extends DialogFragment implements CommunicationFavoriteDialog, OnClickItem {
    private static final String TAG = "TAGG";
    private final MainActivity activity;
    private String day;
    PresenterFavoriteDialog presenterFavoriteDialog;
    AdapterFavoriteDialog adapterFavoriteDialog;
    private CommunicationWithDailog communication;
    public FavoriteDialog(String day, MainActivity activity, CommunicationWithDailog planFragment) {
        this.day = day;
        this.activity=activity;
        communication=planFragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_favorite, null, false);

        builder.setView(view)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        communication.update();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FavoriteDialog.this.getDialog().cancel();
                    }
                });
        RecyclerView recyclerView = view.findViewById(R.id.rec_favorite_dialog);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapterFavoriteDialog = new AdapterFavoriteDialog(this);
        recyclerView.setAdapter(adapterFavoriteDialog);

        presenterFavoriteDialog = new PresenterFavoriteDialog(this, ConcreteLocalData.getInstance(getContext()));
        presenterFavoriteDialog.getListFav( MyUser.getInstance().getEmail());

        return builder.create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setError(String message) {
        Log.i(TAG, "setError: "+message);
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setList(List<FavoriteMeal> favoriteMeals) {
        adapterFavoriteDialog.setArr(favoriteMeals);
        adapterFavoriteDialog.notifyDataSetChanged();
    }

    @Override
    public void sus() {
        Toast.makeText(activity, "Added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteMeal(FavoriteMeal favoriteMeal) {
        Meal meal=Converter.convertFavToMeal(favoriteMeal);
        meal.setDay(day);
        presenterFavoriteDialog.deleteMealFromPlan(meal);
    }

    @Override
    public void saveMeal(FavoriteMeal favoriteMeal) {
        Meal meal=Converter.convertFavToMeal(favoriteMeal);
        meal.setDay(day);
        presenterFavoriteDialog.addMealToPlan(meal);
    }
}
