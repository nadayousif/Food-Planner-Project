package com.example.foodplanner.plan.dialog.search.searchresult;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private List<Meal> selectedMeal;
    private OnClickItem onclickItem;
    private Context context;

    public AdapterSearchDialog(OnClickItem onclickItem) {
        this.arr = new ArrayList<>();
        this.selectedMeal= new ArrayList<>();
        this.onclickItem = onclickItem;
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
        Glide.with(context).load(arr.get(position).getStrMealThumb() + "/preview").into(((SearchViewHolder) holder).imageView);
        ((SearchViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arr.get(holder.getAdapterPosition()).setSelect();
                if (arr.get(holder.getAdapterPosition()).isSelect())
                    selectedMeal.add(arr.get(holder.getAdapterPosition()));
                else
                    selectedMeal.remove(arr.get(holder.getAdapterPosition()));
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return arr.size();
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