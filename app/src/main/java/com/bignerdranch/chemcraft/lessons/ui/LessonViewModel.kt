package com.bignerdranch.chemcraft.lessons.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.lessons.domain.api.GetLessonsInteractor
import com.bignerdranch.chemcraft.lessons.domain.models.LessonsModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LessonViewModel(
    private  val lessonInteractor: GetLessonsInteractor
    ): ViewModel()
{

    private val stateLiveData = MutableLiveData<LessonsState>()
    fun observeState(): LiveData<LessonsState> = stateLiveData

    private var lessons = emptyList<LessonsModel>()

    private fun renderState(state: LessonsState) {
        stateLiveData.postValue(state)
    }

    fun getLessons() {

        viewModelScope.launch {

            renderState(LessonsState.Loading)

            val result = lessonInteractor.getLessons()

            when (result) {
                is Resource.Success -> {
                    lessons = result.data ?: emptyList()
                    renderState(LessonsState.Content(lessons))

                }

                is Resource.Error -> {
                    val errorMessage = result.message ?: "Unknown error"
                    renderState(LessonsState.Error(errorMessage))

                    delay(7000) // ⏱ Подожди 5 секунд
                    getLessons() // 🔁 Повторно запусти

                }
            }

        }
    }



}