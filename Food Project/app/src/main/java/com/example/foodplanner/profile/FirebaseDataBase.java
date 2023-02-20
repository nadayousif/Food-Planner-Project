package com.example.foodplanner.profile;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foodplanner.DBConnection.localdatabase.localdb.ConcreteLocalData;
import com.example.foodplanner.DBConnection.localdatabase.localdb.LocalDataSource;
import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.helper.Converter;
import com.example.foodplanner.helper.MyUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class FirebaseDataBase {

    private static final String TAG = "TAGG";

    public static void addFavouriteToFirebase(Context context, MealFirebase meal) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            Log.i(TAG, "addFavouriteToFirebase: user not login ");
        } else {


            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
            ref.child(firebaseAuth.getUid()).child("Favorites").child(meal.getStrMeal()).setValue(meal)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.i(TAG, "onSuccess: addFavouriteToFirebase");
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
        if (firebaseAuth.getCurrentUser() == null) {
            Toast.makeText(context, "you\re not logged in", Toast.LENGTH_SHORT).show();
        } else {

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Food Planner's Users");
            ref.child(firebaseAuth.getUid()).child("Plan").child(meal.getDay()).child(meal.getStrMeal()).setValue(meal)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context, "added to firebase", Toast.LENGTH_SHORT).show();
                            Log.i("TAGG", "onSuccess: firebase ");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("TAGG", "onFailure: " + e.getMessage());

                        }
                    });


        }
    }

    public static void readData(Context context) {
        LocalDataSource localDataSource = ConcreteLocalData.getInstance(context);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance().getReference()
                .child("Food Planner's Users").
                child(firebaseAuth.getUid()).child("Favorites")


                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            MealFirebase meal = dataSnapshot.getValue(MealFirebase.class);
                            try {
                                FavoriteMeal meal1 = Converter.getFavFroMeaLFirebase(meal);
                                meal1.setEmail(MyUser.getInstance().getEmail());
                                localDataSource.addToFavorite(meal1);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Log.i(TAG, "onDataChange: " + meal.getStrMeal());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.i("test", error.getMessage());
                    }
                });

        FirebaseDatabase.getInstance().getReference().child("Food Planner's Users").child(firebaseAuth.getUid()).child("Plan").child("Saturday")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            MealFirebase meal = dataSnapshot.getValue(MealFirebase.class);
                            try {
                                Meal meal1 = Converter.getMealFroMeaLFirebase(meal);
                                meal.setDay("Saturday");
                                localDataSource.addToPlan(meal1);

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.i("test", error.getMessage());
                    }
                });
        FirebaseDatabase.getInstance().getReference().child("Food Planner's Users").child(firebaseAuth.getUid()).child("Plan").child("Sunday")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            MealFirebase meal = dataSnapshot.getValue(MealFirebase.class);
                            try {
                                Meal meal1 = Converter.getMealFroMeaLFirebase(meal);
                                meal.setDay("Sunday");
                                localDataSource.addToPlan(meal1);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.i("test", error.getMessage());
                    }
                });
        FirebaseDatabase.getInstance().getReference().child("Food Planner's Users").child(firebaseAuth.getUid()).child("Plan").child("Monday")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            MealFirebase meal = dataSnapshot.getValue(MealFirebase.class);
                            try {
                                Meal meal1 = Converter.getMealFroMeaLFirebase(meal);
                                meal.setDay("Monday");
                                localDataSource.addToPlan(meal1);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.i("test", error.getMessage());
                    }
                });
        FirebaseDatabase.getInstance().getReference().child("Food Planner's Users").child(firebaseAuth.getUid()).child("Plan").child("Tuesday")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            MealFirebase meal = dataSnapshot.getValue(MealFirebase.class);
                            try {
                                Meal meal1 = Converter.getMealFroMeaLFirebase(meal);
                                meal.setDay("Tuesday");
                                localDataSource.addToPlan(meal1);
                            } catch (IOException e) {

                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.i("test", error.getMessage());
                    }
                });

        FirebaseDatabase.getInstance().getReference().child("Food Planner's Users").child(firebaseAuth.getUid()).child("Plan").child("Wednesday")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            MealFirebase meal = dataSnapshot.getValue(MealFirebase.class);
                            try {
                                Meal meal1 = Converter.getMealFroMeaLFirebase(meal);
                                meal.setDay("Wednesday");
                                localDataSource.addToPlan(meal1);
                            } catch (IOException e) {

                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.i("test", error.getMessage());
                    }
                });

        FirebaseDatabase.getInstance().getReference().child("Food Planner's Users").child(firebaseAuth.getUid()).child("Plan").child("Thursday")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            MealFirebase meal = dataSnapshot.getValue(MealFirebase.class);
                            try {
                                Meal meal1 = Converter.getMealFroMeaLFirebase(meal);
                                meal.setDay("Thursday");
                                localDataSource.addToPlan(meal1);
                            } catch (IOException e) {

                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.i("test", error.getMessage());
                    }
                });
        FirebaseDatabase.getInstance().getReference().child("Food Planner's Users").child(firebaseAuth.getUid()).child("Plan").child("Friday")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            MealFirebase meal = dataSnapshot.getValue(MealFirebase.class);
                           try {
                                Meal meal1 = Converter.getMealFroMeaLFirebase(meal);
                                meal.setDay("Friday");
                                localDataSource.addToPlan(meal1);
                            } catch (IOException e) {

                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.i("test", error.getMessage());
                    }
                });

    }




    public static void removeFavouriteFromFirebase(Context context, MealFirebase meal) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {

        } else {
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
        if (firebaseAuth.getCurrentUser() == null) {
            Toast.makeText(context, "you\re not logged in", Toast.LENGTH_SHORT).show();
        } else {
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
