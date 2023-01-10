package com.example.recettesdecuisine.formatdonnee

import com.google.gson.annotations.SerializedName

class DetailResponse{
    var meals : List<Detail>? = null
}

class Detail {

    @SerializedName("strMeal")
    var name : String? = null

    @SerializedName("strArea")
    var area : String? = null

    @SerializedName("strMealThumb")
    var thumb : String? = null

    @SerializedName("strInstruction")
    var instruction : String? = null

    @SerializedName("strYoutube")
    var youtube : String? = null

    var ingredient : Ingredient? = null
    var measure : Measure? = null

}

class Ingredient {
    @SerializedName("strIngredient1")
    private var ing1 : String? = null

    @SerializedName("strIngredient2")
    private var ing2 : String? = null

    @SerializedName("strIngredient3")
    private var ing3 : String? = null

    @SerializedName("strIngredient4")
    private var ing4 : String? = null

    @SerializedName("strIngredient5")
    private var ing5 : String? = null

    @SerializedName("strIngredient6")
    private var ing6 : String? = null

    @SerializedName("strIngredient7")
    private var ing7 : String? = null

    @SerializedName("strIngredient8")
    private var ing8 : String? = null

    @SerializedName("strIngredient9")
    private var ing9 : String? = null

    @SerializedName("strIngredient10")
    private var ing10 : String? = null

    @SerializedName("strIngredient11")
    private var ing11 : String? = null

    @SerializedName("strIngredient12")
    private var ing12 : String? = null

    @SerializedName("strIngredient13")
    private var ing13 : String? = null

    @SerializedName("strIngredient14")
    private var ing14 : String? = null

    @SerializedName("strIngredient15")
    private var ing15 : String? = null

    @SerializedName("strIngredient16")
    private var ing16 : String? = null

    @SerializedName("strIngredient17")
    private var ing17 : String? = null

    @SerializedName("strIngredient18")
    private var ing18 : String? = null

    @SerializedName("strIngredient19")
    private var ing19 : String? = null

    @SerializedName("strIngredient20")
    private var ing20 : String? = null

    fun getIngredient(id : Int) : String? {
        when (id) {
            1 -> return ing1
            2 -> return ing2
            3 -> return ing3
            4 -> return ing4
            5 -> return ing5
            6 -> return ing6
            7 -> return ing7
            8 -> return ing8
            9 -> return ing9
            10 -> return ing10
            11 -> return ing11
            12 -> return ing12
            13 -> return ing13
            14 -> return ing14
            15 -> return ing15
            16 -> return ing16
            17 -> return ing17
            18 -> return ing18
            19 -> return ing19
            20 -> return ing20
            else -> return null
        }
    }
}

class Measure {
    @SerializedName("strMeasure1")
    private var ing1 : String? = null

    @SerializedName("strMeasure2")
    private var ing2 : String? = null

    @SerializedName("strMeasure3")
    private var ing3 : String? = null

    @SerializedName("strMeasure4")
    private var ing4 : String? = null

    @SerializedName("strMeasure5")
    private var ing5 : String? = null

    @SerializedName("strMeasure6")
    private var ing6 : String? = null

    @SerializedName("strMeasure7")
    private var ing7 : String? = null

    @SerializedName("strMeasure8")
    private var ing8 : String? = null

    @SerializedName("strMeasure9")
    private var ing9 : String? = null

    @SerializedName("strMeasure10")
    private var ing10 : String? = null

    @SerializedName("strMeasure11")
    private var ing11 : String? = null

    @SerializedName("strMeasure12")
    private var ing12 : String? = null

    @SerializedName("strMeasure13")
    private var ing13 : String? = null

    @SerializedName("strMeasure14")
    private var ing14 : String? = null

    @SerializedName("strMeasure15")
    private var ing15 : String? = null

    @SerializedName("strMeasure16")
    private var ing16 : String? = null

    @SerializedName("strMeasure17")
    private var ing17 : String? = null

    @SerializedName("strMeasure18")
    private var ing18 : String? = null

    @SerializedName("strMeasure19")
    private var ing19 : String? = null

    @SerializedName("strMeasure20")
    private var ing20 : String? = null

    fun getMeasure(id : Int) : String? {
        when (id) {
            1 -> return ing1
            2 -> return ing2
            3 -> return ing3
            4 -> return ing4
            5 -> return ing5
            6 -> return ing6
            7 -> return ing7
            8 -> return ing8
            9 -> return ing9
            10 -> return ing10
            11 -> return ing11
            12 -> return ing12
            13 -> return ing13
            14 -> return ing14
            15 -> return ing15
            16 -> return ing16
            17 -> return ing17
            18 -> return ing18
            19 -> return ing19
            20 -> return ing20
            else -> return null
        }
    }
}