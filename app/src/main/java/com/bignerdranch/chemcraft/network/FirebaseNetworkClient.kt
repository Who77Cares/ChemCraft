package com.bignerdranch.chemcraft.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.bignerdranch.chemcraft.cards.data.CardsResponse
import com.bignerdranch.chemcraft.cards.data.ClientGetCards
import com.bignerdranch.chemcraft.lessons.data.api.ClientGetLessons
import com.bignerdranch.chemcraft.lessons.data.api.LessonsResponse
import com.bignerdranch.chemcraft.items_in_lesson.data.ClientGetItems
import com.bignerdranch.chemcraft.items_in_lesson.data.ItemsResponse
import com.bignerdranch.chemcraft.lesson_task.data.ClientGetTasks
import com.bignerdranch.chemcraft.lesson_task.data.model.TasksResponse
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


import kotlinx.coroutines.tasks.await


class FirebaseNetworkClient(
    private val context: Context
): ClientGetLessons, ClientGetCards, ClientGetItems, ClientGetTasks {

    override suspend fun getLessons(): Response {
        val db = Firebase.firestore

        if (isConnected()) {


            return try {
                val querySnapshot = db.collection("lessonsData").get().await()

                val lessonsListWithId: List<Map<String, Any>> =
                    querySnapshot.documents.mapNotNull { doc ->
                        doc.data?.plus("id" to doc.id)

                    }

                Log.d("getLessons()", "$lessonsListWithId")

                LessonsResponse(result = lessonsListWithId)
                    .apply { resultCode = 200 }


            } catch (e: Exception) {

                Response().apply {
                    message = e.message ?: "Неизвестная ошибка"
                    resultCode = -1
                }
            }

        } else {
            return Response().apply {
                message = "Нет подключения к сети"
                resultCode = -1 }
        }

    }




    override suspend fun getCardsByLessonId(lessonId: String): Response {

        val db = Firebase.firestore

        if (isConnected()) {

            return try {
                val querySnapshot = db.collection("lessonsData")
                    .document(lessonId)
                    .collection("cardData")
                    .get()
                    .await()

                val cardsListWithId: List<Map <String, Any>> =
                    querySnapshot.documents.mapNotNull { doc ->
                        doc.data?.plus("id" to doc.id)
                    }

                Log.d("getCardsByLessonId()", "$cardsListWithId")

                CardsResponse(result = cardsListWithId).apply {
                    resultCode = 200
                }

            } catch (e: Exception) {

                Response().apply {
                    message = e.message ?: "Неизвестная ошибка"
                    resultCode = -1
                }

            }

        } else {
            return Response().apply {
                message = "Нет подключения к сети"
                resultCode = -1 }
        }

    }

    override suspend fun getItemsById(lessonId: String, cardId: String): Response {
        val db = Firebase.firestore

        if (!isConnected()) {
            return Response().apply {
                resultCode = -1
                message = "No internet connection"
            }
        }

        return try {
            val documentSnapshot = db.collection("lessonsData")
                .document(lessonId)
                .collection("cardData")
                .document(cardId)
                .get()
                .await()
            Log.d("Firestore", "Document data: ${documentSnapshot.data}")

            val dataMap = documentSnapshot.data ?: emptyMap()
            // Извлекаем поле cardItems — список элементов
            val cardItems = dataMap["cardItems"] as? List<Map<String, Any>>

            if (!cardItems.isNullOrEmpty()) {
                ItemsResponse(result = cardItems).apply {
                    resultCode = 200
                }
            } else {
                Response().apply {
                    resultCode = -1
                    message = "Ничего не найдено"
                }
            }

        } catch (e: Exception) {
            Log.e("Firestore", "Error fetching data", e)
            Response().apply {
                resultCode = -1
                message = e.localizedMessage ?: "Unknown error"
            }
        }
    }



    // читаешь все документы из testData; вытаскиваешь из каждого testContent;  собираешь их в один список allTasks;  возвращаешь TasksResponse(result = allTasks).
    override suspend fun getTasksById(lessonId: String, cardId: String): Response {
        val db = Firebase.firestore

        if (!isConnected()) {
            return Response().apply {
                resultCode = -1
                message = "No connection"
            }
        }

        return try {
            val querySnapshot = db.collection("lessonsData")
                .document(lessonId)
                .collection("cardData")
                .document(cardId)
                .collection("testData")
                .get()
                .await()

            val allTasks = mutableListOf<Map<String, Any>>()

            for (doc in querySnapshot.documents) {
                val testContent = doc.get("testContent") as? List<Map<String, Any>>
                if (!testContent.isNullOrEmpty()) {
                    allTasks.addAll(testContent)
                }
            }

            return if (allTasks.isNotEmpty()) {
                TasksResponse(result = allTasks).apply { resultCode = 200 }
            } else {
                Response().apply {
                    resultCode = -1
                    message = "Тесты не найдены"
                }
            }

        } catch (e: Exception) {
            Response().apply {
                resultCode = -1
                message = e.localizedMessage ?: "Unknown error"
            }
        }
    }




   private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }




}