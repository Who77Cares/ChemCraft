package com.bignerdranch.chemcraft.cards.data

import com.bignerdranch.chemcraft.network.Response

class CardsResponse(
    val result: List<Map<String, Any>>
): Response() {
}