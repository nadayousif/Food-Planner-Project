package com.example.foodplanner.meal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;


public class AdapterInstructions extends RecyclerView.Adapter<AdapterInstructions.ViewHolder> {
    String[] arrInstruction;
    private boolean isRight=false;

    public AdapterInstructions(String[] arr) {
        this.arrInstruction = arr;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(isRight) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_meal_instruction_right, parent, false);
            isRight=false;
        }


        else {
            isRight = true;
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView index, content;

        public ViewHolder(View view) {
            super(view);
            index = view.findViewById(R.id.tv_insturction_index);
            content = view.findViewById(R.id.tv_insturction_content);
        }
    }
}
