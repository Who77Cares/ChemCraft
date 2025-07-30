package com.bignerdranch.chemcraft.lessons.data.api

import com.bignerdranch.chemcraft.network.Response

class LessonsResponse(
    val result: List<Map<String, Any>>,
): Response() {
}