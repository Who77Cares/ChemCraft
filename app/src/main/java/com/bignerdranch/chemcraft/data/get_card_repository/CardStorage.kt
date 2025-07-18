package com.bignerdranch.chemcraft.data.get_card_repository

import android.util.Log
import com.bignerdranch.chemcraft.ui.cards.models.CardModel


object CardStorage {
    private var cards: List<CardModel> = emptyList()

    fun saveCards(newCards: List<CardModel>) {
        cards = newCards
        Log.d("CardStorage", "Сохранено карточек: ${cards.size}")
    }

    fun getCards(): List<CardModel> = cards
}


