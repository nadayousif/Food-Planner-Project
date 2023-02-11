package com.example.foodplanner.searchresult;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Model.RandomMeal;
import com.example.foodplanner.R;

import java.util.ArrayList;

public class AdapterSearchResult extends RecyclerView.Adapter<AdapterSearchResult.ViewHolder> {
    private ArrayList<RandomMeal> arr;
    private OnClickItem onclickItem;

    public AdapterSearchResult(OnClickItem onclickItem,ArrayList<RandomMeal> arr) {
        this.arr = arr;
        this.onclickItem=onclickItem;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_search_result, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item.setText(arr.get(position).getStrMeal());
        holder.cardView.setOnClickListener((i)->{onclickItem.onClick(arr.get(position).getIdMeal());});


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