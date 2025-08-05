package com.bignerdranch.chemcraft.network.old


import com.bignerdranch.chemcraft.task_for_card.domain.model.TaskModel

data class TestToServerModel(
    var testContent: List<TaskModel> = emptyList(),
    var name: String = ""
) {
}