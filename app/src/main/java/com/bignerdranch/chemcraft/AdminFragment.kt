package com.bignerdranch.chemcraft

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bignerdranch.chemcraft.databinding.FragmentAdminBinding


import com.bignerdranch.chemcraft.network.admin_manager.FirebaseAdminManager
import com.bignerdranch.chemcraft.network.admin_manager.MokeCardToFirebase
import com.bignerdranch.chemcraft.network.admin_manager.MokeLessonToFirebase
import com.bignerdranch.chemcraft.network.admin_manager.MokeTaskToFirebase
import com.bignerdranch.chemcraft.network.admin_manager.models.TaskToServerModel


class AdminFragment: Fragment() {

    private var _binding: FragmentAdminBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // создаем урок
        binding.adminButtonOne.setOnClickListener {

            FirebaseAdminManager.createLesson(
                lessonData = MokeLessonToFirebase.lessonData,
                onLessonCreated = { lessonRef, name ->
                    binding.newDataId.text = "ID урока: ${lessonRef.id}"
                    binding.contentParam.text = "Название нового урока: $name"

                },
                onFailure = { error ->
                    binding.newDataId.text = error
                }
            )
        }
///////////




///////////////  Эта логика по идее должна быть в классе data


        binding.adminButtonLoadCardToBio.setOnClickListener {

            FirebaseAdminManager.addCardToLesson(
                lessonId = MokeCardToFirebase.biologyLessonId,
                cardModel = MokeCardToFirebase.cardsToServerData,
                onSuccess = { cardRef, title, description, itemsSize ->

                    binding.newDataId.text = "ID урока: ${cardRef.id}"
                    binding.contentParam.text = "Название нового урока: $title \nКоличество айтемов: $itemsSize"

                    MokeCardToFirebase.cardItemsMoke.forEach{
                        Log.d("Данные загруженной карточки", "$it")
                    }

                },
                onFailure = { error ->
                    binding.newDataId.text = error
                }
            )
        }


        binding.adminButtonLoadCardToHis.setOnClickListener {

            FirebaseAdminManager.addCardToLesson(
                lessonId = MokeCardToFirebase.historyLessonId,
                cardModel = MokeCardToFirebase.cardsToServerData,
                onSuccess = { cardRef, title, description, itemsSize ->

                    binding.newDataId.text = "ID урока: ${cardRef.id}"
                    binding.contentParam.text = "Название нового урока: $title \nКоличество айтемов: $itemsSize"

                    MokeCardToFirebase.cardItemsMoke.forEach{
                        Log.d("Данные загруженной карточки", "$it")
                    }

                },
                onFailure = { error ->
                    binding.newDataId.text = error
                }
            )
        }

        binding.adminButtonLoadCardToGeo.setOnClickListener {

            FirebaseAdminManager.addCardToLesson(
                lessonId = MokeCardToFirebase.geographyLessonId,
                cardModel = MokeCardToFirebase.cardsToServerData,
                onSuccess = { cardRef, title, description, itemsSize ->

                    binding.newDataId.text = "ID урока: ${cardRef.id}"
                    binding.contentParam.text = "Название нового урока: $title \nКоличество айтемов: $itemsSize"

                    MokeCardToFirebase.cardItemsMoke.forEach{
                        Log.d("Данные загруженной карточки", "$it")
                    }

                },
                onFailure = { error ->
                    binding.newDataId.text = error
                }
            )
        }


///////////////////
        /////////////////////


////////////////////

        // тут вручную бинеятся данные для урока
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