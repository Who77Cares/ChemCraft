package com.bignerdranch.chemcraft.lessons.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.cards.ui.CardsFragment
import com.bignerdranch.chemcraft.databinding.FragmentLessonBinding
import com.bignerdranch.chemcraft.lessons.domain.models.LessonsModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class LessonFragment : Fragment() {


    private var _binding: FragmentLessonBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: LessonContentAdapter

    private val viewModel: LessonViewModel by viewModel()

    private var lessonsList: List<LessonsModel> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLessonBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        adapter = LessonContentAdapter(
            context = requireContext(),
            lessons = lessonsList,
            onLessonClick = { lesson ->

                findNavController().navigate(
                    R.id.action_lessonFragment2_to_cardsFragment,
                    bundleOf(
                        "lesson_id" to lesson.id,
                        "lesson_title" to lesson.name
                    )
                )
            }
        )


        binding.lessonsRecycleView.adapter = adapter

        binding.lessonsRecycleView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }

        viewModel.getLessons()


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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

