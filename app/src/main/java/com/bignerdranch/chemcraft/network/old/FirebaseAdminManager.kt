package com.bignerdranch.chemcraft.network.old

import android.util.Log
import com.bignerdranch.chemcraft.ui.cards.models.CardModel
import com.bignerdranch.chemcraft.ui.test.model.TestToServerModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class FirebaseAdminManager {

    companion object {


        // создаем 1 новый пустой урок
        fun createLesson(
            lessonData: HashMap<String, Any>,
            onLessonCreated: (DocumentReference) -> Unit
        ) {
            val db = Firebase.firestore

            db.collection("lessonsData")
                .add(lessonData)
                .addOnSuccessListener { lessonRef ->
                    Log.d("Firestore", "Урок создан: ${lessonRef.id}")
                    onLessonCreated(lessonRef)
                }
                .addOnFailureListener {
                    Log.e("Firestore", "Ошибка при создании урока", it)
                }
        }


        // добавляем новую карточку по id урока
        fun addCardToLesson(
            lessonId: String,
            cardModel: CardToServerModel,
            onSuccess: (DocumentReference) -> Unit,
            onFailure: (Exception) -> Unit
        ) {
            val db = FirebaseFirestore.getInstance()

            db.collection("lessonsData")
                .document(lessonId)
                .collection("cardData")
                .add(cardModel)

                .addOnSuccessListener { cardRef ->
                    onSuccess(cardRef)
                }
                .addOnFailureListener { e ->
                    onFailure(e)
                }
        }




        // добавляем новый тест по id урока
        fun addTestToCard(
            lessonId: String,
            cardId: String,
            testData: TestToServerModel,
            onSuccess: (DocumentReference) -> Unit
        ) {
            val db = Firebase.firestore

            val cardRef = db.collection("lessonsData")
                .document(lessonId)
                .collection("cardData")
                .document(cardId)

            cardRef.collection("testData")
                .add(testData)
                .addOnSuccessListener { testRef ->
                    onSuccess(testRef)
                }
                .addOnFailureListener {
                    Log.e("Firestore", "Ошибка при добавлении теста в карточку $cardId", it)
                }
        }


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