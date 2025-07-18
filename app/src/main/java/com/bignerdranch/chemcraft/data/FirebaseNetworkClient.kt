package com.bignerdranch.chemcraft.data

import android.util.Log
import com.bignerdranch.chemcraft.SingleCardItemModel


import com.bignerdranch.chemcraft.ui.cards.models.CardModel
import com.bignerdranch.chemcraft.ui.lessons_data.LessonsData
import com.bignerdranch.chemcraft.ui.test.model.TestData
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class FirebaseNetworkClient {

    companion object {

        /*
         загрузка данных из Firebase происходит асинхронно, и возвращение данных из метода getListDataFromFirebase()
         происходит до того, как данные успевают загрузиться. Это означает, что когда вы возвращаете список lessons, он еще пустой,
         так как асинхронная операция с Firebase еще не завершена.

        В Java и Kotlin операции с Firebase выполняются в фоновом потоке, и результаты этих операций возвращаются через колбэки
        (например, с помощью addOnSuccessListener). Следовательно, вам нужно обрабатывать полученные данные после завершения загрузки.
         */


        // получаем все уроки
        fun getAllLessons(
            onSuccess: (List<LessonsData>) -> Unit,
            onFailure: (Exception) -> Unit
        ) {
            val db = Firebase.firestore

            db.collection("lessonsData")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val lessonsList = querySnapshot.documents.mapNotNull { doc ->
                        val lesson = doc.toObject(LessonsData::class.java)
                        lesson?.apply { id = doc.id  }
                    }

                    Log.d("Получили данные уроков их id", lessonsList.toString())

                    onSuccess(lessonsList)
                }
                .addOnFailureListener { e ->
                    onFailure(e)
                }
        }

// получаем данные карточек по айдишнику урока
        fun getCardDataListByLessonId(
            lessonId: String,
            onSuccess: (List<CardModel>) -> Unit,
            onFailure: (Exception) -> Unit
        ) {
            val db = Firebase.firestore

            db.collection("lessonsData")
                .document(lessonId)
                .collection("cardsData")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val cardList = querySnapshot.documents.mapNotNull { doc ->
                        val cards = doc.toObject(CardModel::class.java)
                        cards?.apply { id = doc.id }
                        // поле cardItems мы не маппим сразу

                    }
                    Log.d("КАРТОЧКИ", cardList.toString())
                    onSuccess(cardList)


                }
                .addOnFailureListener { exception ->
                    onFailure(exception)
                }
        }



        fun loadCardItemsById(
            lessonId: String,
            cardId: String,
            onSuccess: (List<SingleCardItemModel>) -> Unit,
            onFailure: (Exception) -> Unit
        ) {
            val db = Firebase.firestore
            Log.d("CardLoader", "Запрос документа: lessonsData/$lessonId/cardsData/$cardId")

            db.collection("lessonsData").document(lessonId)
                .collection("cardsData").document(cardId).get()
                .addOnSuccessListener { document ->
                    Log.d("CardLoader", "onSuccess: Документ получен")

                    if (document.exists()) {
                        val rawList = document.get("cardItems") as? List<Map<String, Any>>
                        if (rawList == null) {
                            Log.d("CardLoader", "cardItems отсутствует или null")
                            onSuccess(emptyList())
                        } else {
                            Log.d("CardLoader", "cardItems получен: ${rawList.size} элементов")

                            val parsedItems = rawList.mapNotNull { item ->
                                val type = (item["type"] as? String)?.lowercase()
                                val content = item["content"] as? String

                                when (type) {
                                    "text" -> content?.let { SingleCardItemModel.Text(it) }
                                    "img" -> content?.let { SingleCardItemModel.Image(it) }
                                    else -> null
                                }
                            }
                            Log.d("CardLoader", "Парсинг завершён, всего элементов: ${parsedItems.size}")
                            onSuccess(parsedItems)
                        }
                    } else {
                        Log.e("CardLoader", "Документ с id $cardId не найден в уроке $lessonId")
                        onFailure(Exception("Документ с id $cardId не найден"))
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("CardLoader", "Ошибка получения документа: ${exception.message}", exception)
                    onFailure(exception)
                }
        }



        // получаем список ключей тестов (id) по ключу урока
        fun getTestDataIdsForLesson(
            lessonId: String,
            onSuccess: (List<String>) -> Unit,
            onFailure: (Exception) -> Unit
        ) {
            val db = Firebase.firestore

            db.collection("lessonsData") // или lessonsData — проверь точное имя!
                .document(lessonId)
                .collection("testData")
                .get()

                .addOnSuccessListener { querySnapshot ->
                    val testDataIds = querySnapshot.documents.map { it.id }
                    onSuccess(testDataIds)
                }
                .addOnFailureListener { e ->
                    onFailure(e)
                }
        }


//        этот код - реализация функции выше
//        FirebaseNetworkClient.getTestDataIdsForLesson(
//        lessonId = "vPtFN1bqd6P5yVOZ0GAW",
//        onSuccess = { listTestId ->
//            Log.d("SnapshotTEST", "$listTestId")
//        },
//        onFailure = { e ->
//            Log.d("SnapshotTEST", "$e")
//        },
//        )


    }

}