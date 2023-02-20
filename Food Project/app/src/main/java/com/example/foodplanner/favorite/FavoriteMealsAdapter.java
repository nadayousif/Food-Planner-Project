package com.example.foodplanner.favorite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMealsAdapter extends RecyclerView.Adapter<FavoriteMealsAdapter.MyViewHolder>{
    private static final String TAG = "MyAdapter";
    Context context;

    OnClickFavoriteItem onClickFavoriteItem;
    private List<FavoriteMeal> arr;

    public FavoriteMealsAdapter(OnClickFavoriteItem onClickFavoriteItem){
        this.arr = new ArrayList<>();
        this.onClickFavoriteItem=onClickFavoriteItem;

    }
    public void setArr(List<FavoriteMeal> arr) {
        this.arr = arr;
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);

        MyViewHolder vh = new MyViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(arr.get(holder.getAbsoluteAdapterPosition()).getStrMeal());
        holder.layout.setOnClickListener((i) -> {
            onClickFavoriteItem.onClick(arr.get(holder.getAbsoluteAdapterPosition()), false);

        });
        if(arr.get(holder.getAbsoluteAdapterPosition()).getImage() !=null){
            Bitmap bmp = BitmapFactory.decodeByteArray(arr.get(holder.getAbsoluteAdapterPosition()).getImage(), 0, arr.get(holder.getAbsoluteAdapterPosition()).getImage().length);
            holder.thumbnails.setImageBitmap(bmp);
            holder.close.setOnClickListener(i -> {
                onClickFavoriteItem.onClick(arr.get(holder.getAbsoluteAdapterPosition()), true);
                arr.remove(holder.getAbsoluteAdapterPosition());
                notifyDataSetChanged();

            });
        }


    }



    class  MyViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout layout;
        TextView name;
        ImageView thumbnails;
        public ImageView close;

        public ConstraintLayout getLayout() {
            return layout;
        }

        public TextView getName() {
            return name;
        }

        public ImageView getThumbnails() {
            return thumbnails;
        }


        public MyViewHolder (View itemView){
            super(itemView);
            layout = itemView.findViewById(R.id.constrain_id);
            name = itemView.findViewById(R.id.text_id);
            thumbnails = itemView.findViewById(R.id.image_favorite);
            close = itemView.findViewById(R.id.iv_button_close_favorite);

        }
    }
}
