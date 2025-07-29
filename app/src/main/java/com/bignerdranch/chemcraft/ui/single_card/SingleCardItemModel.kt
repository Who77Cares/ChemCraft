package com.bignerdranch.chemcraft.ui.single_card

import com.bignerdranch.chemcraft.ui.test.model.TestModel

sealed class SingleCardItemModel {
    data class Text(val content: String) : SingleCardItemModel()
    data class Image(val url: String) : SingleCardItemModel()
}