package com.bignerdranch.chemcraft.network.admin_manager.models


import com.bignerdranch.chemcraft.lesson_task.domain.model.TaskModel

data class TaskToServerModel(
    var testContent: List<TaskModel> = emptyList(),
    var name: String = ""
) {
}