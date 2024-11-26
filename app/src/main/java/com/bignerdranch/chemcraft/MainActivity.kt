package com.bignerdranch.chemcraft

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.ImageLoader
import coil.decode.GifDecoder

import coil.load
import coil.request.CachePolicy

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация элементов с использованием findViewById
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val contentTextView = findViewById<TextView>(R.id.contentTextView)
        val exampleImageView = findViewById<ImageView>(R.id.exampleImageView)


        // Задаем данные
        titleTextView.text = "Пример заголовка"
        contentTextView.text = "Это пример текста, который можно заменить на ваш контент. Он поддерживает длинные абзацы и прокрутку."
        exampleImageView.setImageResource(R.drawable.pnggog) // Убедитесь, что sample_image добавлен в папку res/drawable



        val gifImageView: ImageView = findViewById(R.id.gifImageView)

        // Создание ImageLoader с поддержкой GIF
        val imageLoader = ImageLoader.Builder(this)
            .components {
                add(GifDecoder.Factory()) // Поддержка GIF-анимаций
            }
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build()

        // Загрузка GIF
        gifImageView.load(R.drawable.image1, imageLoader)
    }

    }
