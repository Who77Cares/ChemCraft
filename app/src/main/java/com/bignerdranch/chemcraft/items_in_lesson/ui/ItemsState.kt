package com.bignerdranch.chemcraft.items_in_lesson.ui

import com.bignerdranch.chemcraft.items_in_lesson.domain.models.ItemModel

sealed interface ItemsState {

    data object Loading : ItemsState

    data class Error(
        val errorMessage: String
    ) : ItemsState

    data class Content(
        val items: List<ItemModel>
    ) : ItemsState

}