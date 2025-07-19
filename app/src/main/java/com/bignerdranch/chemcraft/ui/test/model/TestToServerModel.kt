package com.bignerdranch.chemcraft.ui.test.model

data class TestToServerModel(
    var testContent: List<TestModel> = emptyList(),
    var name: String = ""
) {
}