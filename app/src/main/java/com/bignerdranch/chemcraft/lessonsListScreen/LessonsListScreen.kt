package com.bignerdranch.chemcraft.lessonsListScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.FirebaseManager
import com.bignerdranch.chemcraft.databinding.ActivityLessonsListScreenBinding
import com.bignerdranch.chemcraft.lessonScreen.ContentAdapter
import com.bignerdranch.chemcraft.lessonScreen.ContentItem
import com.bignerdranch.chemcraft.lessonScreen.ContentList
import com.bignerdranch.chemcraft.lessonScreen.Lesson
import com.bignerdranch.chemcraft.lessonScreen.LessonScreen
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.io.Serializable

class LessonsListScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLessonsListScreenBinding
    private lateinit var adapter: LessonsListScreenAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonsListScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.lessonsRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Получаем список уроков из Firestore
        FirebaseManager.getListDataFromFirebase(object : FirebaseManager.FirebaseDataCallback {
            override fun onDataReceived(lessons: MutableList<Lesson>) {

                // После того как данные загружены, создаем адаптер и устанавливаем его
                adapter = LessonsListScreenAdapter(this@LessonsListScreen, lessons)
                binding.lessonsRecycleView.adapter = adapter
            }
        })
    }

}
