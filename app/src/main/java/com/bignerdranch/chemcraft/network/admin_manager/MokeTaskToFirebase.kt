package com.bignerdranch.chemcraft.network.admin_manager

import com.bignerdranch.chemcraft.lesson_task.domain.model.TaskModel
import com.bignerdranch.chemcraft.network.admin_manager.models.CardToServerModel

object MokeTaskToFirebase {



    var testData  = listOf(
        TaskModel(
            questionText = "Почему начался упадок греческих полисов?\n" +
                    "А. Из-за набегов персов \n" +
                    "Б. Из-за междоусобных войн \n" +
                    "В. Из-за природных катастроф \n" +
                    "Г. Из-за падения торговли",
            imgUrl = "",
            correctAnswer = "Б",
            maxScore = 1,
            type = 1

        ),
        TaskModel(
            questionText = "Кто победил в Пелопоннесской войне?\n" +
                    "А. Афины \n" +
                    "Б. Спарта \n" +
                    "В. Македония \n" +
                    "С. Персия",
            imgUrl = "",
            correctAnswer = "б",
            maxScore = 1,
            type = 1

        ),
        TaskModel(
            questionText = "Какая страна усиливалась на севере Греции во время её ослабления?\n" +
                    "A. Персия \n" +
                    "Б. Фракия \n" +
                    "В. Египет \n" +
                    "Г. Македония",
            imgUrl = "",
            correctAnswer = "г",
            maxScore = 1,
            type = 1

        )
    )




}