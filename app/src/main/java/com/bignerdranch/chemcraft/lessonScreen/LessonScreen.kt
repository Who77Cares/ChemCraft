package com.bignerdranch.chemcraft.lessonScreen

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.FirebaseManager
import com.bignerdranch.chemcraft.databinding.ActivityLessonScreenBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LessonScreen : AppCompatActivity(), OnBlockClickListener {
    private lateinit var binding: ActivityLessonScreenBinding
    private lateinit var contentAdapter: ContentAdapter
    private lateinit var blocksAdapter: BlocksAdapter
    private lateinit var database: FirebaseFirestore

    private lateinit var description: String
    private lateinit  var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.firestore

        val lessonId = intent.getStringExtra("lessonId")!!
        Log.d("LessonScreen", "Передаем ID урока: $lessonId")
        title = intent.getStringExtra("title") ?: " _ "
        description = intent.getStringExtra("description") ?: " _ "

        // Инициализация адаптеров до загрузки данных
        contentAdapter = ContentAdapter(emptyList()) // Изначально пустой список
        blocksAdapter = BlocksAdapter(emptyList(),this, binding.blocksRecycleView)

        binding.lessonRecycleView.layoutManager = LinearLayoutManager(this)
        binding.lessonRecycleView.adapter = contentAdapter

        binding.blocksRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.blocksRecycleView.adapter = blocksAdapter


        FirebaseManager.loadLessonData(lessonId) { lesson ->
            binding.title.text = lesson.title

            contentAdapter.updateContent(lesson.blocks[0].content)
            blocksAdapter.updateBlocks(lesson.blocks)
            blocksAdapter.selectedPosition = 0
        }
    }


    override fun onBlockClick(contentList: List<ContentItem>) {
        contentAdapter.updateContent(contentList)
    }
}