package com.bignerdranch.chemcraft.task_for_card.data.model

import com.bignerdranch.chemcraft.network.Response

class TasksResponse(
    val result: List<Map<String, Any>>
): Response() {
}