package com.bignerdranch.chemcraft.lessons.ui

import com.bignerdranch.chemcraft.lessons.domain.models.LessonsModel

sealed interface LessonsState {

    data object Loading: LessonsState

    data class Error(
        val errorMessage: String
    ): LessonsState

    data class Content(
        val lessons: List<LessonsModel>
    ): LessonsState

}

