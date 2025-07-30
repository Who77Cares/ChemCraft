package com.bignerdranch.chemcraft.lessons.data.api

import com.bignerdranch.chemcraft.network.Response

interface ClientGetLessons {
    suspend fun getLessons(): Response
}