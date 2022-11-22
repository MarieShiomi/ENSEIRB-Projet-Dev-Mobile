package com.example.recettesdecuisine.formatdonnee

import com.google.gson.annotations.SerializedName

class CategoryResponse {

    var categories: List<Category>? = null
}

class Category {
    @SerializedName("idCategory")
    var id : Int? = null

    @SerializedName("strCategory")
    var name : String? = null

    @SerializedName("strCategoryThumb")
    var thumb : String? = null

    @SerializedName("strCategoryDescription")
    var description : String? = null
}