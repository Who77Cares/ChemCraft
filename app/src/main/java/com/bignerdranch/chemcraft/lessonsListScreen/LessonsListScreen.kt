package com.bignerdranch.chemcraft.lessonsListScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
        getListDataFromFirebase()

    }




    private fun getListDataFromFirebase() {
        val db = Firebase.firestore

        db.collection("lessons")
            .get()
            .addOnSuccessListener { resul ->

                val lessons = mutableListOf<Lesson>()

                for (document in resul) {
                    val title = document.getString("title") ?: " No title "
                    val desciption = document.getString("description") ?: "No description"
                    val lessonId = document.id

                    lessons.add((Lesson(lessonId, title, desciption, listOf())))

                    Log.d("Firestore", "Fetched lesson: $lessonId")
                }

                adapter = LessonsListScreenAdapter(lessons)
                binding.lessonsRecycleView.adapter = adapter

            }
    }

}