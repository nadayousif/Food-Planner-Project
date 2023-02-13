package com.example.foodplanner.serach.category;

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

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {
    private List<String> strings;
    private OnClickItemCategory onclickItemCategory;

    public AdapterCategory(OnClickItemCategory onclickItemCategory) {
        this.strings =new ArrayList<>();
        this.onclickItemCategory = onclickItemCategory;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_search, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item.setText(strings.get(position));
        holder.cardView.setOnClickListener((i)->{
            onclickItemCategory.onClick(strings.get(position));});

    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public int getItemCount() {
        return strings.size();
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