package com.bignerdranch.chemcraft
import android.content.Intent
import android.os.Bundle


import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.chemcraft.databinding.ActivityMainBinding
import com.bignerdranch.chemcraft.lessonsListScreen.LessonsListScreen
import com.bignerdranch.chemcraft.myLessons.MyLessonsScreen
import com.bignerdranch.chemcraft.test.TestScreen
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class MainMenu : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lessonsListButton.setOnClickListener {
            startActivity(Intent(this, LessonsListScreen::class.java))
        }

        binding.myListLessonsButton.setOnClickListener {
            startActivity(Intent(this, MyLessonsScreen::class.java))
        }

        binding.testsButton.setOnClickListener {
            startActivity(Intent(this, TestScreen::class.java))
        }

        val db = Firebase.firestore

    }
}
