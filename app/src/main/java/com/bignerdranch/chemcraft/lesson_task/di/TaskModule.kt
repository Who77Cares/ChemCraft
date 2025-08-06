package com.bignerdranch.chemcraft.lesson_task.di

import com.bignerdranch.chemcraft.network.FirebaseNetworkClient
import com.bignerdranch.chemcraft.lesson_task.data.ClientGetTasks
import com.bignerdranch.chemcraft.lesson_task.data.model.GetTasksRepositoryImpl
import com.bignerdranch.chemcraft.lesson_task.domain.GetTasksInteractorImpl
import com.bignerdranch.chemcraft.lesson_task.domain.api.GetTasksInteractor
import com.bignerdranch.chemcraft.lesson_task.domain.api.GetTasksRepository
import com.bignerdranch.chemcraft.lesson_task.ui.TaskViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val taskModel = module {

    single<ClientGetTasks> {
        FirebaseNetworkClient(
            context = get()
        )
    }

    single<GetTasksRepository> {
        GetTasksRepositoryImpl(firebaseClient = get())
    }

    single<GetTasksInteractor> {
        GetTasksInteractorImpl(repository = get())
    }

    viewModel {
        TaskViewModel(tasksInteractor = get())
    }

}