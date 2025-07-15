package com.bignerdranch.chemcraft.ui.cards

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.ContentCardModel
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.ui.test.TestActivity
import com.google.android.material.card.MaterialCardView

class CardsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val card_title: TextView = itemView.findViewById(R.id.title)
    private val lesson_description: TextView = itemView.findViewById(R.id.lesson_description)

    private val material_card_view: MaterialCardView = itemView.findViewById(R.id.material_card_view)


    fun bind(
        model: ContentCardModel,
        onCardClick: (ContentCardModel) -> Unit) {

        card_title.text = model.id
        lesson_description.text = model.id

        material_card_view.setOnClickListener {
            onCardClick(model)

        }
    }
}