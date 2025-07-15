package com.bignerdranch.chemcraft

data class ContentCardModel(
    val id: String,
    val content: List<SingleCardItemModel> = emptyList(),
    val answerStatus: Boolean) // Сюда бы вместо answerStatus: Boolean вставить отдельный класс с параметрамми (набранный счет, лдоступ, цвет обводки...)

