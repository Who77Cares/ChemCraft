package com.bignerdranch.chemcraft.lessons.domain

import com.bignerdranch.chemcraft.Resource
import com.bignerdranch.chemcraft.lessons.domain.api.GetLessonsInteractor
import com.bignerdranch.chemcraft.lessons.domain.api.GetLessonsRepository
import com.bignerdranch.chemcraft.lessons.domain.models.LessonsModel


class GetLessonsInteractorImpl(
    private val repository: GetLessonsRepository
): GetLessonsInteractor {
    override suspend fun getLessons(): Resource<List<LessonsModel>> {

        // Допустим, здесь может быть логика фильтрации, сортировки и т.д.
        return repository.getLessons()
    }

}