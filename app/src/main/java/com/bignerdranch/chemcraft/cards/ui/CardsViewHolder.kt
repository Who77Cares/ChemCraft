package com.bignerdranch.chemcraft.cards.ui


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.ui.cards.models.CardModel

import com.google.android.material.card.MaterialCardView

class CardsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val card_title: TextView = itemView.findViewById(R.id.title)
    private val lesson_description: TextView = itemView.findViewById(R.id.lesson_description)

    val bookmarkLotti: LottieAnimationView = itemView.findViewById(R.id.bookmarkLotti)

    private val material_card_view: MaterialCardView = itemView.findViewById(R.id.material_card_view)


    fun bind(
        model: CardModel,
        onCardClick: (CardModel) -> Unit) {

        card_title.text = model.title
        lesson_description.text = model.id

        material_card_view.setOnClickListener {
            onCardClick(model)

        }
    }
}