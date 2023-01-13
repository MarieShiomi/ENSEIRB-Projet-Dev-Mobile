package com.example.recettesdecuisine.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recettesdecuisine.R
import com.example.recettesdecuisine.formatdonnee.Prerequisite
import com.example.recettesdecuisine.loader.ImageLoader

class PrerequisiteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var prerequisiteName: TextView = itemView.findViewById(R.id.prerequisite_name)
    var prerequisiteImage: ImageView = itemView.findViewById(R.id.prerequisite_image)


}

class PrerequisiteAdapter(private val prerequisites: MutableList<Prerequisite>) : RecyclerView.Adapter<PrerequisiteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrerequisiteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemprerequisites, parent, false)
        return PrerequisiteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PrerequisiteViewHolder, position: Int) {
        val ingredientName = prerequisites[position].ingredient.toString()
        val string = prerequisites[position].measure.toString() + " of " + ingredientName
        holder.prerequisiteName.text = string
        val url =  "https://www.themealdb.com/images/ingredients/$ingredientName-Small.png"
        ImageLoader.loader(url, holder.prerequisiteImage)
    }

    override fun getItemCount(): Int {
        return prerequisites.size
    }

}