package com.bignerdranch.chemcraft.cards.data

import com.bignerdranch.chemcraft.network.Response

interface ClientGetCards {
    suspend fun getCardsByLessonId(lessonId: String): Response
}