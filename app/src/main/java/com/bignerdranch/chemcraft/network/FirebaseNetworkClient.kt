package com.bignerdranch.chemcraft.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.bignerdranch.chemcraft.cards.data.CardsResponse
import com.bignerdranch.chemcraft.cards.data.ClientGetCards
import com.bignerdranch.chemcraft.lessons.data.api.ClientGetLessons
import com.bignerdranch.chemcraft.lessons.data.api.LessonsResponse
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


import kotlinx.coroutines.tasks.await


class FirebaseNetworkClient(private val context: Context): ClientGetLessons, ClientGetCards {

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