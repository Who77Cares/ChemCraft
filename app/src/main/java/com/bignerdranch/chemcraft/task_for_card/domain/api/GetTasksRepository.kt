package com.bignerdranch.chemcraft.task_for_card.domain.api

import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.task_for_card.domain.model.TaskModel

interface GetTasksRepository {
    suspend fun getTasks(lessonId: String, cardId: String): Resource<List<TaskModel>>
}