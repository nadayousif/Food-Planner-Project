package com.example.foodplanner.plan.dialog.search;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.foodplanner.APIconnection.RetrofitClient;
import com.example.foodplanner.R;
import com.example.foodplanner.databinding.DialogSearchBinding;
import com.example.foodplanner.helper.CheckConnection;
import com.example.foodplanner.helper.MySharedPreference;
import com.example.foodplanner.plan.dialog.search.historyserach.AdapterHistory;
import com.example.foodplanner.plan.dialog.search.historyserach.OnClickItemHistory;
import com.example.foodplanner.plan.dialog.search.presenter.CommunicationSearchDialog;
import com.example.foodplanner.plan.dialog.search.presenter.presenterSearchDialog;
import com.example.foodplanner.plan.dialog.search.searchresult.AdapterSearchDialog;
import com.example.foodplanner.plan.dialog.search.searchresult.OnClickItem;
import com.example.foodplanner.searchresult.SearchResultActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchDialog extends DialogFragment implements OnClickItem , OnClickItemHistory, CommunicationSearchDialog {

    DialogSearchBinding binding;
    private com.example.foodplanner.plan.dialog.search.presenter.presenterSearchDialog presenterSearchDialog;
    private AdapterSearchDialog adapterSearchDialog;
    private AdapterHistory adapterHistory;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view =requireActivity().getLayoutInflater().inflate(R.layout.dialog_search,null,false);

        Toast.makeText(getContext(), "on create dialog", Toast.LENGTH_SHORT).show();
        builder.setView(view)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SearchDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding=DialogSearchBinding.inflate(inflater, container, false);
        binding.recDialogSearch.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
        binding.recDialogSearchHistory.setLayoutManager(new LinearLayoutManager(getContext()));
         adapterHistory=new AdapterHistory(this);
        adapterSearchDialog=new AdapterSearchDialog(this);


        if (CheckConnection.isConnect(getContext())) {
            presenterSearchDialog = new presenterSearchDialog(RetrofitClient.getInstance(), this);


            binding.svDialogSearch.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
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
            binding.svDialogSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    saveIntoShare(query);
                    hiddenHistorySearch();
                    binding.svDialogSearch.clearFocus();
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

        } else
            Snackbar.make(getActivity().findViewById(android.R.id.content), "no internet connection pls try again ", Snackbar.LENGTH_LONG)
                    .show();
        binding.svDialogSearch.requestFocus();
        return binding.getRoot();
    }


    @Override
    public void onClick(String idMeal) {

    }

    @Override
    public void onClickHistory(String name) {

    }
    void showHistorySearch() {
        binding.recDialogSearchHistory.setVisibility(View.VISIBLE);
        binding.recDialogSearch.setVisibility(View.GONE);
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
        binding.recDialogSearchHistory.setVisibility(View.GONE);
        binding.recDialogSearch.setVisibility(View.VISIBLE);
    }
    void saveIntoShare(String name) {

        String s = MySharedPreference.getListOfHistory(getActivity());
        boolean status = !Arrays.stream(s.split(",")).filter(i -> !i.isEmpty()).anyMatch(i -> i.equals(name));
        if (status)
            s += name + ",";
       MySharedPreference.saveInHistory(getActivity(),s);
    }
}
