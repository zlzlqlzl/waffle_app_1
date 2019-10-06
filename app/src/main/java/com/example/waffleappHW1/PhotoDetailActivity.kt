package com.example.waffleappHW1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_photo.*

class PhotoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_photo)

        if (intent.hasExtra("throw")) {
            var final_intent = intent.getParcelableExtra<Photo>("throw")
            Glide.with(this).load(final_intent.url).into(final_image)
        }
    }
}