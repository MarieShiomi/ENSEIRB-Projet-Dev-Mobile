package com.example.recettesdecuisine.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recettesdecuisine.R
import com.example.recettesdecuisine.formatdonnee.Category
import com.example.recettesdecuisine.listener.OnItemClickListener
import com.example.recettesdecuisine.loader.ImageLoader


class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var categoryName: TextView = itemView.findViewById(R.id.category_name)
    var categoryDesc: TextView = itemView.findViewById(R.id.category_desc)
    var categoryImage: ImageView = itemView.findViewById(R.id.category_image)


}

class CategoryAdapter(private val categories: List<Category>) : RecyclerView.Adapter<CategoryViewHolder>() {
    var listener: OnItemClickListener? = null

    fun setClickListener(listener1: OnItemClickListener){
        listener = listener1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemcategory, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.categoryName.text = categories[position].name
        holder.categoryDesc.text = categories[position].description
        holder.itemView.setOnClickListener {
            categories[position].name?.let { it1 -> listener!!.onClick(it1) }
        }
        ImageLoader.loader(categories[position].thumb, holder.categoryImage)


    }

    override fun getItemCount(): Int {
        return categories.count()
    }

}
