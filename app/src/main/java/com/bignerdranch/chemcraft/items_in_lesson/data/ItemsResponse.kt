package com.bignerdranch.chemcraft.items_in_lesson.data

import com.bignerdranch.chemcraft.network.Response

class ItemsResponse(
    val result: List<Map<String, Any>>
): Response() {
}