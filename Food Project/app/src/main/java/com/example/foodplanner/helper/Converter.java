package com.example.foodplanner.helper;

import android.graphics.Bitmap;

import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;

import java.io.ByteArrayOutputStream;

public class Converter {
    public static FavoriteMeal convertMealToFav(Meal meal){
        return new FavoriteMeal(meal.getEmail(),meal.getIdMeal(),meal.getImage(), meal.getStrMeal(), meal.getStrDrinkAlternate(), meal.getStrCategory(), meal.getStrArea()
        , meal.getStrInstructions(), meal.getStrIngredient(), meal.getStrMealThumb(), meal.getStrYoutube(), meal.getStrIngredient1(), meal.getStrIngredient2(),
                meal.getStrIngredient3(),meal.getStrIngredient4(),meal.getStrIngredient5(),meal.getStrIngredient6(),meal.getStrIngredient7(),meal.getStrIngredient8(),meal.getStrIngredient9(),meal.getStrIngredient10(),meal.getStrIngredient11(),meal.getStrIngredient12(),meal.getStrIngredient13(),meal.getStrIngredient14(),meal.getStrIngredient15(),meal.getStrIngredient16(),meal.getStrIngredient17(),meal.getStrIngredient18(),meal.getStrIngredient19(),meal.getStrIngredient20(),meal.getStrMeasure1(),meal.getStrMeasure2(),meal.getStrMeasure3(),meal.getStrMeasure4(),meal.getStrMeasure5(),meal.getStrMeasure6(),meal.getStrMeasure7(),meal.getStrMeasure8(),meal.getStrMeasure9(),meal.getStrMeasure10(),meal.getStrMeasure11(),meal.getStrMeasure12(),meal.getStrMeasure13(),meal.getStrMeasure14(),meal.getStrMeasure15(),meal.getStrMeasure16(),meal.getStrMeasure17(),meal.getStrMeasure18(),meal.getStrMeasure19(),meal.getStrMeasure20());
    }
    public static Meal convertFavToMeal(FavoriteMeal meal){
        return new Meal(meal.getEmail(),meal.getIdMeal(),"",meal.getImage(), meal.getStrMeal(), meal.getStrDrinkAlternate(), meal.getStrCategory(), meal.getStrArea()
        , meal.getStrInstructions(), meal.getStrIngredient(), meal.getStrMealThumb(), meal.getStrYoutube(), meal.getStrIngredient1(), meal.getStrIngredient2(),
                meal.getStrIngredient3(),meal.getStrIngredient4(),meal.getStrIngredient5(),meal.getStrIngredient6(),meal.getStrIngredient7(),meal.getStrIngredient8(),meal.getStrIngredient9(),meal.getStrIngredient10(),meal.getStrIngredient11(),meal.getStrIngredient12(),meal.getStrIngredient13(),meal.getStrIngredient14(),meal.getStrIngredient15(),meal.getStrIngredient16(),meal.getStrIngredient17(),meal.getStrIngredient18(),meal.getStrIngredient19(),meal.getStrIngredient20(),meal.getStrMeasure1(),meal.getStrMeasure2(),meal.getStrMeasure3(),meal.getStrMeasure4(),meal.getStrMeasure5(),meal.getStrMeasure6(),meal.getStrMeasure7(),meal.getStrMeasure8(),meal.getStrMeasure9(),meal.getStrMeasure10(),meal.getStrMeasure11(),meal.getStrMeasure12(),meal.getStrMeasure13(),meal.getStrMeasure14(),meal.getStrMeasure15(),meal.getStrMeasure16(),meal.getStrMeasure17(),meal.getStrMeasure18(),meal.getStrMeasure19(),meal.getStrMeasure20());
    }

    public static byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }
}
