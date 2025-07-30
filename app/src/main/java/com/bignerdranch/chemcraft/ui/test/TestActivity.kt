package com.bignerdranch.chemcraft.ui.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieDrawable
import com.bignerdranch.chemcraft.data.old.FirebaseNetworkClientOld
import com.bignerdranch.chemcraft.databinding.ActivityTestBinding


class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding
    private lateinit var correctAnswer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lessonId = intent.getStringExtra("LESSON_ID") ?: ""
        val cardId = intent.getStringExtra("CARD_ID") ?: ""


        binding.testText.text = "dss"


        binding.startLottiVew.setOnClickListener {
            binding.lottiView.setMinProgress(0.0f)
            binding.lottiView.setMaxProgress(1.0f)
            binding.lottiView.repeatCount = LottieDrawable.INFINITE
            binding.lottiView.repeatMode = LottieDrawable.RESTART
            binding.lottiView.playAnimation()

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, AllTestResult::class.java)
                startActivity(intent)
                binding.lottiView.cancelAnimation()
            }, 3000)


        }


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

        FirebaseNetworkClientOld.getTestsFromCard(
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