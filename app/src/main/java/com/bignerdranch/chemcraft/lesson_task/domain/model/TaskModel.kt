package com.bignerdranch.chemcraft.lesson_task.domain.model

data class TaskModel(
    var questionText: String = "",
    var imgUrl: String = "",
    var correctAnswer: String = "",
    var maxScore: Int = 0,
    var type: Int = 1 // лучше заменить на seald еализацию
) {
}