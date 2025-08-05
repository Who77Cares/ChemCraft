package com.bignerdranch.chemcraft.task_for_card.data

import com.bignerdranch.chemcraft.network.Response

interface ClientGetTasks {
    suspend fun getTasksById(lessonId: String, cardId: String): Response
}