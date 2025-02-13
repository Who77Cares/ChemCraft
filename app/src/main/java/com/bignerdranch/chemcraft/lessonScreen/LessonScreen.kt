package com.bignerdranch.chemcraft.lessonScreen

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.load
import coil.request.CachePolicy
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.databinding.ActivityLessonScreenBinding

class LessonScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLessonScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lesson = intent.getSerializableExtra("lesson") as Lesson

        val lessonBlock = lesson.blocks[0]

        binding.title.text = lesson.title



        binding.lessonRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.lessonRecycleView.adapter = ContentAdapter(lessonBlock.content)
    }
}