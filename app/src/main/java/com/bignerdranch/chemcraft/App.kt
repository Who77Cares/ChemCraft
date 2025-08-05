package com.bignerdranch.chemcraft

import android.app.Application
import com.bignerdranch.chemcraft.cards.di.cardsModule
import com.bignerdranch.chemcraft.items_in_lesson.di.itemsModule
import com.bignerdranch.chemcraft.lessons.di.lessonsModule
import com.bignerdranch.chemcraft.task_for_card.di.taskModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(lessonsModule, cardsModule, itemsModule, taskModel))
        }
    }
}