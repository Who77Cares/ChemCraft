package com.bignerdranch.chemcraft.lesson_task.data.model

import android.util.Log
import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.lesson_task.data.ClientGetTasks
import com.bignerdranch.chemcraft.lesson_task.domain.api.GetTasksRepository
import com.bignerdranch.chemcraft.lesson_task.domain.model.TaskModel

class GetTasksRepositoryImpl(
    private val firebaseClient: ClientGetTasks
): GetTasksRepository {

    override suspend fun getTasks(
        lessonId: String,
        cardId: String
    ): Resource<List<TaskModel>> {

        val result = firebaseClient.getTasksById(
            lessonId = lessonId,
            cardId = cardId
        )

        return when(result.resultCode) {
            200 -> {
                val resultList = (result as? TasksResponse)?.result ?: emptyList()

                val tasksModel = resultList.mapNotNull { task ->
                    val questionText = task["questionText"] as? String ?: return@mapNotNull null // хитрая конструкция не добавляет элемент (вроде) если поле имени пустое
                    val imgUrl = task["imgUrl"] as? String ?: ""
                    val correctAnswer = task["correctAnswer"] as? String ?: ""
                    val maxScore = (task["maxScore"] as? Long)?.toInt() ?: 0
                    val type = (task["type"] as? Long)?.toInt() ?: 1

                    TaskModel(
                        questionText = questionText,
                        imgUrl = imgUrl,
                        correctAnswer = correctAnswer,
                        maxScore = maxScore,
                        type = type
                    )
                }
                Log.d("GetTaskRepository", "Mapped itemsModel: $tasksModel")
                Resource.Success(tasksModel)
            } else -> {
                Resource.Error("???")
            }

        }



    }
}