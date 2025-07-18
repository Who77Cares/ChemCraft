package com.bignerdranch.chemcraft.ui.lessons_data

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.data.FirebaseNetworkClient
import com.bignerdranch.chemcraft.databinding.ActivityLessonContentBinding

class LessonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLessonContentBinding
    private lateinit var adapter: LessonContentAdapter

    private var lessonsList: List<LessonsData> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonContentBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.lessonsRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)



        FirebaseNetworkClient.getAllLessons(
            onSuccess = { lessonsList ->

                this.lessonsList = lessonsList

                adapter = LessonContentAdapter(this@LessonActivity, this.lessonsList)
                binding.lessonsRecycleView.adapter = adapter

                Log.d("Snapshot", "Уроков загружено: ${this.lessonsList.size}")
            },
            onFailure = { e ->
                Log.d("Snapshot", "Ошибка загрузки уроков: $e")
            }
        )

    }


}

