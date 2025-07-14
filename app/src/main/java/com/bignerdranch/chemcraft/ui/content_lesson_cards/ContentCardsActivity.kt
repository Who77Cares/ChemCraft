package com.bignerdranch.chemcraft.ui.content_lesson_cards

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.data.FirebaseManager
import com.bignerdranch.chemcraft.databinding.ActivitySubtopicCardsBinding
import com.bignerdranch.chemcraft.LessonsContentModel

class ContentCardsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubtopicCardsBinding
    private lateinit var adapter: SubtopicCardsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubtopicCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.lessonsRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Получаем список уроков из Firestore
        FirebaseManager.getListDataFromFirebase(object : FirebaseManager.FirebaseDataCallback {
            override fun onDataReceived(lessonsContentModels: MutableList<LessonsContentModel>) {

                // После того как данные загружены, создаем адаптер и устанавливаем его
                adapter = SubtopicCardsAdapter(this@ContentCardsActivity, lessonsContentModels)
                binding.lessonsRecycleView.adapter = adapter
            }
        })
    }

}
