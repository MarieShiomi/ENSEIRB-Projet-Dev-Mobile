package com.example.recettesdecuisine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Internet_Failure : AppCompatActivity() {
    private lateinit var textview : ImageView
    private lateinit var button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        //textview = findViewById(R.id.textview_id)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet_failure)
        button = findViewById(R.id.button_id)
        button.text = intent.getStringExtra("categoryName")
        button.setOnClickListener{
            var a = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(a)
        }
    }
}