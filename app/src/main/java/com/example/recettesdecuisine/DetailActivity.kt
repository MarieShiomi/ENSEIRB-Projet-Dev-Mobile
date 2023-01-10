package com.example.recettesdecuisine

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recettesdecuisine.adapter.PrerequisiteAdapter
import com.example.recettesdecuisine.databinding.ActivityDetailBinding
import com.example.recettesdecuisine.formatdonnee.DetailResponse
import com.example.recettesdecuisine.formatdonnee.PrerequisiteResponse
import com.example.recettesdecuisine.loader.ImageLoader
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
    private  lateinit var recyclerView: RecyclerView
    private lateinit var prerequisiteAdapter: PrerequisiteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mealid = intent.getStringExtra("mealid").toString()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailArea = binding.detailArea
        detailImage = binding.detailImage
        detailName = binding.detailName
        detailInstruction = binding.detailInstruction
        recyclerView = binding.recyclerView
        detailInstruction.movementMethod = ScrollingMovementMethod()
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
                    detailsResponse.meals?.get(0).let { it1 ->
                        runOnUiThread{
                            detailArea.text = it1?.area
                            detailName.text = it1?.name
                            detailInstruction.text = it1?.instruction
                            ImageLoader.loader(it1?.thumb, detailImage)

                        }

                    }
                    val prerequisiteResponse = PrerequisiteResponse(detailsResponse)
                    prerequisiteResponse.fill()
                    prerequisiteResponse.prerequisites?.let { it2 ->
                        runOnUiThread{
                            prerequisiteAdapter =  PrerequisiteAdapter(it2)
                            recyclerView.adapter = prerequisiteAdapter
                            recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)
                        }

                    }
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