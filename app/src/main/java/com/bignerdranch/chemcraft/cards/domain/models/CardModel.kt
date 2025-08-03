package com.bignerdranch.chemcraft.ui.cards.models

data class CardModel(
    var id: String = "",

    val imageUrl: String = "",
    val important: Boolean = false,
    val title: String = "",
)