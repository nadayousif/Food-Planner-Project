package com.example.foodplanner.serach;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;

public class AdapterSearch  extends RecyclerView.Adapter<AdapterSearch.ViewHolder> {
    private String[] strings;
    private boolean isRight=false;
    private OnClickItem onclickItem;

    public AdapterSearch(OnClickItem onclickItem, String[] arr) {
        this.strings = arr;
        this.onclickItem=onclickItem;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item.setText(strings[position]);
        holder.cardView.setOnClickListener((i)->{onclickItem.onClick(strings[position]);});

    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        CardView cardView;
        public ViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.tv_search_item_rec);
            cardView=view.findViewById(R.id.cv_search_item);
        }
    }
}