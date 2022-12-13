package com.example.recettesdecuisine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recettesdecuisine.adapter.CategoryAdapter
import com.example.recettesdecuisine.databinding.ActivityMainBinding
import com.example.recettesdecuisine.formatdonnee.CategoryResponse
import com.example.recettesdecuisine.listener.OnItemClickListener
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        //setContentView(R.layout.activity_main)
        circularProgressIndicator = findViewById(R.id.circular_progress_indicator)
        val url = URL("https://www.themealdb.com/api/json/v1/1/categories.php")

        val request = Request.Builder()
            .url(url)
            .build()

        val client = OkHttpClient()
        val internetFailure = Intent(this, Internet_Failure::class.java).apply {

        }
        circularProgressIndicator.visibility = View.VISIBLE

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP", e.localizedMessage)
                circularProgressIndicator.visibility = View.GONE
              startActivity(internetFailure)
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
                    Log.d("OKHTTP", "Got " + categoriesResponse.categories?.count() + " results")
                }
            }
        })
    }
    private val onCLicked  = object : OnItemClickListener {
        override fun onClick(categoryName: String) {
            var intent = Intent(this@MainActivity, Internet_Failure::class.java).apply {

            }
            intent.putExtra("categoryName", categoryName)
            startActivity(intent)
        }
    }
}