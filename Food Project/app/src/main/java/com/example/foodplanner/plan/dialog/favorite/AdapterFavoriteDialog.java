package com.example.foodplanner.plan.dialog.favorite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.helper.Converter;
import com.example.foodplanner.plan.dialog.search.searchresult.AdapterSearchDialog;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AdapterFavoriteDialog extends RecyclerView.Adapter<AdapterFavoriteDialog.ViewHolder> {
    private List<FavoriteMeal> arr;
    private List<FavoriteMeal> selectedMeal;
    private Context context;

    public AdapterFavoriteDialog() {
        this.arr = new ArrayList<>();
        this.selectedMeal = new ArrayList<>();
    }

    public void setArr(List<FavoriteMeal> arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_dialog_favorite, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.item.setText(arr.get(position).getStrMeal());
        holder.cardView.setCardBackgroundColor(arr.get(position).isSelect() ? context.getColor(R.color.primaryColor) : context.getColor(R.color.secondaryColor));
//        byte[] imageByteArray = Base64.decode(arr.get(position).getImage(), Base64.DEFAULT);
        Glide.with(context)
                .asBitmap()
                .load(arr.get(position).getImage())
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
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

    public List<FavoriteMeal> getListMeals() {
        return selectedMeal;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.tv_dialog_favorite);
            cardView = view.findViewById(R.id.cv_favorite_dialog);
            imageView = view.findViewById(R.id.iv_dialog_favorite);
        }
    }
}