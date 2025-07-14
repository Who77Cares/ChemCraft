package com.bignerdranch.chemcraft.ui.sub_cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.ui.sub_cards.models.SubtopicCardModel

class SubtopicCardsAdapter(
    private val zzz: List<SubtopicCardModel>
): RecyclerView.Adapter<SubtopicCardsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtopicCardsViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_subtopic_card, parent, false)

        return SubtopicCardsViewHolder(itemView)
    }

    override fun getItemCount(): Int = zzz.size

    override fun onBindViewHolder(holder: SubtopicCardsViewHolder, position: Int) {
        holder.bind(zzz[position])
    }


}