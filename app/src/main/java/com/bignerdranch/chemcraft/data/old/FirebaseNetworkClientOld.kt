package com.bignerdranch.chemcraft.data.old

import android.util.Log
import com.bignerdranch.chemcraft.ui.single_card.SingleCardItemModel


import com.bignerdranch.chemcraft.ui.cards.models.CardModel
import com.bignerdranch.chemcraft.ui.test.model.TestModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class FirebaseNetworkClientOld {

    companion object {

        /*
         загрузка данных из Firebase происходит асинхронно, и возвращение данных из метода getListDataFromFirebase()
         происходит до того, как данные успевают загрузиться. Это означает, что когда вы возвращаете список lessons, он еще пустой,
         так как асинхронная операция с Firebase еще не завершена.

        В Java и Kotlin операции с Firebase выполняются в фоновом потоке, и результаты этих операций возвращаются через колбэки
        (например, с помощью addOnSuccessListener). Следовательно, вам нужно обрабатывать полученные данные после завершения загрузки.
         */



// получаем данные карточек по айдишнику урока
        fun getCardsByLessonId(
            lessonId: String,
            onSuccess: (List<CardModel>) -> Unit,
            onFailure: (Exception) -> Unit
        ) {
            val db = Firebase.firestore

            db.collection("lessonsData")
                .document(lessonId)
                .collection("cardData")
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
                .collection("cardData").document(cardId).get()
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


        fun getTestsFromCard(
            lessonId: String,
            cardId: String,
            onSuccess: (List<TestModel>) -> Unit,
            onFailure: (Exception) -> Unit
        ) {
            val db = FirebaseFirestore.getInstance()
            db.collection("lessonsData")
                .document(lessonId)
                .collection("cardData")
                .document(cardId)
                .collection("testData")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val tests = mutableListOf<TestModel>()
                    for (doc in querySnapshot.documents) {
                        val rawTestContent = doc.get("testContent")

                        if (rawTestContent is List<*>) {
                            for (item in rawTestContent) {
                                if (item is Map<*, *>) {
                                    val questionText = item["questionText"] as? String ?: ""
                                    val imgUrl = item["imgUrl"] as? String ?: ""
                                    val correctAnswer = item["correctAnswer"] as? String ?: ""
                                    val maxScore = (item["maxScore"] as? Long)?.toInt() ?: 0
                                    val type = (item["type"] as? Long)?.toInt() ?: 1
                                    tests.add(TestModel(questionText, imgUrl, correctAnswer, maxScore, type))
                                }
                            }
                        } else {
                            Log.e("FirestoreTestData", "testContent is missing or wrong type in doc ${doc.id}")
                        }
                    }
                    onSuccess(tests)
                }
                .addOnFailureListener { exception ->
                    onFailure(exception)
                }
        }

    }
}