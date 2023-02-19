package com.example.foodplanner.plan.adapter;

import android.content.Context;
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
import com.example.foodplanner.profile.FirebaseDataBase;
import com.example.foodplanner.profile.MealFirebase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AdapterPlan extends RecyclerView.Adapter<AdapterPlan.ViewHolder> {
    private List<Meal> arr;
    private OnClickItem onclickItem;
    Context context;

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
        context = parent.getContext();
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
                MealFirebase fireBaseRecord = new MealFirebase();
                fireBaseRecord.setIdMeal(arr.get(position).getIdMeal());
                fireBaseRecord.setDay(arr.get(position).getDay());
                fireBaseRecord.setEmail(arr.get(position).getEmail());
                fireBaseRecord.setStrArea(arr.get(position).getStrArea());
                fireBaseRecord.setStrCategory(arr.get(position).getStrCategory());
                fireBaseRecord.setStrMeal(arr.get(position).getStrMeal());
                fireBaseRecord.setStrIngredient(arr.get(position).getStrIngredient());
                fireBaseRecord.setStrInstructions(arr.get(position).getStrInstructions());
                fireBaseRecord.setStrMealThumb(arr.get(position).getStrMealThumb());
                fireBaseRecord.setStrYoutube(arr.get(position).getStrYoutube());
                fireBaseRecord.setIngredients(arr.get(position).getIngredients());
                fireBaseRecord.setMeasures(arr.get(position).getMeasures());
                fireBaseRecord.setImages(IntStream.range(0, arr.get(position).getImage().length).mapToObj(e -> (int) arr.get(position).getImage()[e]).collect(Collectors.toList()));
                FirebaseDataBase.removePlanFromFireBase(context, fireBaseRecord);
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