package com.example.foodplanner.meal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;


public class AdapterInstructions extends RecyclerView.Adapter<AdapterInstructions.ViewHolder> {
    String[] arrInstruction;

    public AdapterInstructions() {

        this.arrInstruction = new String[0];;
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0)
            return 0;
        else
            return 1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_meal_instruction_right, parent, false);
        }


        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_meal_instruction_left, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.content.setText(arrInstruction[position]);
        holder.index.setText((position+1)+"");

    }

    @Override
    public int getItemCount() {
        return arrInstruction.length;
    }

    public void setInstructions(String[] strInstructions) {
        this.arrInstruction=strInstructions;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView index, content;

        public ViewHolder(View view) {
            super(view);
            index = view.findViewById(R.id.tv_insturction_index);
            content = view.findViewById(R.id.tv_insturction_content);
        }
    }
}
