package com.bignerdranch.chemcraft.lessonScreen

data class LessonContent(val id: String, val title: String, val description: String, val contentCards: List<ContentCardViewModel>)


data class ContentCardViewModel(
    val blockName: String,
    val content: List<ContentItem>,
    val answerStatus: Boolean){ // Сюда бы вместо answerStatus: Boolean вставить отдельный класс с параметрамми (набранный счет, лдоступ, цвет обводки...)
}

sealed class ContentItem {
    data class Text(val content: String) : ContentItem()
    data class Image(val url: String) : ContentItem()
}

