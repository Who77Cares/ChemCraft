package com.bignerdranch.chemcraft.items_in_lesson.domain.api

import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.items_in_lesson.domain.models.ItemModel

interface GetItemsRepository {

    suspend fun getLessons(
        lessonId: String,
        cardId: String
    ): Resource<List<ItemModel>>

}