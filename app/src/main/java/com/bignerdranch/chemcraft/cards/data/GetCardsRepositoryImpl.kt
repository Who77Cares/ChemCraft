package com.bignerdranch.chemcraft.cards.data

import android.util.Log
import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.cards.domain.api.GetCardsRepository
import com.bignerdranch.chemcraft.old_arch.cards.models.CardModel

class GetCardsRepositoryImpl(
    private val firebaseClient: ClientGetCards
): GetCardsRepository {
    override suspend fun getCards(lessonId: String): Resource<List<CardModel>> {

        val result = firebaseClient.getCardsByLessonId(lessonId = lessonId)

        return when(result.resultCode) {

            200 -> {

                val resultList = (result as? CardsResponse)?.result ?: emptyList()

                val cardsModels = resultList.mapNotNull { map ->
                    try {
                        CardModel(
                            id = map["id"] as? String ?: "",
                            description = map["description"] as? String ?: "",
                            important = true,
                            title = map["title"] as? String ?: ""
                        )
                    } catch (e: Exception) {
                        null
                    }

                }
                Log.d("GetCardsRepositoryImpl", "$cardsModels")
                Resource.Success(data = cardsModels)
            }

            else -> {
                Resource.Error("${result.message}")
            }
        }

    }
}