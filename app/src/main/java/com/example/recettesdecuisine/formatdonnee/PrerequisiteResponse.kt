package com.example.recettesdecuisine.formatdonnee

import android.util.Log
import com.example.recettesdecuisine.adapter.PrerequisiteAdapter

class PrerequisiteResponse(var details: DetailResponse) {
    var prerequisites : MutableList<Prerequisite>? = mutableListOf()

    fun fill() {
        var i  = 1
        while(!isLast(i, details)) {
            val prerequisite = Prerequisite()
            prerequisite.setIngredientMeasure(details, i)
            prerequisites?.add(prerequisite)
            i++
        }

    }

    private fun isLast(id : Int, details: DetailResponse) : Boolean {
        return details.meals?.get(0)?.getIngredient(id) == null || details.meals?.get(0)?.getIngredient(id) == ""
    }
}

class Prerequisite() {

    var ingredient : String? = null

    var measure : String? = null

    fun setIngredientMeasure(details: DetailResponse, id : Int) {
        ingredient = details.meals?.get(0)?.getIngredient(id)
        measure = details.meals?.get(0)?.getMeasure(id)
    }


}
