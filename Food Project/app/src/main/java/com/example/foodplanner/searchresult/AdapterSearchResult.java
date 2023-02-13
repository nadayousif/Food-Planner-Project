package com.example.foodplanner.searchresult;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterSearchResult extends RecyclerView.Adapter<AdapterSearchResult.ViewHolder> {
    private List<Meal> arr;
    private OnClickItem onclickItem;
    Context context;

    public AdapterSearchResult(OnClickItem onclickItem) {
        this.arr = new ArrayList<>();
        this.onclickItem=onclickItem;
    }

    public void setArr(List<Meal> arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_search_result, parent, false);
        context=parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item.setText(arr.get(position).getStrMeal());
        holder.cardView.setOnClickListener((i)->{onclickItem.onClick(arr.get(position).getIdMeal());});
        Glide.with(context).load(arr.get(position).getStrMealThumb()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        CardView cardView;
        ImageView imageView;
        public ViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.tv_search_result_name_content);
            cardView=view.findViewById(R.id.cv_search_result);
            imageView=view.findViewById(R.id.iv_search_result);
        }
    }
}