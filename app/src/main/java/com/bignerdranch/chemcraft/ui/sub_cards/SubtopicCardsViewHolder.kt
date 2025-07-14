package com.bignerdranch.chemcraft.ui.sub_cards

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.ui.sub_cards.models.SubtopicCardModel

class SubtopicCardsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val card_title: TextView = itemView.findViewById(R.id.title)
    private val lesson_description: TextView = itemView.findViewById(R.id.lesson_description)

    fun bind(model: SubtopicCardModel) {
        card_title.text = model.title
        lesson_description.text = model.description
    }
}