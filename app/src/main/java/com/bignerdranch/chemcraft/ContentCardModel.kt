package com.bignerdranch.chemcraft

data class ContentCardModel(
    val blockName: String,
    val content: List<SingleCardItemModel>,
    val answerStatus: Boolean) // Сюда бы вместо answerStatus: Boolean вставить отдельный класс с параметрамми (набранный счет, лдоступ, цвет обводки...)

