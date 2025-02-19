package com.bignerdranch.chemcraft

import android.util.Log
import com.bignerdranch.chemcraft.lessonScreen.Lesson
import com.bignerdranch.chemcraft.lessonsListScreen.LessonsListScreenAdapter
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class FirebaseManager {

    interface FirebaseDataCallback {
        fun onDataReceived(lessons: MutableList<Lesson>)
    }

    companion object {

        /*
         загрузка данных из Firebase происходит асинхронно, и возвращение данных из метода getListDataFromFirebase()
         происходит до того, как данные успевают загрузиться. Это означает, что когда вы возвращаете список lessons, он еще пустой,
         так как асинхронная операция с Firebase еще не завершена.

        В Java и Kotlin операции с Firebase выполняются в фоновом потоке, и результаты этих операций возвращаются через колбэки
        (например, с помощью addOnSuccessListener). Следовательно, вам нужно обрабатывать полученные данные после завершения загрузки.
         */

        fun getListDataFromFirebase(callback: FirebaseDataCallback){
            val db = Firebase.firestore
            val lessons = mutableListOf<Lesson>()

            db.collection("lessons")
                .get()
                .addOnSuccessListener { resul ->

                    for (document in resul) {
                        val title = document.getString("title") ?: " No title "
                        val desciption = document.getString("description") ?: "No description"
                        val lessonId = document.id

                        lessons.add((Lesson(lessonId, title, desciption, listOf())))

                    }
                    callback.onDataReceived(lessons)
                }
        }


        fun getMyLessons(lessonIds: List<String>, callback: (List<Lesson>) -> Unit) {
            val db = Firebase.firestore
            val lessons = mutableListOf<Lesson>()

            for(lessonId in lessonIds)
                db.collection("lessons")
                    .document(lessonId)
                    .get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) {
                            val title = document.getString("title") ?: "No title"
                            val description = document.getString("description") ?: "No description"
                            lessons.add(Lesson(lessonId, title, description, listOf()))
                        }
                        // Если все запросы завершены, вызываем callback
                        if (lessons.size == lessonIds.size)
                            callback(lessons)
                    }

                    .addOnFailureListener { exception ->
                        Log.e("FirebaseManager", "Error getting document: ", exception)
                    }
        }
    }
}