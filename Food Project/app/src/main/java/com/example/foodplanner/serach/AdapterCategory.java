package com.example.foodplanner.serach;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {
    private String[] strings;
    private OnClickItem onclickItem;

    public AdapterCategory(OnClickItem onclickItem) {
        this.strings = new String[0];
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

    public void setStrings(String[] strings) {
        this.strings = strings;
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