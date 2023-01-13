package com.example.recettesdecuisine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recettesdecuisine.adapter.MealAdapter
import com.example.recettesdecuisine.databinding.ActivityMealBinding
import com.example.recettesdecuisine.formatdonnee.MealResponse
import com.example.recettesdecuisine.listener.OnItemClickListener
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var mealsAdapter: MealAdapter
    private lateinit var circularProgressIndicator: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val categoryName = intent.getStringExtra("categoryName").toString()
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        circularProgressIndicator = binding.circularProgressIndicatorMeal
        val url = URL("https://www.themealdb.com/api/json/v1/1/filter.php?c=$categoryName")

        val request = Request.Builder()
            .url(url)
            .build()

        val client = OkHttpClient()
        val internetFailure = Intent(this, InternetFailure::class.java).apply {

        }
        circularProgressIndicator.visibility = View.VISIBLE
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                circularProgressIndicator.visibility = View.GONE
                startActivity(internetFailure)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {
                    val gson = Gson()
                    val mealsResponse = gson.fromJson(it, MealResponse::class.java)
                    mealsResponse.meals?.let { it1 ->
                        runOnUiThread {
                            circularProgressIndicator.visibility = View.GONE
                            mealsAdapter = MealAdapter(it1)
                            mealsAdapter.setClickListener(onCLicked)
                            recyclerView.adapter = mealsAdapter
                            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                        }

                    }
                }
            }
        })
    }
    private val onCLicked  = object : OnItemClickListener {
        override fun onClick(mealId: String) {
            val intent = Intent(this@MealActivity, DetailActivity::class.java).apply {

            }
            intent.putExtra("mealId", mealId)
            startActivity(intent)
        }
    }
}