package com.bignerdranch.chemcraft.task_for_card.di

import com.bignerdranch.chemcraft.network.FirebaseNetworkClient
import com.bignerdranch.chemcraft.task_for_card.data.ClientGetTasks
import com.bignerdranch.chemcraft.task_for_card.data.model.GetTasksRepositoryImpl
import com.bignerdranch.chemcraft.task_for_card.domain.GetTasksInteractorImpl
import com.bignerdranch.chemcraft.task_for_card.domain.api.GetTasksInteractor
import com.bignerdranch.chemcraft.task_for_card.domain.api.GetTasksRepository
import com.bignerdranch.chemcraft.task_for_card.ui.TaskViewModel
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