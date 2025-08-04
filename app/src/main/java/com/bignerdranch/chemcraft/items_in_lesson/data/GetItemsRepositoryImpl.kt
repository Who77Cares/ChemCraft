package com.bignerdranch.chemcraft.items_in_lesson.data

import android.util.Log
import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.cards.data.CardsResponse
import com.bignerdranch.chemcraft.items_in_lesson.domain.api.GetItemsRepository
import com.bignerdranch.chemcraft.items_in_lesson.domain.models.ItemModel

class GetItemsRepositoryImpl(
    private val firebaseClient: ClientGetItems
): GetItemsRepository {
    override suspend fun getLessons(
        lessonId: String,
        cardId: String
    ): Resource<List<ItemModel>> {
        val result = firebaseClient.getItemsById(
            lessonId = lessonId ,
            cardId = cardId
        )
        Log.d("GetItemsRepository", "FirebaseClient result: $result")


        return when (result.resultCode) {
            200 -> {
                val resultList = (result as? ItemsResponse)?.result ?: emptyList()
                Log.d("GetItemsRepository", "resultList raw: $resultList")

                val itemsModel = resultList.mapNotNull { item ->
                    val type = item["type"] as? String
                    val content = item["content"] as? String

                    when (type) {
                        "text" -> content?.let { ItemModel.TextItem(it) }
                        "img" -> content?.let { ItemModel.ImageItem(it) }
                        else -> null
                    }
                }
                Log.d("GetItemsRepository", "Mapped itemsModel: $itemsModel")
                Resource.Success(itemsModel)

            }

            else -> {
                Resource.Error("???")
            }
        }
        }



//    }


}