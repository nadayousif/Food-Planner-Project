package com.example.foodplanner.plan.dialog.search.searchresult;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.helper.Converter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AdapterSearchDialog extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Meal> arr;
    private List<Meal> selectedMeal;
    private Context context;

    public AdapterSearchDialog() {
        this.arr = new ArrayList<>();
        this.selectedMeal= new ArrayList<>();
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
        ((SearchViewHolder) holder).cardView.setCardBackgroundColor(arr.get(position).isSelect()? context.getColor(R.color.primaryColor) : context.getColor(R.color.secondaryColor));
        Glide.with(context)
                .asBitmap()
                .load(arr.get(position).getStrMealThumb() + "/preview")
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        ((SearchViewHolder) holder).imageView.setImageBitmap(resource);
                        arr.get(holder.getAbsoluteAdapterPosition()).setImage(Converter.getBytesFromBitmap(resource));
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
        ((SearchViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arr.get(holder.getAbsoluteAdapterPosition()).setSelect();
                if (arr.get(holder.getAbsoluteAdapterPosition()).isSelect())
                    selectedMeal.add(arr.get(holder.getAbsoluteAdapterPosition()));
                else
                    selectedMeal.remove(arr.get(holder.getAbsoluteAdapterPosition()));
                notifyItemChanged(holder.getAbsoluteAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return arr.size();
    }

    public List<Meal> getListMeals() {
        return selectedMeal;
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder{
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