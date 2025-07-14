package com.bignerdranch.chemcraft

sealed class SingleCardItemModel {
    data class Text(val content: String) : SingleCardItemModel()
    data class Image(val url: String) : SingleCardItemModel()
}