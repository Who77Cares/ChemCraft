package com.example.chemcraft

data class Card(
    val name: String,
    val readTime: String,
    val icon: String, // подгружается с яндексдиска
    val status: Boolean, // прочитана история или нет. можно сделать более сложную логику используя enum
    val isLike: Boolean)  {
}