package com.bignerdranch.chemcraft.test

data class Test(val id: String, val title: String, val blocks: List<TestContent>)

data class TestContent(val text: String, val url: String, val answer: String, val maxScore: Int, val isCheckable: Boolean)