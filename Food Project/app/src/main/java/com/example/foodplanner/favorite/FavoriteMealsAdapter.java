package com.example.foodplanner.favorite;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;

import java.util.ArrayList;

public class FavoriteMealsAdapter extends RecyclerView.Adapter<FavoriteMealsAdapter.MyViewHolder>{
    private static final String TAG = "MyAdapter";
    Context context;
    Meal[] meals;
    public FavoriteMealsAdapter(Context _context , Meal[]  _meals){
        context = _context;
        meals = _meals;
    }
    public int getItemCount(){return meals.length;}
    @NonNull
    @Override
    public FavoriteMealsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_favorite , parent , false);
        MyViewHolder vh = new MyViewHolder(view);
        Log.i(TAG , "onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMealsAdapter.MyViewHolder holder, int position) {
        Meal current = meals[position];
        holder.name.setText(current.getStrMeal());
        //holder.thumbnails.setImageResource(current.getStrMealThumb());
        Glide.with(context).load(current.getStrMealThumb()).into(holder.getThumbnails());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,current.getStrMeal(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    class  MyViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout layout;
        TextView name;
        ImageView thumbnails;

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
        }
    }
}
