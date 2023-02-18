package com.example.foodplanner.plan.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterPlan extends RecyclerView.Adapter<AdapterPlan.ViewHolder> {
    private List<Meal> arr;
    private OnClickItem onclickItem;

    public AdapterPlan(OnClickItem onclickItem) {
        this.arr = new ArrayList<>();
        this.onclickItem = onclickItem;
    }

    public void setArr(List<Meal> arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_plan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item.setText(arr.get(position).getStrMeal());
        holder.cardView.setOnClickListener((i) -> {
            onclickItem.onClick(arr.get(position).getIdMeal(), false);
        });
        if (arr.get(position).getImage() != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(arr.get(position).getImage(), 0, arr.get(position).getImage().length);
            holder.imageView.setImageBitmap(bmp);
            holder.close.setOnClickListener(i -> {
                onclickItem.onClick(arr.get(position).getIdMeal(), true);
                arr.remove(position);
                notifyDataSetChanged();
            });
        }
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView close;
        TextView item;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.tv_plan);
            cardView = view.findViewById(R.id.cv_plan);
            imageView = view.findViewById(R.id.iv_plan);
            close = view.findViewById(R.id.iv_button_close_plan);
        }
    }
}