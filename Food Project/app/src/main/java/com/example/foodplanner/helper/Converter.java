package com.example.foodplanner.helper;

import android.graphics.Bitmap;

import com.example.foodplanner.Model.FavoriteMeal;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.profile.MealFirebase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Converter {
    public static FavoriteMeal convertMealToFav(Meal meal) {
        return new FavoriteMeal(meal.getEmail(), meal.getIdMeal(), meal.getImage(), meal.getStrMeal(), meal.getStrDrinkAlternate(), meal.getStrCategory(), meal.getStrArea()
                , meal.getStrInstructions(), meal.getStrIngredient(), meal.getStrMealThumb(), meal.getStrYoutube(), meal.getStrIngredient1(), meal.getStrIngredient2(),
                meal.getStrIngredient3(), meal.getStrIngredient4(), meal.getStrIngredient5(), meal.getStrIngredient6(), meal.getStrIngredient7(), meal.getStrIngredient8(), meal.getStrIngredient9(), meal.getStrIngredient10(), meal.getStrIngredient11(), meal.getStrIngredient12(), meal.getStrIngredient13(), meal.getStrIngredient14(), meal.getStrIngredient15(), meal.getStrIngredient16(), meal.getStrIngredient17(), meal.getStrIngredient18(), meal.getStrIngredient19(), meal.getStrIngredient20(), meal.getStrMeasure1(), meal.getStrMeasure2(), meal.getStrMeasure3(), meal.getStrMeasure4(), meal.getStrMeasure5(), meal.getStrMeasure6(), meal.getStrMeasure7(), meal.getStrMeasure8(), meal.getStrMeasure9(), meal.getStrMeasure10(), meal.getStrMeasure11(), meal.getStrMeasure12(), meal.getStrMeasure13(), meal.getStrMeasure14(), meal.getStrMeasure15(), meal.getStrMeasure16(), meal.getStrMeasure17(), meal.getStrMeasure18(), meal.getStrMeasure19(), meal.getStrMeasure20());
    }

    public static Meal convertFavToMeal(FavoriteMeal meal) {
        return new Meal(meal.getEmail(), meal.getIdMeal(), "", meal.getImage(), meal.getStrMeal(), meal.getStrDrinkAlternate(), meal.getStrCategory(), meal.getStrArea()
                , meal.getStrInstructions(), meal.getStrIngredient(), meal.getStrMealThumb(), meal.getStrYoutube(), meal.getStrIngredient1(), meal.getStrIngredient2(),
                meal.getStrIngredient3(), meal.getStrIngredient4(), meal.getStrIngredient5(), meal.getStrIngredient6(), meal.getStrIngredient7(), meal.getStrIngredient8(), meal.getStrIngredient9(), meal.getStrIngredient10(), meal.getStrIngredient11(), meal.getStrIngredient12(), meal.getStrIngredient13(), meal.getStrIngredient14(), meal.getStrIngredient15(), meal.getStrIngredient16(), meal.getStrIngredient17(), meal.getStrIngredient18(), meal.getStrIngredient19(), meal.getStrIngredient20(), meal.getStrMeasure1(), meal.getStrMeasure2(), meal.getStrMeasure3(), meal.getStrMeasure4(), meal.getStrMeasure5(), meal.getStrMeasure6(), meal.getStrMeasure7(), meal.getStrMeasure8(), meal.getStrMeasure9(), meal.getStrMeasure10(), meal.getStrMeasure11(), meal.getStrMeasure12(), meal.getStrMeasure13(), meal.getStrMeasure14(), meal.getStrMeasure15(), meal.getStrMeasure16(), meal.getStrMeasure17(), meal.getStrMeasure18(), meal.getStrMeasure19(), meal.getStrMeasure20());
    }

    public static Meal createMeal(Meal meal) {
        return new Meal(meal.getEmail(), meal.getIdMeal(), "", meal.getImage(), meal.getStrMeal(), meal.getStrDrinkAlternate(), meal.getStrCategory(), meal.getStrArea()
                , meal.getStrInstructions(), meal.getStrIngredient(), meal.getStrMealThumb(), meal.getStrYoutube(), meal.getStrIngredient1(), meal.getStrIngredient2(),
                meal.getStrIngredient3(), meal.getStrIngredient4(), meal.getStrIngredient5(), meal.getStrIngredient6(), meal.getStrIngredient7(), meal.getStrIngredient8(), meal.getStrIngredient9(), meal.getStrIngredient10(), meal.getStrIngredient11(), meal.getStrIngredient12(), meal.getStrIngredient13(), meal.getStrIngredient14(), meal.getStrIngredient15(), meal.getStrIngredient16(), meal.getStrIngredient17(), meal.getStrIngredient18(), meal.getStrIngredient19(), meal.getStrIngredient20(), meal.getStrMeasure1(), meal.getStrMeasure2(), meal.getStrMeasure3(), meal.getStrMeasure4(), meal.getStrMeasure5(), meal.getStrMeasure6(), meal.getStrMeasure7(), meal.getStrMeasure8(), meal.getStrMeasure9(), meal.getStrMeasure10(), meal.getStrMeasure11(), meal.getStrMeasure12(), meal.getStrMeasure13(), meal.getStrMeasure14(), meal.getStrMeasure15(), meal.getStrMeasure16(), meal.getStrMeasure17(), meal.getStrMeasure18(), meal.getStrMeasure19(), meal.getStrMeasure20());

    }

    public static byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }
    public static MealFirebase mealFirebaseFav(FavoriteMeal meal) {
        MealFirebase fireBaseRecord = new MealFirebase();
        fireBaseRecord.setIdMeal(meal.getIdMeal());
        fireBaseRecord.setEmail(meal.getEmail());
        fireBaseRecord.setStrArea(meal.getStrArea());
        fireBaseRecord.setStrCategory(meal.getStrCategory());
        fireBaseRecord.setStrMeal(meal.getStrMeal());
        fireBaseRecord.setStrIngredient(meal.getStrIngredient());
        fireBaseRecord.setStrInstructions(meal.getStrInstructions());
        fireBaseRecord.setStrMealThumb(meal.getStrMealThumb());
        fireBaseRecord.setStrYoutube(meal.getStrYoutube());
        fireBaseRecord.setStrIngredient1(meal.getStrIngredient1());
        fireBaseRecord.setStrIngredient2(meal.getStrIngredient2());
        fireBaseRecord.setStrIngredient3(meal.getStrIngredient3());
        fireBaseRecord.setStrIngredient4(meal.getStrIngredient4());
        fireBaseRecord.setStrIngredient5(meal.getStrIngredient5());
        fireBaseRecord.setStrIngredient6(meal.getStrIngredient6());
        fireBaseRecord.setStrIngredient7(meal.getStrIngredient7());
        fireBaseRecord.setStrIngredient8(meal.getStrIngredient8());
        fireBaseRecord.setStrIngredient9(meal.getStrIngredient9());
        fireBaseRecord.setStrIngredient10(meal.getStrIngredient10());
        fireBaseRecord.setStrIngredient11(meal.getStrIngredient11());
        fireBaseRecord.setStrIngredient12(meal.getStrIngredient12());
        fireBaseRecord.setStrIngredient13(meal.getStrIngredient13());
        fireBaseRecord.setStrIngredient14(meal.getStrIngredient14());
        fireBaseRecord.setStrIngredient15(meal.getStrIngredient15());
        fireBaseRecord.setStrIngredient16(meal.getStrIngredient16());
        fireBaseRecord.setStrIngredient17(meal.getStrIngredient17());
        fireBaseRecord.setStrIngredient18(meal.getStrIngredient18());
        fireBaseRecord.setStrIngredient19(meal.getStrIngredient19());
        fireBaseRecord.setStrIngredient20(meal.getStrIngredient20());

        fireBaseRecord.setStrMeasure1(meal.getStrMeasure1());
        fireBaseRecord.setStrMeasure2(meal.getStrMeasure2());
        fireBaseRecord.setStrMeasure3(meal.getStrMeasure3());
        fireBaseRecord.setStrMeasure4(meal.getStrMeasure4());
        fireBaseRecord.setStrMeasure5(meal.getStrMeasure5());
        fireBaseRecord.setStrMeasure6(meal.getStrMeasure6());
        fireBaseRecord.setStrMeasure7(meal.getStrMeasure7());
        fireBaseRecord.setStrMeasure8(meal.getStrMeasure8());
        fireBaseRecord.setStrMeasure9(meal.getStrMeasure9());
        fireBaseRecord.setStrMeasure10(meal.getStrMeasure10());
        fireBaseRecord.setStrMeasure11(meal.getStrMeasure11());
        fireBaseRecord.setStrMeasure12(meal.getStrMeasure12());
        fireBaseRecord.setStrMeasure13(meal.getStrMeasure13());
        fireBaseRecord.setStrMeasure14(meal.getStrMeasure14());
        fireBaseRecord.setStrMeasure15(meal.getStrMeasure15());
        fireBaseRecord.setStrMeasure16(meal.getStrMeasure16());
        fireBaseRecord.setStrMeasure17(meal.getStrMeasure17());
        fireBaseRecord.setStrMeasure18(meal.getStrMeasure18());
        fireBaseRecord.setStrMeasure19(meal.getStrMeasure19());
        fireBaseRecord.setStrMeasure20(meal.getStrMeasure20());
        fireBaseRecord.setImages(IntStream.range(0, meal.getImage().length).mapToObj(i -> (int) meal.getImage()[i]).collect(Collectors.toList()));
        return fireBaseRecord;

    }
    public static MealFirebase mealFirebasePlan(Meal meal) {
        MealFirebase fireBaseRecord = new MealFirebase();
        fireBaseRecord.setIdMeal(meal.getIdMeal());
        fireBaseRecord.setDay(meal.getDay());
        fireBaseRecord.setEmail(meal.getEmail());
        fireBaseRecord.setStrArea(meal.getStrArea());
        fireBaseRecord.setStrCategory(meal.getStrCategory());
        fireBaseRecord.setStrMeal(meal.getStrMeal());
        fireBaseRecord.setStrIngredient(meal.getStrIngredient());
        fireBaseRecord.setStrInstructions(meal.getStrInstructions());
        fireBaseRecord.setStrMealThumb(meal.getStrMealThumb());
        fireBaseRecord.setStrYoutube(meal.getStrYoutube());
        fireBaseRecord.setStrIngredient1(meal.getStrIngredient1());
        fireBaseRecord.setStrIngredient2(meal.getStrIngredient2());
        fireBaseRecord.setStrIngredient3(meal.getStrIngredient3());
        fireBaseRecord.setStrIngredient4(meal.getStrIngredient4());
        fireBaseRecord.setStrIngredient5(meal.getStrIngredient5());
        fireBaseRecord.setStrIngredient6(meal.getStrIngredient6());
        fireBaseRecord.setStrIngredient7(meal.getStrIngredient7());
        fireBaseRecord.setStrIngredient8(meal.getStrIngredient8());
        fireBaseRecord.setStrIngredient9(meal.getStrIngredient9());
        fireBaseRecord.setStrIngredient10(meal.getStrIngredient10());
        fireBaseRecord.setStrIngredient11(meal.getStrIngredient11());
        fireBaseRecord.setStrIngredient12(meal.getStrIngredient12());
        fireBaseRecord.setStrIngredient13(meal.getStrIngredient13());
        fireBaseRecord.setStrIngredient14(meal.getStrIngredient14());
        fireBaseRecord.setStrIngredient15(meal.getStrIngredient15());
        fireBaseRecord.setStrIngredient16(meal.getStrIngredient16());
        fireBaseRecord.setStrIngredient17(meal.getStrIngredient17());
        fireBaseRecord.setStrIngredient18(meal.getStrIngredient18());
        fireBaseRecord.setStrIngredient19(meal.getStrIngredient19());
        fireBaseRecord.setStrIngredient20(meal.getStrIngredient20());

        fireBaseRecord.setStrMeasure1(meal.getStrMeasure1());
        fireBaseRecord.setStrMeasure2(meal.getStrMeasure2());
        fireBaseRecord.setStrMeasure3(meal.getStrMeasure3());
        fireBaseRecord.setStrMeasure4(meal.getStrMeasure4());
        fireBaseRecord.setStrMeasure5(meal.getStrMeasure5());
        fireBaseRecord.setStrMeasure6(meal.getStrMeasure6());
        fireBaseRecord.setStrMeasure7(meal.getStrMeasure7());
        fireBaseRecord.setStrMeasure8(meal.getStrMeasure8());
        fireBaseRecord.setStrMeasure9(meal.getStrMeasure9());
        fireBaseRecord.setStrMeasure10(meal.getStrMeasure10());
        fireBaseRecord.setStrMeasure11(meal.getStrMeasure11());
        fireBaseRecord.setStrMeasure12(meal.getStrMeasure12());
        fireBaseRecord.setStrMeasure13(meal.getStrMeasure13());
        fireBaseRecord.setStrMeasure14(meal.getStrMeasure14());
        fireBaseRecord.setStrMeasure15(meal.getStrMeasure15());
        fireBaseRecord.setStrMeasure16(meal.getStrMeasure16());
        fireBaseRecord.setStrMeasure17(meal.getStrMeasure17());
        fireBaseRecord.setStrMeasure18(meal.getStrMeasure18());
        fireBaseRecord.setStrMeasure19(meal.getStrMeasure19());
        fireBaseRecord.setStrMeasure20(meal.getStrMeasure20());
        fireBaseRecord.setImages(IntStream.range(0, meal.getImage().length).mapToObj(i -> (int) meal.getImage()[i]).collect(Collectors.toList()));
        return fireBaseRecord;

    }

    public static Meal getMealFroMeaLFirebase(MealFirebase meal) throws IOException {
          Meal fireBaseRecord = new Meal();
        fireBaseRecord.setIdMeal(meal.getIdMeal());
        fireBaseRecord.setDay(meal.getDay());
        fireBaseRecord.setEmail(meal.getEmail());
        fireBaseRecord.setStrArea(meal.getStrArea());
        fireBaseRecord.setStrCategory(meal.getStrCategory());
        fireBaseRecord.setStrMeal(meal.getStrMeal());
        fireBaseRecord.setStrIngredient(meal.getStrIngredient());
        fireBaseRecord.setStrInstructions(meal.getStrInstructions());
        fireBaseRecord.setStrMealThumb(meal.getStrMealThumb());
        fireBaseRecord.setStrYoutube(meal.getStrYoutube());
        fireBaseRecord.setStrIngredient1(meal.getStrIngredient1());
        fireBaseRecord.setStrIngredient2(meal.getStrIngredient2());
        fireBaseRecord.setStrIngredient3(meal.getStrIngredient3());
        fireBaseRecord.setStrIngredient4(meal.getStrIngredient4());
        fireBaseRecord.setStrIngredient5(meal.getStrIngredient5());
        fireBaseRecord.setStrIngredient6(meal.getStrIngredient6());
        fireBaseRecord.setStrIngredient7(meal.getStrIngredient7());
        fireBaseRecord.setStrIngredient8(meal.getStrIngredient8());
        fireBaseRecord.setStrIngredient9(meal.getStrIngredient9());
        fireBaseRecord.setStrIngredient10(meal.getStrIngredient10());
        fireBaseRecord.setStrIngredient11(meal.getStrIngredient11());
        fireBaseRecord.setStrIngredient12(meal.getStrIngredient12());
        fireBaseRecord.setStrIngredient13(meal.getStrIngredient13());
        fireBaseRecord.setStrIngredient14(meal.getStrIngredient14());
        fireBaseRecord.setStrIngredient15(meal.getStrIngredient15());
        fireBaseRecord.setStrIngredient16(meal.getStrIngredient16());
        fireBaseRecord.setStrIngredient17(meal.getStrIngredient17());
        fireBaseRecord.setStrIngredient18(meal.getStrIngredient18());
        fireBaseRecord.setStrIngredient19(meal.getStrIngredient19());
        fireBaseRecord.setStrIngredient20(meal.getStrIngredient20());
        fireBaseRecord.setStrMeasure1(meal.getStrMeasure1());
        fireBaseRecord.setStrMeasure2(meal.getStrMeasure2());
        fireBaseRecord.setStrMeasure3(meal.getStrMeasure3());
        fireBaseRecord.setStrMeasure4(meal.getStrMeasure4());
        fireBaseRecord.setStrMeasure5(meal.getStrMeasure5());
        fireBaseRecord.setStrMeasure6(meal.getStrMeasure6());
        fireBaseRecord.setStrMeasure7(meal.getStrMeasure7());
        fireBaseRecord.setStrMeasure8(meal.getStrMeasure8());
        fireBaseRecord.setStrMeasure9(meal.getStrMeasure9());
        fireBaseRecord.setStrMeasure10(meal.getStrMeasure10());
        fireBaseRecord.setStrMeasure11(meal.getStrMeasure11());
        fireBaseRecord.setStrMeasure12(meal.getStrMeasure12());
        fireBaseRecord.setStrMeasure13(meal.getStrMeasure13());
        fireBaseRecord.setStrMeasure14(meal.getStrMeasure14());
        fireBaseRecord.setStrMeasure15(meal.getStrMeasure15());
        fireBaseRecord.setStrMeasure16(meal.getStrMeasure16());
        fireBaseRecord.setStrMeasure17(meal.getStrMeasure17());
        fireBaseRecord.setStrMeasure18(meal.getStrMeasure18());
        fireBaseRecord.setStrMeasure19(meal.getStrMeasure19());
        fireBaseRecord.setStrMeasure20(meal.getStrMeasure20());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(meal.getImages());
        fireBaseRecord.setImage(bos.toByteArray() );
        return fireBaseRecord;
    }
    public static FavoriteMeal getFavFroMeaLFirebase(MealFirebase meal) throws IOException {
        FavoriteMeal fireBaseRecord = new FavoriteMeal();
        fireBaseRecord.setIdMeal(meal.getIdMeal());
        fireBaseRecord.setEmail(meal.getEmail());
        fireBaseRecord.setStrArea(meal.getStrArea());
        fireBaseRecord.setStrCategory(meal.getStrCategory());
        fireBaseRecord.setStrMeal(meal.getStrMeal());
        fireBaseRecord.setStrIngredient(meal.getStrIngredient());
        fireBaseRecord.setStrInstructions(meal.getStrInstructions());
        fireBaseRecord.setStrMealThumb(meal.getStrMealThumb());
        fireBaseRecord.setStrYoutube(meal.getStrYoutube());
        fireBaseRecord.setStrIngredient1(meal.getStrIngredient1());
        fireBaseRecord.setStrIngredient2(meal.getStrIngredient2());
        fireBaseRecord.setStrIngredient3(meal.getStrIngredient3());
        fireBaseRecord.setStrIngredient4(meal.getStrIngredient4());
        fireBaseRecord.setStrIngredient5(meal.getStrIngredient5());
        fireBaseRecord.setStrIngredient6(meal.getStrIngredient6());
        fireBaseRecord.setStrIngredient7(meal.getStrIngredient7());
        fireBaseRecord.setStrIngredient8(meal.getStrIngredient8());
        fireBaseRecord.setStrIngredient9(meal.getStrIngredient9());
        fireBaseRecord.setStrIngredient10(meal.getStrIngredient10());
        fireBaseRecord.setStrIngredient11(meal.getStrIngredient11());
        fireBaseRecord.setStrIngredient12(meal.getStrIngredient12());
        fireBaseRecord.setStrIngredient13(meal.getStrIngredient13());
        fireBaseRecord.setStrIngredient14(meal.getStrIngredient14());
        fireBaseRecord.setStrIngredient15(meal.getStrIngredient15());
        fireBaseRecord.setStrIngredient16(meal.getStrIngredient16());
        fireBaseRecord.setStrIngredient17(meal.getStrIngredient17());
        fireBaseRecord.setStrIngredient18(meal.getStrIngredient18());
        fireBaseRecord.setStrIngredient19(meal.getStrIngredient19());
        fireBaseRecord.setStrIngredient20(meal.getStrIngredient20());
        fireBaseRecord.setStrMeasure1(meal.getStrMeasure1());
        fireBaseRecord.setStrMeasure2(meal.getStrMeasure2());
        fireBaseRecord.setStrMeasure3(meal.getStrMeasure3());
        fireBaseRecord.setStrMeasure4(meal.getStrMeasure4());
        fireBaseRecord.setStrMeasure5(meal.getStrMeasure5());
        fireBaseRecord.setStrMeasure6(meal.getStrMeasure6());
        fireBaseRecord.setStrMeasure7(meal.getStrMeasure7());
        fireBaseRecord.setStrMeasure8(meal.getStrMeasure8());
        fireBaseRecord.setStrMeasure9(meal.getStrMeasure9());
        fireBaseRecord.setStrMeasure10(meal.getStrMeasure10());
        fireBaseRecord.setStrMeasure11(meal.getStrMeasure11());
        fireBaseRecord.setStrMeasure12(meal.getStrMeasure12());
        fireBaseRecord.setStrMeasure13(meal.getStrMeasure13());
        fireBaseRecord.setStrMeasure14(meal.getStrMeasure14());
        fireBaseRecord.setStrMeasure15(meal.getStrMeasure15());
        fireBaseRecord.setStrMeasure16(meal.getStrMeasure16());
        fireBaseRecord.setStrMeasure17(meal.getStrMeasure17());
        fireBaseRecord.setStrMeasure18(meal.getStrMeasure18());
        fireBaseRecord.setStrMeasure19(meal.getStrMeasure19());
        fireBaseRecord.setStrMeasure20(meal.getStrMeasure20());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(meal.getImages());
        fireBaseRecord.setImage(bos.toByteArray() );
        return fireBaseRecord;
    }
}
