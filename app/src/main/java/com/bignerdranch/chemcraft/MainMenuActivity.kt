package com.bignerdranch.chemcraft
import android.content.Intent
import android.os.Bundle
import android.util.Log


import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.chemcraft.network.admin_manager.FirebaseAdminManager
import com.bignerdranch.chemcraft.databinding.ActivityMainBinding
import com.bignerdranch.chemcraft.lessons.ui.LessonActivity
import com.bignerdranch.chemcraft.network.admin_manager.MokeCardToFirebase
import com.bignerdranch.chemcraft.network.admin_manager.models.TaskToServerModel
import com.bignerdranch.chemcraft.lesson_task.ui.TaskActivity
import com.bignerdranch.chemcraft.local_storage.FavoriteActivity
import com.bignerdranch.chemcraft.network.admin_manager.MokeTaskToFirebase


class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lessonsListButton.setOnClickListener {
            startActivity(Intent(this, LessonActivity::class.java))
        }

        binding.myListLessonsButton.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }

        binding.testsButton.setOnClickListener {
            startActivity(Intent(this, TaskActivity::class.java))
        }




///////////// создаем урок
        // Данные
        val lessonData: HashMap<String, Any> = hashMapOf(
            "description" to "333",
            "name" to "Древний Рим",
        )

        // создаем урок
        binding.adminButtonOne.setOnClickListener {

            FirebaseAdminManager.createLesson(
                lessonData = lessonData,
                onLessonCreated = { result ->
                    Log.d("Урок создан", "id : ${result.id}")
                },

                )

        }
///////////


///////////////  Эта логика по идее должна быть в классе data

        val lessonId_CardToLesson: String = "tPG7SRYJRV64l9dcmjDa"


        binding.adminButtonTwo.setOnClickListener {



            FirebaseAdminManager.addCardToLesson(
                lessonId = lessonId_CardToLesson,
                cardModel = MokeCardToFirebase.cardsToServerData,
                onSuccess = { result ->
                    Log.d("Карточка  добавлена в урок", "Карточка: ${result.id} в $lessonId_CardToLesson")
                    MokeCardToFirebase.cardItemsMoke.forEach{
                        Log.d("Данные загруженной карточки", "$it")
                    }

                },
                onFailure = { }
            )


        }

///////////////////
        /////////////////////



////////////////////

        val lessonIdtestToServerData: String = "tPG7SRYJRV64l9dcmjDa"
        val cardIdtestToServerData: String = "TQabQdCAeEMnOg0c0DIY"


        val testToServerData = TaskToServerModel (
            testContent = MokeTaskToFirebase.testData
        )

        // добавить тест в карточку
        binding.adminButtonThree.setOnClickListener {

            FirebaseAdminManager.addTestToCard(
                lessonId = lessonIdtestToServerData,
                cardId = cardIdtestToServerData,
                testData = testToServerData,
                onSuccess = { result ->
                    Log.d(
                        "Тест загружен",
                        "ID теста: ${result.id} <-  \n <- карточка: $lessonIdtestToServerData \n <- урок: $cardIdtestToServerData "
                    )
                }
            )
        }



    }
}
