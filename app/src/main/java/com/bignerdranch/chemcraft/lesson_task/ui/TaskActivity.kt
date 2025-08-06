package com.bignerdranch.chemcraft.lesson_task.ui

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.chemcraft.databinding.ActivityTaskBinding
import com.bignerdranch.chemcraft.lesson_task.AllTestResult
import com.bignerdranch.chemcraft.lesson_task.domain.model.TaskModel
import org.koin.androidx.viewmodel.ext.android.viewModel

// чистая архитекктура только получения данных
class TaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding
    private lateinit var correctAnswer: String
    private val viewModel: TaskViewModel by viewModel()

    private var tasks: List<TaskModel> = emptyList()
    private var currentTask: TaskModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lessonId = intent.getStringExtra("LESSON_ID") ?: ""
        val cardId = intent.getStringExtra("CARD_ID") ?: ""

        viewModel.observeState().observe(this) {
            render(it)
        }
        viewModel.setLessonId(lessonId = lessonId, cardId = cardId)
        viewModel.getTasks()


        binding.questionStatus.setOnClickListener {
//            binding.lottiView.repeatCount = 4
//            binding.lottiView.playAnimation()
//
//
//            Handler(Looper.getMainLooper()).postDelayed(
//                {
//                    val intent = Intent(this, AllTestResult::class.java)
//                    startActivity(intent)
//                    binding.lottiView.cancelAnimation()
//                }, 3000
//            )


        }


        binding.testEditText.setOnEditorActionListener { v, actionId, _ ->

            val normalizedInput = normalizeAnswer(binding.testEditText.text.toString())
            val normalizedCorrect = normalizeAnswer(correctAnswer)

            Log.d("AnswerCheck", "Input: '$normalizedInput'")
            Log.d("AnswerCheck", "Correct: '$normalizedCorrect'")
            Log.d("AnswerCheck", "Equals: ${normalizedInput == normalizedCorrect}")

            if(actionId == EditorInfo.IME_ACTION_DONE) {

                if (normalizedInput == normalizedCorrect) {

                    binding.lottiCheckYes.visibility = View.VISIBLE
                    binding.lottiCheckYes.repeatCount = 0
                    binding.lottiCheckYes.playAnimation()
                } else {

                    binding.lottiCheckNo.visibility = View.VISIBLE
                    binding.lottiCheckNo.repeatCount = 0
                    binding.lottiCheckNo.playAnimation()
                }

                hideKeyboard(v)

                v.postDelayed({
                    currentTask = viewModel.getNextTask()
                    bindCurrentTask()
                }, 2000)

                true
            } else {
                false
            }
        }

    }



    fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    private fun render(state: TaskState) {
        when(state) {
            TaskState.Loading -> {
                Log.d("asd", "sad")

            }
            is TaskState.Content -> {
                Log.d("TaskActivity", "Получили контент: ${state.items}")
                showContent(tasks = state.items)
            }

            is TaskState.Error -> {
                Log.d("asd", "sad")


            }
        }
    }

    private fun showContent(tasks: List<TaskModel>) {
        this.tasks = tasks
        currentTask = viewModel.getCurrentTask()

        bindCurrentTask()
    }

    private fun bindCurrentTask() {
        currentTask?.let { task ->

            binding.lottiCheckNo.visibility = View.INVISIBLE
            binding.lottiCheckYes.visibility = View.INVISIBLE

            binding.testText.text = task.questionText
            correctAnswer = task.correctAnswer
            binding.testEditText.text?.clear()
        } ?: run {
            Toast.makeText(this, "Вы прошли все задания!", Toast.LENGTH_LONG).show()
            finish()
        }
    }

//    override fun onBackPressed() {
//        val view = currentFocus
//        if (view is EditText) {
//            // Снимем фокус
//            view.clearFocus()
//            // Скрываем клавиатуру
//            hideKeyboard(view)
//            // Поставим задержку для возврата назад — чтобы фокус точно сбросился и клавиатура скрылась
//            window.decorView.postDelayed({
//                super.onBackPressed()
//            }, 150)
//        } else {
//            super.onBackPressed()
//        }
//    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            currentFocus?.let {
                if (it !is EditText) return@let
                val outRect = Rect()
                it.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    it.clearFocus()
                    hideKeyboard(it)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun normalizeAnswer(text: String): String {
        return text
            .trim()                          // Убираем пробелы с начала и конца
            .replace("\\s+".toRegex(), " ") // Заменяем множественные пробелы, табы и переносы на один пробел
            .replace("\u00A0", " ")         // Заменяем неразрывные пробелы (например, из браузеров)
            .lowercase()                    // Приводим к нижнему регистру
    }


}