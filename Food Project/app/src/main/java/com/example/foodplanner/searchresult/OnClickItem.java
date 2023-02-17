package com.example.foodplanner.searchresult;

import com.example.foodplanner.Model.Meal;

interface OnClickItem {
  void onClick(String id);

  void addToFav(OnViewClickSearchPlan onViewClickSearchPlan, Meal tag);

  void removeFromFav(OnViewClickSearchPlan onViewClickSearchPlan, Meal tag);
 }
