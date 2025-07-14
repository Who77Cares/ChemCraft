package com.bignerdranch.chemcraft.ui.single_card

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.data.FirebaseManager
import com.bignerdranch.chemcraft.databinding.ActivitySingleCardBinding
import com.bignerdranch.chemcraft.SingleCardItemModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SingleCardActivity : AppCompatActivity(), OnBlockClickListener {
    private lateinit var binding: ActivitySingleCardBinding
    private lateinit var singleCardAdapter: SingleCardAdapter
//    private lateinit var blocksAdapter: BlocksAdapter
    private lateinit var database: FirebaseFirestore

    private lateinit var description: String
    private lateinit  var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.firestore

        val lessonId = intent.getStringExtra("lessonId")!!
        Log.d("LessonScreen", "Передаем ID урока: $lessonId")
        title = intent.getStringExtra("title") ?: " _ "
        description = intent.getStringExtra("description") ?: " _ "


//        GIT Делаем класс subtopicCardsView
        singleCardAdapter = SingleCardAdapter(emptyList()) // Изначально пустой список
//        blocksAdapter = BlocksAdapter(emptyList(),this, binding.blocksRecycleView)
//
        binding.lessonRecycleView.layoutManager = LinearLayoutManager(this)
        binding.lessonRecycleView.adapter = singleCardAdapter
//
//        binding.blocksRecycleView.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        binding.blocksRecycleView.adapter = blocksAdapter


        FirebaseManager.loadLessonData(lessonId) { lesson ->
            binding.title.text = lesson.title

            singleCardAdapter.updateContent(lesson.contentCards[0].content)
//            blocksAdapter.updateBlocks(lesson.contentCards)
//            blocksAdapter.selectedPosition = 0
        }
    }


    override fun onBlockClick(contentList: List<SingleCardItemModel>) {
        singleCardAdapter.updateContent(contentList)
    }
}