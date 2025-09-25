package com.bignerdranch.chemcraft.items_in_lesson.domain

import android.util.Log
import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.items_in_lesson.domain.api.GetItemsInteractor
import com.bignerdranch.chemcraft.items_in_lesson.domain.api.GetItemsRepository
import com.bignerdranch.chemcraft.items_in_lesson.domain.models.ItemModel

class GetItemsInteractorImpl(
    private val repository: GetItemsRepository
): GetItemsInteractor {

    override suspend fun getCards(lessonId: String, cardId: String): Resource<List<ItemModel>> {
        return repository.getLessons(lessonId, cardId)
    }

}