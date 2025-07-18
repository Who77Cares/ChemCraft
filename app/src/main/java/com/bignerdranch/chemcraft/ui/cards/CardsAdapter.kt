package com.bignerdranch.chemcraft.ui.cards

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.ui.cards.models.CardModel

class CardsAdapter(private val context: Context,
    private var items: List<CardModel> = emptyList(),
    private val onCardClick: (CardModel, Int) -> Unit
): RecyclerView.Adapter<CardsViewHolder>() {

    fun setItems(newItems: List<CardModel>) {
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

        holder.itemView.findViewById<ImageView>(R.id.star).setOnClickListener{
            Toast.makeText(context, "Star", Toast.LENGTH_LONG).show()
        }

    }


}