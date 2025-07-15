package com.bignerdranch.chemcraft.ui.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.ContentCardModel
import com.bignerdranch.chemcraft.R

class CardsAdapter(
    private var items: List<ContentCardModel> = emptyList(),
    private val onCardClick: (ContentCardModel, Int) -> Unit
): RecyclerView.Adapter<CardsViewHolder>() {

    fun setItems(newItems: List<ContentCardModel>) {
        items = newItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_subtopic_card, parent, false)

        return CardsViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind(items[position]) {
            onCardClick(items[position], position)  //
        }

    }


}