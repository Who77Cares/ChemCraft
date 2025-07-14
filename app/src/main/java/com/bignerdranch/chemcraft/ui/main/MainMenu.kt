package com.bignerdranch.chemcraft.ui.main
import android.content.Intent
import android.os.Bundle


import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.chemcraft.databinding.ActivityMainBinding
import com.bignerdranch.chemcraft.ui.subtopic_lesson_cards.SubtopicCardsView
import com.bignerdranch.chemcraft.myLessons.MyLessonsScreen
import com.bignerdranch.chemcraft.test.TestActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class MainMenu : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lessonsListButton.setOnClickListener {
            startActivity(Intent(this, SubtopicCardsView::class.java))
        }

        binding.myListLessonsButton.setOnClickListener {
            startActivity(Intent(this, MyLessonsScreen::class.java))
        }

        binding.testsButton.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }

        val db = Firebase.firestore

    }
}
