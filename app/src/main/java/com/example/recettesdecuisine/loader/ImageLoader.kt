package com.example.recettesdecuisine.loader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import android.os.Handler
import android.widget.ImageView

class ImageLoader {


    companion object {
        private var executor: Executor = Executors.newSingleThreadExecutor()
        private var handler = Handler(Looper.getMainLooper())
        private var image: Bitmap? = null
        fun loader(imageURL: String?, imageView: ImageView) {

            executor.execute {
                try {
                    val inp = java.net.URL(imageURL).openStream()
                    image = BitmapFactory.decodeStream(inp)

                    handler.post {
                        imageView.setImageBitmap(image)
                    }
                }
                catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }
    }





}