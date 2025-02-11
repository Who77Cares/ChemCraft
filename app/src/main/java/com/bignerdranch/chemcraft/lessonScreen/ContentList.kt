package com.bignerdranch.chemcraft.lessonScreen

data class ContentList(val content: List<ContentItem>) {
}

sealed class ContentItem {
    data class Text(val content: String) : ContentItem()
    data class Image(val url: String) : ContentItem()
}