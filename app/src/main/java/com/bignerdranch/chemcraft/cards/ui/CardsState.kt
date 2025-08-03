package com.bignerdranch.chemcraft.cards.ui

import com.bignerdranch.chemcraft.ui.cards.models.CardModel

sealed interface CardsState {

    data object Loading: CardsState

    data class Error(
        val errorMessage: String
    ): CardsState

    data class Content(
        val lessons: List<CardModel>
    ): CardsState

}