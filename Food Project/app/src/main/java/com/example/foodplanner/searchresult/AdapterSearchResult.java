package com.example.foodplanner.searchresult;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

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
import com.example.foodplanner.helper.MyUser;
import com.example.foodplanner.searchresult.presenter.PresenterSearchResult;

import java.util.ArrayList;
import java.util.List;

public class AdapterSearchResult extends RecyclerView.Adapter<AdapterSearchResult.ViewHolder> {
    private List<Meal> arr;
    private OnClickItem onclickItem;
    PresenterSearchResult presenterSearchResult;
    Context context;

    public AdapterSearchResult(OnClickItem onclickItem, PresenterSearchResult presenterSearchResult) {
        this.arr = new ArrayList<>();
        this.onclickItem = onclickItem;
        this.presenterSearchResult=presenterSearchResult;
    }

    public void setArr(List<Meal> arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_search_result, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item.setText(arr.get(position).getStrMeal());
        holder.cardView.setOnClickListener((i) -> {
            onclickItem.onClick(arr.get(position).getIdMeal());
        });
        Glide.with(context)
                .asBitmap()
                .load(arr.get(position).getStrMealThumb() )
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        holder.imageView.setImageBitmap(resource);
                        arr.get(holder.getAbsoluteAdapterPosition()).setImage(Converter.getBytesFromBitmap(resource));
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });

        holder.addToFav.setTag(arr.get(holder.getBindingAdapterPosition()));


    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnViewClickSearchPlan {
        TextView item;
        CardView cardView;
        ImageView imageView;

        ToggleButton addToFav;

        public ViewHolder(View view) {
            super(view);
            item = view.findViewById(R.id.tv_search_result_name_content);
            cardView = view.findViewById(R.id.cv_search_result);
            imageView = view.findViewById(R.id.iv_search_result);
            addToFav = view.findViewById(R.id.iv_search_result_add_to_fav);
            addToFav.setOnClickListener(v -> {
                        v.setEnabled(false);
                        if (MyUser.getInstance().isLogin()) {
                            if (v.getTag() != null)
                            if (((ToggleButton) v).isChecked()) {
                                    onclickItem.addToFav(this, (Meal) v.getTag());
                            } else
                                onclickItem.removeFromFav(this, (Meal) v.getTag());
                        }
                    }
            );
        }
        @Override
        public void setEnable() {
            addToFav.setEnabled(true);
        }

        @Override
        public void undo() {
            addToFav.setChecked(false);
        }
    }
}