package com.bignerdranch.chemcraft.cards.domain

import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.cards.domain.api.GetCardsInteractor
import com.bignerdranch.chemcraft.cards.domain.api.GetCardsRepository
import com.bignerdranch.chemcraft.ui.cards.models.CardModel

class GetCardsInteractorImp(
    private val repository: GetCardsRepository
): GetCardsInteractor
 {
     override suspend fun getCards(lessonId: String): Resource<List<CardModel>> {
         return repository.getCards(lessonId)
     }
 }