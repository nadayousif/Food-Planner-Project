package com.example.foodplanner.plan.presenter;

import com.example.foodplanner.DBConnection.localdatabase.localdb.LocalDataSource;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public class PresenterPlan implements NetworkDelegatePlan{
    private final LocalDataSource localDataSource;
    private final CommunicationPlan communicationPlan;

    public PresenterPlan(LocalDataSource localDataSource, CommunicationPlan communicationPlan) {
        this.localDataSource =localDataSource;
        this.communicationPlan=communicationPlan;
    }

    public void deleteMeal(String idMeal, String email) {
        localDataSource.deletePlanMeal(idMeal,email,this);
    }

    @Override
    public void onComplete() {
        communicationPlan.onComplete();
    }

    @Override
    public void onError(String s) {
        communicationPlan.onError(s);

    }

    @Override
    public void onResponse(List<Meal> planMeals) {
        communicationPlan.onResponse(planMeals);
    }

    public void getPlanMeals(String email) {
        localDataSource.getPlanMeals(email,this);
    }

    public void clear() {
        localDataSource.clear();
    }
}
