package com.example.recettesdecuisine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recettesdecuisine.adapter.CategoryAdapter
import com.example.recettesdecuisine.databinding.ActivityMainBinding
import com.example.recettesdecuisine.formatdonnee.CategoryResponse
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoriesAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        //setContentView(R.layout.activity_main)

        val url = URL("https://www.themealdb.com/api/json/v1/1/categories.php")

        val request = Request.Builder()
            .url(url)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP", e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {
                    val gson = Gson()
                    val categoriesResponse = gson.fromJson(it, CategoryResponse::class.java)
                    categoriesResponse.categories?.let { it1 ->
                        runOnUiThread {
                            categoriesAdapter = CategoryAdapter(it1)
                            recyclerView.adapter = categoriesAdapter
                            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                        }

                    }
                    Log.d("OKHTTP", "Got " + categoriesResponse.categories?.count() + " results")
                }
            }
        })
    }
}