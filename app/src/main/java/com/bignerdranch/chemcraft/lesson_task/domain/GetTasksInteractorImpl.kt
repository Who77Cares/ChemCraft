package com.bignerdranch.chemcraft.lesson_task.domain

import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.lesson_task.domain.api.GetTasksInteractor
import com.bignerdranch.chemcraft.lesson_task.domain.api.GetTasksRepository
import com.bignerdranch.chemcraft.lesson_task.domain.model.TaskModel

class GetTasksInteractorImpl(
    private val repository: GetTasksRepository
): GetTasksInteractor {
    override suspend fun getTasks(lessonId: String, cardsId: String): Resource<List<TaskModel>> {
        return repository.getTasks(lessonId, cardsId)
    }
}