package com.bignerdranch.chemcraft
import android.content.Intent
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.chemcraft.databinding.ActivityMainBinding
import com.bignerdranch.chemcraft.lessonsListScreen.LessonsListScreen
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


class MainMenu : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val db = Firebase.firestore

        binding.lessonsListButton.setOnClickListener {
            startActivity(Intent(this, LessonsListScreen::class.java))
        }

    }
}
