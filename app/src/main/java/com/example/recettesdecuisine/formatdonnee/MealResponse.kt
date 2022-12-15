package com.example.recettesdecuisine.formatdonnee

import com.google.gson.annotations.SerializedName

class MealResponse {
    var meals: List<Meal>? = null
}

class Meal {
    @SerializedName("idMeal")
    var id : Int? = null

    @SerializedName("strMeal")
    var name : String? = null

    @SerializedName("strMealThumb")
    var thumb : String? = null
}