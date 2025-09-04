package com.bignerdranch.chemcraft.lesson_task.ui

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bignerdranch.chemcraft.databinding.FragmentTaskBinding
import com.bignerdranch.chemcraft.lesson_task.domain.model.TaskModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TaskFragment: Fragment() {

    companion object {
        const val LESSON_ID = "lesson_id"
        const val CARD_ID = "card_id"

        fun newInstance(lessonId: String, cardId: String): TaskFragment {
            return TaskFragment().apply {
                arguments = Bundle().apply {
                    putString(LESSON_ID, lessonId)
                    putString(CARD_ID, cardId)
                }
            }
        }

    }


    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!


    private lateinit var correctAnswer: String
    private val viewModel: TaskViewModel by viewModel()

    private var tasks: List<TaskModel> = emptyList()
    private var currentTask: TaskModel? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val lessonId = requireArguments().getString(LESSON_ID)?: ""
        val cardId =  requireArguments().getString(CARD_ID) ?: ""

        viewModel.observeState().observe(viewLifecycleOwner) {
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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
            
            Toast.makeText(requireContext(), "Вы прошли все задания!", Toast.LENGTH_LONG).show()
            parentFragmentManager.popBackStack()

        }
    }


    private fun normalizeAnswer(text: String): String {
        return text
            .trim()                          // Убираем пробелы с начала и конца
            .replace("\\s+".toRegex(), " ") // Заменяем множественные пробелы, табы и переносы на один пробел
            .replace("\u00A0", " ")         // Заменяем неразрывные пробелы (например, из браузеров)
            .lowercase()                    // Приводим к нижнему регистру
    }


}