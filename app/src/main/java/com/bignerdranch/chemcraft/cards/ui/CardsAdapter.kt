package com.bignerdranch.chemcraft.cards.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.R
import com.bignerdranch.chemcraft.ui.cards.models.CardModel

class CardsAdapter(
    private val context: Context,
   var cards: List<CardModel> = emptyList(),
    private val onCardClick: (CardModel, Int) -> Unit,
    private val onBookmarkClick: (CardModel, CardsViewHolder) -> Unit
) : RecyclerView.Adapter<CardsViewHolder>() {

//    fun setItems(newItems: List<CardModel>) {
//        cards = newItems
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_card, parent, false)

        return CardsViewHolder(itemView)
    }

    override fun getItemCount(): Int = cards.size

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind(cards[position]) {
            onCardClick(cards[position], position)  //
        }

        holder.itemView.findViewById<ImageView>(R.id.bookmarkLotti).setOnClickListener{
            Toast.makeText(context, "Star", Toast.LENGTH_LONG).show()
        }
        // Клик по Lottie анимации
        holder.bookmarkLotti.setOnClickListener {
            onBookmarkClick(cards[position], holder)
        }


    }




}