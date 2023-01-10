package com.example.recettesdecuisine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recettesdecuisine.adapter.MealAdapter
import com.example.recettesdecuisine.databinding.ActivityDetailBinding
import com.example.recettesdecuisine.databinding.ActivityMainBinding
import com.example.recettesdecuisine.databinding.ActivityMealBinding
import com.example.recettesdecuisine.formatdonnee.DetailResponse
import com.example.recettesdecuisine.formatdonnee.MealResponse
import com.example.recettesdecuisine.loader.ImageLoader
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
//    private lateinit var circularProgressIndicator: CircularProgressIndicator
    private lateinit var detailName: TextView
    private lateinit var detailArea : TextView
    private lateinit var detailInstruction : TextView
    private lateinit var detailImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mealid = intent.getStringExtra("mealid").toString()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailArea = binding.detailArea
        detailImage = binding.detailImage
        detailName = binding.detailName
        detailInstruction = binding.detailInstruction
//        circularProgressIndicator = binding.circularProgressIndicator

        val url = URL("https://www.themealdb.com/api/json/v1/1/lookup.php?i=$mealid")
        val request = Request.Builder()
            .url(url)
            .build()

        val client = OkHttpClient()
        val internetFailure = Intent(this, Internet_Failure::class.java).apply {

        }
//        circularProgressIndicator.visibility = View.VISIBLE
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP", e.localizedMessage)
//                circularProgressIndicator.visibility = View.GONE
                startActivity(internetFailure)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {
                    val gson = Gson()
                    val detailsResponse = gson.fromJson(it, DetailResponse::class.java)
                    detailArea.text = detailsResponse.meals?.get(0)?.area
                    detailName.text = detailsResponse.meals?.get(0)?.name
                    detailInstruction.text = detailsResponse.meals?.get(0)?.instruction
                    ImageLoader.loader(detailsResponse.meals?.get(0)?.thumb, detailImage)
                    /*detailsResponse.?. let { it1 ->
                        runOnUiThread {
                            circularProgressIndicator.visibility = View.GONE
                        }*/
//
//                    }
//                    Log.d("OKHTTP", "Got " + mealsResponse.meals?.count() + " results")
                }
            }
        })
        //setContentView(R.layout.activity_detail)
    }
}