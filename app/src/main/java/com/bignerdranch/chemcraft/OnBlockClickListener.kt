package com.bignerdranch.chemcraft

import com.bignerdranch.chemcraft.lessonScreen.ContentItem

interface OnBlockClickListener {
    fun onBlockClick(contentList: List<ContentItem>)
}