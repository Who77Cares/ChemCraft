package com.bignerdranch.chemcraft.lesson_task.data.model

import com.bignerdranch.chemcraft.network.Response

class TasksResponse(
    val result: List<Map<String, Any>>
): Response() {
}