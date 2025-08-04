package com.bignerdranch.chemcraft.items_in_lesson.data

import com.bignerdranch.chemcraft.network.Response

interface ClientGetItems {

    suspend fun getItemsById(lessonId: String, cardId: String): Response

}