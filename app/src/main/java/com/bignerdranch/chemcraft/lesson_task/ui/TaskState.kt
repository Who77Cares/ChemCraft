package com.bignerdranch.chemcraft.lesson_task.ui

import com.bignerdranch.chemcraft.lesson_task.domain.model.TaskModel

interface TaskState {

    data object Loading : TaskState

    data class Error(
        val errorMessage: String
    ) : TaskState

    data class Content(
        val items: List<TaskModel>
    ) : TaskState

}