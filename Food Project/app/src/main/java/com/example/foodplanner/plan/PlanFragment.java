package com.example.foodplanner.plan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodplanner.DBConnection.DBModel.PlanMeal;
import com.example.foodplanner.DBConnection.localdatabase.ConcreteLocalData;
import com.example.foodplanner.R;
import com.example.foodplanner.databinding.FragmentPlanBinding;
import com.example.foodplanner.helper.MyUser;
import com.example.foodplanner.meal.MealActivity;
import com.example.foodplanner.plan.adapter.AdapterPlan;
import com.example.foodplanner.plan.adapter.OnClickItem;
import com.example.foodplanner.plan.presenter.CommunicationPlan;
import com.example.foodplanner.plan.presenter.PresenterPlan;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

public class PlanFragment extends Fragment implements OnClickItem, CommunicationPlan {
    private FragmentPlanBinding binding;
    private PresenterPlan presenterPlan;
    private AdapterPlan adapterPlanSaturday;
    private AdapterPlan adapterPlanSunday;
    private AdapterPlan adapterPlanMonday;
    private AdapterPlan adapterPlanTuesday;
    private AdapterPlan adapterPlanWednesday;
    private AdapterPlan adapterPlanThursday;
    private AdapterPlan adapterPlanFriday;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPlanBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        presenterPlan = new PresenterPlan(ConcreteLocalData.getInstance(getContext()), this);
        adapterPlanFriday = new AdapterPlan(this);
        adapterPlanThursday = new AdapterPlan(this);
        adapterPlanWednesday = new AdapterPlan(this);
        adapterPlanTuesday = new AdapterPlan(this);
        adapterPlanMonday = new AdapterPlan(this);
        adapterPlanSunday = new AdapterPlan(this);
        adapterPlanSaturday = new AdapterPlan(this);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recPlanFriday.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recPlanThursday.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recPlanWednesday.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recPlanTuesday.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recPlanMonday.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recPlanSunday.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recPlanSaturday.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.recPlanFriday.setAdapter(adapterPlanFriday);
        binding.recPlanThursday.setAdapter(adapterPlanThursday);
        binding.recPlanWednesday.setAdapter(adapterPlanWednesday);
        binding.recPlanTuesday.setAdapter(adapterPlanTuesday);
        binding.recPlanMonday.setAdapter(adapterPlanMonday);
        binding.recPlanSunday.setAdapter(adapterPlanSunday);
        binding.recPlanSaturday.setAdapter(adapterPlanSaturday);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenterPlan.getPlanMeals(MyUser.getInstance().getEmail());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        presenterPlan.clear();
    }


    @Override
    public void onClick(String idMeal, boolean isClose) {
        if (isClose)
            presenterPlan.deleteMeal(idMeal, MyUser.getInstance().getEmail());
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
    public void onError(String s) {
        Toast.makeText(getContext(), "something failed pls try again", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(List<PlanMeal> planMeals) {
        //--------------------
        List<PlanMeal> listSaturday = new ArrayList<>();
        List<PlanMeal> listSunday = new ArrayList<>();
        List<PlanMeal> listMonday = new ArrayList<>();
        List<PlanMeal> listTuesday = new ArrayList<>();
        List<PlanMeal> listWednesday = new ArrayList<>();
        List<PlanMeal> listThursday = new ArrayList<>();
        List<PlanMeal> listFriday = new ArrayList<>();
        for (PlanMeal meal : planMeals) {
            if (meal.getDay().equals("Saturday"))
                listSaturday.add(meal);
            else if (meal.getDay().equals("Sunday"))
                listSunday.add(meal);
            else if (meal.getDay().equals("Monday"))
                listMonday.add(meal);
            else if (meal.getDay().equals("Tuesday"))
                listTuesday.add(meal);
            else if (meal.getDay().equals("Wednesday"))
                listWednesday.add(meal);
            else if (meal.getDay().equals("Thursday"))
                listThursday.add(meal);
            else if (meal.getDay().equals("Friday"))
                listFriday.add(meal);
        }
        adapterPlanSaturday.setArr(listSaturday);
        adapterPlanSunday.setArr(listSunday);
        adapterPlanMonday.setArr(listMonday);
        adapterPlanTuesday.setArr(listTuesday);
        adapterPlanWednesday.setArr(listWednesday);
        adapterPlanThursday.setArr(listThursday);
        adapterPlanFriday.setArr(listFriday);

        adapterPlanSunday.notifyDataSetChanged();
        adapterPlanSaturday.notifyDataSetChanged();
        adapterPlanMonday.notifyDataSetChanged();
        adapterPlanTuesday.notifyDataSetChanged();
        adapterPlanWednesday.notifyDataSetChanged();
        adapterPlanThursday.notifyDataSetChanged();
        adapterPlanFriday.notifyDataSetChanged();


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.im_from_favorite) {
            Toast.makeText(getContext(), "fav", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(getContext(), "search", Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

}
