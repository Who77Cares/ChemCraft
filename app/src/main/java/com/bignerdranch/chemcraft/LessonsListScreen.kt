package com.bignerdranch.chemcraft

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bignerdranch.chemcraft.databinding.ActivityLessonsListScreenBinding

class LessonsListScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLessonsListScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonsListScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lessonButton.setOnClickListener {
            startActivity(Intent(this, LessonScreen::class.java))
        }

    }
}