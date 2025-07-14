package com.bignerdranch.chemcraft.ui.single_card

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.data.FirebaseManager
import com.bignerdranch.chemcraft.databinding.ActivitySingleCardBinding
import com.bignerdranch.chemcraft.SingleCardItemModel
import com.bignerdranch.chemcraft.ui.sub_cards.SubtopicCardsActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SingleCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingleCardBinding
    private lateinit var singleCardAdapter: SingleCardAdapter
    private lateinit var database: FirebaseFirestore

    private lateinit var button: Button
    private lateinit var description: String
    private lateinit  var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        button = findViewById(R.id.test_test)

        database = Firebase.firestore

        val lessonId = intent.getStringExtra("lessonId")!!
        Log.d("LessonScreen", "Передаем ID урока: $lessonId")

        title = intent.getStringExtra("title") ?: " _ "
        description = intent.getStringExtra("description") ?: " _ "



        singleCardAdapter = SingleCardAdapter(emptyList())

        binding.lessonRecycleView.layoutManager = LinearLayoutManager(this)
        binding.lessonRecycleView.adapter = singleCardAdapter



        FirebaseManager.loadLessonData(lessonId) { lesson ->
            binding.title.text = lesson.title

            singleCardAdapter.contentList = lesson.contentCards[0].content
            singleCardAdapter.notifyDataSetChanged()

        }

        button.setOnClickListener {
            val intent = Intent(this, SubtopicCardsActivity::class.java)
            startActivity(intent)
        }

    }

}