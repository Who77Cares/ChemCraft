package com.bignerdranch.chemcraft.ui.sub_cards

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.ui.sub_cards.models.SubtopicCardModel

class SubtopicCardsActivity : AppCompatActivity() {

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



        val zzz: List<SubtopicCardModel> = listOf(
            SubtopicCardModel(
                "lola",
                "weijf90wejfl,c 09oweir90 iweo",
                false
            ),
            SubtopicCardModel(
                "lola",
                "weijf90wejfl,c 09oweir90 iweo",
                false
            ),
            SubtopicCardModel(
                "lola",
                "weijf90wejfl,c 09oweir90 iweo",
                false
            ),
            SubtopicCardModel(
                "lola",
                "weijf90wejfl,c 09oweir90 iweo",
                false
            ),
            SubtopicCardModel(
                "lola",
                "weijf90wejfl,c 09oweir90 iweo",
                false
            ),
            SubtopicCardModel(
                "lola",
                "weijf90wejfl,c 09oweir90 iweo",
                false
            ),
            SubtopicCardModel(
                "lola",
                "weijf90wejfl,c 09oweir90 iweo",
                false
            ),
            SubtopicCardModel(
                "lola",
                "weijf90wejfl,c 09oweir90 iweo",
                false
            )
        )

        val recyclerView: RecyclerView = findViewById(R.id.recycleView_subtopic)
        val adapter = SubtopicCardsAdapter(zzz)

        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = adapter
    }






}