package com.bignerdranch.chemcraft.lessons.di

import com.bignerdranch.chemcraft.lessons.data.GetLessonsRepositoryImpl
import com.bignerdranch.chemcraft.lessons.data.api.ClientGetLessons
import com.bignerdranch.chemcraft.lessons.domain.GetLessonsInteractorImpl
import com.bignerdranch.chemcraft.lessons.domain.api.GetLessonsInteractor
import com.bignerdranch.chemcraft.lessons.domain.api.GetLessonsRepository
import com.bignerdranch.chemcraft.lessons.ui.LessonViewModel
import com.bignerdranch.chemcraft.network.FirebaseNetworkClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

    val lessonsModule = module {

        single<ClientGetLessons> {
            FirebaseNetworkClient(context = get())
        }

        single<GetLessonsRepository> {
            GetLessonsRepositoryImpl(firebaseClient = get())
        }

        single<GetLessonsInteractor> {
            GetLessonsInteractorImpl(repository = get())
        }

        viewModel {
            LessonViewModel(lessonInteractor = get())
        }



    }
