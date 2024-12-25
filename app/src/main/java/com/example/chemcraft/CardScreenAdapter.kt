package com.example.chemcraft

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CardScreenAdapter(private val cards: List<Card>) : RecyclerView.Adapter<CardScreenAdapter.CardScreenHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardScreenHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_in_activity_cards_screen, parent, false)
        return CardScreenHolder(view)
    }

    override fun getItemCount(): Int = cards.size

    override fun onBindViewHolder(holder: CardScreenHolder, position: Int) {
        holder.bind(cards[position])
    }


    class CardScreenHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val cardName: TextView = itemView.findViewById(R.id.card_text)
        private val icon: ImageView = itemView.findViewById(R.id.card_icon)
        private val like: ImageView = itemView.findViewById(R.id.card_like)


        fun bind(card: Card) {
            cardName.text = card.name

            if(card.isLike) like.imageTintList = ColorStateList.valueOf(Color.parseColor("#F9595F"))

            Glide.with(itemView)
                .load(card.icon)
                .into(icon)
        }
    }
}

