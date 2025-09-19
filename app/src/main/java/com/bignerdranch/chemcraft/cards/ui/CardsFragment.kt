package com.bignerdranch.chemcraft.cards.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieDrawable
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.databinding.FragmentCardsBinding
import com.bignerdranch.chemcraft.old_arch.cards.models.CardModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardsFragment : Fragment() {

    companion object {

        const val LESSON_ID = "lesson_id"
        const val LESSON_TITLE = "lesson_title"


        fun newInstance(lessonId: String, lessonTitle: String): CardsFragment {
            return CardsFragment().apply {
                arguments = Bundle().apply {
                    putString(LESSON_ID, lessonId)
                    putString(LESSON_TITLE, lessonTitle)

                    Log.d("CardsActivity", "Передан ID урока: $lessonId")
                    Log.d("CardsActivity", "Передано название урока: $lessonTitle")
                }
            }
        }
    }

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!


    private lateinit var adapter: CardsAdapter

    private lateinit var recyclerView: RecyclerView

    private val viewModel: CardsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView = binding.cardsRecycleView


        // распаковка интентов из предыдущей активимти
        val lessonId = requireArguments().getString(LESSON_ID) ?: ""
        binding.cardsTitle.text = requireArguments().getString(LESSON_TITLE) ?: ""


        binding.cardsRecycleView.layoutManager = LinearLayoutManager(requireContext())

        adapter = CardsAdapter(
            requireContext(),
            onCardClick = { card, position ->

                val nav = findNavController() // контроллер ТЕКУЩЕГО NavHost

                if (nav.currentDestination?.id == R.id.cardsFragment) {
                    nav.navigate(
                        R.id.action_cardsFragment_to_tabsFragment,
                        bundleOf(
                            "lesson_id" to lessonId,
                            "card_id" to card.id,
                            "card_title" to card.title,
                        )
                    )
                }
            },


            onBookmarkClick = { card, holder ->

                var flag: Boolean = true


                if(flag) {
                    holder.bookmarkLotti.repeatCount = 0
                    holder.bookmarkLotti.playAnimation()
                } else {

                }
            }
        )

        recyclerView.adapter = adapter

        viewModel.setLessonId(lessonId = lessonId) // эта штука нужна чтобы передать id урока во вью модель для полного пути к карточкам бд firebase

        viewModel.getCards()

        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }




    private fun render(state: CardsState) {
        when (state) {
            is CardsState.Content -> showContent(state.lessons)
            is CardsState.Error -> showError(message = state.errorMessage)
            CardsState.Loading -> showLoading()
        }
    }

    private fun showContent(cards: List<CardModel>) {
        adapter.cards = cards
        adapter.notifyDataSetChanged()

        binding.apply {
            cardsErrorLottie.pauseAnimation()
            cardsLoadingLotti.pauseAnimation()

            cardsRecycleView.visibility = View.VISIBLE
            cardsTitle.visibility = View.VISIBLE
            cardsLoadingLotti.visibility = View.GONE
            cardsErrorMessage.visibility = View.GONE
            cardsErrorLottie.visibility = View.GONE
        }
    }

    private fun showError(message: String) {
        binding.apply {

            cardsLoadingLotti.pauseAnimation()

            cardsErrorLottie.repeatMode = LottieDrawable.REVERSE
            cardsErrorLottie.repeatCount = LottieDrawable.INFINITE
            cardsErrorLottie.playAnimation()

            cardsErrorMessage.text = message
            cardsErrorMessage.visibility = View.VISIBLE
            cardsErrorLottie.visibility = View.VISIBLE
            cardsLoadingLotti.visibility = View.GONE
            cardsRecycleView.visibility = View.GONE
            cardsTitle.visibility = View.GONE
        }
    }

    private fun showLoading() {
        binding.apply {

            cardsErrorLottie.pauseAnimation()

            cardsLoadingLotti.repeatMode = LottieDrawable.RESTART
            cardsLoadingLotti.repeatCount = LottieDrawable.INFINITE
            cardsLoadingLotti.playAnimation()

            cardsLoadingLotti.visibility = View.VISIBLE
            cardsRecycleView.visibility = View.GONE
            cardsErrorMessage.visibility = View.GONE
            cardsErrorLottie.visibility = View.GONE
            cardsTitle.visibility = View.GONE

        }
    }

}