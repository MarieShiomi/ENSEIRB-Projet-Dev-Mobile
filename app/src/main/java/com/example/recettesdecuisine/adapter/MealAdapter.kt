package com.example.recettesdecuisine.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recettesdecuisine.R
import com.example.recettesdecuisine.formatdonnee.Category
import com.example.recettesdecuisine.formatdonnee.Meal
import com.example.recettesdecuisine.listener.OnItemClickListener
import com.example.recettesdecuisine.loader.ImageLoader

class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mealName: TextView = itemView.findViewById(R.id.meal_name)
    var mealImage: ImageView = itemView.findViewById(R.id.meal_image)


}

class MealAdapter (private val meals: List<Meal>) : RecyclerView.Adapter<MealViewHolder>() {
    var listener: OnItemClickListener? = null

    fun setClickListener(listener1: OnItemClickListener){
        listener = listener1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemmeal, parent, false)
        return MealViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.mealName.text = meals[position].name
        holder.itemView.setOnClickListener {
            meals[position].id?.let { it1 -> listener!!.onClick(it1.toString()) }
        }
        ImageLoader.loader(meals[position].thumb, holder.mealImage)


    }

    override fun getItemCount(): Int {
        return meals.count()
    }
}