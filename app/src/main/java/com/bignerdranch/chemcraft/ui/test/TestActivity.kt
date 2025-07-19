package com.bignerdranch.chemcraft.ui.test

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.chemcraft.data.FirebaseNetworkClient
import com.bignerdranch.chemcraft.databinding.ActivityTestScreenBinding

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestScreenBinding
    private lateinit var correctAnswer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lessonId = intent.getStringExtra("LESSON_ID") ?: ""
        val cardId = intent.getStringExtra("CARD_ID") ?: ""


        binding.testText.text = "dss"


        binding.textInputLayout.setEndIconOnClickListener {
            Toast.makeText(this, "Паззл нажат", Toast.LENGTH_LONG).show()
        }

        binding.testEditText.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                val text = binding.testEditText.text.toString()
                if (text == correctAnswer) {
                    Toast.makeText(this, "Верно!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Нет", Toast.LENGTH_LONG).show()
                }
                hideKeyboard(v)
                true
            } else {
                false
            }
        }

        FirebaseNetworkClient.getTestsFromCard(
            lessonId = lessonId,
            cardId = cardId,
            onSuccess = { result ->
                Log.d("Получены данные уроков", result.toString())
                binding.testText.text = result[0].questionText
                correctAnswer = result[0].correctAnswer

            },
            onFailure = {

            }
        )

    }
    fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}