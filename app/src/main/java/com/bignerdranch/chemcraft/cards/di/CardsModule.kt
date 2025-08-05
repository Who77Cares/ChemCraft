package com.bignerdranch.chemcraft.cards.di

import com.bignerdranch.chemcraft.cards.data.ClientGetCards
import com.bignerdranch.chemcraft.cards.data.GetCardsRepositoryImpl
import com.bignerdranch.chemcraft.cards.domain.GetCardsInteractorImp
import com.bignerdranch.chemcraft.cards.domain.api.GetCardsInteractor
import com.bignerdranch.chemcraft.cards.domain.api.GetCardsRepository
import com.bignerdranch.chemcraft.cards.ui.CardsViewModel
import com.bignerdranch.chemcraft.network.FirebaseNetworkClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

val cardsModule = module {

    single<ClientGetCards> {
        FirebaseNetworkClient(context = get())
    }

    single<GetCardsRepository> {
        GetCardsRepositoryImpl(firebaseClient = get())
    }

    single<GetCardsInteractor> {
        GetCardsInteractorImp(repository = get())
    }

    viewModel {
        CardsViewModel(cardsInteractor = get())
    }

}