package com.bignerdranch.chemcraft.lessonScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.chemcraft.databinding.ActivityFullScreenImageBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class FullScreenImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullScreenImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imagUrl = intent.getStringExtra("image_url")

        Glide.with(this)
            .load(imagUrl)
            .into(binding.fullscreenImage)


        binding.root.setOnClickListener {
            // Проверяем, был ли клик по картинке
            if (it != binding.fullscreenImage) {
                // Закрываем активность, если клик был не по картинке
                finish()
            }
        }
    }
}