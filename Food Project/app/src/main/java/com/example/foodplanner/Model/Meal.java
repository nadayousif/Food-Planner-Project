package com.example.foodplanner.Model;

import java.util.ArrayList;

public class Meal {
    public Meal(String idMeal, String strMeal) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
    }
    private boolean isSelect=false;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect() {
        isSelect = !isSelect;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    private String idMeal, strMeal, strDrinkAlternate, strCategory, strArea, strInstructions, strIngredient,strMealThumb, strYoutube, strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5, strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10, strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15, strIngredient16, strIngredient17, strIngredient18, strIngredient19, strIngredient20, strMeasure1, strMeasure2, strMeasure3, strMeasure4, strMeasure5, strMeasure6, strMeasure7, strMeasure8, strMeasure9, strMeasure10, strMeasure11, strMeasure12, strMeasure13, strMeasure14, strMeasure15, strMeasure16, strMeasure17, strMeasure18, strMeasure19, strMeasure20;

    public String getIdMeal() {
        return idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrDrinkAlternate() {
        return strDrinkAlternate;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public String[] getStrInstructions() {

        return strInstructions.split("\n");
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public ArrayList<String> getIngredients() {
        ArrayList<String> list = new ArrayList<>();
        if (strIngredient1 != null && !strIngredient1.isEmpty())
            list.add(strIngredient1);

        if (strIngredient2 != null && !strIngredient2.isEmpty())
            list.add(strIngredient2);
        if (strIngredient3 != null && !strIngredient3.isEmpty())
            list.add(strIngredient3);
        if (strIngredient4 != null && !strIngredient4.isEmpty())
            list.add(strIngredient4);
        if (strIngredient5 != null && !strIngredient5.isEmpty())
            list.add(strIngredient5);
        if (strIngredient6 != null && !strIngredient6.isEmpty())
            list.add(strIngredient6);
        if (strIngredient7 != null && !strIngredient7.isEmpty())
            list.add(strIngredient7);
        if (strIngredient8 != null && !strIngredient8.isEmpty())
            list.add(strIngredient8);
        if (strIngredient9 != null && !strIngredient9.isEmpty())
            list.add(strIngredient9);
        if (strIngredient10 != null && !strIngredient10.isEmpty())
            list.add(strIngredient10);
        if (strIngredient11 != null && !strIngredient11.isEmpty())
            list.add(strIngredient11);
        if (strIngredient12 != null && !strIngredient12.isEmpty())
            list.add(strIngredient12);
        if (strIngredient13 != null && !strIngredient13.isEmpty())
            list.add(strIngredient13);
        if (strIngredient14 != null && !strIngredient14.isEmpty())
            list.add(strIngredient14);
        if (strIngredient15 != null && !strIngredient15.isEmpty())
            list.add(strIngredient15);
        if (strIngredient16 != null && !strIngredient16.isEmpty())
            list.add(strIngredient16);
        if (strIngredient17 != null && !strIngredient17.isEmpty())
            list.add(strIngredient17);
        if (strIngredient18 != null && !strIngredient18.isEmpty())
            list.add(strIngredient18);
        if (strIngredient19 != null && !strIngredient19.isEmpty())
            list.add(strIngredient19);
        if (strIngredient20 != null && !strIngredient20.isEmpty())
            list.add(strIngredient20);
        return list;
    }
    public ArrayList<String> getMeasures() {
        ArrayList<String> list = new ArrayList<>();
        if (strMeasure1 != null && !strMeasure1.isEmpty())
            list.add(strMeasure1);
        if (strMeasure2 != null && !strMeasure2.isEmpty())
            list.add(strMeasure2);
        if (strMeasure3 != null && !strMeasure3.isEmpty())
            list.add(strMeasure3);
        if (strMeasure4 != null && !strMeasure4.isEmpty())
            list.add(strMeasure4);
        if (strMeasure5 != null && !strMeasure5.isEmpty())
            list.add(strMeasure5);
        if (strMeasure6 != null && !strMeasure6.isEmpty())
            list.add(strMeasure6);
        if (strMeasure7 != null && !strMeasure7.isEmpty())
            list.add(strMeasure7);
        if (strMeasure8 != null && !strMeasure8.isEmpty())
            list.add(strMeasure8);
        if (strMeasure9 != null && !strMeasure9.isEmpty())
            list.add(strMeasure9);
        if (strMeasure10 != null && !strMeasure10.isEmpty())
            list.add(strMeasure10);
        if (strMeasure11 != null && !strMeasure11.isEmpty())
            list.add(strMeasure11);
        if (strMeasure12 != null && !strMeasure12.isEmpty())
            list.add(strMeasure12);
        if (strMeasure13 != null && !strMeasure13.isEmpty())
            list.add(strMeasure13);
        if (strMeasure14 != null && !strMeasure14.isEmpty())
            list.add(strMeasure14);
        if (strMeasure15 != null && !strMeasure15.isEmpty())
            list.add(strMeasure15);
        if (strMeasure16 != null && !strMeasure16.isEmpty())
            list.add(strMeasure16);
        if (strMeasure17 != null && !strMeasure17.isEmpty())
            list.add(strMeasure17);
        if (strMeasure18 != null && !strMeasure18.isEmpty())
            list.add(strMeasure18);
        if (strMeasure19 != null && !strMeasure19.isEmpty())
            list.add(strMeasure19);
        if (strMeasure20 != null && !strMeasure20.isEmpty())
            list.add(strMeasure20);
        return list;
    }

    public Meal(String strMeal) {
        this.strMeal = strMeal;
    }

    public Meal(String idMeal, String strMeal, String strDrinkAlternate, String strCategory, String strArea, String strInstructions, String strMealThumb, String strYoutube, String strIngredient1, String strIngredient2, String strIngredient3, String strIngredient4, String strIngredient5, String strIngredient6, String strIngredient7, String strIngredient8, String strIngredient9, String strIngredient10, String strIngredient11, String strIngredient12, String strIngredient13, String strIngredient14, String strIngredient15, String strIngredient16, String strIngredient17, String strIngredient18, String strIngredient19, String strIngredient20, String strMeasure1, String strMeasure2, String strMeasure3, String strMeasure4, String strMeasure5, String strMeasure6, String strMeasure7, String strMeasure8, String strMeasure9, String strMeasure10, String strMeasure11, String strMeasure12, String strMeasure13, String strMeasure14, String strMeasure15, String strMeasure16, String strMeasure17, String strMeasure18, String strMeasure19, String strMeasure20) {

        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strDrinkAlternate = strDrinkAlternate;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strYoutube = strYoutube;
        this.strIngredient1 = strIngredient1;
        this.strIngredient2 = strIngredient2;
        this.strIngredient3 = strIngredient3;
        this.strIngredient4 = strIngredient4;
        this.strIngredient5 = strIngredient5;
        this.strIngredient6 = strIngredient6;
        this.strIngredient7 = strIngredient7;
        this.strIngredient8 = strIngredient8;
        this.strIngredient9 = strIngredient9;
        this.strIngredient10 = strIngredient10;
        this.strIngredient11 = strIngredient11;
        this.strIngredient12 = strIngredient12;
        this.strIngredient13 = strIngredient13;
        this.strIngredient14 = strIngredient14;
        this.strIngredient15 = strIngredient15;
        this.strIngredient16 = strIngredient16;
        this.strIngredient17 = strIngredient17;
        this.strIngredient18 = strIngredient18;
        this.strIngredient19 = strIngredient19;
        this.strIngredient20 = strIngredient20;
        this.strMeasure1 = strMeasure1;
        this.strMeasure2 = strMeasure2;
        this.strMeasure3 = strMeasure3;
        this.strMeasure4 = strMeasure4;
        this.strMeasure5 = strMeasure5;
        this.strMeasure6 = strMeasure6;
        this.strMeasure7 = strMeasure7;
        this.strMeasure8 = strMeasure8;
        this.strMeasure9 = strMeasure9;
        this.strMeasure10 = strMeasure10;
        this.strMeasure11 = strMeasure11;
        this.strMeasure12 = strMeasure12;
        this.strMeasure13 = strMeasure13;
        this.strMeasure14 = strMeasure14;
        this.strMeasure15 = strMeasure15;
        this.strMeasure16 = strMeasure16;
        this.strMeasure17 = strMeasure17;
        this.strMeasure18 = strMeasure18;
        this.strMeasure19 = strMeasure19;
        this.strMeasure20 = strMeasure20;
    }
}

