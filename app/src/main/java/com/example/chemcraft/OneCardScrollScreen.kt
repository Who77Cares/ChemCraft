package com.example.chemcraft

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class OneCardScrollScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_one_card_scroll_screen)


        // Инициализация элементов с использованием findViewById
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val contentTextView = findViewById<TextView>(R.id.contentTextView)
        val exampleImageView = findViewById<ImageView>(R.id.exampleImageView)


        // Задаем данные
        titleTextView.text = "Пример заголовка"
        contentTextView.text = "Это пример текста, который можно заменить на ваш контент. Он поддерживает длинные абзацы и прокрутку."
        exampleImageView.setImageResource(R.drawable.pnggog) // Убедитесь, что sample_image добавлен в папку res/drawable

        val gifImageView: ImageView = findViewById(R.id.gifImageView)

        Glide.with(applicationContext)
            .load(R.drawable.image1)
            .into(gifImageView)

    }
}