package com.bignerdranch.chemcraft

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.chemcraft.databinding.ActivityMyLessonsBinding

class MyLessonsScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMyLessonsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyLessonsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}