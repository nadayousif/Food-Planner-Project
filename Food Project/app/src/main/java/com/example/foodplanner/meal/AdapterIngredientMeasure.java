package com.example.foodplanner.meal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;

import java.util.ArrayList;

public class AdapterIngredientMeasure extends RecyclerView.Adapter<AdapterIngredientMeasure.ViewHolder> {
    ArrayList<String> arrIngredient;
    ArrayList<String> arrMeasure;

    public AdapterIngredientMeasure() {
        this.arrIngredient=new ArrayList<>();;
        this.arrMeasure=new ArrayList<>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_meal_ingredient_measure, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.measure.setText(arrMeasure.get(position));
        if(position< arrIngredient.size()){
            holder.ingredient.setText(arrIngredient.get(position));
        }

    }

    @Override
    public int getItemCount() {
        // Returns number of items currently available in Adapter
        return arrMeasure.size();
    }

    public void setIngredients(ArrayList<String> strIngredients) {
        this.arrIngredient = strIngredients;
    }

    public void setMeasures(ArrayList<String> strMeasures) {
        this.arrMeasure = strMeasures;
    }

    // Initializing the Views
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredient,measure;

        public ViewHolder(View view) {
            super(view);
            ingredient=view.findViewById(R.id.tv_name_ingredient);
            measure=view.findViewById(R.id.tv_name_measure);
        }
    }
}