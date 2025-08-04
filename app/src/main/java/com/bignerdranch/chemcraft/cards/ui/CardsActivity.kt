package com.bignerdranch.chemcraft.cards.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieDrawable
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.databinding.ActivityCardsBinding
import com.bignerdranch.chemcraft.ui.cards.models.CardModel
import com.bignerdranch.chemcraft.items_in_lesson.ui.ItemsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardsBinding
    private lateinit var adapter: CardsAdapter

    private lateinit var lessonTitle: String
    private lateinit var titleText: TextView

    private val viewModel: CardsViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        titleText = findViewById(R.id.cardsTitle)
        val recyclerView: RecyclerView = findViewById(R.id.cardsRecycleView)


        // распаковка интентов из предыдущей активимти
        val lessonId = intent.getStringExtra("lessonId")!!

        lessonTitle = intent.getStringExtra("lessonTitle") ?: ""
        titleText.text = lessonTitle
        Log.d("CardsActivity", "Передан ID урока: $lessonId")
        Log.d("CardsActivity", "Передано название урока: $lessonTitle")




        binding.cardsRecycleView.layoutManager = LinearLayoutManager(this)

         adapter = CardsAdapter(
             this,
             onCardClick = { card, position ->
                 val intent = Intent(this, ItemsActivity::class.java)
                 intent.putExtra("LESSON_ID", lessonId)
                 intent.putExtra("CARD_ID", card.id)
                 intent.putExtra("title", card.title)
                 startActivity(intent)

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

        viewModel.observeState().observe(this) {
            render(it)
        }


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