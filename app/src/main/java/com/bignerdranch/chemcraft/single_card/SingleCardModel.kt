package com.bignerdranch.chemcraft.single_card

sealed class SingleCardModel {
    data class TextItem(val content: String) : SingleCardModel()
    data class ImageItem(val url: String) : SingleCardModel()
}