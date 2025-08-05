package com.bignerdranch.chemcraft.items_in_lesson.domain.models

sealed class ItemModel {
    data class TextItem(val content: String) : ItemModel()
    data class ImageItem(val url: String) : ItemModel()
}

