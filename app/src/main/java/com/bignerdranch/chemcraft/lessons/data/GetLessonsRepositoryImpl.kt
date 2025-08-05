package com.bignerdranch.chemcraft.lessons.data

import android.util.Log
import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.lessons.data.api.ClientGetLessons
import com.bignerdranch.chemcraft.lessons.data.api.LessonsResponse
import com.bignerdranch.chemcraft.lessons.domain.api.GetLessonsRepository
import com.bignerdranch.chemcraft.lessons.domain.models.LessonsModel

class GetLessonsRepositoryImpl(
    private val firebaseClient: ClientGetLessons
): GetLessonsRepository {
    override suspend fun getLessons(): Resource<List<LessonsModel>> {

        val result = firebaseClient.getLessons()

        return when(result.resultCode) {

            200 -> {

                val resultList = (result as? LessonsResponse)?.result ?: emptyList()

                val lessonModels = resultList.mapNotNull { map ->
                    try {
                        LessonsModel(
                            id = map["id"] as? String ?: "",
                            name = map["name"] as? String?: "",
                            description = map["description"] as? String ?: "",
                            score = (map["score"] as? Int)?.toInt() ?: 0

                        )
                    } catch (e: Exception) {
                        null
                    }

                }

                Log.d("GetAllLessonsRepositoryImpl", "$lessonModels")
                Resource.Success(data = lessonModels)
            }

            else -> {
                Resource.Error("${result.message}")
            }
        }


    }
}