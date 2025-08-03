package com.bignerdranch.chemcraft.cards.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.cards.domain.api.GetCardsInteractor
import com.bignerdranch.chemcraft.lessons.ui.LessonsState

import com.bignerdranch.chemcraft.ui.cards.models.CardModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CardsViewModel(
    private val cardsInteractor: GetCardsInteractor
): ViewModel() {



    private val stateLiveData = MutableLiveData<CardsState>()
    fun observeState(): LiveData<CardsState> = stateLiveData

    private val currentLessonIdLiveData = MutableLiveData<String>()
    fun observeCurrentId(): LiveData<String> = currentLessonIdLiveData

    private var cards = emptyList<CardModel>()

    private fun renderState(state: CardsState) {
        stateLiveData.postValue(state)
    }

    fun getCards() {

        val currentId = currentLessonIdLiveData.value ?: ""
        renderState(CardsState.Loading)

        viewModelScope.launch {
            val result = cardsInteractor.getCards(currentId)

            when(result) {
                is Resource.Success -> {
                   cards = result.data ?: emptyList()
                    renderState(CardsState.Content(cards))
                }

                is Resource.Error -> {
                  val errorMessage = result.message ?: "Unknown error"
                    renderState(CardsState.Error(errorMessage))

//                    delay(7000) // ⏱ Подожди 5 секунд
//                    getCards() // 🔁 Повторно запусти

                }

            }

        }

    }

    fun setLessonId(lessonId: String) {
        currentLessonIdLiveData.value = lessonId
    }



}
