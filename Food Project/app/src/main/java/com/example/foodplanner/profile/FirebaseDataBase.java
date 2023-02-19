package com.example.foodplanner.profile;
import android.content.Context;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.example.foodplanner.helper.MyUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDataBase {

        public  static void  addFavouriteToFirebase(Context context, MealFirebase meal) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            if (firebaseAuth.getCurrentUser()==null){
                Toast.makeText(context, "you\re not logged in", Toast.LENGTH_SHORT).show();
            }
            else {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
                ref.child(firebaseAuth.getUid()).child("Favorites").child(meal.getStrMeal()).setValue(meal)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "added to firebase", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }
        }

        public static void addPlanToFirebase(Context context, MealFirebase meal) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            if (firebaseAuth.getCurrentUser()==null){
                Toast.makeText(context, "you\re not logged in", Toast.LENGTH_SHORT).show();
            }
            else {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
                ref.child(firebaseAuth.getUid()).child("Plan").child(meal.getDay()).child(meal.getStrMeal()).setValue(meal)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "added to firebase", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });


            }
        }

      /*  public static void getFavouriteFromFirebase(Context context, MyUser user) {

            DatabaseReference rootFav = FirebaseDatabase.getInstance().getReference().child("Food Planner's Users").child(user.getUid()).child("Favorites");
            rootFav.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren() ){
                        Meal meal = dataSnapshot.getValue(Meal.class);
                        GeneralRepository repo =  GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(context),context);
                        repo.insert(meal);
                        Log.i("finaaaaaaaal",meal.getStrMeal()+""+meal.getIdMeal());
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.i("test",error.getMessage());
                }
            });
        }


        public static void getPlanFromFireBase(Context context, MyUser user , String day ) {
            DatabaseReference rootPlan = FirebaseDatabase.getInstance().getReference().child("Food Planner's Users").child(user.getUid()).child("Plan").child(day);
            rootPlan.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren() ) {
                        Meal meal = dataSnapshot.getValue(Meal.class);
                        GeneralRepository repo =  GeneralRepository.getInstance(MealClient.getInstance(), DataBaseRepository.getInstance(context),context);
                        repo.insert(meal);
                        Log.i("finaaaaaaaal",meal.getStrMeal()+""+meal.getIdMeal());
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.i("test",error.getMessage());
                }
            });

        }*/


        public static void removeFavouriteFromFirebase(Context context, MealFirebase meal) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            if (firebaseAuth.getCurrentUser()==null){

            }
            else {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
                ref.child(firebaseAuth.getUid()).child("Favorites").child(meal.getStrMeal()).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

            }
        }

        public static void removePlanFromFireBase(Context context, MealFirebase meal) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            if (firebaseAuth.getCurrentUser()==null){
                Toast.makeText(context, "you\re not logged in", Toast.LENGTH_SHORT).show();
            }
            else {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
                ref.child(firebaseAuth.getUid()).child("Plan").child(meal.getDay()).child(meal.getStrMeal()).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {


                            }
                        });

            }
        }



}
