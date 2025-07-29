package com.bignerdranch.chemcraft.ui.cards.models

data class CardToServerModel(
    val imageUrl: String = "",
    val important: Boolean = false,
    val title: String = "",
    val cardItems: List<Map<String, String>>
)