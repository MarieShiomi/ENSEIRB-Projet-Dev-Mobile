package com.example.recettesdecuisine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recettesdecuisine.adapter.CategoryAdapter
import com.example.recettesdecuisine.databinding.ActivityMainBinding
import com.example.recettesdecuisine.formatdonnee.CategoryResponse
import com.example.recettesdecuisine.formatdonnee.DetailResponse
import com.example.recettesdecuisine.listener.OnItemClickListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL
import com.google.android.material.progressindicator.CircularProgressIndicator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoriesAdapter: CategoryAdapter
    private lateinit var circularProgressIndicator: CircularProgressIndicator
    private lateinit var button : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val internetFailure = Intent(this, InternetFailure::class.java).apply {
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        button = findViewById(R.id.fab)
        button.setOnClickListener{
            val url = URL("https://www.themealdb.com/api/json/v1/1/random.php")
            val request = Request.Builder()
                .url(url)
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    startActivity(internetFailure)
                }

                override fun onResponse(call: Call, response: Response) {

                    response.body?.string()?.let {
                        val gson = Gson()
                        val detailsResponse = gson.fromJson(it, DetailResponse::class.java)
                        val mealId = detailsResponse.meals?.get(0)?.id
                        val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {

                        }
                        intent.putExtra("mealId", mealId)
                        startActivity(intent)
                    }
                }
            })
        }
        circularProgressIndicator = binding.circularProgressIndicator
        val url = URL("https://www.themealdb.com/api/json/v1/1/categories.php")
        val request = Request.Builder()
            .url(url)
            .build()
        val client = OkHttpClient()
        circularProgressIndicator.visibility = View.VISIBLE
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread{
                    circularProgressIndicator.visibility = View.GONE
                    startActivity(internetFailure)
                }

            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {
                    val gson = Gson()
                    val categoriesResponse = gson.fromJson(it, CategoryResponse::class.java)
                    categoriesResponse.categories?.let { it1 ->
                        runOnUiThread {
                            circularProgressIndicator.visibility = View.GONE
                            categoriesAdapter = CategoryAdapter(it1)
                            categoriesAdapter.setClickListener(onCLicked)
                            recyclerView.adapter = categoriesAdapter
                            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                        }

                    }
                }
            }
        })
    }
    private val onCLicked  = object : OnItemClickListener {
        override fun onClick(categoryName: String) {
            val intent = Intent(this@MainActivity, MealActivity::class.java).apply {

            }
            intent.putExtra("categoryName", categoryName)
            startActivity(intent)
        }
    }
}