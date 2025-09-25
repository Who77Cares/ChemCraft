package com.bignerdranch.chemcraft.network.admin_manager.models

data class CardToServerModel(
    val description: String = "",
    val important: Boolean = false,
    val title: String = "",
    val cardItems: List<Map<String, String>>
)