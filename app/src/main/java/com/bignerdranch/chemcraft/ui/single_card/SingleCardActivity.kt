package com.bignerdranch.chemcraft.ui.single_card

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.data.FirebaseNetworkClient
import com.bignerdranch.chemcraft.databinding.ActivitySingleCardBinding
import com.bignerdranch.chemcraft.ui.test.TestActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SingleCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingleCardBinding
    private lateinit var singleCardAdapter: SingleCardAdapter
    private lateinit var database: FirebaseFirestore

    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        button = findViewById(R.id.test_test)

        database = Firebase.firestore


        val lessonId = intent.getStringExtra("LESSON_ID")!!
        val cardId = intent.getStringExtra("CARD_ID")!!
        val title = intent.getStringExtra("title") ?: " _ "


        Log.d("LessonScreen", "Передаем ID урока: $cardId, ---- $title")



        singleCardAdapter = SingleCardAdapter(emptyList())

        binding.lessonRecycleView.layoutManager = LinearLayoutManager(this)
        binding.lessonRecycleView.adapter = singleCardAdapter



        FirebaseNetworkClient.loadCardItemsById(
            lessonId = lessonId,
            cardId = cardId,
            onSuccess = {  listSingleCardItems ->
                singleCardAdapter.contentList = listSingleCardItems
                singleCardAdapter.notifyDataSetChanged()
            },
            onFailure = {

            }
        )


        button.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }

    }

}