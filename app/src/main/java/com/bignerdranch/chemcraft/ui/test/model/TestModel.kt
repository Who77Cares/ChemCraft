package com.bignerdranch.chemcraft.ui.test.model

data class TestModel(
    var questionText: String = "",
    var imgUrl: String = "",
    var correctAnswer: String = "",
    var maxScore: Int = 0,
    var type: Int = 1
) {
}