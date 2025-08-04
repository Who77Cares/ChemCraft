package com.bignerdranch.chemcraft.single_card

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.data.old.FirebaseNetworkClientOld
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
    private lateinit var title: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        button = findViewById(R.id.test_test)
        title = findViewById(R.id.title)

        database = Firebase.firestore


        val lessonId = intent.getStringExtra("LESSON_ID")!!
        val cardId = intent.getStringExtra("CARD_ID")!!
        title.text = cardId


        Log.d("LessonScreen", "Передаем ID урока: $cardId, ---- $title")



        singleCardAdapter = SingleCardAdapter(emptyList())

        binding.lessonRecycleView.layoutManager = LinearLayoutManager(this)
        binding.lessonRecycleView.adapter = singleCardAdapter



        FirebaseNetworkClientOld.loadCardItemsById(
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
            intent.putExtra("LESSON_ID", lessonId)
            intent.putExtra("CARD_ID", cardId)
            startActivity(intent)
        }


    }

}