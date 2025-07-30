package com.bignerdranch.chemcraft.lessons.domain.api

import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.lessons.domain.models.LessonsModel

interface GetLessonsInteractor {

    suspend fun getLessons(): Resource<List<LessonsModel>>

}