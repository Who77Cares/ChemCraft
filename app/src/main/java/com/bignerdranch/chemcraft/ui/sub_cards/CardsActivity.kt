package com.bignerdranch.chemcraft.ui.sub_cards

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.data.FirebaseManager

class CardsActivity : AppCompatActivity() {

    private lateinit var description: String
    private lateinit var title: String
    private lateinit var titleText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_cards)


        titleText = findViewById(R.id.titleText)


        val lessonId = intent.getStringExtra("lessonId")!!
        Log.d("LessonScreen", "Передаем ID урока: $lessonId")




        title = intent.getStringExtra("title") ?: " _ "
        description = intent.getStringExtra("description") ?: " _ "

        titleText.text = "$title ::: $description"




        val recyclerView: RecyclerView = findViewById(R.id.recycleView_subtopic)
        recyclerView.layoutManager = LinearLayoutManager(this) // ОБЯЗАТЕЛЬНО
        val adapter = CardsAdapter()
        recyclerView.adapter = adapter


        FirebaseManager.getCard(lessonId) { listCards ->
            adapter.setItems(listCards)
            adapter.notifyDataSetChanged()
        }


    }

}