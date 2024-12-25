package com.example.chemcraft

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listButton: TextView
    private lateinit var favouriteButton: TextView
    private lateinit var settingsButton: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        listButton = findViewById(R.id.main_menu_list_textview)

        listButton.setOnClickListener {
            startActivity(Intent(this, CardsScreen::class.java))
        }
    }
}