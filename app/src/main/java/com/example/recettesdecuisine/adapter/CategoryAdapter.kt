package com.example.recettesdecuisine.adapter

import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.recettesdecuisine.R
import com.example.recettesdecuisine.formatdonnee.Category
import com.example.recettesdecuisine.loader.ImageLoader
import org.w3c.dom.Text


class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var categoryName: TextView = itemView.findViewById(R.id.category_name)
    var categoryDesc: TextView = itemView.findViewById(R.id.category_desc)
    var categoryImage: ImageView = itemView.findViewById(R.id.category_image)

}

class CategoryAdapter(val categories: List<Category>) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemcategory, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.categoryName.setText(categories.get(position).name)
        holder.categoryDesc.setText(categories.get(position).description)
        ImageLoader.loader(categories.get(position).thumb, holder.categoryImage)


    }

    override fun getItemCount(): Int {
        return categories.count()
    }

}
