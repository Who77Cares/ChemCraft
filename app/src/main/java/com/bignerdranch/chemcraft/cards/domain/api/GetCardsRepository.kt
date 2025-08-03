package com.bignerdranch.chemcraft.cards.domain.api

import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.ui.cards.models.CardModel

interface GetCardsRepository{

    suspend fun getCards(lessonId: String): Resource<List<CardModel>>

}