package com.bignerdranch.chemcraft

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

import com.bignerdranch.chemcraft.databinding.FragmentMainMenuBinding
import com.bignerdranch.chemcraft.lesson_task.ui.TaskFragment
import com.bignerdranch.chemcraft.lessons.ui.LessonFragment
import com.bignerdranch.chemcraft.local_storage.FavoriteActivity
import com.bignerdranch.chemcraft.network.admin_manager.FirebaseAdminManager
import com.bignerdranch.chemcraft.network.admin_manager.MokeCardToFirebase
import com.bignerdranch.chemcraft.network.admin_manager.MokeTaskToFirebase
import com.bignerdranch.chemcraft.network.admin_manager.models.TaskToServerModel


class MainMenuFragment: Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.lessonsListButton.setOnClickListener {
            parentFragmentManager.commit {
                replace(
                    R.id.rootFragmentContainerView,
                    LessonFragment()
                )
                addToBackStack(null)
            }
        }

        binding.myListLessonsButton.setOnClickListener {
            val intent = Intent(requireContext(), FavoriteActivity::class.java)
            startActivity(intent)

        }

        binding.testsButton.setOnClickListener {
            val intent = Intent(requireContext(), TaskFragment::class.java)
            startActivity(intent)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}