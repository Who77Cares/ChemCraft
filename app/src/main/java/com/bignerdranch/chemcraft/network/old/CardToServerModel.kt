package com.bignerdranch.chemcraft.network.old

data class CardToServerModel(
    val imageUrl: String = "",
    val important: Boolean = false,
    val title: String = "",
    val cardItems: List<Map<String, String>>
)