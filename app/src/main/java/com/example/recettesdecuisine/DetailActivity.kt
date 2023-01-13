package com.example.recettesdecuisine

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recettesdecuisine.adapter.PrerequisiteAdapter
import com.example.recettesdecuisine.databinding.ActivityDetailBinding
import com.example.recettesdecuisine.formatdonnee.DetailResponse
import com.example.recettesdecuisine.formatdonnee.PrerequisiteResponse
import com.example.recettesdecuisine.loader.ImageLoader
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL


class DetailActivity :  YouTubeBaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var circularProgressIndicator: CircularProgressIndicator
    private lateinit var detailName: TextView
    private lateinit var detailArea : TextView
    private lateinit var detailInstruction : TextView
    private lateinit var detailImage : ImageView
    private  lateinit var recyclerView: RecyclerView
    private lateinit var prerequisiteAdapter: PrerequisiteAdapter
    private  lateinit var youtubePlayer: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mealId = intent.getStringExtra("mealId").toString()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailArea = binding.detailArea
        detailImage = binding.detailImage
        detailName = binding.detailName
        detailInstruction = binding.detailInstruction
        recyclerView = binding.recyclerView
        youtubePlayer = binding.ytPlayer
        circularProgressIndicator = binding.circularProgressIndicatorDetail
        val apiKey = "temporary"
        val url = URL("https://www.themealdb.com/api/json/v1/1/lookup.php?i=$mealId")
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
                    val detailsResponse = gson.fromJson(it, DetailResponse::class.java)
                    detailsResponse.meals?.get(0).let { it1 ->
                        val prerequisiteResponse = PrerequisiteResponse(detailsResponse)
                        prerequisiteResponse.fill()
                        prerequisiteResponse.prerequisites?.let { it2 ->
                            runOnUiThread {
                                circularProgressIndicator.visibility = View.GONE
                                detailArea.text = it1?.area
                                detailName.text = it1?.name
                                detailInstruction.text = it1?.instruction
                                ImageLoader.loader(it1?.thumb, detailImage)
                                prerequisiteAdapter =  PrerequisiteAdapter(it2)
                                recyclerView.adapter = prerequisiteAdapter
                                recyclerView.layoutManager = GridLayoutManager(applicationContext, 4)
                                val videoId = it1?.youtube.toString().split("https://www.youtube.com/watch?v=")[1]
                                youtubePlayer.initialize(apiKey, object : YouTubePlayer.OnInitializedListener{
                                    override fun onInitializationSuccess(
                                        provider: YouTubePlayer.Provider?,
                                    player: YouTubePlayer?,
                                    p2: Boolean
                                    ) {
                                        player?.loadVideo(videoId)
                                        player?.play()
                                        }

                                    override fun onInitializationFailure(
                                        p0: YouTubePlayer.Provider?,
                                    p1: YouTubeInitializationResult?
                                    ) {
                                        Toast.makeText(this@DetailActivity , "Video player Failed" , Toast.LENGTH_SHORT).show()
                                        }
                                    })
                            }

                        }

                    }
                }
            }
        })
    }
}