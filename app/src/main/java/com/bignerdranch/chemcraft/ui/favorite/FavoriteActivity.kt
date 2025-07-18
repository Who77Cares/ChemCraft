package com.bignerdranch.chemcraft.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.data.FirebaseNetworkClient
import com.bignerdranch.chemcraft.databinding.ActivityFavoriteBinding
import com.bignerdranch.chemcraft.data.SharedPrefManager
import com.bignerdranch.chemcraft.ui.lessons_data.LessonContentAdapter

class FavoriteActivity : AppCompatActivity(){

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: LessonContentAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
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

//
//        FirebaseNetworkClient.getMyLessons(favoritesList) { lessons ->
//            adapter = LessonContentAdapter(this@FavoriteActivity, lessons)
//            binding.myLessonsRecycleView.adapter = adapter
//        }

    }

}