package com.example.foodplanner.serach.historyserach;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolder> {
    private String[] strings;
    private OnClickItemHistory onClickItemHistory;

    public AdapterHistory(OnClickItemHistory onClickItemHistory) {
        this.strings =new String[0];
        this.onClickItemHistory = onClickItemHistory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_search_histroy, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item.setText(strings[position]);
        holder.item.setOnClickListener((i) -> {
           onClickItemHistory.onClickHistory(strings[position]);

        });

    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;


        public ViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.tv_search_histroy);
        }
    }
}