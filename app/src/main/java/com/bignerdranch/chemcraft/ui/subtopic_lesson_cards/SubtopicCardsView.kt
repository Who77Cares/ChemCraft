package com.bignerdranch.chemcraft.ui.subtopic_lesson_cards

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.FirebaseManager
import com.bignerdranch.chemcraft.databinding.ActivitySubtopicCardsBinding
import com.bignerdranch.chemcraft.lessonScreen.LessonContent
import com.bignerdranch.chemcraft.lessonsListScreen.LessonsListScreenAdapter

class SubtopicCardsView : AppCompatActivity() {
    private lateinit var binding: ActivitySubtopicCardsBinding
    private lateinit var adapter: LessonsListScreenAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubtopicCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.lessonsRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Получаем список уроков из Firestore
        FirebaseManager.getListDataFromFirebase(object : FirebaseManager.FirebaseDataCallback {
            override fun onDataReceived(lessonContents: MutableList<LessonContent>) {

                // После того как данные загружены, создаем адаптер и устанавливаем его
                adapter = LessonsListScreenAdapter(this@SubtopicCardsView, lessonContents)
                binding.lessonsRecycleView.adapter = adapter
            }
        })
    }

}
