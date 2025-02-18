package com.bignerdranch.chemcraft.lessonScreen

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.chemcraft.databinding.ActivityLessonScreenBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LessonScreen : AppCompatActivity(), OnBlockClickListener {
    private lateinit var binding: ActivityLessonScreenBinding
    private lateinit var contentAdapter: ContentAdapter
    private lateinit var blocksAdapter: BlocksAdapter
    private lateinit var database: FirebaseFirestore

    private lateinit var description: String
    private lateinit  var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.firestore

        val lessonId = intent.getStringExtra("lessonId")
        Log.d("LessonScreen", "Передаем ID урока: $lessonId")
        title = intent.getStringExtra("title") ?: " _ "
        description = intent.getStringExtra("description") ?: " _ "

        // Инициализация адаптеров до загрузки данных
        contentAdapter = ContentAdapter(emptyList()) // Изначально пустой список
        blocksAdapter = BlocksAdapter(emptyList(),this, binding.blocksRecycleView)

        binding.lessonRecycleView.layoutManager = LinearLayoutManager(this)
        binding.lessonRecycleView.adapter = contentAdapter

        binding.blocksRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.blocksRecycleView.adapter = blocksAdapter

//        binding.lessonRecycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                // Если прокручиваем второй RecyclerView вниз
//                if (dy > 0) {
//                    // Сдвигаем первый RecyclerView (blocks_recycleView) вверх
//                    binding.blocksRecycleView.offsetTopAndBottom(-dy)
//                }
//            }
//        })

       


        lessonId?.let {
            // Загружаем урок по id
            loadLessonData(it)
        }
    }

    private fun loadLessonData(lessonId: String) {
        // начало загрузки данных
        Log.d("LessonScreen", "Загрузка урока с ID: $lessonId")

        database.collection("lesson_content").document(lessonId).get()
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

                        ContentList(blockName, content)
                    } ?: emptyList()

                    Log.d("LessonScreen", "Всего блоков: ${blocks.size}")

                    // Создаем объект урока и обновляем интерфейс
                    val lesson = Lesson(id = lessonId, title = title, description = description, blocks = blocks)
                    binding.title.text = lesson.title

                    // Обновляем адаптеры с реальными данными
                    if (blocks.isNotEmpty()) {
                        contentAdapter.updateContent(lesson.blocks[0].content)
                        blocksAdapter.updateBlocks(lesson.blocks)
                        blocksAdapter.selectedPosition = 0 // для установки первого блока в состояние isClicked
                        Log.d("LessonScreen", "Адаптеры обновлены")
                    } else {
                        Log.d("LessonScreen", "Нет блоков для отображения.")
                    }
                } else {
                    Log.d("LessonScreen", "Документ с ID $lessonId не найден.")
                }
            }
            .addOnFailureListener { e ->
                Log.e("LessonScreen", "Ошибка загрузки урока: ${e.message}")
                Toast.makeText(this@LessonScreen, "Ошибка загрузки урока", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onBlockClick(contentList: List<ContentItem>) {
        contentAdapter.updateContent(contentList)
    }
}