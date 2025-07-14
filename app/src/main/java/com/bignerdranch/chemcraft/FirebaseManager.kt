package com.bignerdranch.chemcraft

import android.util.Log
import com.bignerdranch.chemcraft.lessonScreen.ContentItem
import com.bignerdranch.chemcraft.lessonScreen.ContentCardViewModel
import com.bignerdranch.chemcraft.lessonScreen.LessonContent
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class FirebaseManager {

    interface FirebaseDataCallback {
        fun onDataReceived(lessonContents: MutableList<LessonContent>)
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
            val lessonContents = mutableListOf<LessonContent>()

            db.collection("lessons")
                .get()
                .addOnSuccessListener { resul ->

                    for (document in resul) {
                        val title = document.getString("title") ?: " No title "
                        val desciption = document.getString("description") ?: "No description"
                        val lessonId = document.id

                        lessonContents.add((LessonContent(lessonId, title, desciption, listOf())))

                    }
                    callback.onDataReceived(lessonContents)
                }
        }


        fun getMyLessons(lessonIds: List<String>, callback: (List<LessonContent>) -> Unit) {
            val db = Firebase.firestore
            val lessonContents = mutableListOf<LessonContent>()

            for(lessonId in lessonIds)
                db.collection("lessons")
                    .document(lessonId)
                    .get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) {
                            val title = document.getString("title") ?: "No title"
                            val description = document.getString("description") ?: "No description"
                            lessonContents.add(LessonContent(lessonId, title, description, listOf()))
                        }
                        // Если все запросы завершены, вызываем callback
                        if (lessonContents.size == lessonIds.size)
                            callback(lessonContents)
                    }

                    .addOnFailureListener { exception ->
                        Log.e("FirebaseManager", "Error getting document: ", exception)
                    }
        }

        fun loadLessonData(lessonId: String, callback: (LessonContent) -> Unit) {
            val db = Firebase.firestore

            db.collection("lesson_content").document(lessonId).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        // Извлекаем блоки
                        val blocksData = document.get("blocks") as? List<Map<String, Any>>?
                        Log.d("LessonScreen", "Количество блоков: ${blocksData?.size}")

                        // Преобразуем данные в модель Lesson
                        val blocks = blocksData?.mapNotNull { blockData ->
                            val blockName = blockData["blockName"] as? String ?: ""
                            val contentData = blockData["content"] as? List<Map<String, Any>>?
                            val content = contentData?.mapNotNull { itemData ->
                                when (itemData["type"] as? String) {
                                    "Text" -> ContentItem.Text(content = itemData["content"] as? String ?: "")
                                    "Img" -> ContentItem.Image(url = itemData["url"] as? String ?: "")
                                    else -> null
                                }
                            } ?: emptyList()

                            Log.d("LessonScreen", "Блок: $blockName, Содержимое: ${content.size} элементов")

                            ContentCardViewModel(blockName, content, true)
                        } ?: emptyList()

                        Log.d("LessonScreen", "Всего блоков: ${blocks.size}")

                        // Создаем объект урока
                        val lessonContent = LessonContent(id = lessonId, title = "Название урока", description = "Описание урока", contentCards = blocks)
                        callback(lessonContent) // Передаем загруженные данные в callback
                    } else {
                        Log.d("Error", "Документ с ID $lessonId не найден.")
                    }
                }
                .addOnFailureListener { e ->
                    Log.d("Error2", "Ошибка загрузки урока: ${e.message}")
                }
        }

    }
}