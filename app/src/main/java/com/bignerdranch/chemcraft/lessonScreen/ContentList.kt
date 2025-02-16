package com.bignerdranch.chemcraft.lessonScreen


data class ContentList( val blockName: String, val content: List<ContentItem>){
}

data class Lesson(val id: String, val title: String, val description: String, val blocks: List<ContentList>)

sealed class ContentItem {
    data class Text(val content: String) : ContentItem()
    data class Image(val url: String) : ContentItem()
}