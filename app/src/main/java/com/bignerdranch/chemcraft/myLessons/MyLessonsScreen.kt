package com.bignerdranch.chemcraft.myLessons

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.FirebaseManager
import com.bignerdranch.chemcraft.databinding.ActivityMyLessonsBinding
import com.bignerdranch.chemcraft.lessonsListScreen.LessonsListScreenAdapter

class MyLessonsScreen : AppCompatActivity(){

    private lateinit var binding: ActivityMyLessonsBinding
    private lateinit var adapter: LessonsListScreenAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyLessonsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = SharedPrefManager(this)
        val favoritesList = sharedPreference.getFavorite()

        if (favoritesList.isEmpty()) {
            binding.myListIsEmpty.visibility = View.VISIBLE
        } else {
            binding.myListIsEmpty.visibility = View.INVISIBLE
        }

        binding.myLessonsRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        FirebaseManager.getMyLessons(favoritesList) { lessons ->
            adapter = LessonsListScreenAdapter(this@MyLessonsScreen, lessons)
            binding.myLessonsRecycleView.adapter = adapter
        }

    }

}