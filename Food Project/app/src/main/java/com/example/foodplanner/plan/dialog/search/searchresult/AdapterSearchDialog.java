package com.example.foodplanner.plan.dialog.search.searchresult;

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

public class AdapterSearchDialog extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Meal> arr;
    private OnClickItem onclickItem;
    private Context context;
    private Boolean isResult;

    public AdapterSearchDialog(OnClickItem onclickItem) {
        this.arr = new ArrayList<>();
        this.onclickItem = onclickItem;
        isResult = true;
    }

    public void setArr(List<Meal> arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        RecyclerView.ViewHolder viewHolder = null;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_dialog_favorite, parent, false);
        viewHolder = new SearchViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((SearchViewHolder) holder).item.setText(arr.get(position).getStrMeal());
        ((SearchViewHolder) holder).cardView.setOnClickListener((i) -> {
            onclickItem.onClick(arr.get(position).getIdMeal());
        });
        Glide.with(context).load(arr.get(position).getStrMealThumb()).into(((SearchViewHolder) holder).imageView);
    }


    @Override
    public int getItemCount() {
        return arr.size();
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        CardView cardView;
        ImageView imageView;

        public SearchViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.tv_dialog_favorite);
            cardView = view.findViewById(R.id.cv_favorite_dialog);
            imageView = view.findViewById(R.id.iv_dialog_favorite);
        }
    }
}