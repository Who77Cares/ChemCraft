package com.bignerdranch.chemcraft.ui.content_lesson_cards

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.data.FirebaseManager
import com.bignerdranch.chemcraft.LessonsContentModel
import com.bignerdranch.chemcraft.data.get_card_repository.CardStorage
import com.bignerdranch.chemcraft.databinding.ActivityLessonContentBinding

class LessonContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLessonContentBinding
    private lateinit var adapter: LessonContentAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonContentBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.lessonsRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Получаем список уроков из Firestore
        FirebaseManager.getListDataFromFirebase(object : FirebaseManager.FirebaseDataCallback {
            override fun onDataReceived(lessonsContentModels: MutableList<LessonsContentModel>) {

                // После того как данные загружены, создаем адаптер и устанавливаем его
                adapter = LessonContentAdapter(this@LessonContentActivity, lessonsContentModels)
                binding.lessonsRecycleView.adapter = adapter

                val savedCards = CardStorage.getCards()

                for (card in savedCards) {
                    Log.d("------", "ID: ${card.id}, Ответ: ${card.answerStatus}")


                }

            }
        })



}
}
