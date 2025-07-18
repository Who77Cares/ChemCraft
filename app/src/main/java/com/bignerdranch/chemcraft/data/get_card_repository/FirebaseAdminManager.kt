package com.bignerdranch.chemcraft.data.get_card_repository

import android.util.Log
import com.bignerdranch.chemcraft.ui.cards.models.CardModel
import com.bignerdranch.chemcraft.ui.cards.models.CardToServerModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class FirebaseAdminManager {

    companion object {




        // создаем файл урока в firebase
        fun createLessonData(
            lessonData: HashMap<String, Any>,
            testData: HashMap<String, Any>,
            cardsData: CardToServerModel
        ) {

            val db = Firebase.firestore

            db.collection("lessonsData")
                .add(lessonData)
                .addOnSuccessListener { newLesson ->
                    Log.d("Firestore", "Урок $lessonData создан с ID ${newLesson.id}")

                    newLesson.collection("testData")
                        .add(testData)
                        .addOnSuccessListener {
                            Log.d(
                                "Firestore",
                                "Тест $testData добавлен в testData урока ${newLesson.id}"
                            )
                        }

                    newLesson.collection("cardsData")
                        .add(cardsData)
                        .addOnSuccessListener {
                            Log.d(
                                "Firestore",
                                "Тест $testData добавлен в testData урока ${newLesson.id}"
                            )
                        }
                }
        }

//     Инициализируем код выше
//        FirebaseAdminManager.createLessonData(
//        lessonData = lessonData,
//        testData = testData,
//        cardsData = cardsData
//        )




        // добавляем новую карточку по id урока
        fun addCardToLesson(
            lessonId: String,
            cardModel: CardToServerModel,
            onSuccess: () -> Unit,
            onFailure: (Exception) -> Unit
        ) {
            val db = FirebaseFirestore.getInstance()

            db.collection("lessonsData")
                .document(lessonId)
                .collection("cardsData")
                .add(cardModel)
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener { e ->
                    onFailure(e)
                }
        }
//       Для инициализации
//        FirebaseAdminManager.addCardToLesson(
//        lessonId = "YNVB4KAtQJozmTyhTxjw",
//        cardModel = CardToServerModel(
//
//        ),
//        onSuccess = {  },
//        onFailure = {  }
//        )


        fun exportCardsToJson(
            lessonId: String,
            onSuccess: (String) -> Unit,
            onFailure: (Exception) -> Unit
        ) {
            val db = FirebaseFirestore.getInstance()

            db.collection("lessonsData")
                .document(lessonId)
                .collection("cardsData")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val cardList =
                        querySnapshot.documents.mapNotNull { it.toObject(CardModel::class.java) }

                    val gson: Gson = GsonBuilder().setPrettyPrinting().create()
                    val jsonOutput = gson.toJson(cardList)
                    Log.d("ВОТ ОН!!!", jsonOutput)
                    onSuccess(jsonOutput)


                    for (doc in querySnapshot.documents) {
                        Log.d("DEBUG_RAW_DOC", "ID: ${doc.id}, DATA: ${doc.data}")
                    }

                }
                .addOnFailureListener { e ->
                    onFailure(e)
                }
        }
    }
}