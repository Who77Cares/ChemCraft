package com.bignerdranch.chemcraft.task_for_card.domain

import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.task_for_card.domain.api.GetTasksInteractor
import com.bignerdranch.chemcraft.task_for_card.domain.api.GetTasksRepository
import com.bignerdranch.chemcraft.task_for_card.domain.model.TaskModel

class GetTasksInteractorImpl(
    private val repository: GetTasksRepository
): GetTasksInteractor {
    override suspend fun getTasks(lessonId: String, cardsId: String): Resource<List<TaskModel>> {
        return repository.getTasks(lessonId, cardsId)
    }
}