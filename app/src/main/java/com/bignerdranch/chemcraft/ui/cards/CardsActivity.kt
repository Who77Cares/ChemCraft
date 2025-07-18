package com.bignerdranch.chemcraft.ui.cards

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.data.FirebaseNetworkClient
import com.bignerdranch.chemcraft.ui.cards.models.CardModel
import com.bignerdranch.chemcraft.ui.single_card.SingleCardActivity

class CardsActivity : AppCompatActivity() {

    private lateinit var description: String
    private lateinit var title: String
    private lateinit var titleText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_cards)

        title = ""
        description = ""

        titleText = findViewById(R.id.titleText)
        val recyclerView: RecyclerView = findViewById(R.id.recycleView_subtopic)


        val lessonId = intent.getStringExtra("lessonId")!!
        Log.d("LessonScreen", "Передаем ID урока: $lessonId")





        titleText.text = "$title ::: $description"


        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = CardsAdapter(
            this,
            onCardClick = { card, position ->
                val intent = Intent(this, SingleCardActivity::class.java)
                intent.putExtra("LESSON_ID", lessonId)
                intent.putExtra("CARD_ID", card.id) // ч
                intent.putExtra("title", card.title)
                startActivity(intent)

            })
        recyclerView.adapter = adapter


        FirebaseNetworkClient.getCardDataListByLessonId(
            lessonId = lessonId,
            onSuccess = { cardModelList ->

                adapter.setItems(cardModelList)
                adapter.notifyDataSetChanged()

            },
            onFailure = {

            },
        )






    }

}