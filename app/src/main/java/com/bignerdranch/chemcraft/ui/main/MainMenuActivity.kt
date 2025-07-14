package com.bignerdranch.chemcraft.ui.main
import android.content.Intent
import android.os.Bundle


import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.chemcraft.databinding.ActivityMainBinding
import com.bignerdranch.chemcraft.ui.content_lesson_cards.ContentCardsActivity
import com.bignerdranch.chemcraft.ui.favorite.FavoriteActivity
import com.bignerdranch.chemcraft.ui.test.TestActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lessonsListButton.setOnClickListener {
            startActivity(Intent(this, ContentCardsActivity::class.java))
        }

        binding.myListLessonsButton.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }

        binding.testsButton.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }

        val db = Firebase.firestore

    }
}
