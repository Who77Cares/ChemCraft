package com.bignerdranch.chemcraft.lesson_task.domain.api

import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.lesson_task.domain.model.TaskModel

interface GetTasksInteractor {
    suspend fun getTasks(lessonId: String, cardsId: String): Resource<List<TaskModel>>
}