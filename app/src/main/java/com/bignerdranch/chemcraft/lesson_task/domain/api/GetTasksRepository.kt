package com.bignerdranch.chemcraft.lesson_task.domain.api

import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.lesson_task.domain.model.TaskModel

interface GetTasksRepository {
    suspend fun getTasks(lessonId: String, cardId: String): Resource<List<TaskModel>>
}