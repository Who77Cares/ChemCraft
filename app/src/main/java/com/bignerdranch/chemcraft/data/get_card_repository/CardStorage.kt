package com.bignerdranch.chemcraft.data.get_card_repository

import android.util.Log
import com.bignerdranch.chemcraft.ContentCardModel


object CardStorage {
    private var cards: List<ContentCardModel> = emptyList()

    fun saveCards(newCards: List<ContentCardModel>) {
        cards = newCards
        Log.d("CardStorage", "Сохранено карточек: ${cards.size}")
    }

    fun getCards(): List<ContentCardModel> = cards
}


