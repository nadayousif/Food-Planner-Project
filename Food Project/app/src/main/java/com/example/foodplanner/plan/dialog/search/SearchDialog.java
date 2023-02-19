package com.example.foodplanner.plan.dialog.search;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.APIconnection.RetrofitClient;
import com.example.foodplanner.DBConnection.localdatabase.localdb.ConcreteLocalData;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.helper.CheckConnection;
import com.example.foodplanner.helper.MySharedPreference;
import com.example.foodplanner.helper.MyUser;
import com.example.foodplanner.plan.dialog.CommunicationWithDailog;
import com.example.foodplanner.plan.dialog.search.historyserach.AdapterHistory;
import com.example.foodplanner.plan.dialog.search.historyserach.OnClickItemHistory;
import com.example.foodplanner.plan.dialog.search.presenter.CommunicationSearchDialog;
import com.example.foodplanner.plan.dialog.search.presenter.presenterSearchDialog;
import com.example.foodplanner.plan.dialog.search.searchresult.AdapterSearchDialog;
import com.example.foodplanner.plan.dialog.search.searchresult.OnClickItem;
import com.example.foodplanner.profile.FirebaseDataBase;
import com.example.foodplanner.profile.MealFirebase;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SearchDialog extends DialogFragment implements  OnClickItemHistory, CommunicationSearchDialog , OnClickItem {

    private static final String TAG = "Dialog";
    private final MainActivity activity;
    private presenterSearchDialog presenterSearchDialog;
    private AdapterSearchDialog adapterSearchDialog;
    private AdapterHistory adapterHistory;
    private RecyclerView recDialogSearch;
    private RecyclerView recDialogSearchHistory;
    private CommunicationWithDailog communication;
    private String day;

    public SearchDialog(String day, MainActivity activity, CommunicationWithDailog planFragment) {
        this.day = day;
        this.activity=activity;
        communication=planFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_search, null, false);

        Toast.makeText(getContext(), "on create dialog", Toast.LENGTH_SHORT).show();
        builder.setView(view)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        communication.update();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SearchDialog.this.getDialog().cancel();
                    }
                });
        recDialogSearch = view.findViewById(R.id.rec_dialog_search);

        recDialogSearchHistory = view.findViewById(R.id.rec_dialog_search_history);
        recDialogSearch.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recDialogSearchHistory.setLayoutManager(new LinearLayoutManager(getContext()));

        adapterHistory = new AdapterHistory(this);
        adapterSearchDialog = new AdapterSearchDialog(this);

        recDialogSearchHistory.setAdapter(adapterHistory);
        recDialogSearch.setAdapter(adapterSearchDialog);
        SearchView svDialogSearch = view.findViewById(R.id.sv_dialog_search);
        if (CheckConnection.isConnect(getContext())) {
            presenterSearchDialog = new presenterSearchDialog(RetrofitClient.getInstance(), this, new ConcreteLocalData(getContext()));


            svDialogSearch.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        showHistorySearch();
                        showHistorySearchList();

                    } else {
                        hiddenHistorySearch();
                    }
                }
            });
            svDialogSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    saveIntoShare(query);
                    hiddenHistorySearch();
                    svDialogSearch.clearFocus();
                    presenterSearchDialog.getMeals(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (!newText.isEmpty()) {
                        presenterSearchDialog.search(newText);
                    } else {
                        showHistorySearchList();
                    }

                    return false;
                }
            });

        } else {
            Snackbar.make(getActivity().findViewById(android.R.id.content), "no internet connection pls try again ", Snackbar.LENGTH_LONG)
                    .show();
        }
        svDialogSearch.requestFocus();
        return builder.create();
    }




    @Override
    public void onClickHistory(String name) {
        hiddenHistorySearch();
        presenterSearchDialog.getMeals(name);

    }

    void showHistorySearch() {
        recDialogSearchHistory.setVisibility(View.VISIBLE);
        recDialogSearch.setVisibility(View.GONE);
    }

    void showHistorySearchList() {
        String[] arr = new String[0];
        String s = MySharedPreference.getListOfHistory(getActivity());
        if (!s.isEmpty()) {
            List<String> list = Arrays.stream(s.split(",")).filter(i -> !i.isEmpty()).collect(Collectors.toList());
            Collections.reverse(list);
            if (list.size() > 10)
                list = list.subList(0, 10);
            arr = list.stream().toArray(String[]::new);

        }
        adapterHistory.setStrings(arr);
        adapterHistory.notifyDataSetChanged();
    }

    void hiddenHistorySearch() {
        recDialogSearchHistory.setVisibility(View.GONE);
        recDialogSearch.setVisibility(View.VISIBLE);
    }

    void saveIntoShare(String name) {
        String s = MySharedPreference.getListOfHistory(getActivity());
        boolean status = !Arrays.stream(s.split(",")).filter(i -> !i.isEmpty()).anyMatch(i -> i.equals(name));
        if (status)
            s += name + ",";
        MySharedPreference.saveInHistory(getActivity(), s);
    }

    @Override
    public void setListHistory(String[] names) {
        adapterHistory.setStrings(names);
        adapterHistory.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String message) {
        Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .show();
        Log.i(TAG, "onFailure: " + message);
    }

    @Override
    public void setListMeals(List<Meal> list) {
        adapterSearchDialog.setArr(list);
        adapterSearchDialog.notifyDataSetChanged();
    }

    @Override
    public void onComplete() {
        Snackbar.make(activity.findViewById(android.R.id.content), "added ", Snackbar.LENGTH_LONG)
                .show();
    }




    @Override
    public void saveMealToPlan(Meal meal) {
        meal.setEmail(MyUser.getInstance().getEmail());
        meal.setDay(day);
        presenterSearchDialog.saveMealToPlan(meal);
        MealFirebase fireBaseRecord = new MealFirebase();
        fireBaseRecord.setIdMeal(meal.getIdMeal());
        fireBaseRecord.setDay(meal.getDay());
        fireBaseRecord.setEmail(meal.getEmail());
        fireBaseRecord.setStrArea(meal.getStrArea());
        fireBaseRecord.setStrCategory(meal.getStrCategory());
        fireBaseRecord.setStrMeal(meal.getStrMeal());
        fireBaseRecord.setStrIngredient(meal.getStrIngredient());
        fireBaseRecord.setStrInstructions(meal.getStrInstructions());
        fireBaseRecord.setStrMealThumb(meal.getStrMealThumb());
        fireBaseRecord.setStrYoutube(meal.getStrYoutube());
        fireBaseRecord.setIngredients(meal.getIngredients());
        fireBaseRecord.setMeasures(meal.getMeasures());
        fireBaseRecord.setImages(IntStream.range(0, meal.getImage().length).mapToObj(i -> (int) meal.getImage()[i]).collect(Collectors.toList()));
        FirebaseDataBase.addPlanToFirebase(getContext(),fireBaseRecord);

    }

    @Override
    public void removeMealToPlan(Meal meal) {
        meal.setEmail(MyUser.getInstance().getEmail());
        meal.setDay(day);
        presenterSearchDialog.removeMealToPlan(meal);
        MealFirebase fireBaseRecord = new MealFirebase();
        fireBaseRecord.setIdMeal(meal.getIdMeal());
        fireBaseRecord.setDay(meal.getDay());
        fireBaseRecord.setEmail(meal.getEmail());
        fireBaseRecord.setStrArea(meal.getStrArea());
        fireBaseRecord.setStrCategory(meal.getStrCategory());
        fireBaseRecord.setStrMeal(meal.getStrMeal());
        fireBaseRecord.setStrIngredient(meal.getStrIngredient());
        fireBaseRecord.setStrInstructions(meal.getStrInstructions());
        fireBaseRecord.setStrMealThumb(meal.getStrMealThumb());
        fireBaseRecord.setStrYoutube(meal.getStrYoutube());
        fireBaseRecord.setIngredients(meal.getIngredients());
        fireBaseRecord.setMeasures(meal.getMeasures());
        fireBaseRecord.setImages(IntStream.range(0, meal.getImage().length).mapToObj(i -> (int) meal.getImage()[i]).collect(Collectors.toList()));
        FirebaseDataBase.removePlanFromFireBase(getContext(),fireBaseRecord);
    }
}
