package com.example.foodplanner.meal;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.foodplanner.APIconnection.RetrofitClient;
import com.example.foodplanner.DBConnection.localdatabase.localdb.ConcreteLocalData;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;

import com.example.foodplanner.Welcome.WelcomeActivity;
import com.example.foodplanner.helper.CheckConnection;
import com.example.foodplanner.helper.Converter;
import com.example.foodplanner.helper.MyUser;
import com.example.foodplanner.meal.presenter.CommunicationMeal;
import com.example.foodplanner.meal.presenter.PresenterMeal;

import com.example.foodplanner.profile.FirebaseDataBase;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import com.google.android.material.snackbar.Snackbar;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


public class MealActivity extends AppCompatActivity implements CommunicationMeal {
    private static final String TAG = "TAGG";

    AdapterInstructions adapterInstructions;
    ImageView mealPhoto;
    TextView mealName;
    TextView mealCategory;
    TextView mealCountry;
    ToggleButton favoriteMeal;

    YouTubePlayerView youTubePlayerView;
    AdapterIngredientMeasure adapterIngredientMeasure;
    PresenterMeal presenterMeal;
    Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meal);
        mealPhoto = findViewById(R.id.iv_meal);
        mealName = findViewById(R.id.tv_mael_name);
        mealCategory = findViewById(R.id.tv_meal_category);
        mealCountry = findViewById(R.id.tv_country);
        favoriteMeal = findViewById(R.id.iv_favorite_image_button);


        youTubePlayerView = findViewById(R.id.ybv);
        RecyclerView recIngrMeas = findViewById(R.id.rc_meal_Ingredient_Measure);
        RecyclerView recInst = findViewById(R.id.rec_mael_instructions);

        if (getIntent() != null) {
            String id = getIntent().getStringExtra(getString(R.string.mealID));
            presenterMeal = new PresenterMeal(RetrofitClient.getInstance(), this, ConcreteLocalData.getInstance(this));
            Boolean isLocal = getIntent().getBooleanExtra(getString(R.string.isLocal), true);
            if (isLocal) {
                boolean isFav = getIntent().getBooleanExtra(getString(R.string.isFav), false);
                if (isFav)
                    presenterMeal.getMealLocalFav(id, MyUser.getInstance().getEmail());
                else
                    presenterMeal.getMealLocal(id, MyUser.getInstance().getEmail());
            } else if (CheckConnection.isConnect(this)) {
                presenterMeal.getMeal(id);
            } else {

                Snackbar.make(findViewById(android.R.id.content), "no internet connection pls try again ", Snackbar.LENGTH_LONG)
                        .show();
            }
            favoriteMeal.setOnClickListener(v -> {

                v.setEnabled(false);

                if (MyUser.getInstance().isLogin()) {
                    meal.setEmail(MyUser.getInstance().getEmail());
                    if (((ToggleButton) v).isChecked()) {
                        Log.i(TAG, "onCreate: add to firebase");
                        presenterMeal.addToFav(Converter.convertMealToFav(meal));
                        FirebaseDataBase.addFavouriteToFirebase(this, Converter.mealFirebasePlan(meal));
                    } else {
                        presenterMeal.removeFromFav(Converter.convertMealToFav(meal));
                        FirebaseDataBase.removeFavouriteFromFirebase(this, Converter.mealFirebasePlan(meal));
                    }
                }

                if (MyUser.getInstance().isLogin()) {
                    v.setEnabled(false);
                    if (MyUser.getInstance().isLogin() && meal != null) {
                        meal.setEmail(MyUser.getInstance().getEmail());
                        if (((ToggleButton) v).isChecked()) {
                            presenterMeal.addToFav(Converter.convertMealToFav(meal));
                        } else
                            presenterMeal.removeFromFav(Converter.convertMealToFav(meal));

                    }
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "pleas create account to save meals and add meals to plan", Snackbar.LENGTH_LONG)
                            .setAction(R.string.create_account, i -> {
                                Intent intent = new Intent(this, WelcomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            })
                            .show();
                    ;
                }
            });
        }

        recIngrMeas.setLayoutManager(new StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL));
        adapterIngredientMeasure = new AdapterIngredientMeasure();
        recIngrMeas.setAdapter(adapterIngredientMeasure);
        recInst.setLayoutManager(new LinearLayoutManager(this));
        adapterInstructions = new AdapterInstructions();
        recInst.setAdapter(adapterInstructions);


    }

    public void finishActivity(View view) {
        finish();
    }


    public void addToPLan(View view) {
        if (MyUser.getInstance().isLogin()) {
            DialogFragment newFragment = new DialogPlan(this);
            newFragment.show(this.getSupportFragmentManager(), "Add From Favorite");

        } else {
            Snackbar.make(findViewById(android.R.id.content), "something wrong pls try again", Snackbar.LENGTH_LONG)
                    .setAction(R.string.create_account, i -> {
                        Intent intent = new Intent(this, WelcomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    })
                    .show();
        }
    }

    public void setError(String message) {
        Snackbar.make(findViewById(android.R.id.content), "something wrong pls try again", Snackbar.LENGTH_LONG)
                .show();
    }

    public void setMeal(Meal meals, Boolean isLocal) {
        adapterIngredientMeasure.setIngredients(meals.getIngredients());
        adapterIngredientMeasure.setMeasures(meals.getMeasures());
        adapterIngredientMeasure.notifyDataSetChanged();
        String[] strInstructions = Arrays.stream(meals.getArrOfStrInstructions()).filter(i -> (!i.trim().isEmpty()) || (i.trim().contains("Step"))).toArray(String[]::new);
        adapterInstructions.setInstructions(strInstructions);
        adapterInstructions.notifyDataSetChanged();
        mealName.setText(meals.getStrMeal());
        mealCategory.setText(meals.getStrCategory());
        mealCountry.setText(meals.getStrArea());
        if (isLocal) {
            if (meals.getImage() != null) {
                Glide.with(this).load(meals.getImage()).into(mealPhoto);
                meal = meals;
            }
        } else {
            Glide.with(this)
                    .asBitmap()
                    .load(meals.getStrMealThumb())
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            mealPhoto.setImageBitmap(resource);
                            meals.setImage(Converter.getBytesFromBitmap(resource));
                            meal = meals;
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                        }
                    });

            final String[] VideoUrl = {meals.getStrYoutube()};
            try {
                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                                                               @Override
                                                               public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                                                                   super.onReady(youTubePlayer);
                                                                   if (VideoUrl[0] != null) {
                                                                       VideoUrl[0] = VideoUrl[0].substring(VideoUrl[0].indexOf("=") + 1);
                                                                       StringTokenizer st = new StringTokenizer(VideoUrl[0], "&");
                                                                       if (st.hasMoreElements()) {
                                                                           VideoUrl[0] = st.nextToken();
                                                                           youTubePlayer.loadVideo(VideoUrl[0], 0);
                                                                           youTubePlayer.pause();
                                                                       }

                                                                   }
                                                               }

                                                           }
                );

            } catch (Exception ex) {
                Log.i(TAG, "setMeal: " + ex.getMessage());
            }
        }

    }


    @Override
    public void susToAdd() {
        Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();
        favoriteMeal.setEnabled(true);
    }

    @Override
    public void onFailureToAdd(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        favoriteMeal.setEnabled(true);
    }

    @Override
    public void updateDB(List<String> list) {
        meal.setEmail(MyUser.getInstance().getEmail());
        List<Meal> meals = list.stream().map(i -> {
            Meal meal1 = Converter.createMeal(meal);
            meal1.setDay(i);
            return meal1;
        }).collect(Collectors.toList());

        for (Meal meal : meals) {
            FirebaseDataBase.addPlanToFirebase(this, Converter.mealFirebasePlan(meal));
        }

        presenterMeal.addToPlan(meals);
    }

    public static class DialogPlan extends DialogFragment {
        CommunicationMeal communicationMeal;

        public DialogPlan(CommunicationMeal communicationMeal) {
            this.communicationMeal = communicationMeal;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            View view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_plan, null, false);
            ChipGroup chipGroup = view.findViewById(R.id.chip_group);
            builder.setView(view)
                    .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {


                            List<String> list = chipGroup.getCheckedChipIds().stream()
                                    .map(i ->
                                            ((Chip) view.findViewById(i)).getTag().toString()).collect(Collectors.toList());
                            Log.i(TAG, "onClick: " + list.size());
                            communicationMeal.updateDB(list);

                        }
                    });


            return builder.create();
        }
    }
}
