package com.bignerdranch.chemcraft.lesson_task.data

import com.bignerdranch.chemcraft.network.Response

interface ClientGetTasks {
    suspend fun getTasksById(lessonId: String, cardId: String): Response
}