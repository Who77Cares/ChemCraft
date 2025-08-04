package com.bignerdranch.chemcraft.items_in_lesson.di

import com.bignerdranch.chemcraft.items_in_lesson.data.ClientGetItems
import com.bignerdranch.chemcraft.items_in_lesson.data.GetItemsRepositoryImpl
import com.bignerdranch.chemcraft.items_in_lesson.domain.GetItemsInteractorImpl
import com.bignerdranch.chemcraft.items_in_lesson.domain.api.GetItemsInteractor
import com.bignerdranch.chemcraft.items_in_lesson.domain.api.GetItemsRepository
import com.bignerdranch.chemcraft.items_in_lesson.ui.ItemsViewModel
import com.bignerdranch.chemcraft.network.FirebaseNetworkClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val itemsModule = module {


    single<ClientGetItems> {
        FirebaseNetworkClient(context = get())
    }

    single<GetItemsRepository> {
        GetItemsRepositoryImpl(firebaseClient = get())
    }

    single<GetItemsInteractor> {
        GetItemsInteractorImpl(
            repository = get()
        )
    }

    viewModel {
        ItemsViewModel(itemsInteractor = get())
    }


}