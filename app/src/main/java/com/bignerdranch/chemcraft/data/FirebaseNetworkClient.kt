package com.bignerdranch.chemcraft.data

import android.util.Log
import com.bignerdranch.chemcraft.SingleCardItemModel
import com.bignerdranch.chemcraft.ContentCardModel
import com.bignerdranch.chemcraft.LessonsContentModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
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

        fun getListDataFromFirebase(callback: (List<LessonsContentModel>) -> Unit) {
            val db = Firebase.firestore
            val lessonsContentModels = mutableListOf<LessonsContentModel>()

            Log.d("Firebase", "Начало загрузки данных из коллекции 'lessons'")

            db.collection("lessons")
                .get()
                .addOnSuccessListener { result ->
                    Log.d("Firebase", "Успешно получены данные: ${result.size()} документов")

                    for (document in result) {
                        val title = document.getString("title") ?: "No title"
                        val description = document.getString("description") ?: "No description"
                        val lessonId = document.id

                        val lesson = LessonsContentModel(lessonId, title, description, listOf())
                        lessonsContentModels.add(lesson)

                        Log.d("Firebase", "Добавлен урок: id=$lessonId, title=$title")
                    }

                    Log.d("Firebase", "Всего добавлено уроков: ${lessonsContentModels.size}")
                    callback(lessonsContentModels)
                }
                .addOnFailureListener { exception ->
                    Log.e("Firebase", "Ошибка при загрузке данных: ${exception.message}", exception)
                    callback(emptyList())
                }
        }

        fun getCard(lessonId: String, callback: (List<ContentCardModel>) -> Unit) {
            val db = Firebase.firestore
            Log.d("getCard", "Начало загрузки карточек для lessonId: $lessonId")

            db.collection("lesson_content").document(lessonId).get()
                .addOnSuccessListener { document ->
                    Log.d("getCard", "Загрузка документа прошла успешно")

                    if (document.exists()) {
                        Log.d("getCard", "Документ найден: ${document.id}")

                        val blocksData = document.get("blocks") as? List<Map<String, Any>>
                        if (blocksData != null) {
                            Log.d("getCard", "Найдено блоков: ${blocksData.size}")
                        } else {
                            Log.d("getCard", "Поле 'blocks' отсутствует или имеет неверный формат")
                        }

                        val cards = blocksData?.map { block ->
                            val blockName = block["blockName"] as? String ?: "Без названия"
                            Log.d("getCard", "Обработка блока: $blockName")
                            ContentCardModel(
                                id = blockName,
                                content = emptyList(),
                                answerStatus = false
                            )
                        } ?: emptyList()

                        Log.d("getCard", "Создано карточек: ${cards.size}")

//                        // ✅ Сохраняем карточки
//                        CardStorage.saveCards(cards)

                        callback(cards)
                    } else {
                        Log.d("getCard", "Документ не найден в коллекции")
                        callback(emptyList())
                    }
                }
                .addOnFailureListener {
                    Log.e("getCard", "Ошибка при загрузке документа: ${it.message}", it)
                    callback(emptyList())
                }
        }




        fun getMyLessons(lessonIds: List<String>, callback: (List<LessonsContentModel>) -> Unit) {
            val db = Firebase.firestore
            val lessonsContentModels = mutableListOf<LessonsContentModel>()

            for(lessonId in lessonIds)
                db.collection("lessons")
                    .document(lessonId)
                    .get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) {
                            val title = document.getString("title") ?: "No title"
                            val description = document.getString("description") ?: "No description"
                            lessonsContentModels.add(LessonsContentModel(lessonId, title, description, listOf()))
                        }
                        // Если все запросы завершены, вызываем callback
                        if (lessonsContentModels.size == lessonIds.size)
                            callback(lessonsContentModels)
                    }

                    .addOnFailureListener { exception ->
                        Log.e("FirebaseManager", "Error getting document: ", exception)
                    }
        }

        fun loadLessonData(lessonId: String, callback: (LessonsContentModel) -> Unit) {
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
                                    "Text" -> SingleCardItemModel.Text(content = itemData["content"] as? String ?: "")
                                    "Img" -> SingleCardItemModel.Image(url = itemData["url"] as? String ?: "")
                                    else -> null
                                }
                            } ?: emptyList()

                            Log.d("LessonScreen", "Блок: $blockName, Содержимое: ${content.size} элементов")

                            ContentCardModel(blockName, content, true)
                        } ?: emptyList()

                        Log.d("LessonScreen", "Всего блоков: ${blocks.size}")

                        // Создаем объект урока
                        val lessonsContentModel = LessonsContentModel(id = lessonId, title = "Название урока", description = "Описание урока", contentCards = blocks)
                        callback(lessonsContentModel) // Передаем загруженные данные в callback
                    } else {
                        Log.d("Error", "Документ с ID $lessonId не найден.")
                    }
                }
                .addOnFailureListener { e ->
                    Log.d("Error2", "Ошибка загрузки урока: ${e.message}")
                }
        }




        // Логика добавления урока (lesons)

        fun createLesson(
            lesson: HashMap<String, String>,
            documentPath: String,
        ) {
            val db = Firebase.firestore

            db.collection("lessons").document(documentPath)
                .set(lesson)

                .addOnSuccessListener {
                    Log.d("Firestore", "Урок $lesson загружен в lessons --> $documentPath")
                }
        }


        // заменяет существующие карточки (блоки) на новые
        fun updateContentLesson(

            documentPath: String,
            content: HashMap<String, List<HashMap<String, Any>>>

        ) {
            val db = Firebase.firestore

            db.collection("lesson_content").document(documentPath)
                .set(content)
                .addOnSuccessListener {
                    Log.d("Firestore", "Content added to lessons_content --> $documentPath")
                }
                .addOnFailureListener { e ->
                    Log.e("Firestore", "Error uploading Lesson 1 content", e)
                }

        }

        // добавляет новые карточки (блоки)
        fun addContentToLesson(
            documentPath: String,
            content: HashMap<String, HashMap<String, Any>>
        ) {
            val db = Firebase.firestore
            val docRef = db.collection("lesson_content").document(documentPath)

            val block = content["blocks"]
            if (block != null) {
                docRef.update("blocks", FieldValue.arrayUnion(block))
                    .addOnSuccessListener {
                        Log.d("Firestore", "Block added to lesson: $documentPath")
                    }
                    .addOnFailureListener { e ->
                        Log.e("Firestore", "Error adding block", e)
                    }
            } else {
                Log.e("Firestore", "No 'blocks' key in content map")
            }
        }


    }
}