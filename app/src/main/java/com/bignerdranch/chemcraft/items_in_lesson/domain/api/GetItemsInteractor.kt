package com.bignerdranch.chemcraft.items_in_lesson.domain.api

import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.items_in_lesson.domain.models.ItemModel

interface GetItemsInteractor {
    suspend fun getCards(
        lessonId: String,
        cardId: String
    ): Resource<List<ItemModel>>
}