package com.bignerdranch.chemcraft.myLessons

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.chemcraft.FirebaseManager
import com.bignerdranch.chemcraft.databinding.ActivityMyLessonsBinding

class MyLessonsScreen : AppCompatActivity(){

    private lateinit var binding: ActivityMyLessonsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyLessonsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = SharedPrefManager(this)

        val favoritesList = sharedPreference.getFavorite()

        FirebaseManager.getMyLessons(favoritesList) { lessons ->
            Log.d("LessonsData", "Lessons: $lessons")
        }

    }

}