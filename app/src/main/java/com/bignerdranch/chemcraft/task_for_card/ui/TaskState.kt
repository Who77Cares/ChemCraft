package com.bignerdranch.chemcraft.task_for_card.ui

import com.bignerdranch.chemcraft.task_for_card.domain.model.TaskModel

interface TaskState {

    data object Loading : TaskState

    data class Error(
        val errorMessage: String
    ) : TaskState

    data class Content(
        val items: List<TaskModel>
    ) : TaskState

}