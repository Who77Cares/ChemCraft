package com.bignerdranch.chemcraft.lessons.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.bignerdranch.chemcraft.cards.ui.CardsActivity
import com.bignerdranch.chemcraft.databinding.ActivityLessonBinding
import com.bignerdranch.chemcraft.lessons.domain.models.LessonsModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LessonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLessonBinding
    private lateinit var adapter: LessonContentAdapter

    private val viewModel: LessonViewModel by viewModel()

    private var lessonsList: List<LessonsModel> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonBinding.inflate(layoutInflater)
        setContentView(binding.root)



        adapter = LessonContentAdapter(
            context = this@LessonActivity,
            lessons = lessonsList,
            onLessonClick = { lesson ->
                val intent = Intent(this, CardsActivity::class.java)
                intent.putExtra("lessonId", lesson.id)
                intent.putExtra("lessonTitle", lesson.name)
                startActivity(intent)
            }
        )


        binding.lessonsRecycleView.adapter = adapter

        binding.lessonsRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        viewModel.observeState().observe(this) {
            render(it)
        }

        viewModel.getLessons()


    }

    private fun render(state: LessonsState) {
        when (state) {
            is LessonsState.Content -> showContent(state.lessons)
            is LessonsState.Error -> showError(message = state.errorMessage)
            LessonsState.Loading -> showLoading()

        }
    }




    private fun showContent(lessons: List<LessonsModel>) {



        adapter.lessons = lessons
        adapter.notifyDataSetChanged()

        binding.apply {
            lessonsErrorLottie.pauseAnimation()
            loadingLotti.pauseAnimation()

            lessonsRecycleView.visibility = View.VISIBLE
            loadingLotti.visibility = View.GONE
            lessonsErrorMessage.visibility = View.GONE
            lessonsErrorLottie.visibility = View.GONE

        }
    }

    private fun showError(message: String) {
        binding.apply {

            loadingLotti.pauseAnimation()

            lessonsErrorLottie.repeatMode = LottieDrawable.REVERSE
            lessonsErrorLottie.repeatCount = LottieDrawable.INFINITE
            lessonsErrorLottie.playAnimation()

            lessonsErrorMessage.text = message
            lessonsErrorMessage.visibility = View.VISIBLE
            lessonsErrorLottie.visibility = View.VISIBLE
            loadingLotti.visibility = View.GONE
            lessonsRecycleView.visibility = View.GONE
        }

    }

    private fun showLoading() {
        binding.apply {

            lessonsErrorLottie.pauseAnimation()

            loadingLotti.repeatMode = LottieDrawable.RESTART
            loadingLotti.repeatCount = LottieDrawable.INFINITE
            loadingLotti.playAnimation()


            loadingLotti.visibility = View.VISIBLE
            lessonsRecycleView.visibility = View.GONE
            lessonsErrorMessage.visibility = View.GONE
            lessonsErrorLottie.visibility = View.GONE

        }
    }


}

