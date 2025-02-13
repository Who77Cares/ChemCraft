package com.bignerdranch.chemcraft.lessonScreen

import java.io.Serializable

data class ContentList(val blockName: String, val content: List<ContentItem>): Serializable {
}

data class Lesson(val title: String, val blocks: List<ContentList>): Serializable

sealed class ContentItem: Serializable {
    data class Text(val content: String) : ContentItem()
    data class Image(val url: String) : ContentItem()
}