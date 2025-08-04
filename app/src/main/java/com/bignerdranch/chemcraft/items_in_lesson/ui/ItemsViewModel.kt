package com.bignerdranch.chemcraft.items_in_lesson.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.cards.ui.CardsState
import com.bignerdranch.chemcraft.items_in_lesson.domain.api.GetItemsInteractor
import com.bignerdranch.chemcraft.items_in_lesson.domain.models.ItemModel
import kotlinx.coroutines.launch


class ItemsViewModel(
    val itemsInteractor: GetItemsInteractor
): ViewModel() {

    private val stateLiveData = MutableLiveData<ItemsState>()
    fun observeState(): LiveData<ItemsState> = stateLiveData

    private val currentLessonIdLiveData = MutableLiveData<String>()
    private val currentCardIdLiveData = MutableLiveData<String>()

    private var items: List<ItemModel> = emptyList<ItemModel>()

    private fun renderState(state: ItemsState) {
        stateLiveData.postValue(state)
    }






    fun getItems() {

        renderState(ItemsState.Loading)
        Log.d("ItemsViewModel", "Загружаем карточки...")
        val currentLessonId = currentLessonIdLiveData.value ?: ""
        val currentCardId = currentCardIdLiveData.value ?: ""
        Log.d("ItemsViewModel", "lessonId = $currentLessonId, cardId = $currentCardId")


        viewModelScope.launch {
            val result = itemsInteractor.getCards(currentLessonId,currentCardId)
            Log.d("ItemsViewModel", "Результат загрузки: $result")
            when(result) {
                is Resource.Success -> {
                    items = result.data ?: emptyList()
                    renderState(ItemsState.Content(items))
                    Log.d("ItemsViewModel", "Items to show: $items")
                }

                is Resource.Error -> {
                    val errorMessage = result.message ?: "Неизвестная ошибка"
                    renderState(ItemsState.Error(errorMessage))
                }
            }

        }


    }

    fun setLessonId(lessonId: String, cardId: String) {
        currentLessonIdLiveData.value = lessonId
        currentCardIdLiveData.value = cardId
    }


}