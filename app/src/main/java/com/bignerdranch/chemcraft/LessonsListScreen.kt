package com.bignerdranch.chemcraft

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.chemcraft.databinding.ActivityLessonsListScreenBinding
import com.bignerdranch.chemcraft.lessonScreen.LessonScreen

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