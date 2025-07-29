package com.bignerdranch.chemcraft.data.models

import com.bignerdranch.chemcraft.ui.cards.models.CardToServerModel
import com.bignerdranch.chemcraft.ui.test.model.TestToServerModel

data class CardsWithTests(
    val card: CardToServerModel,
    val tests: List<TestToServerModel>
) {
}