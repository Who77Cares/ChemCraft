package com.bignerdranch.chemcraft.lesson_task.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.lesson_task.domain.api.GetTasksInteractor
import com.bignerdranch.chemcraft.lesson_task.domain.model.TaskModel
import kotlinx.coroutines.launch

class TaskViewModel(
    val tasksInteractor: GetTasksInteractor
) : ViewModel() {

    private var currentTaskIndex = 0

    private val stateLiveData = MutableLiveData<TaskState>()
    fun observeState(): LiveData<TaskState> = stateLiveData

    private val currentLessonIdLiveData = MutableLiveData<String>()
    private val currentCardIdLiveData = MutableLiveData<String>()

    private var tasks: List<TaskModel> = emptyList()

    private fun renderState(state: TaskState) {
        stateLiveData.postValue(state)
    }

    fun getTasks() {

        val currentLessonId = currentLessonIdLiveData.value ?: ""
        val currentCardId = currentCardIdLiveData.value ?: ""

        viewModelScope.launch {
            val result = tasksInteractor.getTasks(currentLessonId, currentCardId)
            Log.d("TasksViewModel", "Результат загрузки: $result")


            when (result) {
                is Resource.Success -> {
                    tasks = result.data ?: emptyList()
                    renderState(TaskState.Content(tasks))
                }

                is Resource.Error -> {
                    val errorMessage = result.message ?: "Неизвестная ошибка"
                    renderState(TaskState.Error(errorMessage))
                }
            }


        }

    }

    fun setLessonId(lessonId: String, cardId: String) {
        currentLessonIdLiveData.value = lessonId
        currentCardIdLiveData.value = cardId
    }

    fun getCurrentTask(): TaskModel? {
        return tasks.getOrNull(currentTaskIndex)
    }

    fun getNextTask(): TaskModel? {
        currentTaskIndex++
        return tasks.getOrNull(currentTaskIndex)
    }

}